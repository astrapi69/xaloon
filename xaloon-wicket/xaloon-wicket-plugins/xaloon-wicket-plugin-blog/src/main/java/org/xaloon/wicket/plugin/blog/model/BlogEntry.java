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
package org.xaloon.wicket.plugin.blog.model;

import java.util.Date;
import java.util.List;

import org.xaloon.core.api.bookmark.Bookmarkable;
import org.xaloon.core.api.classifier.ClassifierItem;
import org.xaloon.core.api.image.model.Album;
import org.xaloon.core.api.keyvalue.KeyValue;
import org.xaloon.core.api.plugin.comment.Commentable;
import org.xaloon.core.api.storage.FileDescriptor;
import org.xaloon.core.api.user.model.User;
import org.xaloon.wicket.plugin.blog.path.BlogEntryPathTypeEnum;

/**
 * Interface defines properties, which should be used to store and display blog entry information. Concrete implementation should take care how to
 * store and return these required elements
 * 
 * @author vytautas r.
 */
public interface BlogEntry extends Commentable, Bookmarkable, Album {
	/**
	 * Gets internal id of blog entry
	 * 
	 * @return id of this entry
	 */
	Long getId();

	/**
	 * Returns the title of blog entry
	 * 
	 * @return string representation of this object
	 */
	String getTitle();

	/**
	 * Returns blog entry creation date
	 * 
	 * @return java date representation when blog entry was created
	 */
	Date getCreateDate();

	/**
	 * Entry update date
	 * 
	 * @return java date representation when blog entry was updated
	 */
	Date getUpdateDate();

	/**
	 * Author of blog entry
	 * 
	 * @return author instance of blog entry
	 */
	User getOwner();

	/**
	 * Sets current user as author
	 * 
	 * @param owner
	 *            user instance to set as author of blog entry
	 */
	void setOwner(User owner);

	/**
	 * Short description of blog entry
	 * 
	 * @return first part of blog content, max 255 chars
	 */
	String getDescription();

	/**
	 * Sets blog entry desription
	 * 
	 * @param description
	 *            string value to set as description of blog entry
	 */
	void setDescription(String description);

	/**
	 * Get custom path for blog entry if do not want to use generated by default
	 * 
	 * @return string representation of custom blog entry path. null is returned if no custom path is used for this blog entry
	 */
	String getCustomPath();

	/**
	 * Returns image descriptor
	 * 
	 * @return file descriptor of image
	 */
	FileDescriptor getThumbnail();

	/**
	 * Sets file descritor as thumbnail photo of blog entry
	 * 
	 * @param thumbnailIdentifier
	 *            file descriptor with link to actual image
	 */
	void setThumbnail(FileDescriptor thumbnailIdentifier);

	/**
	 * Returns category of blog entry. Concrete implementation is responsible how category should be stored. Category should start with delimiter "/",
	 * for example "/sports"
	 * 
	 * @return classifier item of blog entry category
	 */
	ClassifierItem getCategory();

	/**
	 * Returns list of tags, related to blog entry
	 * 
	 * @return empty list if there are no tags added to blog entry
	 */
	List<? extends KeyValue<String, String>> getTags();

	/**
	 * Returns if blog entry should be always on top
	 * 
	 * @return true if blog entry is sticky and should be shown on top, false - otherwise
	 */
	boolean isSticky();

	/**
	 * Returns the blog entry path type enumeration. This defines how blog entry path should be constructed:
	 * <p>
	 * is date stamp included in blog entry link
	 * <p>
	 * <p>
	 * is username included in blog entry link
	 * </p>
	 * <p>
	 * etc.
	 * </p>
	 * 
	 * @return enumeration of blog entry path type
	 */
	BlogEntryPathTypeEnum getBlogEntryPathType();

	/**
	 * @param blogEntryPathType
	 *            sets the type of the blog entry path
	 */
	void setBlogEntryPathType(BlogEntryPathTypeEnum blogEntryPathType);

	/**
	 * Content of blog entry
	 * 
	 * @return full blog entry, which is stored in repository as Blog or String
	 */
	String getContent();
	
	/**
	 * Sets the content of current blog entry
	 * 
	 * @param content full blog entry, which is stored in repository as Blog or String
	 */
	void setContent(String content);
}
