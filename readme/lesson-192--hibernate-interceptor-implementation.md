#### Lesson 192. EncryptionInterceptor Implementation

onLoad is not called in Hibernate 6.6 when using findById(), is as follows:

▶️ Hibernate 6 no longer uses Interceptor#onLoad when loading an entity from the database.
They rewrote the behavior, and now loading is handled via EventListeners (LoadEventListener, InitializeCollectionEventListener, etc.).

Interceptor#onLoad in Hibernate 6 is not actually triggered by regular repository loads (findById, findAll, etc.).

And onFlushDirty is called because it has not been removed yet — changes to the entity are handled separately in the dirty checking mechanism.
How to fix

If you want your code to be executed when loading an entity, starting with Hibernate 6, you need to register your LoadEventListener, and not rely on Interceptor#onLoad.