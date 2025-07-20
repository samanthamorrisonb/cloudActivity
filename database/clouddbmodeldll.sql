-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema clouddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clouddb` DEFAULT CHARACTER SET utf8 ;
USE `clouddb` ;

-- -----------------------------------------------------
-- Table `clouddb`.`user_credentials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clouddb`.`user_credentials` (
  `user_id` INT(10) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `clouddb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clouddb`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `clouddb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clouddb`.`user` (
  `user_id` INT(10) NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(15) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `update_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_credentials_user_id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `fk_user_user_credentials1`
    FOREIGN KEY (`user_id`)
    REFERENCES `clouddb`.`user_credentials` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `clouddb`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clouddb`.`user_roles` (
  `roles_id` INT(11) NOT NULL,
  `user_id` INT(10) NOT NULL,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  INDEX `fk_user_roles_roles1_idx` (`roles_id` ASC),
  INDEX `fk_user_roles_user_credentials1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_roles_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `clouddb`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_roles_user_credentials1`
    FOREIGN KEY (`user_id`)
    REFERENCES `clouddb`.`user_credentials` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `clouddb`.`user_credentials`
-- -----------------------------------------------------
START TRANSACTION;
USE `clouddb`;
INSERT INTO `clouddb`.`user_credentials` (`user_id`, `username`, `password`) VALUES (1, 'admin', '$2a$10$JPeLvy9/LVoyYP3kUm3NCeeIAOkPpU5x1/mz.FKuvgTsb5m/TAx4u');

COMMIT;




-- -----------------------------------------------------
-- Data for table `clouddb`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `clouddb`;
INSERT INTO `clouddb`.`roles` (`id`, `name`) VALUES (1, 'admin');
INSERT INTO `clouddb`.`roles` (`id`, `name`) VALUES (2, 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `clouddb`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `clouddb`;
INSERT INTO `clouddb`.`user` (`user_id`, `first_name`, `last_name`, `email`, `phone`, `created_date`, `update_date`) VALUES (1, 'Admin', 'User', 'admin@admin.com', '5555555555', '2025-05-10 18:15:55', '2025-05-10 18:15:55');

COMMIT;


-- -----------------------------------------------------
-- Data for table `clouddb`.`user_roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `clouddb`;
INSERT INTO `clouddb`.`user_roles` (`roles_id`, `user_id`) VALUES (1, 1);

COMMIT;

