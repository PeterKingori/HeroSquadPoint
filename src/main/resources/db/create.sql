SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS heroes (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  age INT,
  superpower VARCHAR,
  weakness VARCHAR,
  squadId INT
);

CREATE TABLE IF NOT EXISTS squads (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    cause VARCHAR
);