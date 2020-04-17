DROP TABLE IF EXISTS `equipment`;
DROP TABLE IF EXISTS `equipment_type`;
DROP TABLE IF EXISTS `loan`;
DROP TABLE IF EXISTS `stay`;
DROP TABLE IF EXISTS `guest`;
DROP TABLE IF EXISTS `id_gen`;

  CREATE TABLE `guest` (
    `id` bigint(20) NOT NULL,
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
      `id` bigint(20) NOT NULL,
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
      `email` varchar(100) NOT NULL,
      `phone` varchar(100) NOT NULL,
      `passport_id` varchar(100) NOT NULL,
      `check_in_date` DATE NOT NULL,
      `check_out_date` DATE,
      `arrive_date` DATE NOT NULL,
      `leave_date` DATE NOT NULL,
      `hotel` varchar(100) NOT NULL,
      `room` varchar(100) NOT NULL,
      `last_dive_date` DATE NOT NULL,
      `brevet` varchar(100) NOT NULL,
      `dives_amount` int(10) NOT NULL,
      `nitrox` tinyint(1) NOT NULL,
      `medical_statement` tinyint(1) NOT NULL,
      `active` tinyint(1) NOT NULL,
      `pre_booking` varchar(400),
      FOREIGN KEY (`guest_id`) REFERENCES guest(`id`),
      PRIMARY KEY (`id`)
    );

  CREATE TABLE `equipment_type` (
    `id` bigint(20) NOT NULL,
    `type` varchar(100) NOT NULL,
    `description` varchar(400) NOT NULL,
    `price` double precision NOT NULL,
    `active` tinyint(1) NOT NULL DEFAULT '1',
    PRIMARY KEY (`id`)
  );

   CREATE TABLE `equipment` (
     `id` bigint(20) NOT NULL,
     `eq_type_id` bigint(20) NOT NULL,
     `serial_number` varchar(100) NOT NULL,
     `status` varchar(100) NOT NULL,
     FOREIGN KEY (`eq_type_id`) REFERENCES equipment_type(`id`),
     PRIMARY KEY (`id`)
   );

   CREATE TABLE `loan` (
     `id` bigint(20) NOT NULL,
     `stay_id` bigint(20) NOT NULL,
     `equipment_id` bigint(20) NOT NULL,
     `day_out` DATE NOT NULL,
     `day_return` DATE,
     `price` double precision NOT NULL,
     FOREIGN KEY (`stay_id`) REFERENCES stay(`id`),
     FOREIGN KEY (`equipment_id`) REFERENCES equipment(`id`),
     PRIMARY KEY (`id`)
   );

  CREATE TABLE `id_gen`(
    `id_value` INTEGER NOT NULL,
    `id_name` VARCHAR(24) NOT NULL,
    PRIMARY KEY(`id_name`)
  );