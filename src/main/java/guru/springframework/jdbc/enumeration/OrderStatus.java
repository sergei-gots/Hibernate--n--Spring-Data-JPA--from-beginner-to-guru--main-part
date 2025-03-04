package guru.springframework.jdbc.enumeration;

/**
 * Created by sergei on 03/03/2025
 */
public enum OrderStatus implements Titled {

    NEW("Order created"),
    PAID("Order has being paid"),
    IN_PROCESS("Order is being completed"),
    DELIVERED("Order delivered");

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
