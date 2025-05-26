Project to the course 

# Hibernate -n- Spring Data JPA: from beginner to guru
### by the Udemy course of John Thompson

This project represent the main part of the course starting from the section 9 'DAO Pattern with JDBC'.
<br>
Each commit represents the completing the corresponding lesson.
<br>
The sections about Hibernate Interceptors and Listeners I can consider as optiional but useful.
<br>
Start lesson at <b>Udemy</b>:

https://www.udemy.com/course/hibernate-and-spring-data-jpa-beginner-to-guru/learn/lecture/29065616#overview

The git-project to the sections 1-to-8 is available here:

https://github.com/sergei-gots/Hibernate-n-Spring-Data-JPA-from-beginner-to-Guru.-chapters-1-to-8

### Commands

```mvn package```

builds <code>/target/< artifactId >.jar</code> files.

```docker build -f ./src/main/dockerBase/dockerfile -t kbe-rest-beer .```

builds docker image of the <code>kbe-rest-beer</code> application (<code>Lesson 247</code>).

```docker run --rm -p 8080:8080 -d kbe-rest-beer```

launches <code>kbe-res-beer</code>-image as a docker container.