### Defining indexes (MySQL example)

Let's consider the having script to initialize the database.
We have the next statement's fragment in MySQL there:

    KEY `meta_key` (`meta_key`(191))

This is the part of the DDL in MySQL that creates the index. Here it is, piece by piece:
🔍 Breakdown:
Part Explanation
KEY Synonym for INDEX. Creates a regular non-unique index.
`meta_key` Index name. The name by which MySQL will know it.
(`meta_key`(191)) Specifies the meta_key column, but only the first 191 characters are indexed.
❓ Why exactly 191 characters?

This is due to the limitation on the length of indexes in MySQL:

For utf8mb4 (4-byte encoding), the maximum key length is 767 bytes.

VARCHAR(255) in utf8mb4 = 255 × 3 or 4 = up to 1020 bytes (too much).

191 characters x 4 bytes = 764 bytes — fits the limit.

👉 So: meta_key(191) is a working workaround to index VARCHAR(255) under utf8mb4.
📌 Summary

This line:

KEY `meta_key` (`meta_key`(191))

— creates a non-unique index on the first 191 characters of the meta_key column.
This is a necessary measure if the column is long and utf8mb4 is used, otherwise MySQL will return an error Index column size too large.
