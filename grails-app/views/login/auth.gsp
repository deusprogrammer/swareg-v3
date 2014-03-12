<html>
	<head>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'new.css')}" type="text/css">
		<meta name='layout' content='new'/>
	</head>
	<body>
		<div id='login-view' class='dash'>
			<span class="legend">Login</span>
			<g:if test='${flash.message}'>
				<div class='login_message'>${flash.message}</div>
			</g:if>
	
			<div class="inner-x">
				<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
			        <table>
		                <tr><td>Email Address</td><td><input type='text' class='text_' name='j_username' id='username' placeholder="user@domain.com" /></td></tr>
		                <tr><td>Password</td><td><input type='password' class='text_' name='j_password' id='password' placeholder="password" /></td></tr>
	                </table>
		
					<p id="remember_me_holder">
						<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
						<label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
					</p>
		
					<p>
						<input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
					</p>
				</form>
			</div>
		</div>
	</body>
</html>
