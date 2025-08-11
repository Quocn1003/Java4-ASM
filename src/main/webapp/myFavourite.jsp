<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="vi">

    <head>
        <meta charset="UTF-8">
        <title>My Favourites</title>
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
                background-color: rgb(18, 148, 255);
            }

            .btn-share {
                background-color: orange;
            }

            .pagination {
                text-align: center;
                margin-top: 20px;
            }

            .page-btn {
                margin: 0 5px;
                padding: 5px 10px;
                background-color: #ccc;
                border: none;
                border-radius: 3px;
                font-weight: bold;
                color: black;
                text-decoration: none;
                display: inline-block;
            }
        </style>
    </head>

    <body>


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
            <div class="video-grid">
                <c:forEach items="${videos}" var="video">
                    <div class="video-card">
                        <a href="detail?id=${video.id}">
                            <div class="poster">POSTER</div>
                        </a>
                        <div class="video-title">${video.title}</div>
                        <div class="actions">
                            <form method="post" action="unlike?id=${video.id}" style="display:inline;">
                                <button type="submit" class="btn btn-like">Unlike</button>
                            </form>
                            <form method="get" action="share?id=${video.id}" style="display:inline;">
                                <button type="submit" class="btn btn-share">Share</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="pagination">
            <a href="?page=1" class="page-btn">&lt;|</a>
            <a href="?page=${prevPage}" class="page-btn">&lt;&lt;</a>
            <span>${currentPage} / ${totalPages}</span>
            <a href="?page=${nextPage}" class="page-btn">&gt;&gt;</a>
            <a href="?page=${totalPages}" class="page-btn">|&gt;</a>
        </div>
    </body>

    </html>