DROP TABLE IF EXISTS `loan`;
DROP TABLE IF EXISTS `equipment`;
DROP TABLE IF EXISTS `equipment_type`;
DROP TABLE IF EXISTS `stay`;
DROP TABLE IF EXISTS `guest`;
DROP TABLE IF EXISTS `id_gen`;
DROP TABLE IF EXISTS `box_management`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `dive_center`;

  create TABLE `dive_center`(
      `id` bigint(20) NOT NULL,
      `name` VARCHAR(400) NOT NULL,
      `location` VARCHAR(400) NOT NULL,
      PRIMARY KEY (`id`)
   );

  create TABLE `guest` (
    `id` bigint(20) NOT NULL,
    `dive_center_id` bigint(20) NOT NULL,
    `first_name` varchar(400) NOT NULL,
    `last_name` varchar(400) NOT NULL,
    `birth_date` DATE NOT NULL,
    `nationality` varchar(400) NOT NULL,
    `country` varchar(400) NOT NULL,
    `city` varchar(400) NOT NULL,
    `postcode` varchar(400) NOT NULL,
    `street` varchar(400) NOT NULL,
    `email` varchar(400),
    `phone` varchar(400),
    `passport_id` varchar(400),
    `checked_in` tinyint(1) NOT NULL DEFAULT '0',
    FOREIGN KEY (`dive_center_id`) REFERENCES dive_center(`id`),
    PRIMARY KEY (`id`)
  );

  create TABLE `stay` (
      `id` bigint(20) NOT NULL,
      `dive_center_id` bigint(20) NOT NULL,
      `guest_id` bigint(20) NOT NULL,
      `first_name` varchar(400) NOT NULL,
      `last_name` varchar(400) NOT NULL,
      `box_number` varchar(400) NOT NULL,
      `birth_date` DATE NOT NULL,
      `nationality` varchar(400) NOT NULL,
      `country` varchar(400) NOT NULL,
      `city` varchar(400) NOT NULL,
      `postcode` varchar(400) NOT NULL,
      `street` varchar(400) NOT NULL,
      `email` varchar(400) NOT NULL,
      `phone` varchar(400) NOT NULL,
      `passport_id` varchar(400) NOT NULL,
      `check_in_date` DATE NOT NULL,
      `check_out_date` DATE,
      `arrive_date` DATE NOT NULL,
      `leave_date` DATE NOT NULL,
      `hotel` varchar(400) NOT NULL,
      `room` varchar(400) NOT NULL,
      `last_dive_date` DATE NOT NULL,
      `brevet` varchar(400) NOT NULL,
      `dives_amount` int(10) NOT NULL,
      `nitrox` tinyint(1) NOT NULL,
      `medical_statement` tinyint(1) NOT NULL,
      `active` tinyint(1) NOT NULL,
      `pre_booking` varchar(400),
      FOREIGN KEY (`guest_id`) REFERENCES guest(`id`),
      FOREIGN KEY (`dive_center_id`) REFERENCES dive_center(`id`),
      PRIMARY KEY (`id`)
    );

  create TABLE `equipment_type` (
    `id` bigint(20) NOT NULL,
    `dive_center_id` bigint(20) NOT NULL,
    `type` varchar(100) NOT NULL,
    `description` varchar(400) NOT NULL,
    `price` double precision NOT NULL,
    `active` tinyint(1) NOT NULL DEFAULT '1',
    FOREIGN KEY (`dive_center_id`) REFERENCES dive_center(`id`),
    PRIMARY KEY (`id`)
  );

   create TABLE `equipment` (
     `id` bigint(20) NOT NULL,
     `dive_center_id` bigint(20) NOT NULL,
     `eq_type_id` bigint(20) NOT NULL,
     `serial_number` varchar(100) NOT NULL,
     `status` varchar(100) NOT NULL,
     FOREIGN KEY (`eq_type_id`) REFERENCES equipment_type(`id`),
     FOREIGN KEY (`dive_center_id`) REFERENCES dive_center(`id`),
     PRIMARY KEY (`id`)
   );

   create TABLE `loan` (
     `id` bigint(20) NOT NULL,
     `dive_center_id` bigint(20) NOT NULL,
     `stay_id` bigint(20) NOT NULL,
     `equipment_id` bigint(20) NOT NULL,
     `date_out` DATE NOT NULL,
     `date_return` DATE,
     `price` double precision NOT NULL,
     FOREIGN KEY (`stay_id`) REFERENCES stay(`id`),
     FOREIGN KEY (`equipment_id`) REFERENCES equipment(`id`),
     FOREIGN KEY (`dive_center_id`) REFERENCES dive_center(`id`),
     PRIMARY KEY (`id`)
   );

  create TABLE `box_management`(
    `id` bigint(20) NOT NULL,
    `dive_center_id` bigint(20) NOT NULL,
    `box_number` VARCHAR(400) NOT NULL,
    UNIQUE (box_number,dive_center_id),
    FOREIGN KEY (`dive_center_id`) REFERENCES dive_center(`id`),
    PRIMARY KEY (`id`)
  );

  create TABLE `user`(
      `id` bigint(20) NOT NULL,
      `dive_center_id` bigint(20) NOT NULL,
      `name` VARCHAR(200) NOT NULL,
      `login` VARCHAR(200) NOT NULL,
      `password` VARCHAR(200) NOT NULL,
      UNIQUE (login),
      FOREIGN KEY (`dive_center_id`) REFERENCES dive_center(`id`),
      PRIMARY KEY (`id`)
    );


  create TABLE `id_gen`(
    `id_value` INTEGER NOT NULL,
    `id_name` VARCHAR(24) NOT NULL,
    PRIMARY KEY (`id_name`)
  );


