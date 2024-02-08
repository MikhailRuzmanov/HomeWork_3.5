CREATE TABLE Person (
    id SERIAL PRIMARY KEY,
    name Text,
    Age INTEGER,
    license BOOLEAN,

);

CREATE TABLE Car(
    id SERIAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    cost NUMERIC(9,2)
    );
CREATE TABLE PersonCar(
    car_id INTEGER REFERENCES Car(id)
    person_id INTEGER REFERENCES Person(id)
);
