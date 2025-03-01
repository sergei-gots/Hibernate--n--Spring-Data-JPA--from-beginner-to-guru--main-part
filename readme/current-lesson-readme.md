#### Chapter 14: Paging-n-Sorting
## Assignment 16. Implement Author Hibernate DAO
### What are we going to do
<li> Create Hibernate version of Author DAO
<li> Create stubs of previously covered methods
<li> Do not add as Spring managed component, not needed
<li> Implement Find All Authors by Last Name with paging and sorting
<li> Make sort optional
<li> Use named bind parameters
<li> Create test and verify

#### Extra Material

## Annotation @ComponentScan upon a test class

The annotation:

    @ComponentScan("guru.springframework.jdbc.dao")
    on a test class is redundant if the test itself is already in the same package (but under the test source set).

Why is it unnecessary?
Spring automatically scans the package where the test class is located (since it's typically annotated with @SpringBootTest or another test configuration annotation).
Explicitly specifying the same package does nothing extra—Spring would scan it anyway.
It can cause confusion—if the test already loads the full application context, this annotation might be mistakenly thought to alter the scanning behavior.
When is it needed?
If you want to override default scanning (e.g., include additional packages that are not scanned by default).
If you're testing a limited part of the application (e.g., using 

    @ContextConfiguration(classes = SomeConfig.class) 

and need to manually specify beans).
<br>

✅ So, if the test class is already in guru.springframework.jdbc.dao, remove @ComponentScan—it's unnecessary.