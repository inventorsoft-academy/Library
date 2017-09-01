CREATE TABLE books
(
  id         SERIAL PRIMARY KEY,
  user_id    INT,
  name       VARCHAR(100) NOT NULL,
  author     VARCHAR(100) NOT NULL,
  year       VARCHAR(4)   NOT NULL,
  quantity   INT          NOT NULL,
  genre      VARCHAR      NOT NULL,
  rent_start VARCHAR(20),
  rent_end   VARCHAR(20)
);


CREATE TABLE users
(
  id         SERIAL PRIMARY KEY,
  first_name VARCHAR(40) NOT NULL,
  last_name  VARCHAR(40) NOT NULL,
  passport   VARCHAR(10) NOT NULL
);


INSERT INTO books
VALUES (1, NULL, 'В поисках утраченного времени', 'Марсель Пруст', '1927', 2, 'полуавтобиографический', NULL, NULL);
INSERT INTO books VALUES (2, NULL, 'Процесс', 'Франц Кафка', '1925', 1, 'роман', NULL, NULL);
INSERT INTO books
VALUES (3, NULL, 'Маленький принц', 'Антуан де Сент Экзюпери', '1943', 2, 'повесть-сказка', NULL, NULL);
INSERT INTO books VALUES (4, NULL, 'Гроздья гнева', 'Джон Стейнбек', '1939', 4, 'роман', NULL, NULL);
INSERT INTO books VALUES (5, NULL, 'По ком звонит колокол', 'Эрнест Хемингуэй', '1940', 2, 'роман', NULL, NULL);

INSERT INTO users VALUES (1, 'Petro', 'Petrenko', 'FH-863123');
INSERT INTO users VALUES (2, 'Ivan', 'Ivanenko', 'FK-973123');
INSERT INTO users VALUES (3, 'Andriy', 'Andrienko', 'FG-343123');