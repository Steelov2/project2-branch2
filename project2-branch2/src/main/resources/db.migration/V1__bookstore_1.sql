
create sequence if not exists author_sequence start 1 increment 1;
create sequence if not exists book_sequence start 1 increment 1;
create sequence if not exists genre_sequence start 1 increment 1;
create sequence if not exists order_sequence start 1 increment 1;
create sequence if not exists publisher_sequence start 1 increment 1;
create sequence if not exists user_sequence start 1 increment 1;


create table if not exists author  (
                        id int8 not null,
                        date_of_birth date,
                        name varchar(255),
                        patronymic varchar(255),
                        surname varchar(255),
                        primary key (id)
);


create table if not exists author_books (
                              book_id int8 not null,
                              author_id int8 not null
);


create table if not exists author_genre (
                              author_id int8 not null,
                              genre_id int8 not null,
                              primary key (author_id, genre_id)
);


create table if not exists book (
                      id int8 not null,
                      is_in_stock boolean,
                      name varchar(255),
                      number_of_pages int4 not null,
                      price int4 not null,
                      year_of_issue date,
                      publisher_id int8,
                      primary key (id)
);


create table if not exists book_genre (
                            book_id int8 not null,
                            genre_id int8 not null
);


create table if not exists genre (
                       id int8 not null,
                       name varchar(255),
                       primary key (id)
);


create table if not exists order_book (
                            order_id int8 not null,
                            book_id int8 not null
);


create table if not exists orders (
                        id int8 not null,
                        created_at date,
                        status varchar(255),
                        user_id int8 not null,
                        primary key (id)
);


create table if not exists publisher (
                           id int8 not null,
                           name varchar(255),
                           primary key (id)
);


create table if not exists users (
                       id int8 not null,
                       is_blocked boolean,
                       password varchar(255) not null,
                       role varchar(255),
                       username varchar(255) unique not null,
                       primary key (id)
);




alter table author_books
    add constraint author_books_fk
        foreign key (author_id)
            references author;


alter table author_books
    add constraint book_author_fk
        foreign key (book_id)
            references book;


alter table author_genre
    add constraint author_genre_fk
        foreign key (genre_id)
            references genre;


alter table author_genre
    add constraint genre_author_fk
        foreign key (author_id)
            references author;


alter table book
    add constraint book_publisher_fk
        foreign key (publisher_id)
            references publisher;


alter table book_genre
    add constraint book_genre_fk
        foreign key (genre_id)
            references genre;


alter table book_genre
    add constraint genre_book_fk
        foreign key (book_id)
            references book;


alter table order_book
    add constraint order_book_fk
        foreign key (book_id)
            references book;


alter table order_book
    add constraint book_order_fk
        foreign key (order_id)
            references orders;


alter table orders
    add constraint order_user_fk
        foreign key (user_id)
            references users;