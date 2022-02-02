CREATE TABLE IF NOT EXISTS `vacinas`.`raca` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
    `nome` VARCHAR(50) NOT NULL COMMENT '',
    PRIMARY KEY (`id`)  COMMENT '')
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `vacinas`.`animal` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
    `nome` VARCHAR(100) NOT NULL COMMENT '',
    `dono` VARCHAR(100) NOT NULL COMMENT '',
    `telefone` VARCHAR(9) NOT NULL COMMENT '',
    `tipo` CHAR NOT NULL COMMENT 'G - Gato\nC - Cachorro',
    `nascimento` DATE NULL COMMENT '',
    `raca` INT NOT NULL COMMENT '',
    PRIMARY KEY (`id`)  COMMENT '',
    INDEX `fk_animal_raca1_idx` (`raca` ASC)  COMMENT '',
    CONSTRAINT `fk_animal_raca1`
    FOREIGN KEY (`raca`)
    REFERENCES `vacinas`.`raca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `vacinas`.`vacina` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(100) NOT NULL COMMENT '',
    `data` DATE NOT NULL COMMENT '',
    `animal` INT NOT NULL COMMENT '',
    PRIMARY KEY (`id`)  COMMENT '',
    INDEX `fk_vacina_animal_idx` (`animal` ASC)  COMMENT '',
    CONSTRAINT `fk_vacina_animal`
    FOREIGN KEY (`animal`)
    REFERENCES `vacinas`.`animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;

