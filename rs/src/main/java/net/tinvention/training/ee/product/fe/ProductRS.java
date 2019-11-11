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
package net.tinvention.training.ee.product.fe;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.tinvention.training.ee.product.manager.ProductDTO;
import net.tinvention.training.ee.product.manager.ProductManager;

/**
 * 
 * http://localhost:8080/helloworld-rs/api/products
 * http://localhost:8080/helloworld-rs/api/products/1
 * 
 */
@Path("products")
public class ProductRS {
	
	private final Logger LOGGER = Logger.getLogger( this.getClass().getName() );

	@Context
	private UriInfo uriInfo;

	@Inject
	private ProductManager productManager;

	// @formatter:off
	/**
	 * 1. Respond to a GET request on the following web-service endpoint:
	 * /rsService/product/{id} where {id} is the numeric identifier for the product
	 * resource, matching the id field in the product data files
	 * 
	 * @return
	 */
	// @formatter:on
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public ProductDTO load(@PathParam("id") Long id) {
		LOGGER.fine("load, ProductRS: " + this + ", productManager: " + productManager );
		return productManager.load(id);
	}

	// @formatter:off
	/**
	 * 2. Respond to a GET request on the following web-service endpoint:
	 * /rsService/products This request will respond with a collection of products.
	 * The collection request can be filtered and sorted using the following
	 * query-string request parameters: type = food | toy | furniture make = any
	 * string
	 * 
	 * 
	 * @return
	 */
	// @formatter:on
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductDTO> list() {
		LOGGER.fine("list, ProductRS: " + this + ", productManager: " + productManager );
		return productManager.list();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response add(@Valid ProductDTO p) {
		LOGGER.fine("add, ProductRS: " + this + ", productManager: " + productManager );
		ProductDTO saved = productManager.store(p);
		URI createdResourceUri = uriInfo.getAbsolutePathBuilder().path(saved.getId().toString()).build();
		return Response.created(createdResourceUri).build();
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") Long id) {
		LOGGER.fine("add, ProductRS: " + this + ", productManager: " + productManager );
		
		productManager.remove(id);

		return Response.status(Response.Status.OK).build();
	}

}
