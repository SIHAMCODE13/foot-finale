package com.club.config;

import com.club.model.User;
import com.club.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Migrates ACTIVATION_REQUISE accounts that already have a password to ACTIF.
 */
@Component
@Order(2)
public class ActivateExistingPasswordUsersRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    public ActivateExistingPasswordUsersRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        int updated = 0;
        for (User user : userRepository.findAll()) {
            if (user.getAccountStatus() == User.AccountStatus.ACTIVATION_REQUISE
                    && user.getPassword() != null
                    && !user.getPassword().isBlank()) {
                user.setActif(true);
                user.setAccountStatus(User.AccountStatus.ACTIF);
                user.setActivationToken(null);
                userRepository.save(user);
                updated++;
            }
        }
        if (updated > 0) {
            System.out.println("Migrated " + updated + " account(s) from ACTIVATION_REQUISE to ACTIF.");
        }
    }
}