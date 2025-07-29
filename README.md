# Data Sharing Demo

This demo demonstrates sharing data through different scopes in Java web applications:

## 📋 Components Demonstrated

### 1. **HttpServletRequest** (Request Scope)
- **File**: `DataSharingServlet.java`
- **Scope**: Single request only
- **Usage**: Store data that's needed only for the current request
- **Example**: Form data, request-specific calculations

### 2. **HttpSession** (Session Scope)
- **File**: `DataSharingServlet.java`
- **Scope**: Across multiple requests from the same user
- **Usage**: User preferences, shopping cart, login status
- **Example**: Page view counter, user name

### 3. **ServletContext** (Application Scope)
- **File**: `AppListener.java` (initialized) and `demo.jsp` (accessed)
- **Scope**: Shared across all users and sessions
- **Usage**: Application configuration, global counters, shared resources
- **Example**: Visitor counter, application start time

### 4. **PageContext** (Page Scope)
- **File**: `demo.jsp`
- **Scope**: Available only within the JSP page
- **Usage**: Page-specific data, temporary calculations
- **Example**: Current timestamp, page-specific formatting

### 5. **WebListener**
- **File**: `AppListener.java`
- **Purpose**: Initialize application data, track sessions
- **Events**: Application start/stop, session create/destroy

## 🚀 How to Run

1. **Using Maven and Jetty:**
   ```bash
   mvn clean compile
   mvn jetty:run
   ```

2. **Open browser:**
   ```
   http://localhost:8080
   ```

## 🧪 Testing the Demo

1. **Request Scope**: Refresh the page - notice request data changes each time
2. **Session Scope**: Enter your name and submit - it persists across page refreshes
3. **Application Scope**: Open in different browsers - visitor count increases
4. **Page Scope**: Data is recreated on each page load
5. **WebListener**: Check console output for session creation/destruction messages

## 📁 File Structure

```
src/main/
├── java/com/slide5/
│   ├── AppListener.java        # WebListener for app/session events
│   ├── DataSharingServlet.java # Main servlet demonstrating scopes
│   └── Main.java              # Original main class
├── webapp/
│   ├── WEB-INF/
│   │   └── web.xml            # Web application configuration
│   ├── demo.jsp               # JSP demonstrating all scopes
│   └── index.html             # Welcome page
└── resources/
```

## 🎯 Key Learning Points

- **Request Scope**: Perfect for data that doesn't need to persist
- **Session Scope**: Ideal for user-specific data across requests
- **Application Scope**: Best for shared, global application data
- **Page Scope**: Useful for JSP-specific temporary data
- **WebListener**: Essential for application lifecycle management

## 💡 Real-world Applications

- **Request**: Form validation results, search results
- **Session**: User authentication, personalization settings
- **Application**: Database connection pools, configuration data
- **Page**: Dynamic page titles, temporary calculations
- **WebListener**: Resource initialization, cleanup operations
