CREATE SCHEMA IF NOT EXISTS samurai_test_patients;

CREATE TYPE samurai_test_patients.patient_gender AS ENUM('male', 'female');

CREATE CAST (CHARACTER VARYING AS samurai_test_patients.patient_gender) WITH INOUT AS ASSIGNMENT;

CREATE TABLE IF NOT EXISTS samurai_test_patients.patient(
  id SERIAL PRIMARY KEY,
  patient_name CHARACTER VARYING,
  gender  samurai_test_patients.patient_gender NOT NULL,
  birth_date DATE,
  address CHARACTER VARYING,
  insurance_id CHARACTER VARYING(16) UNIQUE
  );

