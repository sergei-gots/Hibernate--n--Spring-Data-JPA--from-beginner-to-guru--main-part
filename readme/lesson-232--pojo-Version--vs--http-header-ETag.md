## Section XXVI
# <span style="color:gray">Intro to </span><span style="color:green">Spring Data REST</span>
### Lesson 232
## @Version -n- ETag

### Spring Data Rest Doc Ref

https://docs.spring.io/spring-data/rest/reference/etags-and-other-conditionals.html

### In a nutshell

The POJO's Entity @Version is used in SPRING DATA REST as <code>ETag</code> in HTTP-header.

### Example

    sergei@fedora:~$ curl -H 'If-None-Match: "0"' http://localhost:8080/api/v1/beer/83420eb5-af1d-4b13-9835-e477302f7e8d

returns in the console nothing: <code>@Version version</code> is actually <code>"0"</code>. 

The HTTP-Status will be <code>304: Not Modified</code>


     curl -H 'If-None-Match: "1"' http://localhost:8080/api/v1/beer/83420eb5-af1d-4b13-9835-e477302f7e8d

will return 

    {
        "beerName" : "Mango Bobs",
        "beerStyle" : "ALE",
        "upc" : "0631234200036",
        "quantityOnHand" : 4791,
        "price" : 81.60,
        "createdDate" : "2025-05-13T10:37:55.263+00:00",
        "lastModifiedData" : "2025-05-13T10:37:55.263+00:00",
        "_links" : {
            "self" : {
                "href" : "http://localhost:8080/api/v1/beer/83420eb5-af1d-4b13-9835-e477302f7e8d"
            },
            "beer" : {
                "href" : "http://localhost:8080/api/v1/beer/83420eb5-af1d-4b13-9835-e477302f7e8d"
            }
        }
    }

and the HTTP-Status will be <code>200: OK</code>  