CREATE DATABASE herosquad;
\c herosquad;
CREATE TABLE heroes (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  age INT,
  superpower VARCHAR,
  weakness VARCHAR,
  squadId INT
);

CREATE TABLE squads (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    cause VARCHAR
);
CREATE DATABASE herosquad_test WITH TEMPLATE herosquad;