
SET NAMES utf8;

INSERT INTO `artiste` (`id`, `bibliographie`, `nom`, `prenom`) VALUES
(1, 'Sculpteur breton', 'Ruffet', 'Vincent'),
(2, 'Artiste de rue habitant a Neuilly', 'Henry', 'Charle'),
(3, 'Peintre aimant representer des chats', 'Guerin', 'Puma'),
(4, 'Photographe, acteur, producteur et scenariste americain, ne le 18 novembre 1968 a Dallas, au Texas (Etats-Unis)', 'Wilson', 'Owen'),
(5, 'Sculpteur senegalais', 'Sene', 'Mame Birame'),
(6, 'Photographe, acteur, chanteur, producteur, compositeur et scenariste americain ne le 28 aout 1969 a Hermosa Beach (Californie)', 'Black', 'Jack');


INSERT INTO `musee`.`oeuvre` (
`DTYPE` ,
`id` ,
`annee` ,
`caracteristique` ,
`hauteur` ,
`largeur` ,
`longueur` ,
`hasBeenReproduced` ,
`resume` ,
`tag` ,
`titre` ,
`realisation` ,
`support` ,
`materiaux` ,
`artiste_id`
)
VALUES 
('Photographie', 1 , '1989', NULL , '50' , '50', '70', '', 'cliche de New York', NULL , 'Big apple', NULL , 'ALUMINIUM' , NULL , '4'),
('Photographie', 2 , '1992', NULL , '50' , '40', '80', '1', 'Manhattan', NULL , 'Delicious night', NULL , 'ALUMINIUM' , NULL , '6'),
('Peinture', 3 , '1995', NULL , '50' , '50', '70', '', 'Entrepris pour le tombeau du pape Jules II des 1505,', NULL , 'captif', NULL , NULL , NULL , '5'),
('Sculpture', 4 , '1989', NULL , '50' , '50', '70', '', 'Diamant', NULL , 'Le regent', NULL , NULL , NULL , '5'),

('Sculpture', 5 , '19891', NULL , '50' , '50', '70', '1', 'buste feminin trouve a Faleries', NULL , 'Ariane', NULL , NULL , NULL , '1'),
('Sculpture', 6 , '1993', NULL , '50' , '40', '80', '', 'cratere en calice', NULL , 'figures rouges', NULL , 'BOIS' , NULL , '2'),
('Sculpture', 7 , '2002', NULL , '50' , '50', '70', '', 'code de hamurabi,', NULL , 'babylone', NULL , NULL , NULL , '4'),
('Sculpture', 8 , '2006', NULL , '50' , '50', '70', '1', 'trouvé au parthenon', NULL , 'plaque des ergastines', NULL , NULL , NULL , '6'),

('Photographie', 9 , '2012', NULL , '50' , '50', '70', '1', 'Venus de milo', NULL , 'Aphrodite', NULL , NULL , 'Pierre' , '3'),
('Photographie', 10 , '2007', NULL , '50' , '40', '80', '', 'victoire', NULL , 'samothrace', NULL , NULL , 'Pierre' , '6'),
('Peinture', 11 , '1985', NULL , '50' , '50', '70', '', 'taureau androcephale', NULL , 'buffalo grill', NULL , NULL , NULL , '5'),
('Sculpture', 12 , '1984', NULL , '50' , '50', '70', '', 'classique', NULL , 'le tricheur a l\'as de carreau', NULL , NULL , NULL , '3'),

('Photographie', 13 , '2045', NULL , '50' , '50', '70', '', 'vers 31 av JC', NULL , 'Livie', NULL , 'ALUMINIUM' , 'Argile' , '4'),
('Photographie', 14 , '1800', NULL , '50' , '40', '80', '', 'mosaique', NULL , 'jugement de paris', NULL , NULL , NULL , '6'),
('Peinture', 15 , '2012', NULL , '50' , '50', '70', '1', 'la joconde', NULL , 'lisa gherardini', NULL , NULL , NULL , '1'),
('Sculpture', 16 , '1989', NULL , '50' , '50', '70', '', 'portrait de femme', NULL , 'l\'europeene', NULL , NULL , NULL , '2'),

