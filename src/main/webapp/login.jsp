<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }

            /* Navbar */
            .navbar {
                display: flex;
                background-color: #f1c40f;
                padding: 10px 20px;
                align-items: center;
                border-radius: 10px;
                font-weight: bold;
            }

            .navbar a {
                margin: 0 15px;
                text-decoration: none;
                color: #1f3dc4;
            }

            .navbar a.brand {
                color: darkred;
                font-size: 20px;
                margin-right: auto;
            }

            /* Dropdown */
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: white;
                min-width: 160px;
                border: 1px solid #ccc;
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 8px 12px;
                text-decoration: none;
                display: block;
                border-bottom: 1px solid #ccc;
            }

            .dropdown-content a:hover {
                background-color: #f1f1f1;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            /* Form */
            .btn {
                padding: 5px 10px;
                border: none;
                color: white;
                font-weight: bold;
                cursor: pointer;
                border-radius: 3px;
                margin: 0 5px;
            }

            .btn-login {
                background-color: orange;
            }

            .login-container {
                border: 1px solid #ccc;
                padding: 10px;
                width: 30%;
                box-shadow: 2px 2px 6px #ddd;
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-top: 10px;
            }

            .login-title {
                width: 100%;
                text-align: center;
                margin: 10px;
                background-color: #d0e6c2;
                font-weight: bold;
            }

            .login-button {
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-bottom: 10px;
            }

            .container {
                margin: 20px auto;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .error {
                color: red;
                font-size: 14px;
                margin-bottom: 10px;
            }
        </style>
    </head>

    <body>
        <c:if test="${not empty sessionScope.successMessage}">
            <div style="color: green; font-weight: bold;">${sessionScope.successMessage}</div>
            <c:remove var="successMessage" scope="session" />
        </c:if>



        <div class="navbar">
            <a href="home" class="brand">ONLINE ENTERTAINMENT</a>
            <a href="myFavourite">MY FAVORITES</a>
            <div class="dropdown">
                <a href="#">MY ACCOUNT</a>
                <div class="dropdown-content">
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <!-- Đã đăng nhập -->
                            <a href="forgotPassword">Forgot Password</a>
                            <a href="changePassword">Change Password</a>
                            <a href="editProfile">Edit Profile</a>
                            <a href="logout">Logout</a>
                        </c:when>
                        <c:otherwise>
                            <!-- Chưa đăng nhập -->
                            <a href="login">Login</a>
                            <a href="registration">Registration</a>
                            <a href="forgotPassword">Forgot Password</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="login-container">
                <div class="login-title">
                    <p>Login</p>
                </div>

                <!-- Hiển thị lỗi -->
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/login" method="post">
                    <label for="username">Username:</label><br>
                    <input type="text" id="username" name="username"
                        value="${cookie.username.value != null ? cookie.username.value : ''}" required><br><br>

                    <label for="password">Password:</label><br>
                    <input type="password" id="password" name="password"
                        value="${cookie.password.value != null ? cookie.password.value : ''}" required><br><br>

                    <input type="checkbox" id="remember" name="remember" <c:if
                        test="${cookie.username.value != null}">checked</c:if> >
                    <label for="remember">Remember me</label><br><br>

                    <div class="login-button">
                        <button class="btn btn-login" type="submit">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </body>

    </html>