CREATE DATABASE "Library123"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Ukrainian_Ukraine.1251'
    LC_CTYPE = 'Ukrainian_Ukraine.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE books
(
    "Id" integer NOT NULL,
    "Name" VARCHAR(50) NOT NULL,
    "Author" VARCHAR(50) NOT NULL,
    "Year" VARCHAR(4) NOT NULL,
    "Quantity" INT NOT NULL,
    "Genre" VARCHAR NOT NULL,

    CONSTRAINT "Books_pkey" PRIMARY KEY ("Id")
);


ALTER TABLE books
    OWNER to postgres;
	
	CREATE TABLE public.users
(
    id integer NOT NULL,
    first_name "char"[] NOT NULL,
    last_name "char"[] NOT NULL,
    passport "char"[],
    CONSTRAINT "Users_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;
INSERT INTO books VALUES (0,'Посторонний','Альбер Камю','1234',3,'повесть');

INSERT INTO books VALUES (1,'В поисках утраченного времени','Марсель Пруст','1927',2,'полуавтобиографический');

INSERT INTO books VALUES (2,'Процесс','Франц Кафка','1925',1,'роман');

INSERT INTO books VALUES (3,'Маленький принц','Антуан де Сент Экзюпери','1943',2,'повесть-сказка');

INSERT INTO books VALUES (4,'Гроздья гнева','Джон Стейнбек','1939',4,'роман');

INSERT INTO books VALUES (5,'По ком звонит колокол','Эрнест Хемингуэй','1940',2,'роман');
