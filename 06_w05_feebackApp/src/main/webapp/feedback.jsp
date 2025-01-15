<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Feedback</title>
<%@include file="comp/links.jsp"%>
</head>
<body>
	<%@include file="header.jsp"%>
	<div style="height: 80vh"
		class="container-fluid d-flex justify-content-center align-items-center flex-column mt-2 p-2 text-light">
		<h3>Share Your Feedback Here!!</h3>
		<form action ="<%=application.getContextPath() %>/feedback" method="post">
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Email address</label>
				<input name="email" type="email"  class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
				placeholder="enter your email">
			</div>
			<div class="mb-3">
				<label for="inputPhoneNumber" class="form-label">Phone Number</label>
				<input name="phoneNumber" type="text" class="form-control" id="inputPhoneNumber"
				 placeholder="enter your phone no.">
			</div>
			<div class="mb-3 ">
				<label for="comments" class="form-label" >Type your message</label>
				<textarea name="comments" rows="10" cols="" class="form-control" id="inputComments"></textarea>
			</div>
			
			<div class="mb-3 d-flex justify-content-center gap-2">
				<button type="submit" id="submitBtn" class="btn btn-success">Submit</button>
				<button type="reset" id="resetBtn" class="btn btn-light">Reset</button>
			</div>
			
		</form>
	</div>
</body>
<%@include file="scripts.jsp"%>
</html>