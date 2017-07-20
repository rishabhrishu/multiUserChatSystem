-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema demo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema demo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `demo` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema login_chat
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema login_chat
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `login_chat` DEFAULT CHARACTER SET utf8 ;
USE `demo` ;

-- -----------------------------------------------------
-- Table `demo`.`login_test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `demo`.`login_test` (
  `Username` VARCHAR(45) NULL DEFAULT NULL,
  `Password` VARCHAR(45) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `login_chat` ;

-- -----------------------------------------------------
-- Table `login_chat`.`login_test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_chat`.`login_test` (
  `First Name` VARCHAR(45) NULL DEFAULT NULL,
  `Last Name` VARCHAR(45) NULL DEFAULT NULL,
  `Username` VARCHAR(45) NULL DEFAULT NULL,
  `Screenname` VARCHAR(45) NULL DEFAULT NULL,
  `Password` VARCHAR(45) NULL DEFAULT NULL,
  `ProfilePic` VARCHAR(45) NULL DEFAULT NULL,
  `IP` VARCHAR(45) NULL DEFAULT NULL,
  `Status` VARCHAR(45) NULL DEFAULT NULL,
  `gender` VARCHAR(45) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `login_chat` ;

-- -----------------------------------------------------
-- Placeholder table for view `login_chat`.`new_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `login_chat`.`new_view` (`First Name` INT, `Last Name` INT, `Username` INT, `Screenname` INT, `Password` INT, `ProfilePic` INT, `IP` INT, `Status` INT, `gender` INT);

-- -----------------------------------------------------
-- View `login_chat`.`new_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `login_chat`.`new_view`;
USE `login_chat`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `login_chat`.`new_view` AS select `login_chat`.`login_test`.`First Name` AS `First Name`,`login_chat`.`login_test`.`Last Name` AS `Last Name`,`login_chat`.`login_test`.`Username` AS `Username`,`login_chat`.`login_test`.`Screenname` AS `Screenname`,`login_chat`.`login_test`.`Password` AS `Password`,`login_chat`.`login_test`.`ProfilePic` AS `ProfilePic`,`login_chat`.`login_test`.`IP` AS `IP`,`login_chat`.`login_test`.`Status` AS `Status`,`login_chat`.`login_test`.`gender` AS `gender` from `login_chat`.`login_test`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
