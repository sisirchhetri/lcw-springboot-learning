<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Welcome to Servlet Page</title>
        <link rel="stylesheet" href="<%= application.getContextPath() %>/css/style.css" />
    </head>

    <body>
        
        <%@ include file="menu.jsp"%>
        <div class="container">
            <h1>Welcome To Servlet Crash Course</h1>
            <p>This is an information video about this.</p>
        </div>

        <div> 
            <p>This is a form</p>
            <form action="<%= application.getContextPath() %>/third" method="post">
            	<input name="name" type="text" placeholder="Enter your message " >
                <button id="submit">Submit</button>
            </form>

        </div>
        <script src="<%= application.getContextPath()%>/js/script.js"></script>
    </body>

    </html>