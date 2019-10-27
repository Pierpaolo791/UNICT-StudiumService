CREATE TABLE IF NOT EXISTS `Dipartimenti` (
  `id` varchar(5) NOT NULL,
  `nome` text NOT NULL,
  `anno_accademico` int NOT NULL,
  PRIMARY KEY(`id`, `anno_accademico`)
) DEFAULT CHARSET=utf8;

INSERT INTO `dipartimenti` (`id`, `nome`, `anno_accademico`) VALUES ('D251', 'MATEMATICA E INFORMATICA', '2020'); 

CREATE TABLE IF NOT EXISTS `CdS` (
  `id` int NOT NULL,
  `nome` text,
  `id_dipartimento` varchar(5) NOT NULL,
  `anno_accademico` int NOT NULL,
  PRIMARY KEY(`id`, `anno_accademico`),
  FOREIGN KEY(`id_dipartimento`,`anno_accademico`) REFERENCES `Dipartimenti`(`id`,`anno_accademico`)
) DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `Materie` (
    `id` int NOT NULL,
    `id_cds` int NOT NULL,
    `nome` text NOT NULL,
    `anno_accademico` int NOT NULL,
    `anno` int NULL,
    `semestre` varchar(4) NULL,
    PRIMARY KEY(`id`,`anno_accademico`),
    FOREIGN KEY(`id_cds`) REFERENCES `CdS`(`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `Avvisi` (
  `id` int NOT NULL,
  `id_materia` int NOT NULL,
  `titolo` text,
  `contenuto` text,
  `docente` text,
  `data` text,
  `spammed` int(1) DEFAULT 0,
  PRIMARY KEY(`id`, `id_materia`),
  FOREIGN KEY(`id_materia`) REFERENCES `Materie`(`id`)
) DEFAULT CHARSET=utf8;