<html>
    <head>
        <meta name="layout" content="flow">
        <title>Registration:Login</title>
    </head>
    <body>
        <h3>Resume Registration for ${event}</h3>
        <div class="pretext">
            <p>We found a prior registration created for you that you haven't paid for yet, would you like to resume this registration?</p>
            <p>Registration: ${registration}</p>
        </div>
        <div class="round">
            <g:form>
                <g:submitButton name="resume" value="Resume" />
                <g:submitButton name="startOver" value="Start Over" />
            </g:form>
        </div>
    </body>
</html>