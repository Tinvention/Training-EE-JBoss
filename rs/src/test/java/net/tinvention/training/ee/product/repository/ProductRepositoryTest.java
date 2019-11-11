package net.tinvention.training.ee.product.repository;

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
public class ProductRepositoryTest {

	@Deployment
	public static WebArchive deployment() throws IllegalArgumentException, FileNotFoundException {
		return new DefaultDeployment().withPersistence().withImportedData().getArchive()
				.addPackage(ProductRepository.class.getPackage());
	}

	@Inject
	private ProductRepository productRepository;

	private ProductEntity toBeSaved;

	private final Long ID10 = -10L;

	@Before
	public void setUp() throws Exception {
		toBeSaved = new ProductEntity();
		toBeSaved.setName("name_1");
	}

	@Test
	public void list() throws Exception {
		assertEquals(2, productRepository.list().size());
	}

	@Test
	public void testStore() {
		assertNotNull(productRepository.store(toBeSaved).getId());
	}

	@Test
	public void testLoad() {
		assertNotNull(productRepository.load(ID10).getName());
	}

	@Test
	public void testRemove() {
		productRepository.remove(productRepository.load(ID10));
		assertEquals(1, productRepository.list().size());
	}

}
