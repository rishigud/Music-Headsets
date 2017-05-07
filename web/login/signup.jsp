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
    <form action="<c:url value='/user/register'/>" method="post">
        <input type="hidden" name="action" value="login">
        <label >Email Address *</label>
        <input type="email" name="email" required/> <br><br>
        <label >First Name *</label>
        <input type="text" name="firstName" required/> <br><br>
        <label >Last Name *</label>
        <input type="text" name="lastName" required/> <br><br>
        <label> Password *</label>
        <input type="password" name="password" required/><br>
        <label> Confirm Password *</label>
        <input type="password" name="cpassword" required/><br>
        <% String password = request.getParameter("password");
           String cpassword = request.getParameter("cpassword");
          
           if(password!=null && cpassword!=null && !password.matches(cpassword)){
         %>      
         <script>
             alert("password not matching")
         </script> 
         <%  
           }
            
        %>
        <label>&nbsp;</label>
        <input type="submit" value="register" id="login_button" name="login" >

        <br>
    </form>
    <%-- Code to go to Sign up for a new account  --%>
    
</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />