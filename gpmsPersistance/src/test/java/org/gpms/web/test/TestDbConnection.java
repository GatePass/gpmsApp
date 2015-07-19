package org.gpms.web.test;

import java.util.List;

import org.gpms.web.domain.AssetsRepository;
import org.gpms.web.domain.UserAssetsRepository;
import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.assets.UserAssetEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath*:gpms-persistance-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDbConnection extends AbstractJUnit4SpringContextTests {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserAssetsRepository userAssetsRepository;

	@Autowired
	AssetsRepository assetsRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		List<UserAssetEntity> list = userAssetsRepository
				.getUserAssetByCorpEmail("gpmsEmployee3@cgi.com");
		System.out.println(list.size());
	}

	/**
	 * @throws java.lang.Exception
	 */
	// @After
	// public void tearDown() throws Exception {
	// }

	@Test
	public void test() {
		System.out.println(">>>>>>>>>> " + usersRepository);
		System.out.println(">>>>>>>>>> ");
		// usersRepository.createUser();
		usersRepository.getAllSecurityQuestionEntity();

		// fail("Not yet implemented");
	}
}
