CREATE DATABASE "Library123"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Ukrainian_Ukraine.1251'
    LC_CTYPE = 'Ukrainian_Ukraine.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE public."Books"
(
    "Id" integer NOT NULL,
    "Name" "char"[] NOT NULL,
    "Author" "char"[] NOT NULL,
    "Year" numeric NOT NULL,
    "Genre" "char"[] NOT NULL,
    "Quantity" "char"[] NOT NULL,
    CONSTRAINT "Books_pkey" PRIMARY KEY ("Id")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Books"
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