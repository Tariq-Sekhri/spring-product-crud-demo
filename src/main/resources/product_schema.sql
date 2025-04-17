CREATE TABLE product (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255),
	category varchar(255),
	qty INT
);

INSERT INTO product (name, category, qty) VALUES 
('Laptop', 'tech', 10),
('Smartphone', 'tech', 25),
('Jeans', 'clothes', 50),
('Pizza', 'food', 15),
('Car', 'cars', 3),
('T-Shirt', 'clothes', 40),
('Tablet', 'tech', 8),
('Burger', 'food', 20);
