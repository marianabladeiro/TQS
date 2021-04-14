Lab4 - Ex1
a)Identify a couple of examples on the use of AssertJ expressive methods chaining

In the EmployeeRepositoryTest.java file:
    
 - assertThat(fromDb).isNotNull();
 - assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());
 - assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
 
In the EmployeeRestControllerTemplateIt.java file:

 - assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
 
In the EmployeeRestControllerIT.java file:
 - assertThat(found).extracting(Employee::getName).containsOnly("bob");
 
b)Identify an example in which you mock the behavior of the repository (and avoid involving a database).

We can find examples in the EmployeeService_UnitTest.java file:
 - Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);

In this case, we are instructing the employeeRepository mock to return "john" when
the method getName() is called.

c)What is the difference between standard @Mock and @MockBean?
@Mock is the shorthand for the Mockito.mock() method. It is used in a test class. This
annotation makes it easir to find the problem mock in case something wrong happens.

@MockBean is used to add mock objects to the SpringBoot application context. This annotation
will replace existing beans of the same type in the application context. This is particulary
helpful in integration tests where a certain bean needs to be mocked.

So the main difference focuses on the type of test being done. @Mock is used in unit testing
(using JUnit and Mockito), while @MockBean is used when a test is backed by a Spring test
context and you want to make an addition or replacement to a bean with a mocked version of it.

d)What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
The role of the properties file is to make sure that the integration tests work and also work in a different environment.
It is used with the annotation @TestPropertySource. 

