<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
            <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="${url}" method="post">
            <label for="email">email:</label>
            <input type="text" id="email" name="email" required><br><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>
            <button type="submit">Login</button>
        </form>
</html>