# ANSWERS


### a) Identify a couple of examples that use AssertJ expressive methods chaining.

Asserting that an object is equal to another:
```java
assertThat(found).isEqualTo(alex);
```

Asserting that an object is null:
```java
Employee fromDb = employeeRepository.findByName("Does Not Exist");

assertThat(fromDb).isNull();
```

Asserting that an object is not null and its property equals to a specific value:

```java
Employee fromDb = employeeRepository.findById(emp.getId()).orElse(null);

assertThat(fromDb).isNotNull();
assertThat(fromDb.getEmail()).isEqualTo(emp.getEmail());
```

Asserting the list of employees has size of 3 and the name of them should only contain alex, ron and bob.
```java
List<Employee> allEmployees = employeeRepository.findAll();

assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```


### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

Mock repository
```java
@Mock( lenient = true)
private EmployeeRepository employeeRepository;
```
Mock behavior of the repository
```java
Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
```

### c) What is the difference between standard @Mock and @MockBean?

@Mock is a Mockito annotation used to create and inject mocked instances. @Mock is used in unit tests where Spring context is not needed. It creates a mock in the traditional sense, where the instance is completely dummy and only returns what you've specifically stubbed.

@MockBean: This is a Spring Boot annotation used in integration tests where you need to add mock objects to the Spring application context. The mock will replace any existing bean of the same type in the application context. If no bean of the same type is defined, a new one will be added. This is useful when we want to mock a specific bean only for a specific test case.

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The application-integrationtest.properties file is a configuration file that is used specifically for integration tests in a Spring Boot application.

The role of this file is to override the default configurations or the ones specified in the application.properties file for the scope of integration tests. 

### e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 
The main differences between these strategies are the scope of the tests and the components being tested. Strategy C is a unit test for the web layer, while Strategies D and E are integration tests that also involve the data access layer. 