package com.club.controller;

import com.club.model.User;
import com.club.service.FileStorageService;
import com.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/me/photo")
    public ResponseEntity<?> uploadProfilePhoto(@RequestParam("file") MultipartFile file,
            Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            String fileName = fileStorageService.storeFile(file);

            // Construire l'URL publique de l'image
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/photos/")
                    .path(fileName)
                    .toUriString();

            // Mettre à jour l'utilisateur (on stocke le nom du fichier ou l'URL complète ?
            // Stockons l'URL complète pour simplifier le front)
            // Mais UserService attend peut-être juste le changement. Faisons une méthode
            // dédiée dans UserService.
            // Pour l'instant, mettons à jour l'objet User ici et sauvegardons via
            // UserService.

            // Note: Idéalement on stocke le chemin relatif et on reconstruit l'URL, ou on
            // stocke l'URL.
            // Vu la simplicité, stockons l'URL complète générée.

            // Problème : User est détaché ou il faut le recharger ?
            // UserDetails du principal est souvent détaché. Il vaut mieux recharger depuis
            // la DB.

            User updatedUser = userService.updateUserPhoto(user.getId(), fileDownloadUri);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Photo mise à jour avec succès");
            response.put("photoUrl", fileDownloadUri);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", com.club.exception.SafeErrorMessages.UPLOAD_FAILED));
        }
    }

    @PostMapping("/me/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> body,
            Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(Map.of("error", com.club.exception.SafeErrorMessages.ACCESS_DENIED));
        }
        try {
            User current = (User) authentication.getPrincipal();
            userService.changePassword(
                    current.getEmail(),
                    body != null ? body.get("currentPassword") : null,
                    body != null ? body.get("newPassword") : null,
                    body != null ? body.get("confirmPassword") : null);
            return ResponseEntity.ok(Map.of("message", "Mot de passe modifié avec succès"));
        } catch (com.club.exception.BusinessException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", com.club.exception.SafeErrorMessages.sanitizeBusinessMessage(e.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", com.club.exception.SafeErrorMessages.OPERATION_FAILED));
        }
    }
}
