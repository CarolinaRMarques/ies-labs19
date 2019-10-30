##-------2.1-------
A Servlet is a class that runs at the server, handles requests, processes them and reply with a
response. A servlet must be deployed into a (Java) Servlet Container in order to become usable.
Servlet is a generic interface and the HttpServlet is an extension of that interface

// To start
$ sudo /etc/init.d/tomcat8 start  // same as above
 
// To stop
$ sudo /etc/init.d/tomcat8 stop  // same as above

aliena h) :
Para obter uma mensagem personalizada o URL será : http://localhost:8080/MyFirstServlet?username=<name>
exemplo: http://localhost:8080/MyFirstServlet?username=fred

What are the responsibilities/services of a “servlet container”?
#Answer
The basic idea of Servlet container is using Java to dynamically generate the web page on the server side. So servlet container is essentially a part of a web server that interacts with the servlets.
Servlet declares three essential methods for the life cycle of a servlet - init(), service(), and destroy().


##-------2.2-------
##-------2.3-------


The @GetMapping annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.

@RequestParam binds the value of the query String parameter name into the name parameter of the greeting() method. This query String parameter is not required; if it is absent in the request, the defaultValue of "World" is used. The value of the name parameter is added to a Model object, ultimately making it accessible to the view template.

The implementation of the method body relies on a view technology, in this case Thymeleaf, to perform server-side rendering of the HTML. Thymeleaf parses the greeting.html template below and evaluates the th:text expression to render the value of the ${name} parameter that was set in the controller.



@SpringBootApplication is a convenience annotation that adds all of the following:

    @Configuration: Tags the class as a source of bean definitions for the application context.

    @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.

    @ComponentScan: Tells Spring to look for other components, configurations, and services in the hello package, letting it find the controllers.



Provide a name query string parameter with http://localhost:8080/greeting?name=User. 

"Hello, User!"

This change demonstrates that the @RequestParam arrangement in GreetingController is working as expected. The name parameter has been given a default value of "World", but can always be explicitly overridden through the query string.
 
NOTA : ao aceder ao localhost/8080 tenho por default o nome Carolina (foi apenas para testar)


Alinea m)


The implementation of the method body creates and returns a new Greeting object with id and content attributes based on the next value from the counter, and formats the given name by using the greeting template.

A key difference between a traditional MVC controller and the RESTful web service controller above is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service controller simply populates and returns a Greeting object. The object data will be written directly to the HTTP response as JSON.

This code uses Spring 4’s new @RestController annotation, which marks the class as a controller where every method returns a domain object instead of a view. It’s shorthand for @Controller and @ResponseBody rolled together.

The Greeting object must be converted to JSON. Thanks to Spring’s HTTP message converter support, you don’t need to do this conversion manually. Because Jackson 2 is on the classpath, Spring’s MappingJackson2HttpMessageConverter is automatically chosen to convert the Greeting instance to JSON.

Web applications in Java can be deployed to stand-alone applications servers or embedded servers.
Elaborate on when to choose one over the other.
#Answer: 
Web Server is designed to serve HTTP Content. App Server can also serve HTTP Content but is not limited to just HTTP. It can be provided other protocol support such as RMI/RPC
Application servers have Web Server as integral part of them, that means App Server can do whatever Web Server is capable of.
Web servers are well suited for static content and app servers for dynamic content.

Which annotations are transitively included in the @SpringBootApplication?
#Answer : 
@EnableAutoConfiguration: enable Spring Boot’s auto-configuration mechanism
@ComponentScan: enable @Component scan on the package where the application is located (see the best practices)
@Configuration: allow to register extra beans in the context or import additional configuration classes

## Authors
* **Carolina Resende Marques**  - [CarolinaRMarques](https://github.com/CarolinaRMarques)
