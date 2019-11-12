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

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.tinvention.training.ee.product.manager.AppManager;

/**
 * 
 * http://localhost:8080/helloworld-rs/api/app
 * 
 * 
 */
@Path("app")
public class AppRS {

	private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

	@Inject
	private AppManager appManager;

	// @formatter:off
	/**
	 * 1. Respond to a GET request on the following web-service endpoint:
	 * /rsService/app/ espose singleton initlized property
	 * 
	 * @return
	 */
	// @formatter:on
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public String getAppProperty() {
		LOGGER.fine("getAppProperty, AppRS: " + this + ", appManager: " + appManager);
		return appManager.getInitilizedProperty();
	}

}
