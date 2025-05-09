## Section XXV
# Multiple Data Sources
### Lessons 221
## jakarta.persistence.@Transient Properties


When we mark an entity's property as <b>@Transient</b> this property is not taken into account
when the entity is going to be persisted to the database or read from the database.

Now we can populate our <b>CreditCard</b> Entity Class with the fields aggregating properties
of the rest entities read from our other two databases:


    import hibernate.Transient;


    @Entity
    public class CreditCard {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Convert(converter = EncryptionConverter.class)
        private String cvv;

        private String expirationDate;

        @Transient
        private String creditCardNumber;

        @Transient
        private String firstName;

        @Transient
        private String lastName;

        @Transient
        private String zipCode;
    }