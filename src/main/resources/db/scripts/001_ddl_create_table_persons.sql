CREATE TABLE IF NOT EXISTS persons (
    id SERIAL PRIMARY KEY NOT NULL ,
    login TEXT UNIQUE NOT NULL ,
    password TEXT NOT NULL
);

COMMENT ON TABLE persons IS 'Пользователи';
COMMENT ON COLUMN persons.id IS 'Идентификатор пользователя';
COMMENT ON COLUMN persons.login IS 'Логин';
COMMENT ON COLUMN persons.password IS 'Пароль';