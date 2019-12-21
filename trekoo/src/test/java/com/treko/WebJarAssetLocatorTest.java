package com.treko;

import org.junit.Assert;

import org.junit.Test;
import org.webjars.WebJarAssetLocator;

public class WebJarAssetLocatorTest {

	@Test
	public void getFullPath_JQuery_ShouldReturnFullPath() {
		WebJarAssetLocator locator = new WebJarAssetLocator();
		String fullPathToBootstrap = locator.getFullPath("jquery.min.js");

		Assert.assertEquals(fullPathToBootstrap, "META-INF/resources/webjars/jquery/3.3.1-1/jquery.min.js");

	}
}
