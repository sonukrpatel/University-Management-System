<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>

    <div class="overlay"></div> <!-- Dark overlay for better contrast -->

    <div class="login-container">
        <h2>Login</h2>
        <form action="login" method="post">
            <input type="text" name="email" placeholder="Enter Email" required><br>
            <input type="password" name="password" placeholder="Enter Password" required><br>
            <input type="submit" value="Login">
            
            <% String errorMessage = (String) request.getAttribute("error"); %>
				<% if (errorMessage != null) { %>
   				 <p style="color: red;"><%= errorMessage %></p>
			<% } %>
            
            
            
            
        </form>
        <p>Don't have an account? <a href="signup.jsp">Sign up</a></p>
    </div>

</body>
</html>
