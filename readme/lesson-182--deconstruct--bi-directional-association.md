#### Section 20. Legacy Database Mapping
### Lesson 182. Refactor for Bi-Directional Association

We will deconstruct relation between User and UserMeta.
Having

    @Entity
    @Table(name = "wp_usermeta")
    public class UserMeta {

    ...

    private Long userId;

    ...

    }

We will refactor <code>UserMeta</code> and <code>User</code>:

    @Entity
    @Table(name = "wp_usermeta")
    public class UserMeta {

    ...

    @ManyToOne
    private User user;

    ...

    }

    @Entity
    @Table(name = "wp_users")
    public class User {

    ...

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<UserMeta> userMetaSet;

    ...

    }

