DROP TABLE IF EXISTS `guest`;
DROP TABLE IF EXISTS `stay`;

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
    `email` varchar(100),
    `phone` varchar(100) NOT NULL,
    `passport_id` varchar(100) NOT NULL,
    `checked_in` tinyint(1) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
  );

  CREATE TABLE `stay` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `guest_id` bigint(20) NOT NULL,
      `first_name` varchar(100) NOT NULL,
      `last_name` varchar(100) NOT NULL,
      `box_number` varchar(40) NOT NULL,
      `birth_date` DATE NOT NULL,
      `nationality` varchar(100) NOT NULL,
      `country` varchar(100) NOT NULL,
      `city` varchar(100) NOT NULL,
      `postcode` varchar(100) NOT NULL,
      `street` varchar(100) NOT NULL,
      `email` varchar(100),
      `phone` varchar(100) NOT NULL,
      `passport_id` varchar(100) NOT NULL,
      `check_in_date` DATE NOT NULL,
      `check_out_date` DATE NOT NULL,
      `arrive_date` DATE NOT NULL,
      `leave_date` DATE NOT NULL,
      `hotel` varchar(100) NOT NULL,
      `room` varchar(100) NOT NULL,
      `last_dive_date` DATE NOT NULL,
      `brevet` varchar(100) NOT NULL,
      `dives_amount` int(10) NOT NULL,
      `nitrox` tinyint(1) NOT NULL,
      `medical_statement` tinyint(1) NOT NULL,
      PRIMARY KEY (`id`)
    );