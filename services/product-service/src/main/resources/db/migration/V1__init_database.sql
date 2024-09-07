create table IF NOT EXISTS category(

    id INTEGER not null primary key,
    name VARCHAR(255) not null,
    description VARCHAR(255)
);

create table IF NOT EXISTS product(

    id INTEGER not null primary key,
    name VARCHAR(255) not null,
    description VARCHAR(255),
    price DECIMAL(10, 2) not null,
    quantity INTEGER not null,
    category_id INTEGER,
    foreign key (category_id) references category(id)
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;