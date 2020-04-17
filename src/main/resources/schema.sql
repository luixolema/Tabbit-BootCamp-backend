drop table IF EXISTS `loan`;
drop table IF EXISTS `equipment`;
drop table IF EXISTS `equipment_type`;
drop table IF EXISTS `stay`;
drop table IF EXISTS `guest`;
drop table IF EXISTS `id_gen`;

  create TABLE `guest` (
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

  create TABLE `stay` (
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
      `active` tinyint(1) NOT NULL,
      `pre_booking` varchar(400),
      FOREIGN KEY (`guest_id`) REFERENCES guest(`id`),
      PRIMARY KEY (`id`)
    );

  create TABLE `id_gen`(
    `id_value` INTEGER NOT NULL,
    `id_name` VARCHAR(24) NOT NULL,
    PRIMARY KEY(`id_name`)
  );

  create TABLE `equipment_type` (
    `id` bigint(20) NOT NULL,
    `type` varchar(100) NOT NULL,
    `description` varchar(400) NOT NULL,
    `price` DECIMAL(13,2) NOT NULL,
    `active` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
  );

create TABLE `equipment` (
    `id` bigint(20) NOT NULL,
    `equipment_type_id` bigint(20) NOT NULL,
    `serial_number` varchar(100) NOT NULL,
    `status` varchar(100) NOT NULL,
    FOREIGN KEY (`equipment_type_id`) REFERENCES equipment_type(`id`),
    CONSTRAINT uni_sety UNIQUE (`serial_number`, `equipment_type_id`),
    PRIMARY KEY (`id`)
  );

create TABLE `loan` (
    `id` bigint(20) NOT NULL,
    `equipment_id` bigint(20) NOT NULL,
    `stay_id` bigint(20) NOT NULL,
    `date_out` DATE NOT NULL,
    `date_return` DATE,
    FOREIGN KEY (`equipment_id`) REFERENCES equipment(`id`),
    FOREIGN KEY (`stay_id`) REFERENCES stay(`id`),
    PRIMARY KEY (`id`)
  );