package com.swag.registration.domain

class ConfigHolder implements Serializable {
    String configKey
    String configValue

    public static String getConfig(String key) {
        return ConfigHolder.findByConfigKey(key)?.configValue
    }
	
	public static Boolean getSwitch(String key) {
		return ConfigHolder.findByConfigKey(key)?.configValue == "true"
	}
	
	public static void setConfig(String key, String value) {
		ConfigHolder.findByConfigKey(key)?.configValue = value
	}
	
	public static void setSwitch(String key, Boolean value) {
		ConfigHolder.findByConfigKey(key)?.configValue = (value ? "true" : "false")
	}

    static constraints = {
        configKey unique: true
    }
}
