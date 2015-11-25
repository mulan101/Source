package com.lbass.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfigContainer {

	private static final Logger log = LoggerFactory.getLogger(AppConfigContainer.class);
	private static ConfigDataBean configData = null;

	public static void setConfigData(ConfigDataBean config) {
		if(configData == null) {
			AppConfigContainer.configData = config;			
		} else {
			log.error("not change application config");
		}
	}
	
	public static ConfigDataBean getConfigData() {
		return configData;
	}
}
