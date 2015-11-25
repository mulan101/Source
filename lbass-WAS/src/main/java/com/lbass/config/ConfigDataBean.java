package com.lbass.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigDataBean {
	private static final Logger log = LoggerFactory.getLogger(AppConfigContainer.class);
	private int port;
	private Map<String, HostDataBean> hostsMap = null;
	private Map<String, VirtualHostDatBean> virtualHostsMap = null;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	public void setHostsMap(Map<String, HostDataBean> hostsMap) {
		this.hostsMap = hostsMap;
	}
	
	public HostDataBean getHostMap(String hostName) {
		return this.hostsMap.get(hostName);
	}
	public Map<String, VirtualHostDatBean> getVirtualHostsMap() {
		return virtualHostsMap;
	}
	public void setVirtualHostsMap(Map<String, VirtualHostDatBean> virtualHostsMap) {
		this.virtualHostsMap = virtualHostsMap;
	}

}
