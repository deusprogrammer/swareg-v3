<%@ page import="com.swag.registration.domain.ConfigHolder" %>



<div class="fieldcontain ${hasErrors(bean: configHolderInstance, field: 'configKey', 'error')} ">
	<label for="configKey">
		<g:message code="configHolder.configKey.label" default="Config Key" />
		
	</label>
	<g:textField name="configKey" value="${configHolderInstance?.configKey}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: configHolderInstance, field: 'configValue', 'error')} ">
	<label for="configValue">
		<g:message code="configHolder.configValue.label" default="Config Value" />
		
	</label>
	<g:textField name="configValue" value="${configHolderInstance?.configValue}"/>
</div>

