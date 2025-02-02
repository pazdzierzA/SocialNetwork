-- MySQL Script generated by MySQL Workbench
-- Sun Feb  2 11:29:44 2025
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema social_networks
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `social_networks` ;

-- -----------------------------------------------------
-- Schema social_networks
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `social_networks` DEFAULT CHARACTER SET utf8 COLLATE utf8_danish_ci ;
USE `social_networks` ;

-- -----------------------------------------------------
-- Table `social_networks`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Users` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Users` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Posts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Posts` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Posts` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `like_quantity` INT NOT NULL DEFAULT 0,
  `comment_quantity` INT NOT NULL DEFAULT 0,
  `creator_id` BIGINT(32) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Posts_Users_idx` (`creator_id` ASC) INVISIBLE,
  CONSTRAINT `fk_Posts_Users`
    FOREIGN KEY (`creator_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Comments` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Comments` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(45) NOT NULL,
  `author_id` BIGINT(32) UNSIGNED NOT NULL,
  `post_id` BIGINT(32) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Comments_Posts1_idx` (`post_id` ASC) VISIBLE,
  INDEX `fk_Comments_Users1_idx` (`author_id` ASC) VISIBLE,
  CONSTRAINT `fk_Comments_Posts1`
    FOREIGN KEY (`post_id`)
    REFERENCES `social_networks`.`Posts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comments_Users1`
    FOREIGN KEY (`author_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Groups` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Groups` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `type` ENUM('private', 'public') NOT NULL DEFAULT 'public',
  `group_creator_id` BIGINT(32) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Groups_Users1_idx` (`group_creator_id` ASC) VISIBLE,
  CONSTRAINT `fk_Groups_Users1`
    FOREIGN KEY (`group_creator_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Posts_Likes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Posts_Likes` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Posts_Likes` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `post_id` BIGINT(32) UNSIGNED NOT NULL,
  `user_id` BIGINT(32) UNSIGNED NOT NULL,
  INDEX `fk_Likes_Posts1_idx` (`post_id` ASC) VISIBLE,
  INDEX `fk_Likes_Users1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Likes_Posts1`
    FOREIGN KEY (`post_id`)
    REFERENCES `social_networks`.`Posts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Likes_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Messages` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Messages` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(45) NOT NULL,
  `to_user_id` BIGINT(32) UNSIGNED NOT NULL,
  `from_user_id` BIGINT(32) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Messages_Users1_idx` (`to_user_id` ASC) VISIBLE,
  INDEX `fk_Messages_Users2_idx` (`from_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Messages_Users1`
    FOREIGN KEY (`to_user_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messages_Users2`
    FOREIGN KEY (`from_user_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Notifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Notifications` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Notifications` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(45) NOT NULL,
  `type` ENUM("LIKE", "COMMENT", "FRIEND_REQUESTS", "MESSAGE") NOT NULL,
  `user_id` BIGINT(32) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Notifications_Users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Notifications_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Stories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Stories` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Stories` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(45) NOT NULL,
  `picture_story_url` VARCHAR(100) NOT NULL,
  `story_creator_id` BIGINT(32) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Stories_Users1_idx` (`story_creator_id` ASC) VISIBLE,
  CONSTRAINT `fk_Stories_Users1`
    FOREIGN KEY (`story_creator_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Group_Members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Group_Members` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Group_Members` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role` ENUM('admin', 'member') NOT NULL,
  `group_id` BIGINT(32) UNSIGNED NOT NULL,
  `user_id` BIGINT(32) UNSIGNED NOT NULL,
  INDEX `fk_Groups_has_Users_Users2_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Groups_has_Users_Groups2_idx` (`group_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Groups_has_Users_Groups2`
    FOREIGN KEY (`group_id`)
    REFERENCES `social_networks`.`Groups` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Groups_has_Users_Users2`
    FOREIGN KEY (`user_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Friendships`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Friendships` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Friendships` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` ENUM('Pending', 'Accepted', 'Blocked') NOT NULL DEFAULT 'Pending',
  `type` ENUM('Close_Friends', 'Family', 'Others') NOT NULL DEFAULT 'Others',
  `user_id` BIGINT(32) UNSIGNED NOT NULL,
  `friend_id` BIGINT(32) UNSIGNED NOT NULL,
  INDEX `fk_Users_has_Users_Users2_idx` (`friend_id` ASC) VISIBLE,
  INDEX `fk_Users_has_Users_Users1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `friend_id` (`friend_id` ASC) INVISIBLE,
  UNIQUE INDEX `unique_friendship` (`user_id` ASC, `friend_id` ASC) INVISIBLE,
  CONSTRAINT `fk_Users_has_Users_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Users_has_Users_Users2`
    FOREIGN KEY (`friend_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Events` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Events` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `location` POINT NOT NULL,
  `start_date` DATETIME(6) NOT NULL,
  `end_date` DATETIME(6) NOT NULL,
  `creator_id` BIGINT(32) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Events_Users1_idx` (`creator_id` ASC) VISIBLE,
  CONSTRAINT `fk_Events_Users1`
    FOREIGN KEY (`creator_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Events_Participants`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Events_Participants` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Events_Participants` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` ENUM('Going', 'Interested', 'Not_Going', 'No_Information') NOT NULL DEFAULT 'No_Information',
  `event_id` BIGINT(32) UNSIGNED NOT NULL,
  `user_id` BIGINT(32) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Events_Participants_Events1_idx` (`event_id` ASC) VISIBLE,
  INDEX `fk_Events_Participants_Users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Events_Participants_Events1`
    FOREIGN KEY (`event_id`)
    REFERENCES `social_networks`.`Events` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Events_Participants_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `social_networks`.`Saved_Posts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `social_networks`.`Saved_Posts` ;

CREATE TABLE IF NOT EXISTS `social_networks`.`Saved_Posts` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `post_id` BIGINT(32) UNSIGNED NOT NULL,
  `user_id` BIGINT(32) UNSIGNED NOT NULL,
  INDEX `fk_Posts_has_Users_Users1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Posts_has_Users_Posts1_idx` (`post_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Posts_has_Users_Posts1`
    FOREIGN KEY (`post_id`)
    REFERENCES `social_networks`.`Posts` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Posts_has_Users_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `social_networks`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
