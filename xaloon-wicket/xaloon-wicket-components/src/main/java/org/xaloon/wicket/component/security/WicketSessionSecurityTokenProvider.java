/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xaloon.wicket.component.security;

import java.io.Serializable;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;
import org.xaloon.core.api.security.external.OauthSecurityTokenProvider;

/**
 * @author vytautas r.
 */
public class WicketSessionSecurityTokenProvider implements OauthSecurityTokenProvider {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Access token, stored in wicket session for external authentications
	 */
	private static final MetaDataKey<Serializable> METADATAKEY_AUTH_TOKEN = new MetaDataKey<Serializable>() {
		private static final long serialVersionUID = 1L;
	};

	@Override
	public Serializable getSecurityToken() {
		return Session.get().getMetaData(METADATAKEY_AUTH_TOKEN);
	}

	@Override
	public void setSecurityToken(Serializable securityAccessToken) {
		Session.get().setMetaData(METADATAKEY_AUTH_TOKEN, securityAccessToken);
	}
}
