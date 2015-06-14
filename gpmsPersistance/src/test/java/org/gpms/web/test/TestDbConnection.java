package org.gpms.web.test;

import org.gpms.web.domain.Login;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath*:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDbConnection extends AbstractJUnit4SpringContextTests {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	Login login;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println(">>>>>>>>>>........... " + applicationContext);
	}

	/**
	 * @throws java.lang.Exception
	 */
	// @After
	// public void tearDown() throws Exception {
	// }

	@Test
	public void test() {
		System.out.println(">>>>>>>>>> " + login);
		System.out.println(">>>>>>>>>> ");
		login.getUsers();

		// fail("Not yet implemented");
	}
}
