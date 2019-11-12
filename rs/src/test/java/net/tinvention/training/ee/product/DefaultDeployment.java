package net.tinvention.training.ee.product;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import net.tinvention.training.ee.ITest;
import net.tinvention.training.ee.product.manager.ProductManager;
import net.tinvention.training.ee.product.repository.ProductRepository;

public class DefaultDeployment {

	private static final String WEBAPP_SRC = "src/main/webapp";

	private WebArchive webArchive;

	public DefaultDeployment() {
		webArchive = ShrinkWrap.create(WebArchive.class, "unit-test.war").addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/beans.xml"));
	}

	public DefaultDeployment withPersistence() {
		webArchive = webArchive.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
		return this;
	}

	public DefaultDeployment withImportedData() {
		webArchive = webArchive.addAsResource("import.sql");
		return this;
	}

	public DefaultDeployment withPackages() {
		// @formatter:off
		webArchive
		.addPackages(true, ProductRepository.class.getPackage(), ProductManager.class.getPackage())
		.addClass(ITest.class);
		return this;
		// @formatter:on
	}

	public WebArchive getArchive() {
		return webArchive;
	}
}
