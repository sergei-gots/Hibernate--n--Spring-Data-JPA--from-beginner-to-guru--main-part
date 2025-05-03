### SLF4J (simple)

#### Dependency

Dependency is already included within spring-boot-starter

#### Using

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;


    public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

        public static void main(String[] args) {
            logger.info("His, SLF4J!");
            logger.warn("This is a warning");
            logger.error("And this is an error!");
            logger.debug("This will be output on the debug level");
            logger.trace("And this one will be output on the traced level-)");
    }