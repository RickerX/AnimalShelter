-- liquibase formatted sql

-- changeset oxana:1
CREATE TABLE animal (
                       id SERIAL,
                       nickname TEXT NOT NULL ,
                       breed TEXT NOT NULL ,
                       age FLOAT NOT NULL ,
                       weight FLOAT NOT NULL ,
                       color TEXT NOT NULL ,
                       gender TEXT NOT NULL
);

-- changeset oxana:2
CREATE TABLE shelter_user(
    chat_id BIGINT NOT NULL PRIMARY KEY ,
    shelter_type SMALLINT NOT NULL ,
    user_status SMALLINT NOT NULL ,
    phone_number VARCHAR(20) NOT NULL ,
    username VARCHAR(20) NOT NULL );

-- changeset oxana:3

INSERT INTO shelter_user(chat_id, shelter_type, user_status, phone_number, username)
VALUES (DEFAULT,0,0,DEFAULT,DEFAULT);


