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
package org.xaloon.wicket.component.resource;

import java.io.IOException;
import java.io.InputStream;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.xaloon.core.api.storage.AbstractInputStreamContainer;

/**
 * @author vytautas r.
 */
public class WicketInputStreamContainer extends AbstractInputStreamContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final FileUpload fileUpload;

	/**
	 * Construct.
	 * 
	 * @param fileUpload
	 */
	public WicketInputStreamContainer(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return fileUpload.getInputStream();
	}

	@Override
	public void close() {
		if (fileUpload == null) {
			return;
		}
		fileUpload.closeStreams();
		fileUpload.delete();
	}

	@Override
	public boolean isEmpty() {
		return fileUpload == null;
	}
}
