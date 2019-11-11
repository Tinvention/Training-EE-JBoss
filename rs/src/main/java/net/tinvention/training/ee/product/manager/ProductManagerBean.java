/**
 * Copyright Tinvention -Ingegneria Informatica- http://tinvention.net/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tinvention.training.ee.product.manager;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.tinvention.training.ee.product.repository.Address;
import net.tinvention.training.ee.product.repository.ProductEntity;
import net.tinvention.training.ee.product.repository.ProductRepository;

@Stateless
public class ProductManagerBean implements ProductManager {
	
	private final Logger LOGGER = Logger.getLogger( this.getClass().getName() );

	@Inject
	private ProductRepository productRepository;

	@Override
	public List<ProductDTO> list() {
		LOGGER.fine("list, ProductManager: " + this + ", productRepository: " + productRepository );
		return entityToDTO(productRepository.list());
	}

	@Override
	public ProductDTO load(final Long id) {
		LOGGER.fine("load, ProductManager: " + this + ", productRepository: " + productRepository );
		return entityToDTO(productRepository.load(id));
	}

	@Override
	public ProductDTO store(ProductDTO toBeSaved) {
		LOGGER.fine("store, ProductManager: " + this + ", productRepository: " + productRepository );
		return entityToDTO(productRepository.store(dtoToEntity(toBeSaved)));
	}

	@Override
	public void remove(Long idToBeRemoved) {
		LOGGER.fine("remove, ProductManager: " + this + ", productRepository: " + productRepository );
		productRepository.remove(productRepository.load(idToBeRemoved));
	}

	private List<ProductDTO> entityToDTO(final List<ProductEntity> entities) {
		return entities.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	//TODO may I use mapstruct or similar other libs ?
	private ProductDTO entityToDTO(final ProductEntity entity) {
		ProductDTO result = new ProductDTO();
		result.setId(entity.getId());
		result.setPurchaseDate(entity.getPurchaseDate());
		result.setName(entity.getName());
		
		Optional.ofNullable(entity.getAddress()).ifPresent(address -> {
			result.setCity(address.getCity());
			result.setCountry(address.getCountry());
		});

		return result;
	}

	private ProductEntity dtoToEntity(final ProductDTO dto) {
		ProductEntity result = new ProductEntity();
		Address address = new Address();
		address.setCity(dto.getCity());
		address.setCountry(dto.getCountry());
		result.setAddress(address);

		result.setId(dto.getId());
		result.setPurchaseDate(dto.getPurchaseDate());
		result.setName(dto.getName());
		return result;
	}

}
