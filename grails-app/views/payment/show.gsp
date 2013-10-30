
<%@ page import="com.swag.registration.domain.Payment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'payment.label', default: 'Payment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-payment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-payment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list payment">
			
	            <g:if test="${paymentInstance?.paymentId}">
                <li class="fieldcontain">
                    <span id="paymentId-label" class="property-label"><g:message code="payment.paymentId.label" default="Payment Id" /></span>
                    
                        <span class="property-value" aria-labelledby="paymentId-label"><g:fieldValue bean="${paymentInstance}" field="paymentId"/></span>
                    
                </li>
                </g:if>
			
				<g:if test="${paymentInstance?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label"><g:message code="payment.total.label" default="Total" /></span>
					
						<span class="property-value" aria-labelledby="total-label">${String.format("\$%.2f", paymentInstance?.total.round(2))}</span>
					
				</li>
				</g:if>
			
				<g:if test="${paymentInstance?.subtotal}">
				<li class="fieldcontain">
					<span id="subtotal-label" class="property-label"><g:message code="payment.subtotal.label" default="Subtotal" /></span>
					
						<span class="property-value" aria-labelledby="subtotal-label">${String.format("\$%.2f", paymentInstance?.subtotal.round(2))}</span>
					
				</li>
				</g:if>
			
				<g:if test="${paymentInstance?.tax}">
				<li class="fieldcontain">
					<span id="tax-label" class="property-label"><g:message code="payment.tax.label" default="Tax" /></span>
					
						<span class="property-value" aria-labelledby="tax-label">${String.format("\$%.2f", paymentInstance?.tax.round(2))}</span>
					
				</li>
				</g:if>
			
				<g:if test="${paymentInstance?.paymentType}">
				<li class="fieldcontain">
					<span id="paymentType-label" class="property-label"><g:message code="payment.paymentType.label" default="Payment Type" /></span>
					
						<span class="property-value" aria-labelledby="paymentType-label"><g:fieldValue bean="${paymentInstance}" field="paymentType"/></span>
					
				</li>
				</g:if>
			

			
				<g:if test="${paymentInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="payment.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${paymentInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${paymentInstance?.completed}">
				<li class="fieldcontain">
					<span id="completed-label" class="property-label"><g:message code="payment.completed.label" default="Completed" /></span>
					
						<span class="property-value" aria-labelledby="completed-label"><g:formatBoolean boolean="${paymentInstance?.completed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${paymentInstance?.debugId}">
				<li class="fieldcontain">
					<span id="debugId-label" class="property-label"><g:message code="payment.debugId.label" default="Debug Id" /></span>
					
						<span class="property-value" aria-labelledby="debugId-label"><g:fieldValue bean="${paymentInstance}" field="debugId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${paymentInstance?.transactionId}">
				<li class="fieldcontain">
					<span id="transactionId-label" class="property-label"><g:message code="payment.transactionId.label" default="Transaction Id" /></span>
					
						<span class="property-value" aria-labelledby="transactionId-label"><g:fieldValue bean="${paymentInstance}" field="transactionId"/></span>
					
				</li>
				</g:if>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${paymentInstance?.id}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
