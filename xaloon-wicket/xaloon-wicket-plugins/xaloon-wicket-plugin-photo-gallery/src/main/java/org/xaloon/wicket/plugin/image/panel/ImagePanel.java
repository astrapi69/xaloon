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
package org.xaloon.wicket.plugin.image.panel;

import javax.inject.Inject;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.AjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.xaloon.core.api.storage.ByteArrayAsInputStreamContainer;
import org.xaloon.core.api.storage.FileDescriptor;
import org.xaloon.core.api.storage.FileRepositoryFacade;
import org.xaloon.core.api.storage.UrlInputStreamContainer;
import org.xaloon.core.api.util.HtmlElementEnum;

/**
 * @author vytautas r.
 */
public class ImagePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int imageWidth = 200;

	private int imageHeight = 100;

	@Inject
	private FileRepositoryFacade fileRepositoryFacade;
	
	/**
	 * Construct.
	 * 
	 * @param id
	 * @param model
	 */
	public ImagePanel(String id, IModel<org.xaloon.core.api.image.model.Image> model) {
		super(id, model);
		setOutputMarkupId(true);
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		removeAll();
		final org.xaloon.core.api.image.model.Image image = (org.xaloon.core.api.image.model.Image)getDefaultModelObject();


		

		// Add show temporary image
		FileDescriptor temporaryFiledeDescriptor = image.getThumbnail();
		if (temporaryFiledeDescriptor == null) {
			temporaryFiledeDescriptor = image;
		}
		if (temporaryFiledeDescriptor != null && temporaryFiledeDescriptor.getImageInputStreamContainer() == null) {
			if (temporaryFiledeDescriptor.isExternal()) {
				temporaryFiledeDescriptor.setImageInputStreamContainer(new UrlInputStreamContainer(temporaryFiledeDescriptor.getPath()));
			} else {
				temporaryFiledeDescriptor.setImageInputStreamContainer(new ByteArrayAsInputStreamContainer(fileRepositoryFacade.getFileByPath(temporaryFiledeDescriptor.getPath())));
			}
		}
		TemporaryResource temporaryResource = new TemporaryResource(temporaryFiledeDescriptor);
		Image temporaryImage = new NonCachingImage("temporary-image", temporaryResource);
		temporaryImage.add(AttributeModifier.replace(HtmlElementEnum.WIDTH.value(), String.valueOf(imageWidth)));
		temporaryImage.add(AttributeModifier.replace(HtmlElementEnum.HEIGHT.value(), String.valueOf(imageHeight)));
		temporaryImage.setVisible(!temporaryResource.isEmpty());
		add(temporaryImage);

		// Add delete image link
		add(new AjaxLink<Void>("delete-image-link") {
			private static final long serialVersionUID = 1L;

			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new AjaxCallDecorator() {
					private static final long serialVersionUID = 1L;

					@Override
					public CharSequence decorateScript(Component c, CharSequence script) {
						return "if(!confirm('Are you sure you want to delete this item?')) return false;" + script;
					}
				};
			}

			@Override
			public void onClick(AjaxRequestTarget target) {
				deleteFileDescriptor(image);
				Component componentToRefresh = getOnCloseRefreshComponent();
				if (componentToRefresh != null) {
					target.add(componentToRefresh);
				}
			}
		});
	}

	protected Component getOnCloseRefreshComponent() {
		return ImagePanel.this;
	}


	/**
	 * Delete file descriptor and file
	 * 
	 * @param imageToDelete
	 */
	protected void deleteFileDescriptor(org.xaloon.core.api.image.model.Image imageToDelete) {

	}

	/**
	 * @param imageWidth
	 */
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	/**
	 * @param imageHeight
	 */
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
}
