package guru.springframework.orderservice.enumeration;

public enum ProductStatus implements Titled {

    NEW("New"),
    IN_STOCK("In stock"),
    DISCONTINUED("Discountinued");

    private final String title;

    ProductStatus(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

}
