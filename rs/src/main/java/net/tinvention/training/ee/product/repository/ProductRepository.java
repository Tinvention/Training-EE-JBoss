//@formatter:off
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
//@formatter:on
package net.tinvention.training.ee.product.repository;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProductRepository {

	private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

	// TODO this is a proxy using it by ejb , a entityManager per tx is used
	// use only with thread managed by the AS
	// it as to be used with JTA tx manager
	// persistence context scope identical to the current transaction scope. (
	// default - ContextType.TRANSACTION)
	// if ContextType is different, you may consider using SFSB
	@PersistenceContext
	private EntityManager entityManager;

	public ProductEntity store(ProductEntity toBeSaved) {
		LOGGER.fine("list, ProductRepository: " + this + ", entityManager: " + entityManager);
		entityManager.persist(toBeSaved);
		return toBeSaved;
	}

	public ProductEntity load(final Long id) {
		LOGGER.fine("list, ProductRepository: " + this + ", entityManager: " + entityManager);
		return entityManager.find(ProductEntity.class, id);
	}

	public void remove(ProductEntity toBeRemoved) {
		LOGGER.fine("list, ProductRepository: " + this + ", entityManager: " + entityManager);
		entityManager.remove(toBeRemoved);
	}

	public List<ProductEntity> list() {
		LOGGER.fine("list, ProductRepository: " + this + ", entityManager: " + entityManager);
		Query query = entityManager.createNamedQuery("products");

		@SuppressWarnings("unchecked")
		List<ProductEntity> list = query.getResultList();
		return list;
	}

}
