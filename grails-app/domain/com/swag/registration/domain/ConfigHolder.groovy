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
        ConfigHolder config = ConfigHolder.findByConfigKey(key)
		if (config) {
			config.configValue = value
		} else {
			config = new ConfigHolder(configKey: key, configValue: value)
		}
		
		config.save()
    }

    public static void setSwitch(String key, Boolean value) {
        ConfigHolder config = ConfigHolder.findByConfigKey(key)
		if (config) {
			config.configValue = (value ? "true" : "false")
		} else {
			config = new ConfigHolder(configKey: key, configValue: value ? "true" : "false")
		}
		
		config.save()
    }

    static constraints = {
        configKey unique: true
    }

    public String toString() {
        return "${configKey} => ${configValue}"
    }
}
