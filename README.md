BasicAuth with SprinBoot
-------
Project is showing how to run and configure BasicAuth for RestApi or other application type. 
Database used in project is a H2 database, passwods of users are encoded by using 
[BCrypt](https://en.wikipedia.org/wiki/Bcrypt).


##### HOW IT WORKS?

Like you probably know SpringBoot include Tomcat server so when you start 
cloned project you should get run localhost:8080/user endpoint. If you do 
that you see browser question about user and password.

![log in image][logo]

now please look at 
[SpringSecurityConfig](\src\main\java\pl\szymanski\pawel\basicauth\configuration\SpringSecurityConfig.java)
and configure method: 


[logo]: /gitImages/pass.png