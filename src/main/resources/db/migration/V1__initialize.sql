CREATE TABLE `products` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `price` INTEGER NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `products` (`title`, `price`)
VALUES
('Milk1', 60),
('Cheese1', 80),
('Bread1', 25),
('Chocolate1', 100),
('Meat1', 250),
('Milk2', 50),
('Cheese2', 70),
('Bread2', 35),
('Chocolate2', 90),
('Meat2', 260),
('Milk3', 70),
('Cheese3', 90),
('Bread3', 15),
('Chocolate3', 110),
('Meat3', 150),
('Milk4', 55),
('Cheese4', 95),
('Bread4', 55),
('Chocolate4', 105),
('Meat4', 380);
