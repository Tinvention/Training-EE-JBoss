package net.tinvention.training.ee.product.manager;

import java.util.List;

public interface ProductManager {

	public List<ProductDTO> list();

	public ProductDTO load(Long id);

	public ProductDTO store(ProductDTO toBeSaved);

	public void remove(Long idToBeRemoved);

}
