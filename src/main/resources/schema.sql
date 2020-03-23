DROP TABLE IF EXISTS `guest`;
DROP TABLE IF EXISTS `box`;

CREATE TABLE `box` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);

CREATE TABLE `guest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `box_id` bigint(20) DEFAULT NULL,
  `checked_in` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `guest_fk` FOREIGN KEY (`box_id`) REFERENCES `box` (`id`)
);