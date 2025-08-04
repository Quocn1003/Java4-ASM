<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Data Sharing Demo</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .logout-btn { background-color: #dc3545; color: white; padding: 8px 16px; text-decoration: none; border-radius: 4px; }
        .logout-btn:hover { background-color: #c82333; }
        .user-info { color: #666; }
        .section { border: 1px solid #ddd; margin: 10px 0; padding: 15px; border-radius: 5px; }
        .request { background-color: #e8f5e8; }
        .session { background-color: #e8f0ff; }
        .application { background-color: #fff8e8; }
        .page { background-color: #f0e8ff; }
        h2 { margin-top: 0; }
        .code { background-color: #f5f5f5; padding: 10px; border-radius: 3px; font-family: monospace; }
    </style>
</head>
<body>
    <%
        // 4. PageContext - Page scope (available only within this JSP page)
        pageContext.setAttribute("pageData", "Data stored in page scope");
        pageContext.setAttribute("currentTime", new java.util.Date().toString());
    %>
    
    <div class="header">
        <h1>🚀 Data Sharing Demo: Request, Session, Application & Page Scopes</h1>
        <div>
            <span class="user-info">Welcome, ${sessionScope.user.email}!</span>
            <a href="logout" class="logout-btn">Logout</a>
        </div>
    </div>
    
    <!-- Request Scope Data -->
    <div class="section request">
        <h2>📨 Request Scope (HttpServletRequest)</h2>
        <p><strong>Scope:</strong> Single request only</p>
        <div class="code">
            <p>Request Data: <c:out value="${requestData}"/></p>
            <p>Request Info: <c:out value="${requestInfo}"/></p>
        </div>
    </div>
    
    <!-- Session Scope Data -->
    <div class="section session">
        <h2>👤 Session Scope (HttpSession)</h2>
        <p><strong>Scope:</strong> Across multiple requests from the same user</p>
        <div class="code">
            <p>Session Data: <c:out value="${sessionData}"/></p>
            <p>Your Name: <c:out value="${userName}" default="Not set"/></p>
            <p>Session ID: <%= session.getId() %></p>
        </div>
        
        <!-- Form to update session data -->
        <form method="post" action="demo">
            <label>Enter your name: </label>
            <input type="text" name="userName" value="${userName}" placeholder="Your name">
            <input type="submit" value="Save to Session">
        </form>
    </div>
    
    <!-- Application Scope Data -->
    <div class="section application">
        <h2>🌍 Application Scope (ServletContext) - Set by WebListener</h2>
        <p><strong>Scope:</strong> Shared across all users and sessions</p>
        <div class="code">
            <p>App Name: <c:out value="${applicationScope.appName}"/></p>
            <p>Total Visitors: <c:out value="${applicationScope.visitorCount}"/></p>
            <p>App Start Time: <%= new java.util.Date((Long)application.getAttribute("appStartTime")) %></p>
        </div>
    </div>
    
    <!-- Page Scope Data -->
    <div class="section page">
        <h2>📄 Page Scope (PageContext)</h2>
        <p><strong>Scope:</strong> Available only within this JSP page</p>
        <div class="code">
            <p>Page Data: <c:out value="${pageScope.pageData}"/></p>
            <p>Current Time: <c:out value="${pageScope.currentTime}"/></p>
        </div>
    </div>
    
    <hr>
    <div style="background-color: #f0f0f0; padding: 15px; border-radius: 5px;">
        <h3>🔄 Test the Demo:</h3>
        <ol>
            <li><strong>Request Scope:</strong> Refresh the page - request data changes each time</li>
            <li><strong>Session Scope:</strong> Enter your name and submit - it persists across refreshes</li>
            <li><strong>Application Scope:</strong> Open in different browsers - visitor count increases</li>
            <li><strong>Page Scope:</strong> Data is recreated on each page load</li>
            <li><strong>WebListener:</strong> Check console output for session creation/destruction messages</li>
        </ol>
    </div>
    
    <p><a href="demo">🔄 Refresh Demo</a></p>
</body>
</html>
