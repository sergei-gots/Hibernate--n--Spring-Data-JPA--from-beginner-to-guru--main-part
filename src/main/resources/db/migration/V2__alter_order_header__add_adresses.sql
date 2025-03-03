ALTER TABLE order_header
    ADD COLUMN shipping_address VARCHAR(30),
    ADD COLUMN shipping_city VARCHAR(30),
    ADD COLUMN shipping_state VARCHAR(30),
    ADD COLUMN shipping_zip_code VARCHAR(15),
    ADD COLUMN billing_address VARCHAR(30),
    ADD COLUMN billing_city VARCHAR(30),
    ADD COLUMN billing_state VARCHAR(30),
    ADD COLUMN billing_zip_code VARCHAR(15);