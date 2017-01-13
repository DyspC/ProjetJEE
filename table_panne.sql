create database weboo;
use weboo;



SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

CREATE TABLE IF NOT EXISTS table_panne 
(
    id INT not null,
    nom_machine VARCHAR(16) not null,
    type_machine VARCHAR(8) not null,
    type_panne VARCHAR(20) not null,
    date_panne DATETIME not null,
    Traite BOOLEAN not null,
    PRIMARY KEY (id)
)ENGINE=MyISAM DEFAULT CHARSET=latin1;


