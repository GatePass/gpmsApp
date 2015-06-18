package org.gpms.web.gpmsBusinessSrv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@ContextConfiguration("classpath*:gpms-business-app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private LoginBusinessSrv loginBusinessSrv;

	@Test
	public void testApp() {
		System.out.println("loginBusinessSrv " + loginBusinessSrv);
		loginBusinessSrv.getLogin();
	}
}
