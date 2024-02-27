package com.zim0101.concurrentbookingproblem;

import com.zim0101.concurrentbookingproblem.model.Account;
import com.zim0101.concurrentbookingproblem.model.Conference;
import com.zim0101.concurrentbookingproblem.model.Seat;
import com.zim0101.concurrentbookingproblem.model.enums.Role;
import com.zim0101.concurrentbookingproblem.repository.AccountRepository;
import com.zim0101.concurrentbookingproblem.repository.ConferenceRepository;
import com.zim0101.concurrentbookingproblem.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class ConcurrentBookingProblemApplication implements CommandLineRunner {

	private final PasswordEncoder passwordEncoder;

	private final AccountRepository accountRepository;

	private final ConferenceRepository conferenceRepository;

	private final SeatRepository seatRepository;

    public ConcurrentBookingProblemApplication(PasswordEncoder passwordEncoder,
											   AccountRepository accountRepository,
                                               ConferenceRepository conferenceRepository,
                                               SeatRepository seatRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.conferenceRepository = conferenceRepository;
        this.seatRepository = seatRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(ConcurrentBookingProblemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Account user1 = new Account();
		user1.setUsername("user1");
		user1.setFirstName("Mr. ");
		user1.setLastName("User 1");
		user1.setEmail("user1@gmail.com");
		user1.setPassword(passwordEncoder.encode("password@User1"));
		user1.setRoles(Set.of(Role.USER));
		accountRepository.save(user1);

		Account user2 = new Account();
		user2.setUsername("user2");
		user2.setFirstName("Mr. ");
		user2.setLastName("User 2");
		user2.setEmail("user2@gmail.com");
		user2.setPassword(passwordEncoder.encode("password@User2"));
		user2.setRoles(Set.of(Role.USER));
		accountRepository.save(user2);

		Conference conference = new Conference();
		conference.setName("Java Conference 2024");
		conference.setStartTime(LocalDateTime.now().plusHours(5));
		conference.setEndTime(LocalDateTime.now().plusHours(6));
		conferenceRepository.save(conference);

		for (int i = 1; i <= 10; i++) {
			Seat seat = new Seat();
			seat.setNumber(i);
			seat.setConference(conference);
			seat.setAvailable(true);
			seatRepository.save(seat);
		}
	}
}
