SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS PERSON_FILMOGRAPHY;
DROP TABLE IF EXISTS PERSON_INFO;




/* Create Tables */

CREATE TABLE PERSON_FILMOGRAPHY
(
	PF_ID int NOT NULL,
	PF_TITLE varchar(20),
	PF_ORIGINAL_TITLE varchar(80),
	PF_OVERVIEW text,
	PF_POSTER_PATH text,
	PF_RELEASE_DATE varchar(20),
	PF_CHARACTER varchar(20),
	PI_ID int NOT NULL,
	PRIMARY KEY (PF_ID, PI_ID)
);


CREATE TABLE PERSON_INFO
(
	PI_ID int NOT NULL,
	PI_BIOGRAPHY text,
	PI_BIRTHDAY varchar(20),
	PI_GENDER int,
	PI_HOMEPAGE varchar(80),
	PI_PLACE_OF_BIRTH varchar(70),
	PI_PROFILE_PATH text,
	PI_NAME varchar(20),
	PRIMARY KEY (PI_ID)
);



/* Create Foreign Keys */

ALTER TABLE PERSON_FILMOGRAPHY
	ADD FOREIGN KEY (PI_ID)
	REFERENCES PERSON_INFO (PI_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



