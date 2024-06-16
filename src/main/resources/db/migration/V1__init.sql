CREATE TABLE IF NOT EXISTS patient (
    id SERIAL PRIMARY KEY,
    name VARCHAR(55),
    last_name VARCHAR(55),
    age TIMESTAMP,
    date_diagnosis DATE,
    address VARCHAR(55),
    stage VARCHAR(55)
    );

CREATE TABLE IF NOT EXISTS alarm (
    id SERIAL PRIMARY KEY,
    title VARCHAR(75),
    time TIME,
    repeat BOOLEAN,
    date DATE,
    patient_id INT,
    FOREIGN KEY (patient_id) REFERENCES patient(id)
    );

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(80) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    locked BOOLEAN NOT NULL,
    disabled BOOLEAN NOT NULL,
    name VARCHAR(50),
    last_name VARCHAR(50),
    role VARCHAR(20) NOT NULL, -- Puede ser 'admin', 'caregiver', etc.
    relationship VARCHAR(55),
    patient_id INT,
    FOREIGN KEY (patient_id) REFERENCES patient(id)
    );

CREATE TABLE IF NOT EXISTS card (
    id SERIAL PRIMARY KEY,
    date_time TIMESTAMP,
    hour TIME,
    patient_id INT,
    card_uid VARCHAR(15) UNIQUE,
    FOREIGN KEY (patient_id) REFERENCES patient(id)
    );

CREATE TABLE IF NOT EXISTS cardname (
    id SERIAL PRIMARY KEY,
    card_uid VARCHAR(15) UNIQUE,
    card_id INT,
    FOREIGN KEY (card_id) REFERENCES card(id)
    );



CREATE TABLE IF NOT EXISTS reminders (
    id SERIAL PRIMARY KEY,
    title VARCHAR(50),
    description VARCHAR(75),
    date DATE,
    start_time TIME,
    end_time TIME,
    status VARCHAR(75),
    repeat BOOLEAN,
    patiente VARCHAR(75),
    user_id INT,
    patient_id INT,
    card_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (card_id) REFERENCES card(id)
    );

CREATE TABLE IF NOT EXISTS interactions (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200),
    date_time TIMESTAMP,
    hour TIME,
    card_id INT,
    FOREIGN KEY (card_id) REFERENCES card(id)
    );

CREATE TABLE IF NOT EXISTS configurations (
    id SERIAL PRIMARY KEY,
    volume INT,
    alert_tones VARCHAR(55),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS role (
    id SERIAL PRIMARY KEY,
    role VARCHAR(25) NOT NULL,
    user_id INTEGER NOT NULL,
    UNIQUE (role, user_id),
    FOREIGN KEY (user_id) REFERENCES users(id)
    );
