# F20-T05-BookReader
Currently, people have a difficult time organizing and keeping track of their books, and discovering new books to read. They also lack a social network of fellow book readers. Our goal is to create a user friendly application that supports users’ readings by providing an easy way to track and organize their library of books along with a way to connect with friends and discover new books. Our system allows users to recommend books to their friends and see their friends’ book reviews and book progresses on timelines as well as their own reviews and book progresses. Our system is specifically focused on the aspect of library management since we want to support users’ ability to organize their books in customized bookshelves. 

## Installation Guide:
1. Import `data.sql` file that contains our database schemas and dump data to the localhost database. One way to achieve this is to use MySql Workbench to import the `data.sql` file. Or you can choose the way you are familiar with to import data to the local database. 
2. Set environment variables to connect with your local database. The following variables need to be filled in correspondingly: 
* `SPRING_DATASOURCE_URL` - url to your local database
* `SPRING_DATASOURCE_USERNAME` - username of your local database
* `SPRING_DATASOURCE_PASSWORD` - password of your local database<br>
Note: if the app didn’t successfully run, then try to paste the connection details directly into `/src/main/resources/application.properties` file，<br>
for exmaple:
* `spring.datasource.url=jdbc:mysql://localhost:3306/bookreader`
* `spring.datasource.username=admin`
* `spring.datasource.password=bookreader123$`

3. If using the terminal, run: `mvn spring-boot:run`<br>
Note: If using IDE, right click on “com.group5.BookRead.BookReadApplication.java” and select run Java application.
4. Once started, the application will be running at localhost:8080. The landing page of our application is at localhost:8080/login
