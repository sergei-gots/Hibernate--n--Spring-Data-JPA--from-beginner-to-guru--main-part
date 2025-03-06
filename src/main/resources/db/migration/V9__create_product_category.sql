CREATE TABLE category (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

    description VARCHAR(100) NOT NULL,

    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) engine = InnoDB;


CREATE TABLE product_category (
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY(product_id, category_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id),

    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) engine = InnoDB;

INSERT INTO product (description, product_status)
    VALUES
    ('Product 1', 'NEW'),
    ('Product 2', 'NEW'),
    ('Product 3', 'NEW'),
    ('Product 4', 'NEW'),
    ('Product 5', 'NEW'),
    ('Product 6', 'NEW'),
    ('Product 7', 'NEW');

INSERT INTO category (description)
    VALUES
    ('Category 1'),
    ('Category 2'),
    ('Category 3'),
    ('Category 4'),
    ('Category 5');

 INSERT INTO product_category (product_id, category_id)
     SELECT p.id, c.id FROM product p JOIN category c
     WHERE p.description = 'Product 1' AND c.description = 'Category 1';

INSERT INTO product_category (product_id, category_id)
     SELECT p.id, c.id FROM product p JOIN category c
     WHERE p.description = 'Product 1' AND c.description = 'Category 2';

INSERT INTO product_category (product_id, category_id)
     SELECT p.id, c.id FROM product p JOIN category c
     WHERE p.description = 'Product 2' AND c.description = 'Category 2';
