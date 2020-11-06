create table profiles (
  id                    bigserial primary key,
  firstname             varchar(255),
  lastname              varchar(255),
  phone                 varchar(255),
  email                 varchar(255),
  birthyear             int,
  gender                varchar(10),
  city                  varchar(255)
);

create table users (
  id                        bigserial,
  username                  varchar(30) not null,
  password                  varchar(80) not null,
  email                     varchar(50) unique,
  profile_id                bigint references profiles (id),
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

insert into profiles (firstname, lastname, phone, email, birthyear, gender, city)
values
('Bob', 'Burnquist', '+18001234567', 'bob@email.com', 2000, 'male', 'New York'),
('Tony', 'Hawk', '+18007894561', 'tony@email.com', 1999, 'male', 'Los Angeles');

insert into users (username, password, email, profile_id)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com', 1),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com', 2);

insert into users_roles (user_id, role_id) values (1, 1), (1, 2), (2, 1), (2, 2);

create table categories (
    id                      bigserial primary key,
    title                   varchar(255)
);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int,
    category_id             bigint references categories (id)
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

insert into categories (title)
values
('Food'),
('Notebook'),
('Smartphone');

insert into products (title, price, category_id)
values
('Bread', 1, 1),
('Samsung V100', 2, 3),
('Acer X1000', 3, 2);
