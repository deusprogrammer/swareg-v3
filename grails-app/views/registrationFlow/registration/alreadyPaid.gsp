<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Login</title>
    </head>
    <body>
        <h3>Already paid for ${event}</h3>
        <div class="pretext">
            <p>You have already paid for this event.  Would you like to purchase an additional registration?</p>
        </div>
        <div class="round">
            <g:form>
                <g:submitButton name="yes" value="Yes" />
                <g:submitButton name="no" value="No" />
            </g:form>
        </div>
    </body>
</html>