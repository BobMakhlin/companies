CREATE TABLE company
(
    id          UUID PRIMARY KEY,
    status      VARCHAR(50) NOT NULL,
    modified_at TIMESTAMP   NOT NULL,
    deleted     BOOLEAN DEFAULT FALSE,
    "version"   INT     DEFAULT 0 NULL
);

CREATE TABLE company_address
(
    id         BIGSERIAL PRIMARY KEY,
    company_id UUID REFERENCES company (id),
    category   VARCHAR(100) NOT NULL,
    country    VARCHAR(100),
    city       VARCHAR(100),
    zip        VARCHAR(20),
    street     VARCHAR(255)
);

CREATE TABLE company_names
(
    id          BIGSERIAL PRIMARY KEY,
    company_id  UUID REFERENCES company (id),
    name        VARCHAR(100) NOT NULL,
    modified_at TIMESTAMP    NOT NULL
)
