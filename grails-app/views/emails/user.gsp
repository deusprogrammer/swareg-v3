<html>
    <head>
        <meta name="layout" content="email">
    </head>
    <body>
        <h3>Your New User</h3>
        <div class="pretext">
            <p>Thank you for creating a user on SWAreG!  You can get started creating and registering for events.
            It will also allow you to store your information for future registrations to events managed by
            SWAreG Registration System.</p>
            
            <p>If you buy multiple registrations, you can also gift those extra registrations to your friends.
            Simply go to our dashboard, click and hold on a badge, and then select gift.  You will simply put their
            email address.  If they already have an account it will set up a transfer for you.  If they don't
            exist already, we will create a temporary account and then send them an email so they can finish their
            registration.</p>
            
            <p>Please see below for your registered account information.</p>
        </div>
        <div class="round">
            <fieldset class="flow">
             <legend>User Info</legend>
          <table>
              <tr><td>Email</td><td>${user?.emailAddress}</td></tr>
              <tr><td>Password</td><td>****************</td></tr>
              <tr><td>First Name</td><td>${user?.firstName}</td></tr>
              <tr><td>Last Name</td><td>${user?.lastName}</td></tr>
          </table>
         </fieldset>
        </div>
    </body>
</html>