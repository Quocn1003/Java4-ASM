<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Change Password</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }

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

            .btn {
                padding: 5px 10px;
                border: none;
                color: white;
                font-weight: bold;
                cursor: pointer;
                border-radius: 3px;
                margin: 0 5px;
            }

            .btn-change {
                background-color: orange;
            }

            .form-container {
                border: 1px solid #ccc;
                padding: 10px;
                width: 30%;
                box-shadow: 2px 2px 6px #ddd;
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-top: 10px;
            }

            .form-title {
                width: 100%;
                text-align: center;
                margin: 10px;
                background-color: #d0e6c2;
                font-weight: bold;
            }

            .form-button {
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

            .success {
                color: green;
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
            <div class="form-container">
                <div class="form-title">
                    <p>Change Password</p>
                </div>

                <!-- Hiển thị thông báo -->
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="success">${success}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/changePassword" method="post">
                    <label for="id">Username:</label><br>
                    <input type="text" id="id" name="id" required><br><br>

                    <label for="currentPassword">Current Password:</label><br>
                    <input type="password" id="currentPassword" name="currentPassword" required><br><br>

                    <label for="newPassword">New Password:</label><br>
                    <input type="password" id="newPassword" name="newPassword" required><br><br>

                    <label for="confirmPassword">Confirm New Password:</label><br>
                    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>

                    <div class="form-button">
                        <button class="btn btn-change" type="submit">Change Password</button>
                    </div>
                </form>
            </div>
        </div>
    </body>

    </html>