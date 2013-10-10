<html>
    <body>
        <h1>Create Registration Level</h1>
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
    </body>
</html>