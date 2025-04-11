### @Table(@Indexes = {} )

The @Table(indexes = …) annotation does not force Hibernate to add an index to an existing database if:

spring.jpa.hibernate.ddl-auto=none or validate or none — Hibernate does not generate DDL at all.

Even on update, Hibernate does not touch indexes to avoid damaging manually created schemas.

👉 Hibernate does not synchronize indexes by default when ddl-auto=update.

🔧 What can be done
✅ Option 1: temporarily use ddl-auto=create

If you just want to regenerate the schema and see how Hibernate creates the index:

spring.jpa.hibernate.ddl-auto=create

Then Hibernate will drop the old schema and recreate everything — and the user_login_key1 index will appear.

⚠️ Important: this will delete all data! Use only in a dev environment.