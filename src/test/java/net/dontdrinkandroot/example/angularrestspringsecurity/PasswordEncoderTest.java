package net.dontdrinkandroot.example.angularrestspringsecurity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context.xml" })
public class PasswordEncoderTest
{

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Test
	public void testDefaultPasswords()
	{

		System.out.println(this.passwordEncoder.encode("user"));
		System.out.println(this.passwordEncoder.encode("admin"));

		Assert.assertEquals(
				"949f4ae5896a01d231c6f5af079dff23bab120cec83b787f527bc02b03f8fc91",
				this.passwordEncoder.encode("user"));
		Assert.assertEquals(
				"f82959d41f9330bd853d3e11345e08eda948544666bfc17806493df9d4b305f0",
				this.passwordEncoder.encode("admin"));

		Assert.assertTrue(this.passwordEncoder.matches(
				"user",
				"949f4ae5896a01d231c6f5af079dff23bab120cec83b787f527bc02b03f8fc91"));
		Assert.assertTrue(this.passwordEncoder.matches(
				"admin",
				"f82959d41f9330bd853d3e11345e08eda948544666bfc17806493df9d4b305f0"));
	}

}
