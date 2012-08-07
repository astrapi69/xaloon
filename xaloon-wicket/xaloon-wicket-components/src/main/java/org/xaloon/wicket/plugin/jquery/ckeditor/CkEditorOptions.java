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
package org.xaloon.wicket.plugin.jquery.ckeditor;

import java.io.Serializable;

import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.odlabs.wiquery.core.options.Options;

/**
 * @author vytautas r.
 */
public class CkEditorOptions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final JavaScriptResourceReference jsCKEditorResource = new JavaScriptResourceReference(CkEditorOptions.class, "ckeditor.js");

	public static final JavaScriptResourceReference jsCKEditorConfig = new JavaScriptResourceReference(CkEditorOptions.class, "config.js");

	public static final JavaScriptResourceReference jsCKEditorStyle = new JavaScriptResourceReference(CkEditorOptions.class,
		"plugins/styles/styles/default.js");


	public static final JavaScriptResourceReference jsCKEditorAdapterResource = new JavaScriptResourceReference(CkEditorOptions.class,
		"ckeditor-jqueryadapter-3.5.js");

	org.odlabs.wiquery.core.options.Options options;

	/**
	 * Construct.
	 */
	public CkEditorOptions() {
		options = new org.odlabs.wiquery.core.options.Options();
	}

	/**
	 * @return
	 */
	public Options getOptions() {
		return options;
	}
}
