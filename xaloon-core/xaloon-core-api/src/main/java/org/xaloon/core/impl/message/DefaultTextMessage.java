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
package org.xaloon.core.impl.message;

import org.xaloon.core.api.classifier.ClassifierItem;
import org.xaloon.core.api.message.model.TextMessage;

/**
 * @author vytautas r.
 */
public class DefaultTextMessage extends DefaultMessage implements TextMessage {
	private static final long serialVersionUID = 1L;

	private ClassifierItem folder;

	private String subject;

	public ClassifierItem getFolder() {
		return folder;
	}

	public void setFolder(ClassifierItem folder) {
		this.folder = folder;
	}

	/**
	 * @see org.xaloon.core.message.Message#getSubject()
	 */
	@Override
	public String getSubject() {
		return subject;
	}

	/**
	 * @see org.xaloon.core.message.Message#setSubject(java.lang.String)
	 */
	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
