#### Section 15. Database Relationship Mappings
### Lessons 132-133
##  1-to-Many Relationship : OrderHeader-to-OrderLines

Relationship in SQL:

    CREATE TABLE order_line (
        ...
        order_header_id BIGINT NOT NULL,
        ...
        CONSTRAINT order_header_fk
            FOREIGN KEY (order_header_id) REFERENCES order_header(id)
    ) ENGINE = InnoDB;

Relationship in Java: 

    public class OrderHeader extends BaseEntity{

        ...

        @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.PERSIST)
        Set<OrderLine> orderLines;
        ...
    }

    public class OrderLine extends BaseEntity{
        ...
        @ManyToOne(fetch = FetchType.LAZY)
        private OrderHeader orderHeader;
        ...
    }


Note: to avoid the situation when cyclic dependency causes <code>StackOverFlowError</code>
we reduce <code>OrderLine.hashCode()</code> and <code>OrderLine.toString()</code> methods
to use <code>orderHeader.getId()</code> instead of <code>orderHeader</code>:

    public class OrderHeader extends BaseEntity{
        ...
        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), quantityOrdered, orderHeader.getId());
        }

        @Override
        public String toString() {
            return "OrderLine{" +
                super.toString() +
                    "quantityOrdered=" + quantityOrdered +
                    ", orderHeader.id=" + orderHeader.getId() +
                '}';
        }
        ...
    }