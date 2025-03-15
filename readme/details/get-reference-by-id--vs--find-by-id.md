### getReferenceById(id) vs findById(id)

#### Key Differences:
    
• Method JpaRepository.<code>getReferenceById(id)</code>
<br>
    Returns a lazy proxy that may throw EntityNotFoundException only when accessed.
• Method CrudRepository.<code>findById(id)</code>
<br>
    Performs an immediate database query and returns Optional<OrderHeader>.
<br>
👉 Use findById(id) if you want an explicit check for entity existence.

#### Example
If you expect a null result, use findById(id), which returns an Optional<OrderHeader>:

    @Override
    public OrderHeader getById(Long id) {
        return orderHeaderRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }
