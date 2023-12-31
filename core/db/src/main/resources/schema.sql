DROP TABLE IF EXISTS books;
CREATE TABLE IF NOT EXISTS books (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE PRECISION,
    author VARCHAR(255) NOT NULL
);

INSERT INTO books (id, name, description, price, author) VALUES ( RANDOM_UUID(), '1', '1', 1.0, 'mick' );
INSERT INTO books (id, name, description, price, author) VALUES ( RANDOM_UUID(), '2', '2', 2.0, 'mick' );
INSERT INTO books (id, name, description, price, author) VALUES ( RANDOM_UUID(), '3', '3', 3.0, 'mick' );
INSERT INTO books (id, name, description, price, author) VALUES ( RANDOM_UUID(), '4', '4', 4.0, 'mick' );
INSERT INTO books (id, name, description, price, author) VALUES ( RANDOM_UUID(), '5', '5', 5.0, 'mick' );

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
     id UUID PRIMARY KEY NOT NULL,
     login VARCHAR(255) NOT NULL,
     email VARCHAR(255),
     password TEXT NOT NULL,
     registration_date TIMESTAMP NOT NULL,
     last_login_date TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS purchases;
CREATE TABLE IF NOT EXISTS purchases (
    id IDENTITY PRIMARY KEY NOT NULL,
    user_id UUID NOT NULL,
    book_id UUID NOT NULL,
    purchase_date TIMESTAMP NOT NULL
);