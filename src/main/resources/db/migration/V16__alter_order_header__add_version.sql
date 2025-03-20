ALTER TABLE order_header ADD COLUMN version INTEGER;

UPDATE order_header SET version = 0;