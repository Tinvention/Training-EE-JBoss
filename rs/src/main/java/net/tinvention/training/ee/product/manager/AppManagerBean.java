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

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER) // This is the default..
public class AppManagerBean implements AppManager {

	private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

	private String initilizedProperty;

	@Override
	@Lock(LockType.READ)
	public String getInitilizedProperty() {
		LOGGER.fine("getInitilizedProperty, initilizedProperty: " + initilizedProperty);
		return initilizedProperty;
	}

	@PostConstruct
	@Lock(LockType.WRITE)
	public void initialize() {
		LOGGER.fine("initialize");
		initilizedProperty = "This the Singleton initlized value";
	}
}
