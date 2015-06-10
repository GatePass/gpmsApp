/**
 * 
 */
package org.gpms.web.test;

import static org.junit.Assert.fail;

import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author narenda.kumar
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/resources/test-context.xml" })
public class TestDbConnection extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println(">>>>>>>>>> " + entityManagerFactory);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		fail("Not yet implemented");
	}

}
