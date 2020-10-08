CREATE TABLE `users` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL,
    `password` VARCHAR(80) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `roles` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `users_roles` (
    `user_id` BIGINT UNSIGNED NOT NULL,
    `role_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`)
);

INSERT INTO `roles` (`name`)
VALUES
('ROLE_USER'),
('ROLE_ADMIN'),
('SOMETHING');

INSERT INTO `users` (`username`, `password`, `email`)
VALUES
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES
(1, 1),
(1, 2);

CREATE TABLE `products` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `price` INTEGER NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `orders` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT UNSIGNED NOT NULL,
    `price` INTEGER NOT NULL,
    `address` VARCHAR(1000) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);

CREATE TABLE `order_items` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT UNSIGNED NOT NULL,
    `order_id` BIGINT UNSIGNED NOT NULL,
    `price_per_product` INTEGER NOT NULL,
    `price` INTEGER NOT NULL,
    `quantity` INTEGER NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`product_id`) REFERENCES `products`(`id`),
    FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`)
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
