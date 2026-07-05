-- V1__init_schema.sql

-- 1. Roles
CREATE TABLE roles
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP          NOT NULL,
    updated_at TIMESTAMP          NOT NULL
);

-- 2. Users
CREATE TABLE users
(
    id                    UUID PRIMARY KEY,
    email                 VARCHAR(255) UNIQUE NOT NULL,
    password              VARCHAR(255)        NOT NULL,
    first_name            VARCHAR(100)        NOT NULL,
    last_name             VARCHAR(100)        NOT NULL,
    is_enabled            BOOLEAN             NOT NULL DEFAULT FALSE,
    is_account_non_locked BOOLEAN             NOT NULL DEFAULT TRUE,
    created_at            TIMESTAMP           NOT NULL,
    updated_at            TIMESTAMP           NOT NULL
);

-- 3. Junction Table: Users-Roles
CREATE TABLE users_roles
(
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    role_id UUID NOT NULL REFERENCES roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- 4. Tokens (Refresh, Reset, Verification)
CREATE TABLE refresh_tokens
(
    id          UUID PRIMARY KEY,
    user_id     UUID UNIQUE         NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    token       VARCHAR(255) UNIQUE NOT NULL,
    expiry_date TIMESTAMP           NOT NULL,
    created_at  TIMESTAMP           NOT NULL,
    updated_at  TIMESTAMP           NOT NULL
);

CREATE TABLE password_reset_tokens
(
    id          UUID PRIMARY KEY,
    user_id     UUID UNIQUE         NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    token       VARCHAR(255) UNIQUE NOT NULL,
    expiry_date TIMESTAMP           NOT NULL,
    created_at  TIMESTAMP           NOT NULL,
    updated_at  TIMESTAMP           NOT NULL
);

CREATE TABLE verification_tokens
(
    id          UUID PRIMARY KEY,
    user_id     UUID UNIQUE         NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    token       VARCHAR(255) UNIQUE NOT NULL,
    expiry_date TIMESTAMP           NOT NULL,
    created_at  TIMESTAMP           NOT NULL,
    updated_at  TIMESTAMP           NOT NULL
);