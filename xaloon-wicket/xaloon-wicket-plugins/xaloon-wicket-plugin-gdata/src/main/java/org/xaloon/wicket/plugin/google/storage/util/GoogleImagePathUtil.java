package org.xaloon.wicket.plugin.google.storage.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class GoogleImagePathUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String getGoogleResizedPath(String imagePath, int size) {
		if (StringUtils.isEmpty(imagePath) || !imagePath.contains("ggpht.com") || !imagePath.contains("google")) {
			return imagePath;
		}
		
		String[] path = imagePath.split("/");
		int lastElem = path.length-1;
		
		if (isGoogleWidthProvided(path[lastElem-1])) {
			//replace existing with new one
			path[lastElem-1] = "s" + size;
			return StringUtils.join(path, "/");
		} else {
			List<String> items = Arrays.asList(path);
			items.add(lastElem-1, "s" + size);
			return StringUtils.join(items, "/");
		}
	}

	private static boolean isGoogleWidthProvided(String path) {
		if (!path.startsWith("s")) {
			return false;
		}
		String width = path.substring(1);
		return StringUtils.isNumeric(width);
	}
}