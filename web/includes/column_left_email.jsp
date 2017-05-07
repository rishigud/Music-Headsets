<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebarA">
    <nav>
       <ul class="list-group">
          <li class="list-group-item"><a class="current" href="<c:url value='/' />">
                  Home</a></li>
          <li class="list-group-item"><a href="<c:url value='/catalog' />">
                  Browse Catalog</a></li>
          <li class="list-group-item"><a href="<c:url value='/email' />">
                  Join Email List</a></li>
          <li class="list-group-item" ><a href="<c:url value='/customer_service' />">
                  Customer Service</a></li>
      </ul>
    </nav>
</aside>