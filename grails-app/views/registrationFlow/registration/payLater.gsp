<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:PayLater</title>
    </head>
    <body>
        <h3>Pay Later for ${event}</h3>
        <div class="pretext">
            <p>By choosing pay later, you are risking missing out on the current promotion of ${String.format('$%.2f', registration.price)} for ${event}.  Are you okay with this?</p>
        </div>
        <div class="round">
            <g:form>
                <g:submitButton name="ok" value="Ok" />
                <g:submitButton name="wait" value="Wait!" />
            </g:form>
        </div>
    </body>
</html>