('Peinture', 17 , '1989', NULL , '50' , '50', '70', '', 'louis 14', NULL , 'roi soleil', NULL , 'ALUMINIUM' , NULL , '4'),
('Peinture', 18 , '1992', NULL , '50' , '40', '80', '', 'inoubliable', NULL , 'noces de cana', NULL , NULL , NULL , '6'),
('Sculpture', 19 , '1995', NULL , '50' , '50', '70', '', 'scribe accroupi', NULL , 'toutencarton', NULL , NULL , NULL , '5'),
('Sculpture', 20 , '1989', NULL , '50' , '50', '70', '', 'pour toujours', NULL , 'epoux de cervetri', NULL , NULL , NULL , '1'),

('Photographie', 21 , '1950', NULL , '50' , '50', '70', '', 'grosse tete', NULL , 'Hadrien', NULL , 'ALUMINIUM' , NULL , '4'),
('Photographie', 22 , '1960', NULL , '50' , '40', '80', '', 'mince alors', NULL , 'enlevement des sabines', NULL , 'ALUMINIUM' , NULL , '6'),
('Sculpture', 23 , '1970', NULL , '50' , '50', '70', '', 't\'as une tache', NULL , 'la femme au miroir', NULL , NULL , NULL , '2'),
('Sculpture', 24 , '1980', NULL , '50' , '50', '70', '1', 'faut pas toucher', NULL , 'radeau de la meduse', NULL , NULL , NULL , '3');




INSERT INTO `collection` (`Id`,`etat`,`libele`) VALUES ('1','RESTORATION','dui. Fusce diam nunc, ullamcorper eu,');
INSERT INTO `collection` (`Id`,`etat`,`libele`) VALUES ('2','RESTORATION','Nunc quis arcu');
INSERT INTO `collection` (`Id`,`etat`,`libele`) VALUES ('3','EXPOSED','id sapien. Cras dolor');
INSERT INTO `collection` (`Id`,`etat`,`libele`) VALUES ('4','RESERVE','arcu.');
INSERT INTO `collection` (`Id`,`etat`,`libele`) VALUES ('5','RESTORATION','lectus. Nullam suscipit, est ac facilisis facilisis,');
INSERT INTO `collection` (`Id`,`etat`,`libele`) VALUES ('6','EXPOSED','tincidunt dui augue');
INSERT INTO `collection` (`Id`,`etat`,`libele`) VALUES ('7','EXPOSED','consequat dolor');
INSERT INTO `collection` (`Id`,`etat`,`libele`) VALUES ('8','RESERVE','gravida sit amet, dapibus id,');
INSERT INTO `collection` (`Id`,`etat`,`libele`) VALUES ('9','RESTORATION','augue porttitor interdum. Sed auctor');



INSERT INTO `musee`.`collection_oeuvre` (
`Collection_id` ,
`oeuvres_id`
)
VALUES 
('1', '13'),
('1', '2'),
('2', '13'),
('2', '15'),
('2', '24'),
('2', '2'),
('3', '7'),
('3', '5'),
('4', '9'),
('5', '11'),
('5', '12'),
('5', '5'),
('6', '8'),
('7', '20'),
('7', '21'),
('8', '23'),
('8', '17'),
('9', '18');


INSERT INTO `musee`.`employe` (
`id` ,
`login` ,
`password` ,
`nom` ,
`prenom` ,
`status`
)
VALUES 
(NULL , 'conservateur', 'conservateur', 'stiler', 'ben', 'CONSERVATEUR'),
(NULL , 'libraire', 'libraire', 'charlie', 'chaplin', 'LIBRAIRE');


INSERT INTO `musee`.`reproduction` (
`id` ,
`prix` ,
`support` ,
`oeuvre_id`
)
VALUES 
(NULL , '10', 'CARTE', '2'),
(NULL , '50', 'TOILE', '5'),
(NULL , '10', 'CARTE', '2'),
(NULL , '10', 'CARTE', '2'),
(NULL , '10', 'CARTE', '2'),
(NULL , '50', 'AFFICHE', '24'),
(NULL , '50', 'AFFICHE', '24'),
(NULL , '50', 'AFFICHE', '24'),
(NULL , '10', 'CARTE', '15'),
(NULL , '50', 'TOILE', '15'),
(NULL , '10', 'CARTE', '8'),
(NULL , '10', 'CARTE', '8');




