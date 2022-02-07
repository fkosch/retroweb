CREATE TABLE IF NOT EXISTS projects (
  id SERIAL PRIMARY KEY,
  name varchar(255) NOT NULL,
  isactive SMALLINT NOT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,
  userpw varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  isadmin smallint NOT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS retros (
  id SERIAL PRIMARY KEY,
  name varchar(255) NOT NULL,
  scheduled timestamp NOT NULL,
  isactive smallint DEFAULT NULL,
  project_id bigint NOT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp DEFAULT CURRENT_TIMESTAMP,
  
  CONSTRAINT fk_retros_projects
      FOREIGN KEY(project_id) 
	  REFERENCES projects (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS itemtypes (
  id SERIAL PRIMARY KEY,
  itemtype varchar(255) NOT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS retroitems (
  id SERIAL PRIMARY KEY,
  content varchar(255) NOT NULL,
  retro_id bigint NOT NULL,
  user_id bigint NOT NULL,
  itemtype_id bigint NOT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp DEFAULT CURRENT_TIMESTAMP,
  
  CONSTRAINT fk_retroitems_retros
      FOREIGN KEY(retro_id) 
	  REFERENCES retros (id)
	  ON DELETE CASCADE,
  CONSTRAINT fk_retroitems_users
      FOREIGN KEY(user_id) 
	  REFERENCES users (id)
	  ON DELETE CASCADE,
  CONSTRAINT fk_retroitems_itemtypes
      FOREIGN KEY(itemtype_id) 
	  REFERENCES itemtypes (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS actionitems (
  id SERIAL PRIMARY KEY,
  name varchar(50) NOT NULL,
  content varchar(50) NOT NULL,
  duedate timestamp NOT NULL,
  isclosed smallint NOT NULL,
  retro_id bigint NOT NULL,
  user_id bigint DEFAULT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp DEFAULT CURRENT_TIMESTAMP,
  
  CONSTRAINT fk_actionitems_retros
      FOREIGN KEY(retro_id) 
	  REFERENCES retros (id)
	  ON DELETE CASCADE,
  CONSTRAINT fk_actionitems_users
      FOREIGN KEY(user_id) 
	  REFERENCES users (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS projects_users (
  project_id bigint NOT NULL REFERENCES projects (id) ON UPDATE CASCADE ON DELETE CASCADE,
  user_id bigint NOT NULL REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_users_projects_users PRIMARY KEY (project_id,user_id)
);

INSERT INTO users (id, username, userpw, email, isadmin, created, updated) VALUES
	(1, 'fritz', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'fritz@test.de', 1, '2018-07-17 15:38:58', '2018-07-17 15:38:58'),
	(2, 'anton', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'anton@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(3, 'thomas', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'thomas@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(4, 'andreas', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'andreas@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(5, 'susanne', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'susanne@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(6, 'nadine', '$2a$12$djCFq.nuALsoNE5ZlqwBauHMmblaAmoiYSXVbQYGbfQBuL30MGI0a', 'nadine@test.de', 0, '2018-08-10 15:38:58', '2018-08-10 15:38:58');

INSERT INTO itemtypes (id, itemtype, created, updated) VALUES
	(1, 'continue', '2018-10-02 09:38:58', '2018-10-02 09:38:58'),
	(2, 'start', '2018-10-02 09:38:58', '2018-10-02 09:38:58'),
	(3, 'stop', '2018-10-02 09:38:58', '2018-10-02 09:38:58');

INSERT INTO projects (id, name, isactive, created, updated) VALUES
	(1, 'project1', 0, '2018-07-17 15:38:58', '2018-07-17 15:38:58'),
	(2, 'project2', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(3, 'project3', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(4, 'project4', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(5, 'project5', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(6, 'project6', 1, '2018-08-10 15:38:58', '2018-08-10 15:38:58');

INSERT INTO retros (id, name, scheduled, isactive, project_id, created, updated) VALUES
	(1, 'retro1', '2018-08-11 14:30:00', 0, 1, '2018-07-17 15:38:58', '2018-07-17 15:38:58'),
	(2, 'retro2', '2018-08-17 15:30:00', 0, 2, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(3, 'retro3', '2018-09-05 15:00:00', 1, 2, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(4, 'retro4', '2018-09-17 15:00:00', 0, 3, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(5, 'retro5', '2018-09-18 15:00:00', 0, 4, '2018-08-10 15:38:58', '2018-08-10 15:38:58'),
	(6, 'retro6', '2018-09-19 15:00:00', 0, 5, '2018-08-10 15:38:58', '2018-08-10 15:38:58');

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
