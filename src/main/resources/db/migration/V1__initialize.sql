create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('SOMETHING');

insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2);

create table categories (
    id                      bigserial primary key,
    name                    varchar(255)
);

create table products (
    id                      bigserial primary key,
    category_id             bigint references categories(id),
    title                   varchar(255),
    price                   int
);

create table orders (
    id                      bigserial primary key,
    user_id                 bigint references users(id),
    price                   int,
    address                 varchar(1000)
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   int,
    price_per_product       int,
    quantity                int
);

insert into categories (name)
values
('Молочка'),
('Выпечка'),
('Вода'),
('Бакалея');

insert into products (category_id, title, price)
values
(2, 'Bread1', 20),
(2, 'Bread2', 21),
(2, 'Bread3', 22),
(2, 'Bread4', 23),
(2, 'Bread5', 24),
(1, 'Milk1', 52),
(1, 'Milk2', 60),
(1, 'Milk3', 70),
(1, 'Milk4', 80),
(1, 'Milk5', 90),
(3, 'Water1', 30),
(3, 'Water2', 40),
(3, 'Water3', 50),
(3, 'Water4', 60),
(3, 'Water5', 70),
(4, 'Flour1', 30),
(4, 'Flour2', 40),
(4, 'Flour3', 50),
(4, 'Flour4', 60),
(4, 'Flour5', 40),
(1, 'Butter1', 100),
(1, 'Butter2', 120),
(1, 'Butter3', 140),
(1, 'Butter4', 160),
(1, 'Butter5', 200);
