#### Section 19.
### Lesson 162. Validation. Applying to Customer

We apply <code>@Length</code> to the properties of the class <code>Customer</code>.
For <code>@Embedded</code> we also need to add <code>@Valid</code> annotation.
Also we will make test to check for jakarta.persistence.<code>ConstraintViolationException</code>
and its content if we have values for some properties too long.