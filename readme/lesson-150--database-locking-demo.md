#### Section 18. 
### Lesson 150
##  Database Locking Demo


Outer SQL (commands run on dBeaver):
    
    -- Disable auto-commit
    SET AUTOCOMMIT = false;
    -- Check auto-commit
    SELECT @@autocommit;
    
    -- Start new transaction
    START TRANSACTION;
    -- Lock for updated
    SELECT * FROM order_header WHERE id = 12122 LIMIT 1 FOR UPDATE;
    
    -- End the transaction and release the lock
    COMMIT;
    
Helper SQL statements:
    
    -- Engine status and 'transaction_isolation'
    SHOW ENGINE INNODB STATUS;
    SELECT @@transaction_isolation;
    
Restore auto-commit:    

    -- Restore auto-commit
    SET AUTOCOMMIT = true;


Test method:

    @Test
    public void testUpdate_whenDBLock() {
        OrderHeader orderHeader = orderHeaderDao.getById(12_122L);

        Address address = createTestAddress();
        orderHeader.setBillingAddress(address);
        
        orderHeaderRepository.saveAndFlush(orderHeader);
    }

And if we started a transaction and locked the record under the id = 12 122 
then we have the next test fail:

    2025-03-17T18:11:02.098+03:00 ERROR 34300 --- [spring-boot-book2] [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : Lock wait timeout exceeded; try restarting transaction
    
    org.springframework.dao.PessimisticLockingFailureException: 
        could not execute statement [Lock wait timeout exceeded; try restarting transaction] 
        [update order_header set billing_address=?,billing_city=?,billing_state=?,
            billing_zip_code=?,customer_id=?,last_modified_date=?,order_status=?,
            shipping_address=?,shipping_city=?,shipping_state=?,shipping_zip_code=? 
        where id=?]; 
    
after 50 sec of waiting. 
Or we can release during this waiting time the lock saying <code>COMMIT;</code> in <b>dBeaver</b>












































































