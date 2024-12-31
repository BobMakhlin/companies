-- Dictionaries.

CREATE TABLE industry
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE address_category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Company and addresses.

CREATE TABLE company
(
    id                  UUID PRIMARY KEY,
    name                VARCHAR(100)                 NOT NULL,
    registration_number VARCHAR(100)                 NOT NULL UNIQUE,
    email               VARCHAR(255)                 NOT NULL,
    phone               VARCHAR(20)                  NOT NULL,
    fax                 VARCHAR(20) NULL,
    industry_id         INT REFERENCES industry (id) NOT NULL,
    status              VARCHAR(50)                  NOT NULL,
    created_at          TIMESTAMP                    NOT NULL,
    modified_at         TIMESTAMP                    NOT NULL
);

CREATE INDEX idx_company_industry_id ON company (industry_id);

CREATE TABLE company_address
(
    id          BIGSERIAL PRIMARY KEY,
    company_id  UUID REFERENCES company (id),
    category_id INT REFERENCES address_category (id),
    country     VARCHAR(100) NOT NULL,
    city        VARCHAR(100) NOT NULL,
    zip         VARCHAR(20)  NOT NULL,
    street      VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX uk_company_address ON company_address (company_id, country, city, zip, street, category_id);

-- Fill dictionaries.

INSERT INTO industry("name")
VALUES ('Technology'),
       ('Finance'),
       ('Healthcare'),
       ('Retail'),
       ('Manufacturing'),
       ('Education'),
       ('Real Estate'),
       ('Transportation'),
       ('Telecommunications'),
       ('Energy');

INSERT INTO address_category("name")
VALUES ('Headquarters'),
       ('Warehouse'),
       ('Branch Office'),
       ('Distribution Center');
