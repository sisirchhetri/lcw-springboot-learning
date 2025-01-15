<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <%@include file="comp/links.jsp" %>
  </head>
  <body>
  	<%@include file="header.jsp" %>
    <div style="height: 80vh" class="container-fluid d-flex justify-content-center flex-column  align-items-center mt-2 p-2 ">
    	<a href="<%= application.getContextPath() %>/feedback.jsp" class="btn btn-light">Give Us Feedback</a>
    </div>
   </body>
    <%@include file="scripts.jsp" %>
</html>
