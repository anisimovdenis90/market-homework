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

CREATE TABLE `categories` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `products` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `price` INTEGER NOT NULL,
    `category_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY(`category_id`) REFERENCES `categories`(`id`)
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

INSERT INTO `categories` (`title`)
VALUES
('Продукты питания'),
('Строительные материалы'),
('Бытовая техника');

INSERT INTO `products` (`title`, `price`, `category_id`)
VALUES
('Milk1', 60, 1),
('Cheese1', 80, 1),
('Bread1', 25, 1),
('Chocolate1', 100, 1),
('Meat1', 250, 1),
('Milk2', 50, 1),
('Cheese2', 70, 1),
('Bread2', 35, 1),
('Chocolate2', 90, 1),
('Meat2', 260, 1),
('Milk3', 70, 1),
('Cheese3', 90, 1),
('Bread3', 15, 1),
('Chocolate3', 110, 1),
('Meat3', 150, 1),
('Milk4', 55, 1),
('Cheese4', 95, 1),
('Bread4', 55, 1),
('Chocolate4', 105, 1),
('Meat4', 380, 1),
('Toaster1', 900, 3),
('Toaster2', 980, 3),
('Toaster3', 1200, 3),
('Hammer1', 450, 2),
('Hammer2', 670, 2);
