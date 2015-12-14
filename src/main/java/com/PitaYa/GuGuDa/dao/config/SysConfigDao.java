package com.PitaYa.GuGuDa.dao.config;

import com.PitaYa.model.config.SysConfig;

public interface SysConfigDao {

	SysConfig getConfig(String name);
	
	String getValue(String name);
	
}
