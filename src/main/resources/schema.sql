DROP TABLE IF EXISTS DRIVER;
  
CREATE TABLE DRIVER (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  phone_number BIGINT(11) NOT NULL,
  license_number VARCHAR(250) DEFAULT NULL,
  car_number VARCHAR(250) DEFAULT NULL,
  latitude DOUBLE DEFAULT NULL,
  longitude DOUBLE DEFAULT NULL,
  CONSTRAINT driver_email_uk UNIQUE (email),
  CONSTRAINT driver_ph_uk UNIQUE (phone_number),
  CONSTRAINT driver_ln_uk UNIQUE (license_number),
  CONSTRAINT driver_cn_uk UNIQUE (car_number)
);