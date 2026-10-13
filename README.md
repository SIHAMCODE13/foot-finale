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

</div>

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

```text
┌──────────────────────┐   REST/JSON + JWT   ┌──────────────────────┐
│   Flutter Client     │ ──────────────────► │   Spring Boot API    │
│ (mobile/web/desktop) │ ◄────────────────── │  (context-path /api) │
└──────────┬───────────┘   WebSocket/STOMP   └──────────┬───────────┘
           │                                           │ JDBC
           │                                           ▼
           │                                   ┌──────────────┐
           │                                   │   MySQL 8.0  │
           │                                   └──────┬───────┘
           │                                          │
           │                                          ▼
           │                                   ┌──────────────┐
           └──────────────────────────────────►│   Stockage   │
                                               │ local (uploads/) │
                                               └──────────────┘
