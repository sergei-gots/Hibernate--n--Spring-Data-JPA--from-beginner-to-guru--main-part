ALTER TABLE order_line
    ADD COLUMN product_id BIGINT NOT NULL,
    ADD CONSTRAINT order_line_product_fk FOREIGN KEY (product_id)  REFERENCES product(id);