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

**Projet de Fin d'Année — ENSA, filière 3IACN (2026-2027)**

[Présentation](#-présentation) · [Fonctionnalités](#-fonctionnalités) · [Architecture](#-architecture) · [Installation](#-installation) · [Documentation](#-documentation) · [Auteurs](#-auteurs)

</div>

---

## 📋 Table des matières

- [Présentation](#-présentation)
- [Contexte](#-contexte)
- [Fonctionnalités](#-fonctionnalités)
- [Stack technique](#-stack-technique)
- [Architecture](#-architecture)
- [Structure du projet](#-structure-du-projet)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Utilisation](#-utilisation)
- [API REST](#-api-rest)
- [Tests](#-tests)
- [CI/CD](#-cicd)
- [Sécurité](#-sécurité)
- [Roadmap](#-roadmap)
- [Contribution](#-contribution)
- [Auteurs](#-auteurs)
- [Licence](#-licence)

---

## 📖 Présentation

**Club Foot App** est une application multiplateforme de gestion conçue pour le club de football **Maghreb Association Sportive (MAS) de Fès**, l'un des clubs les plus prestigieux du football marocain, fondé en 1946 et évoluant en Botola Pro D1 Inwi.

L'application répond à un besoin de **digitalisation de la gestion administrative et sportive** du club en centralisant l'ensemble de ses processus :

- Suivi des adhérents et des joueurs
- Gestion des équipes et du calendrier sportif
- Suivi financier des cotisations
- Gestion documentaire (licences, certificats médicaux, pièces d'identité)
- Communication interne en temps réel

> **Objectif :** doter le club d'un outil numérique moderne, centralisé et sécurisé, adapté à sa dimension sportive et à ses contraintes administratives.

---

## 🎯 Contexte

Le MAS de Fès a engagé une **réforme structurelle profonde** de sa gouvernance : augmentation de capital à 200 MDH, budget de fonctionnement porté de 33 à 51 MDH, reconstruction du stade Hassan II (400 MDH) et création d'un centre de formation (100 MDH).

À mesure que le club se professionnalise, les besoins en gestion administrative et sportive augmentent considérablement. La gestion manuelle (registres papier, tableurs partagés) montre ses limites :

- ❌ Dispersion des informations
- ❌ Absence de traçabilité
- ❌ Communication inefficace
- ❌ Manque d'outils de pilotage
- ❌ Gestion insuffisante des rôles et droits d'accès

**Club Foot App** répond à ces problématiques par une solution centralisée, sécurisée et accessible.

---

## ✨ Fonctionnalités

### 🔐 Authentification & Gestion des utilisateurs
- Authentification par email/mot de passe avec **JWT** (expiration 24 h)
- Création de comptes par l'administrateur avec mot de passe initial
- Activation automatique du compte
- Gestion fine de **4 rôles** : `ADMIN`, `ENCADRANT`, `ADHERENT`, `JOUEUR`
- Consultation et modification du profil
- Changement de mot de passe

### 🏆 Gestion sportive
- **Équipes** : création, affectation d'un encadrant, catégories d'âge
- **Joueurs** : affectation à une équipe, poste, numéro de maillot
- **Entraînements** : planification (date, lieu, objectif)
- **Matchs** : adversaire, scores, date

### 💰 Gestion financière
- Création et suivi des cotisations
- Upload de justificatifs de paiement
- Workflow de validation (EN_ATTENTE → VALIDEE / REJETEE)
- Statistiques financières (revenus mensuels)

### 📄 Gestion documentaire
- Upload de documents administratifs (CIN, certificats médicaux, licences fédérales)
- Configuration des pièces requises par rôle
- Workflow de validation avec motif de rejet

### 💬 Communication
- **Chat temps réel par équipe** (WebSocket/STOMP)
- Messagerie privée avec l'administrateur
- Annonces et broadcasts globaux
- Notifications de messages

### 📊 Tableau de bord administrateur
- Statistiques globales (adhérents, joueurs, équipes)
- Évolution de l'activité
- Revenus mensuels (graphiques `fl_chart`)

---

## 🛠️ Stack technique

<table>
<tr>
<td valign="top" width="33%">

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- Spring Security
- Spring Data JPA / Hibernate
- JJWT 0.11.5
- Spring WebSocket (STOMP)
- Lombok
- Maven
- MySQL Connector/J

</td>
<td valign="top" width="33%">

### Frontend
- **Flutter (Dart SDK ≥ 3.0)**
- Provider ~6.1.1
- http ~1.1.0
- stomp_dart_client ~1.0.0
- shared_preferences ~2.2.2
- fl_chart ~0.66.0
- flutter_spinkit
- image_picker / file_picker
- flutter_pdfview / open_file
- intl ~0.19.0

</td>
<td valign="top" width="33%">

### DevOps & Outils
- **Docker** (multi-stage build)
- **Docker Compose**
- **GitHub Actions** (CI/CD)
- **MySQL 8.0**
- Git / GitHub
- VS Code / IntelliJ IDEA
- JUnit 5 / Mockito

</td>
</tr>
</table>

---

## 🏗️ Architecture

### Vue d'ensemble

```mermaid
graph LR
    A[Client Flutter<br/>mobile · web · desktop] -->|REST/JSON + JWT| B[API Spring Boot<br/>context-path /api]
    A -->|STOMP| C[WebSocket<br/>STOMP/SockJS]
    B -->|JDBC| D[(MySQL 8.0)]
    B -->|Fichiers| E[Stockage local<br/>uploads/]
    C --> D
