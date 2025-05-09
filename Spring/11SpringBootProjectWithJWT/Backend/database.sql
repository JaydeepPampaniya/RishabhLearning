create database PhoneX;
use PhoneX;

show tables;
desc customer;

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
     CONSTRAINT chk_user_role CHECK (name IN ('ADMIN', 'CUSTOMER','OWNER'))
);

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,       
    email NVARCHAR(50) NOT NULL UNIQUE,       
    password NVARCHAR(255) NOT NULL,
    role_id INT ,
    created_time TIMESTAMP NOT NULL,
    CONSTRAINT FK_user_roles FOREIGN KEY (role_id) REFERENCES roles(id)
);



CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,              
    username VARCHAR(50) NOT NULL UNIQUE,              
    gender VARCHAR(6) NOT NULL,                      
    address VARCHAR(255),                             
    age INT,                                            
    contact_no BIGINT NOT NULL UNIQUE  ,        
    user_id BIGINT NOT NULL,                           
    CONSTRAINT FK_customer_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT chk_contact_no CHECK ((length(contact_no) =10) AND contact_no NOT LIKE '%[^0-9]%')
);

CREATE TABLE admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,          
    adminname VARCHAR(50) NOT NULL UNIQUE,           
    contact_no BIGINT NOT NULL UNIQUE,               
    user_id BIGINT NOT NULL,                         
    CONSTRAINT FK_admin_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE, -- References User
	CONSTRAINT chk_contact_no_on_Admin CHECK ((length(contact_no) =10) AND contact_no NOT LIKE '%[^0-9]%')

);
select * from product;

CREATE TABLE DeviceType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE Product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    image_name VARCHAR(255),
    image_type VARCHAR(255),
    image_data LONGBLOB,
    company_name VARCHAR(50),
    device_name VARCHAR(50),
    original_price DECIMAL(19, 2),
    current_price DECIMAL(19, 2),
    discount BIGINT,
    description VARCHAR(2000),
    device_type_id INT,
    CONSTRAINT fk_categories FOREIGN KEY (device_type_id) REFERENCES DeviceType(id)
);

CREATE TABLE permissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE role_permissions (
	id BIGINT not null primary key auto_increment,
    role_id INT NOT NULL,
    permission_id BIGINT NOT NULL,
    CONSTRAINT fk_role_permissions_role FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT fk_role_permissions_permission FOREIGN KEY (permission_id) REFERENCES permissions(id)
);
CREATE TABLE Cart (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT,
    user_id BIGINT,
    quantity BIGINT,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES Product(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES User(id)
);

 
select * from user;
select * from product;
select* from admin;
select * from customer;
select* from cart;
select * from product;
select * from role_permissions;

drop table customer;
drop table admin;
drop table cart;
drop table product;
drop table devicetype;
drop table user;
drop table role_permissions;
drop table roles;
drop table permissions;


insert into roles(name) values('ADMIN'),('CUSTOMER'),('OWNER');
INSERT INTO permissions (name) VALUES
('WRITE_CART'),
('READ_CART'),
('WRITE_PRODUCTS'),
('READ_PRODUCTS'),
('READ_DEVICE_TYPE'),
('WRITE_DEVICE_TYPE'),
('READ_PERMISSIONS'),
('READ_ALL_PERMISSIONS'),
('WRITE_PERMISSIONS'),
('WRITE_ROLES'),
('READ_ROLES'),
('WRITE_ROLES_PERMISSIONS'),
('READ_ALL_ROLES_PERMISSIONS'),
('READ_USER'),
('READ_ALL_USERS'),
('UPDATE_USER'),
('READ_ALL_CUSTOMERS'),
('ADD_USER_ROLES'),
('READ_ALL_USER_ROLES');




INSERT INTO role_permissions (role_id, permission_id)
SELECT 
    (SELECT id FROM roles WHERE name = 'OWNER') AS role_id, 
    p.id AS permission_id
FROM permissions p;

commit;
INSERT INTO role_permissions (role_id, permission_id) 
SELECT 1, id 
FROM permissions 
WHERE name IN (
    'WRITE_PRODUCTS',
    'READ_PRODUCTS',
    'READ_DEVICE_TYPE',
    'WRITE_DEVICE_TYPE',
    'READ_PERMISSIONS',
    'READ_ALL_PERMISSIONS',
    'WRITE_PERMISSIONS',
    'WRITE_ROLES',
    'READ_ROLES',
    'WRITE_ROLES_PERMISSIONS',
    'READ_ALL_ROLES_PERMISSIONS',
    'READ_USER',
    'UPDATE_USER',
    'READ_ALL_CUSTOMERS',
    'WRITE_USER_ROLES',
    'READ_ALL_USER_ROLES'
);

rollback;

INSERT INTO role_permissions (role_id, permission_id) 
SELECT 2, id 
FROM permissions 
WHERE name IN (
    'WRITE_CART',
    'READ_CART',
    'READ_DEVICE_TYPE',
    'READ_PRODUCTS',
    'READ_USER',
    'UPDATE_USER'
);


SELECT * FROM PERMISSIONS;

SELECT * FROM user;
select * from role_permissions;
select * from permissions order by id;
select * from role_permissions where role_id=4; 
select * from admin;
update admin set gender = 'male' where id=2; 

alter table admin add column gender varchar(10);
	




