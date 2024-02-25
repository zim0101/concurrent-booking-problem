package com.zim0101.concurrentbookingproblem.repository;

import com.zim0101.concurrentbookingproblem.model.Account;
import com.zim0101.concurrentbookingproblem.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);

    Account findByEmail(String email);

    List<Account> findByRolesContains(Role role);
}