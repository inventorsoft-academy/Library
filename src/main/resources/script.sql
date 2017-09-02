CREATE TABLE books
(
  id         SERIAL PRIMARY KEY,
  user_id    INT,
  name       VARCHAR(100) NOT NULL,
  author     VARCHAR(100) NOT NULL,
  year       VARCHAR(4)   NOT NULL,
  genre      VARCHAR(50)  NOT NULL,
  rent_start VARCHAR(50),
  rent_end   VARCHAR(50)
);


CREATE TABLE users
(
  id         SERIAL      NOT NULL,
  first_name VARCHAR(40) NOT NULL,
  last_name  VARCHAR(40) NOT NULL,
  passport   VARCHAR(10) NOT NULL,
  PRIMARY KEY (id)
);


INSERT INTO books
VALUES (1, NULL, 'В поисках утраченного времени', 'Марсель Пруст', '1927', 'полуавтобиографический', NULL, NULL);
INSERT INTO books VALUES (2, NULL, 'Процесс', 'Франц Кафка', '1925', 'роман', NULL, NULL);
INSERT INTO books
VALUES (3, NULL, 'Маленький принц', 'Антуан де Сент Экзюпери', '1943', 'повесть-сказка', NULL, NULL);
INSERT INTO books VALUES (4, NULL, 'Гроздья гнева', 'Джон Стейнбек', '1939', 'роман', NULL, NULL);
INSERT INTO books VALUES (5, NULL, 'По ком звонит колокол', 'Эрнест Хемингуэй', '1940', 'роман', NULL, NULL);

INSERT INTO users VALUES (1, 'Petro', 'Petrenko', 'FH-863123');
INSERT INTO users VALUES (2, 'Ivan', 'Ivanenko', 'FK-973123');
INSERT INTO users VALUES (3, 'Andriy', 'Andrienko', 'FG-343123');