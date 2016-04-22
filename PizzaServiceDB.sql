

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `PizzaService` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PizzaService` DEFAULT CHARACTER SET utf8 ;
USE `PizzaService` ;

-- -----------------------------------------------------
-- Table `mydb`.`PIZZA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PizzaService`.`PIZZA` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `PRICE` DECIMAL(10,2) NOT NULL,
  `TYPE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `name_UNIQUE` (`NAME` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `mydb`.`ADDRESS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PizzaService`.`ADDRESS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `STREET` VARCHAR(45) NOT NULL,
  `CITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `mydb`.`CUSTOMER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PizzaService`.`CUSTOMER` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  `SURNAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `mydb`.`ACCUMULATIVE_CARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PizzaService`.`ACCUMULATIVE_CARD` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `SUM` DECIMAL(10,2) NOT NULL,
  `CUSTOMER_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ACCUMULATIVE_CARD_CUSTOMER_idx` (`CUSTOMER_ID` ASC),
  CONSTRAINT `fk_ACCUMULATIVE_CARD_CUSTOMER`
    FOREIGN KEY (`CUSTOMER_ID`)
    REFERENCES `PizzaService`.`CUSTOMER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `mydb`.`ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PizzaService`.`ORDER` (
  `ID` INT NOT NULL,
  `STATE` VARCHAR(45) NOT NULL,
  `PRICE` DECIMAL(10,2) NOT NULL,
  `DISCOUNT` DECIMAL(10,2) NULL,
  `CUSTOMER_ID` INT NOT NULL,
  `ADDRESS_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ORDER_CUSTOMER1_idx` (`CUSTOMER_ID` ASC),
  INDEX `fk_ORDER_ADDRESS1_idx` (`ADDRESS_ID` ASC),
  CONSTRAINT `fk_ORDER_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_ID`)
    REFERENCES `PizzaService`.`CUSTOMER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ORDER_ADDRESS1`
    FOREIGN KEY (`ADDRESS_ID`)
    REFERENCES `PizzaService`.`ADDRESS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `mydb`.`CUSTOMER_ADDRESS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PizzaService`.`CUSTOMER_ADDRESS` (
  `CUSTOMER_ID` INT NOT NULL,
  `ADDRESS_ID` INT NOT NULL,
  INDEX `fk_CUSTOMER_has_ADDRESS_ADDRESS1_idx` (`ADDRESS_ID` ASC),
  INDEX `fk_CUSTOMER_has_ADDRESS_CUSTOMER1_idx` (`CUSTOMER_ID` ASC),
  PRIMARY KEY (`CUSTOMER_ID`, `ADDRESS_ID`),
  CONSTRAINT `fk_CUSTOMER_has_ADDRESS_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_ID`)
    REFERENCES `PizzaService`.`CUSTOMER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CUSTOMER_has_ADDRESS_ADDRESS1`
    FOREIGN KEY (`ADDRESS_ID`)
    REFERENCES `PizzaService`.`ADDRESS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `mydb`.`ORDER_has_PIZZA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PizzaService`.`ORDER_has_PIZZA` (
  `ORDER_ID` INT NOT NULL,
  `PIZZA_ID` INT NOT NULL,
  `pizzas` INT NOT NULL,
  PRIMARY KEY (`ORDER_ID`, `PIZZA_ID`),
  INDEX `fk_ORDER_has_PIZZA_PIZZA1_idx` (`PIZZA_ID` ASC),
  INDEX `fk_ORDER_has_PIZZA_ORDER1_idx` (`ORDER_ID` ASC),
  CONSTRAINT `fk_ORDER_has_PIZZA_ORDER1`
    FOREIGN KEY (`ORDER_ID`)
    REFERENCES `PizzaService`.`ORDER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ORDER_has_PIZZA_PIZZA1`
    FOREIGN KEY (`PIZZA_ID`)
    REFERENCES `PizzaService`.`PIZZA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;
