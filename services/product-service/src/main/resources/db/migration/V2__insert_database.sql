INSERT INTO category (id, name, description) 
VALUES (nextval('category_seq'), 'Electronics', 'Devices like phones, laptops, etc.');

INSERT INTO category (id, name, description) 
VALUES (nextval('category_seq'), 'Books', 'Various genres of books.');

INSERT INTO category (id, name, description) 
VALUES (nextval('category_seq'), 'Clothing', 'Apparel and accessories.');


INSERT INTO product (id, name, description, price, quantity, category_id) 
VALUES (nextval('product_seq'), 'iPhone 14', 'Latest iPhone model', 999.99, 50, 
        (SELECT id FROM category WHERE name = 'Electronics'));

INSERT INTO product (id, name, description, price, quantity, category_id) 
VALUES (nextval('product_seq'), 'MacBook Pro', '16-inch MacBook Pro', 2499.99, 30, 
        (SELECT id FROM category WHERE name = 'Electronics'));

INSERT INTO product (id, name, description, price, quantity, category_id) 
VALUES (nextval('product_seq'), 'The Great Gatsby', 'Classic novel by F. Scott Fitzgerald', 10.99, 100, 
        (SELECT id FROM category WHERE name = 'Books'));

INSERT INTO product (id, name, description, price, quantity, category_id) 
VALUES (nextval('product_seq'), 'Jeans', 'Denim jeans', 49.99, 200, 
        (SELECT id FROM category WHERE name = 'Clothing'));
