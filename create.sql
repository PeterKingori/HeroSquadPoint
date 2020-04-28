CREATE DATABASE herosquad;
\c herosquad;

CREATE TABLE heroes (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  age INTEGER,
  superpower VARCHAR,
  weakness VARCHAR,
  squadId INTEGER
);

CREATE TABLE squads (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    cause VARCHAR,
    maxSize INT
);

CREATE DATABASE herosquad_test WITH TEMPLATE herosquad;