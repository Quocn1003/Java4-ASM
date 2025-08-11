<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
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

            .btn-register {
                background-color: orange;
            }

            .register-container {
                border: 1px solid #ccc;
                padding: 10px;
                width: 30%;
                box-shadow: 2px 2px 6px #ddd;
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-top: 10px;
            }

            .register-title {
                width: 100%;
                text-align: center;
                margin: 10px;
                background-color: #d0e6c2;
                font-weight: bold;
            }

            .register-button {
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


        <div class="navbar">
            <a href="home" class="brand">ONLINE ENTERTAINMENT</a>
            <a href="like">MY FAVORITES</a>
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
            <div class="register-container">
                <div class="register-title">
                    <p>Registration</p>
                </div>

                <!-- Hiển thị lỗi -->
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/registration" method="post">
                    <label for="username">Username:</label><br>
                    <input type="text" id="username" name="username" required><br><br>

                    <label for="password">Password:</label><br>
                    <input type="password" id="password" name="password" required><br><br>

                    <label for="fullname">Fullname:</label><br>
                    <input type="text" id="fullname" name="fullname" required><br><br>

                    <label for="email">Email address:</label><br>
                    <input type="email" id="email" name="email" required><br><br>

                    <div class="register-button">
                        <button class="btn btn-register" type="submit">Sign up</button>
                    </div>
                </form>
            </div>
        </div>
    </body>

    </html>