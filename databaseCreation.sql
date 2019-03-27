-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `userType` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`student` (
  `id` INT(11) NOT NULL,
  `idUser` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_student_user1`
    FOREIGN KEY (`idUser`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`contactinformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`contactinformation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idStudent` INT(11) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `phoneNumber` VARCHAR(45) NULL DEFAULT NULL,
  `emailAddress` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_ContactInformation_Student1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `mydb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`course` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `session` VARCHAR(45) NULL DEFAULT NULL,
  `examDate` DATE NULL DEFAULT NULL,
  `enrollmentKey` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`enrollment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`enrollment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idCourse` INT(11) NULL DEFAULT NULL,
  `idStudent` INT(11) NULL DEFAULT NULL,
  `finalGrade` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Enrollment_Course1`
    FOREIGN KEY (`idCourse`)
    REFERENCES `mydb`.`course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Enrollment_Student1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `mydb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`personalinformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`personalinformation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idStudent` INT(11) NULL DEFAULT NULL,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `lastName` VARCHAR(45) NULL DEFAULT NULL,
  `icn` VARCHAR(45) NULL DEFAULT NULL,
  `pnc` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_PersonalInformation_Student1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `mydb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`studentinformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`studentinformation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idStudent` INT(11) NULL DEFAULT NULL,
  `studGroup` VARCHAR(45) NULL DEFAULT NULL,
  `scholarShipState` INT(11) NULL DEFAULT NULL,
  `gradeAvrg` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_StudentInformation_Student1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `mydb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`studentActivity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`studentActivity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idStudent` INT NULL,
  `activityDate` DATE NULL,
  `activityType` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_studentActivity_student1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `mydb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
