DROP TABLE IF EXISTS `guest`;

  CREATE TABLE `guest` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(100) NOT NULL,
    `last_name` varchar(100) NOT NULL,
    `birth_date` DATE NOT NULL,
    `nationality` varchar(100) NOT NULL,
    `country` varchar(100) NOT NULL,
    `city` varchar(100) NOT NULL,
    `postcode` varchar(100) NOT NULL,
    `street` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `phone` varchar(100) NOT NULL,
    `passport_id` varchar(100) NOT NULL,
    `last_dive` DATE NOT NULL,
    `brevet` varchar(100) NOT NULL,
    `dives_amount` int(10) NOT NULL,
    `nitrox` BOOLEAN NOT NULL,
    `medical_statement` BOOLEAN NOT NULL,
    `checked_in` tinyint(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
  );