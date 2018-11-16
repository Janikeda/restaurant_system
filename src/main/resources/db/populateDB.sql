INSERT INTO users (name, email, password) VALUES
('User', 'user@yandex.ru', 'password'),
('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name) VALUES ('BurgerKing'), ('KFS'), ('McDonalds'), ('SubWay');

INSERT INTO votes (DATE_TIME) VALUES;

INSERT INTO dishes (description, price, dish_date_time) VALUES;

