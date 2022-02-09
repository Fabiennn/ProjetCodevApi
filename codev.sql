-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 09 fév. 2022 à 16:56
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `codev`
--

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
                               `id` int(11) NOT NULL,
                               `surname` char(25) DEFAULT NULL,
                               `salt` char(100) DEFAULT NULL,
                               `email` char(50) DEFAULT NULL,
                               `mdp` char(80) DEFAULT NULL,
                               `pays` char(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `surname`, `salt`, `email`, `mdp`, `pays`) VALUES
(1, 'John', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'john.doe@email.com', 'WFGexk0cocZHcs7qJuPawA==', 'admin'),
(15, 'Goubin', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'fabien.goubin@email.com', 'WFGexk0cocZHcs7qJuPawA==', 'apprenant'),
(16, 'Geffrault', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'felix.geffrault@email.com', 'WFGexk0cocZHcs7qJuPawA==', 'apprenant'),
(17, NULL, 'HnPh+cHbMHVtxeWMjJFW3BAhStRYc3w8L81W9W7CqP0=', 'ee', '/dwL4lSVgiOBbmT8xgOiyA==', 'apprenant');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
