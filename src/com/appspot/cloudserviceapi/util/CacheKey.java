package com.appspot.cloudserviceapi.util;

import java.io.Serializable;

public class CacheKey implements Serializable {
	private String key;

	public CacheKey(String key) {
		super();
		this.key = key;
	}

	public String getKey() {
		return key;
	}

//	public void setKey(String key) {
//		this.key = key;
//	}
}
