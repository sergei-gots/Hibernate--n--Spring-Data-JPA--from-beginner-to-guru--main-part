ALTER TABLE order_line ADD COLUMN version INTEGER;

UPDATE order_line SET version = 0;