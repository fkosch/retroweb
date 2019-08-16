DROP DATABASE IF EXISTS retroweb_app;
CREATE DATABASE IF NOT EXISTS retroweb_app;

CREATE TABLE IF NOT EXISTS `retroweb_app`.`projects` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `isactive` tinyint(1) unsigned NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `retroweb_app`.`users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `userpw` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `isadmin` tinyint(1) unsigned zerofill NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `retroweb_app`.`retros` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `scheduled` datetime NOT NULL,
  `isactive` tinyint(1) unsigned DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_retros_projects` (`project_id`),
  CONSTRAINT `FK_retros_projects` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `retroweb_app`.`itemtypes` (
  `id` bigint(20) NOT NULL,
  `itemtype` varchar(255) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `retroweb_app`.`retroitems` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `retro_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `itemtype_id` bigint(20) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_retroitems_retros` (`retro_id`),
  KEY `FK_retroitems_users` (`user_id`),
  KEY `FK_retroitems_itemtypes` (`itemtype_id`),
  CONSTRAINT `FK_retroitems_retros` FOREIGN KEY (`retro_id`) REFERENCES `retros` (`id`),
  CONSTRAINT `FK_retroitems_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `retroweb_app`.`actionitems` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `content` varchar(50) NOT NULL,
  `duedate` datetime NOT NULL,
  `isclosed` tinyint(1) unsigned zerofill NOT NULL,
  `retro_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_actionitems_retros` (`retro_id`),
  KEY `FK_actionitems_users` (`user_id`),
  CONSTRAINT `FK_actionitems_retros` FOREIGN KEY (`retro_id`) REFERENCES `retros` (`id`),
  CONSTRAINT `FK_actionitems_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `retroweb_app`.`projects_users` (
  `project_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `isprojectadmin` tinyint(1) unsigned zerofill,
  PRIMARY KEY (`project_id`,`user_id`),
  KEY `FK_users_projects_users` (`user_id`),
  CONSTRAINT `FK_users_projects_projects` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK_users_projects_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO retroweb_app.users (id, username, userpw, email, isadmin, created, updated) VALUES
	(1, 'fritz', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'fritz@test.de', 1, '2018-07-17 15:38:58', '2018-07-17 15:38:58'),
	(2, 'anton', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'anton@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(3, 'thomas', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'thomas@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(4, 'andreas', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'andreas@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(5, 'susanne', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'susanne@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(6, 'nadine', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'nadine@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58');

INSERT INTO retroweb_app.itemtypes (id, itemtype, created, updated) VALUES
	(1, 'continue', '2018-10-02 09:38:58', '2018-10-02 09:38:58'),
	(2, 'start', '2018-10-02 09:38:58', '2018-10-02 09:38:58'),
	(3, 'stop', '2018-10-02 09:38:58', '2018-10-02 09:38:58');

INSERT INTO retroweb_app.projects (id, name, isactive, created, updated) VALUES
	(1, 'project1', 0, '2018-07-17 15:38:58', '2018-07-17 15:38:58'),
	(2, 'project2', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(3, 'project3', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(4, 'project4', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(5, 'project5', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(6, 'project6', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58');

INSERT INTO retroweb_app.retros (id, name, scheduled, isactive, project_id, created, updated) VALUES
	(1, 'retro1', '2018-08-11 14:30:00', 0, 1, '2018-07-17 15:38:58', '2018-07-17 15:38:58'),
	(2, 'retro2', '2018-08-17 15:30:00', 0, 2, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(3, 'retro3', '2018-09-05 15:00:00', 1, 2, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(4, 'retro4', '2018-09-17 15:00:00', 0, 3, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(5, 'retro5', '2018-09-18 15:00:00', 0, 4, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(6, 'retro6', '2018-09-19 15:00:00', 0, 5, '2018-08-10 15:38:58', '2018-08-10 15:38:58');

INSERT INTO retroweb_app.projects_users (project_id, user_id) VALUES
  (1,1),
  (2,1),
  (3,1),
  (4,1),
  (5,1),
  (6,1),
  (1,2),
  (2,2),
  (2,4),
  (3,3);
