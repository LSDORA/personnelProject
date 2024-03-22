CREATE TABLE ligue(
id_ligue INT AUTO_INCREMENT,
nom VARCHAR(255) NOT NULL,
ADMIN_LIGUE INT UNIQUE,
PRIMARY KEY (id_ligue),
);

CREATE TABLE employe(
ID_EMPLOYE INT AUTO_INCREMENT,
nom VARCHAR(70) NOT NULL,
prenom VARCHAR(70),
mail VARCHAR(100) NOT NULL,
password VARCHAR(50) NOT NULL,
date_arrive TIMESTAMP NOT NULL,
date_depart TIMESTAMP,
root BOOLEAN,
ligue INT,
PRIMARY KEY (ID_EMPLOYE),
);

ALTER TABLE employe
ADD CONSTRAINT fk_ligue
FOREIGN KEY (ligue) REFERENCES ligue(id_ligue);

ALTER TABLE ligue
ADD CONSTRAINT fk_employe
FOREIGN KEY (ADMIN_LIGUE) REFERENCES employe(ID_EMPLOYE);

