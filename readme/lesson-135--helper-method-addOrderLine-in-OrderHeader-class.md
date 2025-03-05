#### Section 15. Database Relationship Mappings
### Lessons 135
##  Many-to-1 Bi-directional Relationship : helper method to set a new relation one

    public class OrderHeader extends BaseEntity{
        ...
        public void addOrderLine(OrderLine orderLine) {
            
            if (orderLines == null) {
                orderLines = new HashSet<>();
            }

            orderLines.add(orderLine);
            orderLine.setOrderHeader(this);
        }
        ...
    }


