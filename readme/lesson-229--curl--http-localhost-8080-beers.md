## Section XXVI
# <span style="color:gray">Intro to </span><span style="color:green">Spring Data REST</span>
### Lesson 229
## Exploring http://localhost:8080/beers

The <code>org.springframework.boot:spring-boot-starter-data-rest</code> generate some http-API for us.
Let's explore the generated end-point 'beers'.

To do that we can use <span style="color:orange">Postman</span> or even the command line <code>curl</code>

    curl http://localhost:8080/beers

As a result we will get a page of by default 20 beers and links to get a beer separately and
some other useful links:

        "_embedded": {
            "beers": [
                     {
                     "beerName": "Mango Bobs",
                     "beerStyle": "ALE",
                     "upc": "0631234200036",
                     "quantityOnHand": 4730,
                     "price": 23.25,
                     "createdDate": "2025-05-12T09:45:11.538+00:00",
                     "lastModifiedData": "2025-05-12T09:45:11.538+00:00",
                     "_links": {
                         "self": {
                             "href": "http://localhost:8080/beers/30f94992-5ce7-4577-b4d0-53541ccadf31"
                         },
                         "beer": {
                             "href": "http://localhost:8080/beers/30f94992-5ce7-4577-b4d0-53541ccadf31"
                         }
                     }
                },
            ...
                {
                    "beerName": "Java Jill",
                    "beerStyle": "LAGER",
                    "upc": "4006016803570",
                    "quantityOnHand": 4575,
                    "price": 88.43,
                    "createdDate": "2025-05-12T09:45:11.635+00:00",
                    "lastModifiedData": "2025-05-12T09:45:11.635+00:00",
                    "_links": {
                        "self": {
                            "href": "http://localhost:8080/beers/f721d958-e100-40c7-af0d-2ac66c7edd00"
                        },
                        "beer": {
                            "href": "http://localhost:8080/beers/f721d958-e100-40c7-af0d-2ac66c7edd00"
                        }
                    }
                }
            ]
        },
        "_links": {
            "first": {
                "href": "http://localhost:8080/beers?page=0&size=20"
            },
            "self": {
                "href": "http://localhost:8080/beers?page=0&size=20"
            },
            "next": {
                "href": "http://localhost:8080/beers?page=1&size=20"
            },
            "last": {
                "href": "http://localhost:8080/beers?page=1&size=20"
            },
            "profile": {
                "href": "http://localhost:8080/profile/beers"
            },
            "search": {
                "href": "http://localhost:8080/beers/search"
            }
        },
        "page": {
            "size": 20,
            "totalElements": 30,
            "totalPages": 2,
            "number": 0
        }
    }

