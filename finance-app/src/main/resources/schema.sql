CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    balance DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS transaction (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    amount DOUBLE PRECISION,
    timestamp TIMESTAMP
);
