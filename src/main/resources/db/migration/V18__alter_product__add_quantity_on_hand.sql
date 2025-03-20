ALTER TABLE product ADD COLUMN quantity_on_hand INTEGER NOT NULL DEFAULT 0;

UPDATE product SET quantity_on_hand = 10;

