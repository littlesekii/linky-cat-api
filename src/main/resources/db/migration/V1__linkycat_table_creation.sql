CREATE TABLE t_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password varchar(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL
);

CREATE TABLE t_user_email_verification (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    verification_code VARCHAR(6) NOT NULL,
    verified BOOLEAN NOT NULL,
    expires_at TIMESTAMP NOT NULL
);
