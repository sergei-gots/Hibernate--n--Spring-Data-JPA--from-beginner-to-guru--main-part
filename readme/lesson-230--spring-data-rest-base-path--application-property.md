## Section XXVI
# <span style="color:gray">Intro to </span><span style="color:green">Spring Data REST</span>
### Lesson 230
## Changing Base Path <code>spring.data.rest.base-path</code>

In this lesson we will change the base path of application http://localhost:8080/beers to be
http://localhost:8080/api/v1/beers according to - air quotes - our API specifications on
https://sfg-beer-works.github.io/brewery-api/#tag/Beer-Service/operation/listBeers

To do that we set one of the  <code>spring.data.rest.*</code> properties

    spring.data.rest.base-path=/api/v1

in our <code>application.properties</code>.