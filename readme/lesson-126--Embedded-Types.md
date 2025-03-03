### Section 15. Hibernate JPA. Embedded Objects
### Lesson 126. Embedded Types 
@javax.persistence annotations 
## @Embedded/@Embeddable 
and 
## @OverrideAttributes({@OverrideAttribute()...}

We use the <code>@Embeddable</code> annotation to declare classes 
whose objects are to be embedded within an <code>@Entity</code> object, 
where they will be annotated with <code>@Embedded</code>. 
Additionally, the <code>@Entity</code> class should also 
include the <code>@OverrideAttributes</code> annotation, 
which contains a set of <code>@OverrideAttribute</code> annotations.