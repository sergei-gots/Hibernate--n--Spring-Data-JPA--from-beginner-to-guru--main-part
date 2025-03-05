#### Section 15. Database Relationship Mappings
### Lessons 134
##  Many-to-1 Unidirectional Relationship : OrderLine-to-Product

Relationship in SQL:

    ALTER TABLE order_line
        ADD COLUMN product_id BIGINT NOT NULL,
        ADD CONSTRAINT order_line_product_fk FOREIGN KEY (product_id)  REFERENCES product(id);

Relationship in Java: 


    public class OrderLine extends BaseEntity{
        ...
        @ManyToOne
        private Product product;
        ...
    }


