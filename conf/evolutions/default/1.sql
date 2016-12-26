# DC schema
 
# --- !Ups


CREATE TABLE "MY_USER"(
    "ID" SERIAL PRIMARY KEY,
    "FIRST_NAME" varchar(255) NOT NULL,
    "LAST_NAME" varchar(255) NOT NULL,
    "EMAIL" varchar(255) NOT NULL,
    "PASSWORD_HASH" text NOT NULL,
    "SALT1" varchar(255) NOT NULL,
    "SALT2" integer NOT NULL
);



CREATE TABLE "LEAFLET" (
    "ID" integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    "TITLE" varchar(255) NOT NULL,
    "IMG" text NOT NULL,
    "PRICE" number NOT NULL,
    "FORMAT" varchar(255) NOT NULL,
    "CATHEGORY" varchar(255) NOT NULL,
    "STICKER" varchar(255) NOT NULL,
    "DESCRIPTION" varchar(255) NOT NULL
);





 
# --- !Downs

DROP TABLE "MY_USER";
DROP TABLE "LEAFLET";
