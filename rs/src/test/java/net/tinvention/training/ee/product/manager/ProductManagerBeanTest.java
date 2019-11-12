package net.tinvention.training.ee.product.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.tinvention.training.ee.product.DefaultDeployment;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class ProductManagerBeanTest {

	@Deployment
	public static WebArchive deployment() throws IllegalArgumentException, FileNotFoundException {
		return new DefaultDeployment().withPersistence().withImportedData().withPackages().getArchive();
	}

	@Inject
	private ProductManager productManager;

	private ProductDTO toBeSaved;

	private final Long ID10 = -10L;

	@Before
	public void setUp() throws Exception {
		toBeSaved = new ProductDTO();
		toBeSaved.setName("name_1");
	}

	@Test
	public void list() throws Exception {
		assertEquals(2, productManager.list().size());
	}

	@Test
	public void store() {
		assertNotNull(productManager.store(toBeSaved).getId());
	}

	@Test
	public void load() {
		assertNotNull(productManager.load(ID10).getName());
	}

	@Test
	public void remove() {
		productManager.remove(ID10);
		assertEquals(1, productManager.list().size());
	}

}
