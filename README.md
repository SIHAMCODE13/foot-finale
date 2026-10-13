<!-- ============================================================ -->
<!--                      BADGES & HEADER                         -->
<!-- ============================================================ -->

<div align="center">

# ⚽ Club Foot App

### Application de gestion du club de football MAS de Fès

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Flutter](https://img.shields.io/badge/Flutter-3.x-02569B?style=for-the-badge&logo=flutter&logoColor=white)](https://flutter.dev/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)](https://github.com/features/actions)

[![CI](https://github.com/firdawsM7/club-foot-app/actions/workflows/ci.yml/badge.svg)](https://github.com/firdawsM7/club-foot-app/actions/workflows/ci.yml)
[![CD](https://github.com/firdawsM7/club-foot-app/actions/workflows/cd.yml/badge.svg)](https://github.com/firdawsM7/club-foot-app/actions/workflows/cd.yml)
[![License](https://img.shields.io/badge/License-Academic-blue.svg)](./LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](./CONTRIBUTING.md)

# ⚽ Club Foot App — MAS de Fès

> Application multiplateforme de gestion du club de football **Maghreb Association Sportive (MAS) de Fès**

---

## 📖 Présentation

**Club Foot App** est une application de gestion conçue pour digitaliser l'ensemble des processus administratifs et sportifs du club **MAS de Fès**. Elle centralise les données relatives aux adhérents, joueurs, équipes, entraînements, matchs, cotisations et documents administratifs, et offre un canal de communication interne en temps réel.

Développée dans le cadre d'un **Projet de Fin d'Année (PFA)** à l'École Nationale des Sciences Appliquées — filière **3IACN** (2026-2027).

---

## 🎯 Fonctionnalités

- 🔐 **Authentification sécurisée** (JWT, BCrypt) avec 4 rôles : `ADMIN`, `ENCADRANT`, `ADHERENT`, `JOUEUR`
- 👥 **Gestion des utilisateurs** : création de comptes, rôles, profils, activation automatique
- 🏆 **Gestion sportive** : équipes, joueurs, entraînements, matchs
- 💰 **Gestion financière** : cotisations, upload de justificatifs, workflow de validation
- 📄 **Gestion documentaire** : pièces d'identité, certificats médicaux, licences fédérales
- 💬 **Communication** : chat temps réel par équipe (WebSocket/STOMP), messagerie privée, annonces
- 📊 **Tableau de bord administrateur** : statistiques, revenus mensuels, graphiques
- 🔔 **Notifications** : broadcast, groupe, privé

---

## 🛠️ Stack technique

| Couche | Technologies |
|---|---|
| **Backend** | Java 17 · Spring Boot 3.2 · Spring Security · JWT · Spring Data JPA · Hibernate · WebSocket/STOMP |
| **Frontend** | Flutter (Dart) · Provider · http · stomp_dart_client · fl_chart |
| **Base de données** | MySQL 8.0 |
| **DevOps** | Docker · Docker Compose · GitHub Actions · Maven |
| **Tests** | JUnit 5 · Mockito · Spring Boot Test |

---

## 🏗️ Architecture
┌────────────────────┐ REST/JSON + JWT ┌──────────────────────┐
│ Flutter Client │ ──────────────────► │ Spring Boot API │
│ (mobile/web/desktop)│ ◄────────────────── │ (context-path /api) │
└─────────┬──────────┘ WebSocket/STOMP └──────────┬───────────┘
│ │ JDBC
│ ▼
│ ┌──────────────┐
│ │ MySQL 8.0 │
│ └──────────────┘
│ │
│ ▼
│ ┌──────────────┐
└──────────────────────────────────►│ Stockage │
│ local (uploads/)│
└──────────────┘

text

---

## 📁 Structure du projet
club-foot-app/
├── backend/ # API Spring Boot
│ ├── src/main/java/
│ │ ├── controller/ # Contrôleurs REST
│ │ ├── service/ # Logique métier
│ │ ├── repository/ # Interfaces JPA
│ │ ├── model/ # Entités JPA
│ │ ├── dto/ # Objets de transfert
│ │ ├── security/ # JWT, filtres, chiffrement
│ │ ├── exception/ # Gestion centralisée des erreurs
│ │ └── config/ # WebSocket, données initiales
│ ├── src/main/resources/
│ │ └── db/migration/ # Scripts SQL de migration
│ └── uploads/ # Stockage local des fichiers
├── frontend/ # Application Flutter
│ └── lib/
│ ├── config/ # URLs API centralisées
│ ├── core/theme/ # Thèmes clair/sombre
│ ├── models/ # Modèles de données
│ ├── providers/ # Gestion d'état (Provider)
│ ├── services/ # Appels HTTP
│ ├── screens/ # Écrans par domaine métier
│ └── widgets/ # Composants réutilisables
├── .github/workflows/ # Pipelines CI/CD
├── docker-compose.yml # Orchestration MySQL + backend
└── .env.example # Modèle de variables d'environnement

text

---

## 🚀 Lancement rapide

### Prérequis

- [Docker](https://docs.docker.com/get-docker/) & Docker Compose
- [Flutter SDK](https://docs.flutter.dev/get-started/install) ≥ 3.0
- [Java 17](https://adoptium.net/) (pour le développement backend)

### Démarrage

```bash
# 1. Cloner le dépôt
git clone https://github.com/firdawsM7/club-foot-app.git
cd club-foot-app

# 2. Configurer les variables d'environnement
cp .env.example .env
# Éditer .env selon votre configuration

# 3. Démarrer l'infrastructure (MySQL + backend)
docker compose up -d

# 4. Lancer le frontend Flutter
cd frontend
flutter pub get
flutter run
Note : l'URL de l'API est configurée automatiquement selon la plateforme :

http://localhost:8084 → Web, Desktop, iOS

http://10.0.2.2:8084 → Émulateur Android

🔒 Sécurité
Authentification JWT (expiration 24 h)

Hashage des mots de passe avec BCrypt

Validation et sanitisation des entrées (InputSanitizer)

Protection contre le path traversal

Rate limiting sur l'authentification (30 req / 60 s)

CORS restreint

Messages d'erreur génériques (pas de fuite d'information technique)

🧪 Tests
Tests unitaires : JwtUtil, MultiPasswordEncoder, SecureFileValidator, InputSanitizer, services métier

Tests d'intégration : messagerie temps réel et notifications

Tests de validation API : authentification, autorisation par rôle, CRUD, upload

Tous les tests sont exécutés automatiquement dans le pipeline CI GitHub Actions.

🔄 CI/CD
Pipeline	Rôle
CI (.github/workflows/ci.yml)	Build Maven + tests backend · Build image Docker · Build Flutter Web
CD (.github/workflows/cd.yml)	Build & push de l'image Docker · Déploiement
👥 Auteurs
Siham TALBI

Firdaws MASROUR

Encadrant professionnel : M. Mohammed EL MOUTAOUKIL
Organisme d'accueil : MAS de Fès
Établissement : École Nationale des Sciences Appliquées — Filière 3IACN

📄 Licence
Projet académique — École Nationale des Sciences Appliquées, 2026-2027.

