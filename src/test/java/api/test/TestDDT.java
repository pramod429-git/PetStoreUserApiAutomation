package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class TestDDT {
	
	@Test(priority = 1,dataProvider="data",dataProviderClass = DataProviders.class)
	void testPostUser(String userId, String username,String firstname,String lastname,String email,String pwd,String ph) {
		User data=new User();
		
		data.setId(Integer.parseInt(userId));
		data.setUsername(username);
		data.setFirstName(firstname);
		data.setLastName(lastname);
		data.setEmail(email);
		data.setPassword(pwd);
		data.setPhone(ph);
		
		Response response=UserEndPoints.createUser(data);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2,dataProvider="username",dataProviderClass = DataProviders.class)
	void testGetUser(String username) {
		Response response=UserEndPoints.getUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().all();
	}
	
	@Test(priority = 3,dataProvider="data",dataProviderClass = DataProviders.class)
	void testUpdateUser(String userId, String username,String firstname,String lastname,String email,String pwd,String ph) {
		User data=new User();
		data.setPassword(pwd);
		data.setPhone(ph);
		
		Response response=UserEndPoints.updateUser(data,username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 4,dataProvider="username",dataProviderClass = DataProviders.class)
	void testDeleteUser(String username) {
		Response response=UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
