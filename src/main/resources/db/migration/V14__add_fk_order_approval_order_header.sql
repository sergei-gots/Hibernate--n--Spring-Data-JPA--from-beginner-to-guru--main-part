ALTER TABLE order_approval
    ADD COLUMN order_header_id BIGINT NOT NULL,
    ADD CONSTRAINT fk_order_approval_order_header FOREIGN KEY (order_header_id) REFERENCES order_header(id);