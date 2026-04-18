CREATE TABLE clothes_inventory (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    size INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    material VARCHAR(100),
    quantity INT DEFAULT 1,
    length INT,
    sleeve_type VARCHAR(50),
    has_print BOOLEAN,
    fit VARCHAR(50)
);