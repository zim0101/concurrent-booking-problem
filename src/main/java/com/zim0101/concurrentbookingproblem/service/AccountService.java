package com.zim0101.concurrentbookingproblem.service;

import com.zim0101.concurrentbookingproblem.model.Account;
import com.zim0101.concurrentbookingproblem.model.enums.Role;
import com.zim0101.concurrentbookingproblem.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository,
                          PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public boolean accountExistWithEmail(String email) {
        return findByEmail(email) != null;
    }

    public List<Account> findAllAdminAccounts() {
        return accountRepository.findByRolesContains(Role.ADMIN);
    }

    @Transactional
    public void saveAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if (account.getRoles() == null) {
            account.setRoles(Set.of(Role.USER));
        }
        accountRepository.save(account);
    }

    @Transactional
    public String register(Account account, BindingResult result) {
        if (result.hasErrors()) return "auth/register";

        if (accountExistWithEmail(account.getEmail())) {
            result.rejectValue("email", "duplicate.email",
                    "There is already an account registered with the same email");
            return "auth/register";
        }

        saveAccount(account);

        return "redirect:/login";
    }
}
