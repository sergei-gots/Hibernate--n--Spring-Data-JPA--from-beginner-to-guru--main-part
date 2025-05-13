## Section XXVI
# <span style="color:gray">Intro to </span><span style="color:green">Spring Data REST</span>
### Lesson 231
## Changing URL Path with @RepositoryRestResource

We will change the http://localhost:8080/api/v1/beers url path to
http://localhost:8080/api/v1/beer to be consistent with our API specification and also English language rules.
Additionally, we will change naming of the beer collection section returning with JSON
to be <code>"beer" : []</code> instead of <code>"beers" : []</code>
- air quotes - our API specifications on
https://sfg-beer-works.github.io/brewery-api/#tag/Beer-Service/operation/listBeers

To do that we set use <b>org.springframework.data.rest.core.annotation.</b><code>@RepositoryRestResource</code> on <code>BeerRepository</code>

    @RepositoryRestResource(path = "beer", collectionResourceRel = "beer")
    public interface BeerRepository extends JpaRepository<Beer, UUID> {
    }