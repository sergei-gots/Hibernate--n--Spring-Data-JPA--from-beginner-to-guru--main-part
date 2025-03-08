INSERT
    INTO customer (customer_name, address, city, state, zip_code, phone, email)
    VALUES ('Customer 1', '123 Duval', 'Key West', 'FL', '33040', '305.292.1435',
        'cheesebureger@margaritville.com');

UPDATE order_header
    SET customer_id = (SELECT id FROM customer LIMIT 1);