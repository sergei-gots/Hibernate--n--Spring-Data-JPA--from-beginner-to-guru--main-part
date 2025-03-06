#### Section 15. Database Relationship Mappings
### Lessons 136
##  Many-to-Many Bi-directional Relationship 

SQL:

    CREATE TABLE product_category (
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY(product_id, category_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id),

        created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ) engine = InnoDB;
   
Java:

    public class Product extends BaseEntity{
        ...
         @ManyToMany
        @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns =  @JoinColumn(name = "category_id")
        )
        private Set<Category> categories;
        ...
    }


    public class Category extends BaseEntity{
        ...
        @ManyToMany
        @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn (name = "product_id")
        )
        private Set<Product> products;        
        ...
    }

Test:

    @Test
    public void testGetByDescription_checkCategory() {
    
            Product fetched = productDao.findProductByDescription("Product 1");
    
            assertNotNull(fetched);
            assertEquals("Product 1", fetched.getDescription());
    
            Set<Category> categories = fetched.getCategories();
            assertNotNull(categories);
            assertEquals(2, categories.size());
            assertTrue(
                    categories.stream()
                            .anyMatch(category ->
                                category.getDescription().equals("Category 2") &&
                                        category.getProducts().size() == 2 &&
                                        category.getProducts().stream()
                                                .anyMatch(product -> product.getDescription().equals("Product 1"))
                            )
            );
        }