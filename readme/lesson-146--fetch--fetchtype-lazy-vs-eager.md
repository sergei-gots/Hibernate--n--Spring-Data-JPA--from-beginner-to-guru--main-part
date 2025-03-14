#### Section 17. 
### Lesson 146
##  fetch = FetchType.LAZY vs EAGER

#### getById() if  
   
        @ManyToOne
        private Customer customer;

We'll have only one sql request:

    SELECT
    oh1_0.id,
    oh1_0.billing_address,
    oh1_0.billing_city,
    oh1_0.billing_state,
    oh1_0.billing_zip_code,
    oh1_0.created_date,
    c1_0.id,
    c1_0.address,
    c1_0.city,
    c1_0.state,
    c1_0.zip_code,
    c1_0.created_date,
    c1_0.customer_name,
    c1_0.email,
    c1_0.last_modified_date,
    c1_0.phone,
    oh1_0.last_modified_date,
    oa1_0.id,
    oa1_0.approved_by,
    oa1_0.created_date,
    oa1_0.last_modified_date,
    oh1_0.order_status,
    oh1_0.shipping_address,
    oh1_0.shipping_city,
    oh1_0.shipping_state,
    oh1_0.shipping_zip_code
    FROM
    order_header oh1_0
    LEFT JOIN customer c1_0 ON
    c1_0.id = oh1_0.customer_id
    LEFT JOIN order_approval oa1_0 ON
    oh1_0.id = oa1_0.order_header_id
    WHERE
    oh1_0.id =?
    
#### getById().getCustomer() if

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

We will have 2 sql requests:
 
    SELECT
    oh1_0.id,
    oh1_0.billing_address,
    oh1_0.billing_city,
    oh1_0.billing_state,
    oh1_0.billing_zip_code,
    oh1_0.created_date,
    oh1_0.customer_id,
    oh1_0.last_modified_date,
    oa1_0.id,
    oa1_0.approved_by,
    oa1_0.created_date,
    oa1_0.last_modified_date,
    oh1_0.order_status,
    oh1_0.shipping_address,
    oh1_0.shipping_city,
    oh1_0.shipping_state,
    oh1_0.shipping_zip_code
    FROM
    order_header oh1_0
    LEFT JOIN order_approval oa1_0 ON
    oh1_0.id = oa1_0.order_header_id
    WHERE
    oh1_0.id =?
    SELECT
    c1_0.id,
    c1_0.address,
    c1_0.city,
    c1_0.state,
    c1_0.zip_code,
    c1_0.created_date,
    c1_0.customer_name,
    c1_0.email,
    c1_0.last_modified_date,
    c1_0.phone
    FROM
    customer c1_0
    WHERE
    c1_0.id =?
    