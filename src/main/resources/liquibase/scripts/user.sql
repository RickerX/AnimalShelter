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

-- changeset oxana:4

INSERT INTO shelter(shelter_id,title,address,phone_number,schedule,shelter_type)
VALUES (1,'Кошки','г.Астана ул.Гагарина д.1','+8123456789','пн-пт 08:00-20:00 сб-вс 08:00-18:00',0),
       (2,'Собаки','г.Астана ул.Звездная 2','+9874563214','пн-пт 08:00-20:00 сб-вс 08:00-18:00',1);

--changeset oxana:5

INSERT INTO dog_message(tag, text) VALUES ('/addressAndSchedule','Адрес и часы работы приюта для собак: ' || '
' || 'г.Астана ул.Звездная д.2 Часы работы: пн-пт 08:00-20:00 сб-вс 08:00-18:00');

INSERT INTO cat_message(tag, text) VALUES ('/addressAndSchedule','Адрес и часы работы приюта для кошек: ' || '
' || 'г.Астана ул.Гагарина д.1 Часы работы: пн-пт 08:00-20:00 сб-вс 08:00-18:00');



