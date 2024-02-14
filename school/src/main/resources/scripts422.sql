CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name Text,
    Age INTEGER,
    license BOOLEAN,

);

CREATE TABLE car(
    id SERIAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    cost NUMERIC(9,2)
    );
CREATE TABLE personCar(
    car_id INTEGER REFERENCES Car(id)
    person_id INTEGER REFERENCES Person(id)
);
