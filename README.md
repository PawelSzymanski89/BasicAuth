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
[SpringSecurityConfig](/src/main/java/pl/szymanski/pawel/basicauth/configuration/SpringSecurityConfig.java)
and configure method: 
```
 @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().disable();
    }
```
we configured our application as following:

- User with role USER can get access just to endpoints starting with /user including 
all next path segments like for example /user/add.
- Same rules are signed to ADMIN role, admin can get access just to endpoints starting with /admin
- All users and guests can get access to h2-console - it's endpoint where can we check our database content.
Remember .permitAll() method is not good solution for database access  **it's not safe!!** i'm using it just for
development purposes and for clear example of using .permitAll() !

**LETS TEST IT BY [POSTMAN](https://www.getpostman.com/)**

We have configured two endpoints in [HelloController](/src/main/java/pl/szymanski/pawel/basicauth/controllers/HelloController.java)
```
@RestController
public class HelloController {

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public String helloUser(){
        return "HELLO USER";
    }

    @RequestMapping(path = "/admin")
    public String helloAdmin(){
        return "HELLO ADMIN";
    }

}
```
default request method in SpringBoot is GET so both below forms of annotations are valid and return same result:
```
@RequestMapping(path = "/user", method = RequestMethod.GET)
@RequestMapping(path = "/admin")
```
Now let's execute our query in Postman

![401 error][postman1]

we have result 401 because we don't set any authorization to our request, we have special 
[@Component](/src/main/java/pl/szymanski/pawel/basicauth/DemoDataLoader.java) to testing data loading - it's CommandLineRunner, by calling this interface you can load sample data 
or other things because CommandLineRunner is starting after SpringBoot context initialization. 





##### REMARKS
- H2 database is valid just for development and hobby purposes, please do not use it to other. :)
- MySql implementation is same easy like H2 [here's nice example](https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/)

[logo]: /gitImages/pass.png
[postman1]: /gitImages/postman1.png