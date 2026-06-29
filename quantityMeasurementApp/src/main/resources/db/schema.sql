CREATE TABLE IF NOT EXISTS quantity_measurement (

    id INT AUTO_INCREMENT PRIMARY KEY,

    first_value DOUBLE,

    first_unit VARCHAR(50),

    second_value DOUBLE,

    second_unit VARCHAR(50),

    measurement_type VARCHAR(50),

    operation VARCHAR(30),

    result VARCHAR(100),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);