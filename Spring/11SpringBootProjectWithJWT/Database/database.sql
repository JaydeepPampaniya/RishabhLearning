create database PhoneX2;
use PhoneX2;



CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,       
    email VARCHAR(255) NOT NULL UNIQUE,       
    password VARCHAR(255) NOT NULL,             
    role VARCHAR(255) NOT NULL,                 
    created_time TIMESTAMP NOT NULL            
);


CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,              -- Auto-generated ID (Primary Key)
    username VARCHAR(255) NOT NULL UNIQUE,              -- Unique username for the customer
    gender VARCHAR(50) NOT NULL,                        -- Gender of the customer
    address VARCHAR(255),                               -- Address of the customer
    age INT,                                            -- Age of the customer
    contact_no BIGINT NOT NULL UNIQUE,                  -- Contact number (unique)
    user_id BIGINT NOT NULL,                            -- Foreign key to the User table
    CONSTRAINT FK_customer_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,            -- Auto-generated ID (Primary Key)
    adminname VARCHAR(255) NOT NULL UNIQUE,           -- Unique admin name
    contact_no BIGINT NOT NULL UNIQUE,                -- Unique contact number for the admin
    user_id BIGINT NOT NULL,                          -- Foreign key to the User table
    CONSTRAINT FK_admin_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE -- References User
);

CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE permission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE role_permission (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permission(id) ON DELETE CASCADE
);

INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('CUSTOMER');

INSERT INTO permission (name) VALUES ('READ');
INSERT INTO permission (name) VALUES ('WRITE');
INSERT INTO permission (name) VALUES ('DELETE');




select * from categories;
select * from user;
select * from product;
select* from admin;
select * from customer;
select* from cart;
select* from role_permission;
select * from role;
select * from permission;
select * from role_permission;



drop table customer;
drop table admin;
drop table cart;
drop table product;
drop table categories;
drop table user;
drop table role_permission;

alter table categories drop column categories_name;
alter table categories rename column categories to categories_name;
insert into cart(product_id,user_id,quantity) values(3,1,1),(1,1,1);

delete from cart where user_id=4;
delete from categories where id=1;
