package com.lbass.config;

import java.util.List;


public class HostDataBean {
	private String hostName;
	private String docbase;
	private String errorPage_403;
	private String errorPage_404;
	private String errorPage_500;

	private List filters;
	private List servlets;
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getDocbase() {
		return docbase;
	}
	public void setDocbase(String docbase) {
		this.docbase = docbase;
	}
	public String getErrorPage_403() {
		return errorPage_403;
	}
	public void setErrorPage_403(String errorPage_403) {
		this.errorPage_403 = errorPage_403;
	}
	public String getErrorPage_404() {
		return errorPage_404;
	}
	public void setErrorPage_404(String errorPage_404) {
		this.errorPage_404 = errorPage_404;
	}
	public String getErrorPage_500() {
		return errorPage_500;
	}
	public void setErrorPage_500(String errorPage_500) {
		this.errorPage_500 = errorPage_500;
	}

	public List getFilters() {
		return filters;
	}
	public void setFilters(List filters) {
		this.filters = filters;
	}
	public List getServlets() {
		return servlets;
	}
	public void setServlets(List servlets) {
		this.servlets = servlets;
	}
}
