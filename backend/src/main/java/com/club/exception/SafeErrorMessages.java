package com.club.exception;

/**
 * Messages génériques exposés au client (pas de détails techniques).
 */
public final class SafeErrorMessages {

    private SafeErrorMessages() {
    }

    public static final String GENERIC = "Une erreur est survenue. Veuillez réessayer.";
    public static final String OPERATION_FAILED = "Impossible de traiter la demande.";
    public static final String NOT_FOUND = "Ressource introuvable.";
    public static final String INVALID_REQUEST = "Requête invalide.";
    public static final String AUTH_FAILED = "Identifiants incorrects.";
    public static final String ACCESS_DENIED = "Accès refusé.";
    public static final String REGISTER_FAILED = "Impossible de finaliser l'inscription.";
    public static final String ACTIVATION_FAILED = "Impossible d'activer le compte.";
    public static final String VALIDATION_FAILED = "Informations invalides.";
    public static final String PASSWORD_INCORRECT = "Mot de passe actuel incorrect.";
    public static final String PASSWORD_MISMATCH = "La confirmation du mot de passe ne correspond pas.";
    public static final String UPLOAD_FAILED = "Échec du téléversement du fichier.";
    public static final String MESSAGE_SEND_FAILED = "Impossible d'envoyer le message.";

    /** Ne jamais exposer les messages métier détaillés (énumération, etc.). */
    public static String sanitizeBusinessMessage(String internalMessage) {
        if (internalMessage == null || internalMessage.isBlank()) {
            return OPERATION_FAILED;
        }
        String lower = internalMessage.toLowerCase();
        if (lower.contains("mot de passe actuel")) {
            return PASSWORD_INCORRECT;
        }
        if (lower.contains("confirmation")) {
            return PASSWORD_MISMATCH;
        }
        if (internalMessage.contains("mot de passe") && internalMessage.contains("6")) {
            return VALIDATION_FAILED;
        }
        if (lower.contains("email")) {
            return REGISTER_FAILED;
        }
        if (lower.contains("token") || lower.contains("activ")) {
            return ACTIVATION_FAILED;
        }
        return OPERATION_FAILED;
    }
}
