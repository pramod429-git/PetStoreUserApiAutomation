package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	Faker faker;
	User data;
	public Logger logger;
	
	@BeforeClass
	void userSetup() {
		data=new User();
		faker=new Faker();
		data.setId(faker.idNumber().hashCode());
		data.setUsername("user" + faker.number().digits(5));
		data.setFirstName(faker.name().firstName());
		data.setLastName(faker.name().lastName());
		data.setEmail(faker.internet().emailAddress());
		data.setPassword(faker.internet().password(5, 7));
		data.setPhone(faker.phoneNumber().cellPhone());
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	void testPostUser() {
		logger.info("***********Creating a User***************");
		Response response=UserEndPoints2.createUser(data);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***********User Created***************");
	}
	
	@Test(priority = 2)
	void testGetUser() {
		logger.info("***********Retriving a User Detailes***************");
	
//this keyword not required since we are mentioning as current user for standerd practice we have used
		Response response=UserEndPoints2.getUser(this.data.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***********Displaying a User Detailes***************");
	}
	
	@Test(priority=3)
	void testUpdateUser() {
		logger.info("***********Updating a User Detailes***************");
		
		data.setFirstName(faker.name().firstName());
		data.setLastName(faker.name().lastName());
		data.setEmail(faker.internet().emailAddress());
		
		Response response=UserEndPoints2.updateUser(data, this.data.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***********User Detailes Updated***************");
		
	}
	
	@Test(priority = 4)
	void testDeleteUser() {
		logger.info("***********Deleting a User Detailes***************");
		
		Response response=UserEndPoints2.deleteUser(this.data.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***********User Detailes Deleted***************");
	}
	
	
	
}
