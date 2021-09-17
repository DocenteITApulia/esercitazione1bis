CREATE TABLE users
(
 nome varchar(100) NOT NULL,
  cognome varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  userId SERIAL PRIMARY KEY,
 email varchar(100) DEFAULT NULL
);
