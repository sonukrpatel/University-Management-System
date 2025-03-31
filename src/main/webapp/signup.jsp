<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup</title>
    <link rel="stylesheet" href="signup.css">
</head>
<body>

    <div class="overlay"></div>

    <div class="signup-container">
        <h2>Sign Up</h2>
        <form action="signup" method="post">
            <input type="text" name="name" placeholder="Enter Full Name" required><br>
            <input type="email" name="email" placeholder="Enter Email" required><br>
            <input type="password" name="password" placeholder="Enter Password" required><br>
            
            <!-- Role Selection -->
            <select name="role" required>
                <option value="">Select Role</option>
                <option value="admin">Admin</option>
                <option value="faculty">Faculty</option>
                <option value="student">Student</option>
            </select><br><br>

            <input type="submit" value="Sign Up">
        </form>
        <p>Already have an account? <a href="login.jsp">Login</a></p>
    </div>

</body>
</html>
