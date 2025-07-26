-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema clouddb
-- -----------------------------------------------------
-- DROP SCHEMA IF EXISTS `clouddb` ;

-- -----------------------------------------------------
-- Schema clouddb
-- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS `clouddb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- USE `clouddb` ;

-- -----------------------------------------------------
-- Table `clouddb`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roles` ;

CREATE TABLE IF NOT EXISTS `roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `name` ON `roles` (`name` ASC);


-- -----------------------------------------------------
-- Table `clouddb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(15) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `update_date` DATETIME NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `user_credentials_user_id_UNIQUE` ON `user` (`user_id` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `user` (`email` ASC);


-- -----------------------------------------------------
-- Table `clouddb`.`user_credentials`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_credentials` ;

CREATE TABLE IF NOT EXISTS `user_credentials` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `user_id_UNIQUE` ON `user_credentials` (`user_id` ASC);

CREATE UNIQUE INDEX `username_UNIQUE` ON `user_credentials` (`username` ASC);


-- -----------------------------------------------------
-- Table `clouddb`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_roles` ;

CREATE TABLE IF NOT EXISTS `user_roles` (
  `roles_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  CONSTRAINT `fk_user_roles_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `roles` (`id`),
  CONSTRAINT `fk_user_roles_user_credentials1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_credentials` (`user_id`),
  CONSTRAINT `fk_user_roles_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `user_id_UNIQUE` ON `user_roles` (`user_id` ASC);

CREATE INDEX `fk_user_roles_roles1_idx` ON `user_roles` (`roles_id` ASC);

CREATE INDEX `fk_user_roles_user_credentials1_idx` ON `user_roles` (`user_id` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `clouddb`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
-- USE `clouddb`;
INSERT INTO `roles` (`id`, `name`) VALUES (0, 'admin');
INSERT INTO `roles` (`id`, `name`) VALUES (1, 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `clouddb`.`user`
-- -----------------------------------------------------
START TRANSACTION;
-- USE `clouddb`;
INSERT INTO `user` (`user_id`, `first_name`, `last_name`, `email`, `phone`, `created_date`, `update_date`) VALUES (0, 'admin', 'admin', 'admin@admin.com', '555-555-5555', '2025-05-10 18:15:55', '2025-05-10 18:15:55');

COMMIT;


-- -----------------------------------------------------
-- Data for table `clouddb`.`user_credentials`
-- -----------------------------------------------------
START TRANSACTION;
-- USE `clouddb`;
INSERT INTO `user_credentials` (`user_id`, `username`, `password`) VALUES (0, 'admin', '$2a$10$JPeLvy9/LVoyYP3kUm3NCeeIAOkPpU5x1/mz.FKuvgTsb5m/TAx4u');

COMMIT;


-- -----------------------------------------------------
-- Data for table `clouddb`.`user_roles`
-- -----------------------------------------------------
START TRANSACTION;
-- USE `clouddb`;
INSERT INTO `user_roles` (`roles_id`, `user_id`) VALUES (0, 0);

COMMIT;

