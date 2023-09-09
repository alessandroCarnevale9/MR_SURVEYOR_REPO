DROP SCHEMA IF EXISTS MrSurveyorDB;
CREATE SCHEMA MrSurveyorDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE MrSurveyorDB;

DROP TABLE IF EXISTS category;
CREATE TABLE category (
    category_name VARCHAR(255) NOT NULL,
    image_path VARCHAR(255) NOT NULL,
    category_description VARCHAR(255) NOT NULL,
    PRIMARY KEY(category_name)
);
SHOW WARNINGS;

DROP TABLE IF EXISTS subcategory;
CREATE TABLE subcategory (
    subcategory_id INT NOT NULL AUTO_INCREMENT,
    subcategory_name VARCHAR(255) NOT NULL,
    subcategory_description VARCHAR(255) NOT NULL,
    category_name VARCHAR(255) NOT NULL,
    PRIMARY KEY(subcategory_id),
    FOREIGN KEY(category_name) REFERENCES category(category_name) ON UPDATE CASCADE ON DELETE CASCADE
);
SHOW WARNINGS;

DROP TABLE IF EXISTS manager;
CREATE TABLE manager (
    manager_username VARCHAR(50) NOT NULL,
    manager_password VARCHAR(125) NOT NULL,
    manager_role ENUM('order_manager','catalog_manager') NOT NULL,
    PRIMARY KEY(manager_username, manager_role)
);
SHOW WARNINGS;

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_id INT NOT NULL AUTO_INCREMENT,
    product_image_path VARCHAR(255) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    product_price DOUBLE(7,2) NOT NULL,
    product_quantity INT NOT NULL,
    product_description VARCHAR(255) NOT NULL,
    manager_username VARCHAR(50) NOT NULL,
    PRIMARY KEY(product_id),
    FOREIGN KEY(manager_username) REFERENCES manager(manager_username) ON UPDATE CASCADE ON DELETE CASCADE
);
SHOW WARNINGS;

DROP TABLE IF EXISTS has_category_product;
CREATE TABLE has_category_product (
    product_id INT NOT NULL,
    category_name VARCHAR(255) NOT NULL,
    PRIMARY KEY(product_id, category_name),
    FOREIGN KEY(product_id) REFERENCES product(product_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(category_name) REFERENCES category(category_name) ON UPDATE CASCADE ON DELETE CASCADE
);
SHOW WARNINGS;

DROP TABLE IF EXISTS has_subcategory_product;
CREATE TABLE has_subcategory_product (
    product_id INT NOT NULL,
    subcategory_id INT NOT NULL,
    PRIMARY KEY(product_id, subcategory_id),
    FOREIGN KEY(product_id) REFERENCES product(product_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(subcategory_id) REFERENCES subcategory(subcategory_id) ON UPDATE CASCADE ON DELETE CASCADE
);
SHOW WARNINGS;

DROP TABLE IF EXISTS end_user;
CREATE TABLE end_user (
    end_user_id INT NOT NULL AUTO_INCREMENT,
    end_user_name VARCHAR(255) NOT NULL,
    end_user_surname VARCHAR(255) NOT NULL,
    end_user_email VARCHAR(255) NOT NULL UNIQUE,
    end_user_password VARCHAR(255) NOT NULL,
    end_user_region VARCHAR(255),
    end_user_province VARCHAR(255),
    end_user_city VARCHAR(255),
    end_user_street VARCHAR(255),
    end_user_cap CHAR(5),
    end_user_house_number INT,
    PRIMARY KEY(end_user_id)
);
SHOW WARNINGS;

DROP TABLE IF EXISTS credit_card;
CREATE TABLE credit_card (
    credit_card_number CHAR(16) NOT NULL,
    credit_card_expiration_date DATE NOT NULL,
    credit_card_cvc CHAR(3) NOT NULL,
    credit_card_holder VARCHAR(255) NOT NULL,
    costumer_id INT NOT NULL,
    PRIMARY KEY(credit_card_number),
    FOREIGN KEY(costumer_id) REFERENCES end_user(end_user_id) ON UPDATE CASCADE ON DELETE CASCADE
);
SHOW WARNINGS;

DROP TABLE IF EXISTS cart_product;
CREATE TABLE cart_product (
    cart_product_name VARCHAR(255) NOT NULL,
    end_user_id INT NOT NULL,
    cart_product_image_path VARCHAR(255) NOT NULL,
    cart_product_price DOUBLE(7,2) NOT NULL,
    cart_product_quantity INT NOT NULL,
    PRIMARY KEY(cart_product_name, end_user_id),
    FOREIGN KEY(end_user_id) REFERENCES end_user(end_user_id) ON UPDATE CASCADE ON DELETE CASCADE
);
SHOW WARNINGS;

DROP TABLE IF EXISTS user_order;
CREATE TABLE user_order (
    order_id INT NOT NULL AUTO_INCREMENT,
    order_date DATE NOT NULL,
    shipment_date DATE,
    total_price DOUBLE(7,2) NOT NULL,
    courier_name VARCHAR(255),
    tracking_number CHAR(25),
    order_state ENUM('to_send','sent') NOT NULL,
    manager_username VARCHAR(50),
    end_user_id INT NOT NULL,
    credit_card_number CHAR(16) NOT NULL,
    PRIMARY KEY(order_id),
    FOREIGN KEY(manager_username) REFERENCES manager(manager_username) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(end_user_id) REFERENCES end_user(end_user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(credit_card_number) REFERENCES credit_card(credit_card_number) ON UPDATE CASCADE ON DELETE CASCADE
);
SHOW WARNINGS;

DROP TABLE IF EXISTS order_product;
CREATE TABLE order_product (
    order_product_name VARCHAR(255) NOT NULL,
    order_id INT NOT NULL,
    order_product_image_path VARCHAR(255) NOT NULL,
    order_product_price DOUBLE(7,2) NOT NULL,
    order_product_quantity INT NOT NULL,
    PRIMARY KEY(order_product_name, order_id),
    FOREIGN KEY(order_id) REFERENCES user_order(order_id) ON UPDATE CASCADE ON DELETE CASCADE
);
SHOW WARNINGS;
