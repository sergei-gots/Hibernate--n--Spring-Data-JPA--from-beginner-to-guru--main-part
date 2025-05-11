## Section XXVI
# <span style="color:gray">Intro to </span><span style="color:green">Spring Data REST</span>
### Lesson 228
## spring-boot-starter-data-rest 

#### Links to explore additionally

https://docs.spring.io/spring-data/rest/reference/data-commons/dependencies.html#dependencies.spring-boot

https://calver.org/

### spring-boot-starter-data-rest dependency

The <code>spring-boot-starter-data-rest</code> dependency is used when you want 
to automatically expose your Spring Data JPA repositories as <b>RESTful web services</b> — 
without writing explicit controller classes.

### What it does:

Exposes CRUD operations over HTTP endpoints automatically.

For example, if you have a <code>JpaRepository<User, Long></code>, 
it will automatically create endpoints like:
<li> GET /users
<li> GET /users/{id}
<li> POST /users
<li> PUT /users/{id}
<li> DELETE /users/{id}

### <span style="color:green">When you might need it:</span>

<li> Rapid prototyping: to quickly expose data for testing or frontend integration.
<li> Admin/backoffice tools: for basic CRUD interfaces.
<li> Microservices: if you're building a lightweight data-exposing service without complex business logic.

### <span style="color:red">When not to use it</span>:

<li> If your API requires custom behavior, validation, authentication, or logic beyond simple CRUD.
<li>When you want full control over endpoint structure, request/response formats, or business rules.

### How to add it:

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-rest</artifactId>
    </dependency>

### Dependencies tree it addds

To view the dependency tree 

    mvn dependency:tree 

List of dependencies added with <code>spring-boot-starter-data-rest</code>:

    [INFO] +- org.springframework.boot:spring-boot-starter-data-rest:jar:3.4.4:compile
    [INFO] |  \- org.springframework.data:spring-data-rest-webmvc:jar:4.4.4:compile
    [INFO] |     +- org.springframework.data:spring-data-rest-core:jar:4.4.4:compile
    [INFO] |     |  +- org.springframework.hateoas:spring-hateoas:jar:2.4.1:compile
    [INFO] |     |  +- org.springframework.plugin:spring-plugin-core:jar:3.0.0:compile
    [INFO] |     |  \- org.atteo:evo-inflector:jar:1.3:compile
    [INFO] |     \- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile

To view the full list of dependencies included in <code>spring-boot-starter-data-rest</code>
use the <span style="color:cyan">maven</span> tool window: some dependencies in the list shown above are omitted because
they were already added with other spring-boot-starters. 