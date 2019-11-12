package net.tinvention.training.ee.product.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.tinvention.training.ee.ITest;
import net.tinvention.training.ee.product.DefaultDeployment;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class AppManagerBeanTest extends ITest {

	@Deployment
	public static WebArchive deployment() throws IllegalArgumentException, FileNotFoundException {
		return new DefaultDeployment().withPersistence().withImportedData().withPackages().getArchive();
	}

	@Inject
	private AppManager appManager;

	@Test
	public void testGetInitilizedProperty() {
		assertNotNull(appManager.getInitilizedProperty());
	}

}
