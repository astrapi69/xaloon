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
package org.xaloon.core.jpa.user;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xaloon.core.api.config.Configuration;
import org.xaloon.core.api.exception.CreateClassInstanceException;
import org.xaloon.core.api.persistence.PersistenceServices;
import org.xaloon.core.api.persistence.QueryBuilder;
import org.xaloon.core.api.user.dao.UserDao;
import org.xaloon.core.api.user.model.User;
import org.xaloon.core.api.util.ClassUtil;
import org.xaloon.core.jpa.user.model.JpaAnonymousUser;
import org.xaloon.core.jpa.user.model.JpaUser;

/**
 * @author vytautas r.
 */
@Named("userDao")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class JpaUserDao implements UserDao {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(JpaUserDao.class);

	@Inject
	@Named("persistenceServices")
	private PersistenceServices persistenceServices;

	@Override
	public <T extends User> void save(T user) {
		boolean merge = user.getId() != null || (user.getPhotoThumbnail() != null && user.getPhotoThumbnail().getId() != null);
		if (merge) {
			persistenceServices.edit(user);
		} else {
			persistenceServices.create(user);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends User> T getUserByUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		QueryBuilder queryBuilder = new QueryBuilder("select user from " + getDiscriminator().getSimpleName() + " user");
		queryBuilder.addParameter("user.username", "USERNAME", username);
		return (T)persistenceServices.executeQuerySingle(queryBuilder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends User> T getUserByEmail(String email) {
		QueryBuilder queryBuilder = new QueryBuilder("select user from " + JpaUser.class.getSimpleName() + " user");
		queryBuilder.addParameter("user.email", "EMAIL", email);
		return (T)persistenceServices.executeQuerySingle(queryBuilder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends User> T newUser() {
		try {
			return (T)ClassUtil.newInstance(getDiscriminator());
		} catch (CreateClassInstanceException e) {
			LOGGER.error("Could not create jpa user with provided discriminator", e);
			return (T)new JpaUser();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends User> T newAnonymousUser() {
		return (T)new JpaAnonymousUser();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends User> T newAnonymousUser(T currentUser) {
		JpaAnonymousUser user = new JpaAnonymousUser();
		user.setDisplayName(currentUser.getDisplayName());
		user.setEmail(currentUser.getEmail());
		user.setWebsite(currentUser.getWebsite());
		return (T)user;
	}

	private Class<? extends User> getDiscriminator() {
		return Configuration.get().getPersistedUserClass();
	}
}