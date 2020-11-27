Coverage: 34%
# Project Title

IMS system porject 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
1. Make sure to have the latest version of java installed 
2. Make sure you have access to the command terminal on your device 

### Prerequisites


```
JDK 15.1 
```

### Installing

A step by step series of examples that tell you how to get a development env running

1. Download IMS-0.0.1-jar-with-dependencies file from the documentation folder 

```
IMS/documentation/IMS-0.0.1-jar-with-dependencies
```

Go to the folder where you have downloaded the file 
On your explorer window click the path and type 'cmd'
```
Type in java- jar- IMS.0.0.1-jar-with-dependencies.jar
```




## Running the tests

To run the tests Fork the repository to your local machine. Then use an IDE to run the Junit tets 

### Unit Tests 
These tests test if the DAO functions correctly give the right output and if the database is working properly

```
	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		Customer customer = new Customer(1l, "jordan", "harrison");
		expected.add(customer);
		
		assertEquals(expected, customerDAO.readAll());
	
		
	}
	
```
This checks if the read all function works 

### Integration Tests 
These tests are made to make sure the objects are behaving correctly

```
	@Test
	public void testReadAll() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1l,"hoover", "hkfoor"));
		
	
		when(customerDAO.readAll()).thenReturn(customers);
		
		assertEquals(customers, customerController.readAll());
		
		verify(customerDAO, times(1)).readAll();			
	}
	
```



## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use git and github for versioning.

## Authors

* **Benediktas Noreika** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 


