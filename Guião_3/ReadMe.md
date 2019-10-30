##-------------3.1------------
Why is that the Employee entity does not have getters and setters defined?
Because of the annotation @Data at the beggining.  @Data is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together. In other words, @Data generates all the boilerplate that is normally associated with simple java objects
##-------------3.2------------
Explain the annotations @Table, @Colum, @Id found in the Employee entity.
@Table - specifies the details of the table that will be used to persist the entity in the database.
@Column - specifies the details of the column that will be used to persist the entity in the database.
@ID- specifies the ID (identifies the Primary Key) of the respective table that will be used to persist the entity in the database.

Explain the use of the annotation @AutoWire
The @Autowired annotation provides more control over where and how autowiring should be accomplished. 
The annotation can be used directly on properties, therefore eliminating the need for getters and setters
##-------------3.3------------
Docker Compose used:
version: '3.1'

services:
 db:
  image: postgres
  restart: always
  environment:
   POSTGRES_PASSWORD: mypassword
  volumes:
    - ./postgres-data:/var/lib/postgresql/data
  ports:
    - 5432:5432
    
The class is annotated with the @Controller annotation to tell the Spring framework that it is a controller.
	The @GetMapping annotation above the method signals the Spring Core that this method should only handle GET requests.
	The getReport() method later will return the base form template in which the user can submit the issue they found. Right now it only returns a string, the functionality will be added later.
	The @PostMapping annotation signals that this method should only handle POST requests and thus only gets called when a POST request is received.
	The submitReport() method is responsible for handling the user input after submitting the form. When the data is received and handled (e.g. added to the database), this method returns the same issuereport template from the first controller method.
	the getIssues() method will handle the HTML template for a list view in which all the requests can be viewed. This method will return a template with a list of all reports that were submitted. The @ResponseBody annotation will be removed in a later step. For now we need to output just the text to the HTML page. If we would remove it now the framework would search for a template with the given name and since there is none would throw an error.
The @Entity annotation tells our JPA provider Hibernate that this class should be mapped to the database.
	Set the database table name with the @Table(name = "issues") annotation. By explicitly setting the table name you avoid the possibility of accidently breaking the database mapping by renaming the class later on.
	
	
	

Spring provides a Model object which can be passed into the controller. You can configure this model object via the addAttribute() method. The first parameter in this method is the key under which the second parameter can be accessed. You will use this name to refer to this object in the template.
	This will pass a new IssueReport object to the template
	This will deliver the data submitted via the form to this method. In the submitReport() method we also want to handle the data submitted via the form.
	This will pass a new IssueReport object to the template. To do this we also need to add IssueReport issueReport to the method parameters.
	Since we want the template to show some kind of feedback upon receiving the form data, we add another attribute containing a boolean. If it’s set to true the template will show some kind of modal or confirming message. Since this boolean is only passed to the template if the route hit from the user was via POST HTTP method (and thus only upon form submission) the confirmation message is ONLY shown after the form was submitted.
