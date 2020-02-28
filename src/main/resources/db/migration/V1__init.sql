CREATE TABLE projects (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE,
	isActive BOOLEAN NOT NULL,
	created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO projects (name, isactive, created, updated) VALUES
	('project1', 0, '2018-07-17 15:38:58', '2018-07-17 15:38:58'),
	('project2', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('project3', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('project4', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('project5', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('project6', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58');

CREATE TABLE retros (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	scheduled DATETIME NOT NULL,
	isactive BOOLEAN NOT NULL,
	project_id BIGINT NOT NULL,
	foreign key (project_id) references projects(id),
	created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO retros (name, scheduled, isactive, project_id, created, updated) VALUES
	('retro1', '2018-08-11 14:30:00', 0, 1, '2018-07-17 15:38:58', '2018-07-17 15:38:58'),
	('retro2', '2018-08-17 15:30:00', 0, 2, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('retro3', '2018-09-05 15:00:00', 1, 2, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('retro4', '2018-09-17 15:00:00', 0, 3, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('retro5', '2018-09-18 15:00:00', 0, 4, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('retro6', '2018-09-19 15:00:00', 0, 5, '2018-08-10 15:38:58', '2018-08-10 15:38:58');
  
CREATE TABLE users (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(255) NOT NULL UNIQUE,
	userpw VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	isadmin BOOLEAN NOT NULL,
	created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users (username, userpw, email, isadmin, created, updated) VALUES
	('fritz', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'fritz@test.de', 1, '2018-07-17 15:38:58', '2018-07-17 15:38:58'),
	('anton', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'anton@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('thomas', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'thomas@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('andreas', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'andreas@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('susanne', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'susanne@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	('nadine', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'nadine@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58');

CREATE TABLE itemtypes (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	itemtype VARCHAR(255) NOT NULL,
	created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO itemtypes (itemtype, created, updated) VALUES
	('continue', '2018-10-02 09:38:58', '2018-10-02 09:38:58'),
	('start', '2018-10-02 09:38:58', '2018-10-02 09:38:58'),
	('stop', '2018-10-02 09:38:58', '2018-10-02 09:38:58');

	
CREATE TABLE retroItems (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	content VARCHAR(255) NOT NULL,
	itemtype_ID BIGINT NOT NULL,
	retro_id BIGINT,
	user_id BIGINT,
	foreign key (retro_id) references retros(id),
	foreign key (user_id) references users(id),
	foreign key (itemtype_id) references itemtypes(id),
	created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE actionitems (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	content VARCHAR(255) NOT NULL,
	dueDate DATETIME NOT NULL,
	isclosed BOOLEAN NOT NULL,
	retro_id BIGINT,
	foreign key (retro_id) references retros(id),
	user_id BIGINT,
	foreign key (user_id) references users(id),
	created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE projects_users (
	project_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	PRIMARY KEY (project_id,user_id),
	foreign key (project_id) references projects(id),
	foreign key (user_id) references users(id),
	isprojectadmin BOOLEAN
);

INSERT INTO projects_users (project_id, user_id) VALUES
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