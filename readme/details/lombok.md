## Take 'Lombok project' in the project
https://projectlombok.org/

### ✅ 1. Lombok-dependency in pom.xml

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.38</version>
        <scope>provided</scope>
    </dependency>

or:

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.38</version>
        <optional>true</optional> <!-- это опционально -->
    </dependency>

But there is more convenient to use 
        
    <scope>provided</scope>

instead of 
    
    <optional>true</optional>

### ✅ 2. Why we use the Lombok plugin in IntelliJ IDEA?

IntelliJ does not know by default, what actually Lombok does.
It's because Lombok generates the code at the project compilation.
Without the Lombok plugin IDEA will mark lombok served fields as "not found"
even though they will have  @Getter/@Setter annotations.

<br>The Navigation without that plugin (like 'Go to definition') will not work too.
Autofill may not see the Lombok generated methods.

👉 To fix that we install the Lombok Plugin in IntelliJ: 

    Settings -> 
        Plugins -> 
            Lombok

and turn on the annotation processing: 
    
    Settings -> 
        Build, Execution, Deployment -> 
            Compiler -> 
                Annotation Processors -> 
                        Enable annotation processing

### ✅ 3. What does it mean  '<optional>true</optional>' and when we should use that?

The tag <code><optional>true</optional></code> tells to Maven:

    "This dependency should not be inherited in dependent projects."

Example: if we create a library, and that library uses Lombok,
then we usually don't want to "obey" the Lombok to the users of our library -
they will deceive it by themselves if they use the Lombok.

And if we create not a library, but a standard Spring Boot App, 
then we can just use the <code>scope</code>:

    <scope>provided</scope>

It means that Lombok is needed only at the compilation time,
and will not be included in the result  JAR (and that's right).

### 💡 Summary — what to do:

<li>Install the Lombok plugin in IntelliJ
<li>Enable annotation processing
<li>>add dependency in pom.xml

       

