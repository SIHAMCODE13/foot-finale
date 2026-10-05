package com.club.service;

import com.club.exception.BusinessException;
import com.club.model.RegistrationStatus;
import com.club.model.User;
import com.club.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public UserService(
                        UserRepository userRepository,
                        PasswordEncoder passwordEncoder) {
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder;
        }

        @Override
        public UserDetails loadUserByUsername(String email)
                        throws UsernameNotFoundException {

                return userRepository.findByEmail(email)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "Identifiants invalides"));
        }

        public User register(User user) {
                user.setEmail(com.club.security.InputSanitizer.sanitizeEmail(user.getEmail()));
                user.setNom(com.club.security.InputSanitizer.sanitizeName(user.getNom()));
                user.setPrenom(com.club.security.InputSanitizer.sanitizeName(user.getPrenom()));
                if (user.getTelephone() != null) {
                        user.setTelephone(com.club.security.InputSanitizer.sanitizeText(user.getTelephone()));
                }
                if (user.getAdresse() != null) {
                        user.setAdresse(com.club.security.InputSanitizer.sanitizeText(user.getAdresse()));
                }

                if (userRepository.existsByEmail(user.getEmail())) {
                        throw new BusinessException("Email déjà utilisé");
                }

                if (user.getPassword() == null || user.getPassword().length() < 6) {
                        throw new BusinessException("Le mot de passe doit contenir au moins 6 caractères");
                }

                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRole(User.Role.ADHERENT);
                user.setActif(true);
                user.setAccountStatus(User.AccountStatus.ACTIF);
                user.setActivationToken(null);
                user.setRegistrationStatus(RegistrationStatus.PENDING);
                user.setDateInscription(LocalDateTime.now());
                return userRepository.save(user);
        }

        /**
         * Change le mot de passe de l'utilisateur connecté.
         */
        public void changePassword(String email, String currentPassword, String newPassword, String confirmPassword) {
                if (newPassword == null || newPassword.length() < 6) {
                        throw new BusinessException("Le mot de passe doit contenir au moins 6 caractères");
                }
                if (confirmPassword == null || !newPassword.equals(confirmPassword)) {
                        throw new BusinessException("La confirmation du mot de passe ne correspond pas");
                }

                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new BusinessException("Utilisateur non trouvé"));

                if (user.getPassword() == null || !passwordEncoder.matches(currentPassword, user.getPassword())) {
                        throw new BusinessException("Mot de passe actuel incorrect");
                }

                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
        }

        public User createUser(User user) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setDateInscription(LocalDateTime.now());
                return userRepository.save(user);
        }

        public User updateUser(Long id, User userDetails) {

                User user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

                user.setNom(userDetails.getNom());
                user.setPrenom(userDetails.getPrenom());
                user.setTelephone(userDetails.getTelephone());
                user.setAdresse(userDetails.getAdresse());
                user.setDateNaissance(userDetails.getDateNaissance());
                user.setPhoto(userDetails.getPhoto());

                if (userDetails.getPassword() != null &&
                                !userDetails.getPassword().isEmpty()) {

                        user.setPassword(
                                        passwordEncoder.encode(userDetails.getPassword()));
                }

                return userRepository.save(user);
        }

        public User changeRole(Long id, User.Role role) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
                user.setRole(role);
                return userRepository.save(user);
        }

        public User toggleUserStatus(Long id) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
                user.setActif(!user.getActif());
                return userRepository.save(user);
        }

        public void updateLastLogin(String email) {
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
                user.setDerniereConnexion(LocalDateTime.now());
                userRepository.save(user);
        }

        public void migratePasswordIfNeeded(String email, String rawPassword) {
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

                String stored = user.getPassword();
                if (stored == null)
                        return;

                boolean looksLikeBcrypt = stored.startsWith("$2a$") || stored.startsWith("$2b$")
                                || stored.startsWith("$2y$");

                if (!looksLikeBcrypt && passwordEncoder.matches(rawPassword, stored)) {
                        user.setPassword(passwordEncoder.encode(rawPassword));
                        userRepository.save(user);
                }
        }

        public List<User> getAllUsers() {
                return userRepository.findAll();
        }

        public List<User> getUsersByRole(User.Role role) {
                return userRepository.findByRole(role);
        }

        public User getUserById(Long id) {
                return userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        }

        public void deleteUser(Long id) {
                userRepository.deleteById(id);
        }

        public User updateUserPhoto(Long id, String photoUrl) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
                user.setPhoto(photoUrl);
                return userRepository.save(user);
        }
}
