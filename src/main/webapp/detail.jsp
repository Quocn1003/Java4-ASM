<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="vi">

    <head>
        <meta charset="UTF-8">
        <title>Trang chi tiet</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }

            /* Navbar container */
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

            /* Dropdown container */
            .dropdown {
                position: relative;
                display: inline-block;
            }

            /* Dropdown menu */
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

            .dropdown>a {
                color: #1f3dc4;
            }

            /* Video grid */
            .container {
                width: 90%;
                margin: 20px auto;
            }

            .video-grid {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                justify-content: center;
            }

            .video-card {
                border: 1px solid #ccc;
                width: 30%;
                min-width: 220px;
                text-align: center;
                padding: 10px;
                box-shadow: 2px 2px 6px #ddd;
            }

            .poster {
                width: 100%;
                height: 120px;
                border: 2px solid orange;
                margin-bottom: 10px;
                background-color: #eee;
                display: flex;
                align-items: center;
                justify-content: center;
                font-weight: bold;
            }

            .video-title {
                background-color: #d0e6c2;
                font-weight: bold;
                padding: 5px;
            }

            .actions {
                margin-top: 10px;
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

            .btn-like {
                background-color: green;
            }

            .btn-share {
                background-color: orange;
            }

            .pagination {
                text-align: center;
                margin-top: 20px;
            }

            .pagination button {
                margin: 0 5px;
                padding: 5px 10px;
                background-color: #ccc;
                border: none;
                border-radius: 3px;
                font-weight: bold;
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
        <div class="container mt-4">
            <div class="row">
                <!-- Main Video Detail -->
                <div class="col-md-9 video-container">
                    <div class="mb-3">
                        <video width="100%" height="400" controls>
                            <source src="${video.link}" type="video/mp4">
                            Trình duyệt không hỗ trợ video.
                        </video>
                    </div>
                    <div class="video-info mb-2">
                        <h5 class="fw-bold">${video.title}</h5>
                        <p>${video.description}</p>
                    </div>
                    <div class="d-flex gap-2">
                        <form action="/like" method="post">
                            <input type="hidden" name="videoId" value="${video.id}">
                            <button type="submit" class="btn btn-like">Like</button>
                        </form>
                        <form action="/share" method="post">
                            <input type="hidden" name="videoId" value="${video.id}">
                            <button type="submit" class="btn btn-share">Share</button>
                        </form>
                    </div>
                </div>

                <!-- Sidebar with suggested videos -->
                <div class="col-md-3 sidebar">
                    <c:forEach items="${suggestedVideos}" var="v">
                        <div class="d-flex mb-3 align-items-center">
                            <a href="/detail?id=${v.id}" class="poster me-2">
                                <img src="${v.poster}" alt="Poster" width="80" height="60">
                            </a>
                            <a href="/detail?id=${v.id}">${v.title}</a>
                        </div>
                    </c:forEach>
                </div>
            </div>
    </body>

    </html>