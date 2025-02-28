#### Chapter 14: Paging-n-Sorting
###  Lesson 119.1. Math.toIntExact(pageable.getOffset()))


Referring to the John Thompson's Lesson 118 'Paging with Hibernate'
we'll add some nuances in the procedure of getting  <b>offset</b> from <code>Pageable</code>.
We'll replace

    long offset = pageable.getOffset();
    if (offset > Integer.MAX_VALUE) {
        throw new IllegalArgumentException("Offset is too large for setFirstResult");
    }
    query.setFirstResult((int) offset);
    
with

    query.setFirstResult(Math.toIntExact(pageable.getOffset()));

 The method <code>Math.toIntExact(long value)</code> will throw the 
 <code>ArithmeticException("integer overflow")</code> if the value is not in the range of integers.

Also, we'll fix the method <code>findAll(int limit, int offset)</code> on this iteration.