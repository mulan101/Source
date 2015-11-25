package com.lbass.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class ConfigReader {
	
	private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);

	public ConfigDataBean getConfigData(String filePath) throws IOException {
		Map resultMap = null;
		BufferedReader br = null;
		String sCurrentLine;
		StringBuffer fullText = new StringBuffer();
		ConfigDataBean configBean = new ConfigDataBean();

		
		File configFile = new File(filePath);
		try {
		
			if(!configFile.exists()) {
				String tempRootPath = ConfigReader.class.getResource(".").getPath();
				int index = tempRootPath.indexOf("com/lbass/config/");
				tempRootPath = tempRootPath.substring(1, index);
				filePath = tempRootPath + "/" + filePath; 
			}
			
			br = new BufferedReader(new FileReader(filePath));
			while ((sCurrentLine = br.readLine()) != null) {
				fullText.append(sCurrentLine);
			}
			Gson gson = new Gson();
			resultMap = gson.fromJson(fullText.toString(), HashMap.class);
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			if(br != null) {
				br.close();				
			}
		}
		
		
		int port = Integer.parseInt((String)resultMap.get("PORT"));
		configBean.setPort(port);
		
		List virtualHostList = (List)resultMap.get("VIRTUALHOST");
		Map virtualHostsMap = new HashMap<String, VirtualHostDatBean>();
		for(int i = 0 ; i < virtualHostList.size() ; i++) {
			VirtualHostDatBean vitualHostBean = new VirtualHostDatBean();
			Map virtualHostDataMap = (Map)virtualHostList.get(i);
			settingVirtualHostData(virtualHostDataMap, vitualHostBean);
			virtualHostsMap.put(vitualHostBean.getHostName(), vitualHostBean);
		}
		configBean.setVirtualHostsMap(virtualHostsMap);
		
		List hostList = (List)resultMap.get("HOST");
		Map hostsMap = new HashMap<String, HostDataBean>();
		for(int i = 0 ; i < hostList.size() ; i++) {
			HostDataBean hostBean = new HostDataBean();
			Map hostDataMap = (Map)hostList.get(i);
			settingHostData(hostDataMap, hostBean);
			hostsMap.put(hostBean.getHostName(), hostBean);
		}
		
		configBean.setHostsMap(hostsMap);
		log.debug(resultMap.toString());
		return configBean;
	}

	private void settingVirtualHostData(Map vHostDataMap,
			VirtualHostDatBean vHostBean) {
		String hostName = (String)vHostDataMap.get("HOSTNAME");
		String className = (String)vHostDataMap.get("CLASSNAME");
		vHostBean.setHostName(hostName);
		vHostBean.setClassName(className);
		
	}

	private void settingHostData(Map hostDataMap, HostDataBean hostBean) {
		String hostName = (String)hostDataMap.get("HOSTNAME");
		String docbase = (String)hostDataMap.get("DOCBASE");
		Map errorMap = (Map)hostDataMap.get("ERRORPAGE");
		List filters = (List)hostDataMap.get("FILTERS");
		List servlets = (List)hostDataMap.get("SERVLETS");
		String errorPage403 = (String)errorMap.get("403");
		String errorPage404 = (String)errorMap.get("404");
		String errorPage500 = (String)errorMap.get("500");
		hostBean.setHostName(hostName);
		hostBean.setDocbase(docbase);
		hostBean.setErrorPage_403(errorPage403);
		hostBean.setErrorPage_404(errorPage404);
		hostBean.setErrorPage_500(errorPage500);
		hostBean.setFilters(filters);
		hostBean.setServlets(servlets);
	}
}

