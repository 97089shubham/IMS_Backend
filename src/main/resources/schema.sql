-- schema.sql
CREATE TABLE IF NOT EXISTS skus (
    id VARCHAR(100) PRIMARY KEY,
    sku VARCHAR(255),
    uom VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS location (
    id INT PRIMARY KEY,
    city_name VARCHAR(255),
    state VARCHAR(255)
);
