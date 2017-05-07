<%-- 
    Document   : index
    Created on : 30 Apr, 2017, 9:35:26 PM
    Author     : rishi
--%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_home.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">
    h1>Login Form - Error</h1>
<p>You did not log in successfully.</p>
<p>Please check your username and password and try again.</p>
    <form action="<c:url value='/user/loginuser'/>" method="post">
        <input type="hidden" name="action" value="login">
        <label >Email Address *</label>
        <input type="email" name="email" required/> <br><br>
        <label> Password *</label>
        <input type="password" name="password" required/><br>
        <label>&nbsp;</label>
        <input type="submit" value="Login" id="login_button" name="login" >

        <br>
    </form>
    <%-- Code to go to Sign up for a new account  --%>
    <a href="<c:url value='/login/signup.jsp'/>" id="sign_up_link">register here</a>
</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />