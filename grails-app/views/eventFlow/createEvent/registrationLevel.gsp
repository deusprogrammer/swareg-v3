<html>
    <head>
        <meta name="layout" content="flow">
        <title>Event:Registration-Levels</title>
    </head>
    <body>
        <h1>Create Registration Level</h1>
        <div class="pretext">
            <p>Now tell us what levels of registration you offer, you can enter as many as you like.</p>
        </div>
        <div class="round">
	        <g:form>
	            <h5>Registration Level Information</h5>
	            <table>
	                <tr><td>Name</td><td><g:textField name="name" value="${eventData?.name}"/></td></tr>
	                <tr><td>Description</td><td><g:textField name="description" value="${eventData?.description}"/></td></tr>
	                <tr><td>Base Price</td><td><g:textField name="price" value="${eventData?.price}"/></td></tr>
	                <tr><td>Valid For</td><td><g:textField name="validFor" value="${eventData?.validFor}"/></td></tr>
	            </table>
	            <g:submitButton name="addAnother" value="Add Another"/>
	            <g:submitButton name="done" value="Done"/>
	        </g:form>
        </div>
    </body>
</html>