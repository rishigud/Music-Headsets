<%@page import="music.business.User"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
<head>
    <meta charset="utf-8">
    <title>Music Headsets</title>
    <link rel="shortcut icon" href="<c:url value='/images/favicon.ico'/>">
    <link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
    <link rel="stylesheet" href="<c:url value='/styles/css/bootstrap.min.css'/> ">
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
</head>

<body>
   
    <header>
      
      <div class="jumbotron">
      <div class="container"> 
        <h1>Music Headsets</h1>
        <h2>For Music Lovers!</h2>
        <p>Unleash your senses....</p>
       
      </div>
   </div>
        
    </header>
        <% User user = (User)session.getAttribute("user"); %>
        <nav class="navbar navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Music Headsets</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
            <% if(user!=null){ %>
            <li><a href="<c:url value='/user/deleteCookies'/>">
                    Delete Cookies</a></li>
            <li><a href="<c:url value='/order/showCart'/>">
                    Show Cart</a></li>
            <li><a href="<c:url value='/user/logout'/>">
                    logout</a></li>        
                    
              <% } else { %> 
                <li><a href="<c:url value='/login'/>">
                    login</a></li>
            <li><a href="<c:url value='/admin'/>">
                    Admin</a></li>
              <% } %>      
        </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
   