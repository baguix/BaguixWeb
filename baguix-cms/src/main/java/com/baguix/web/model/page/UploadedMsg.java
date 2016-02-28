package com.baguix.web.model.page;

import java.util.ArrayList;
import java.util.List;

public class UploadedMsg implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private boolean success = false;
	private List<String> urls = new ArrayList<String>();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
