<%--
  Created by IntelliJ IDEA.
  User: dsi
  Date: ২১/১২/২৩
  Time: ৩:৪২ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
        <title>User Management Application</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>


<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #058695">
        <div>
            <a class="navbar-brand"> User Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}'/>" />
                    </c:if>

                    <fieldset class="form-group m-3">
                        <label>User Name</label> <input type="text" value="<c:out value='${user.name}'/>" class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group m-3">
                        <label>User Email</label> <input type="text" value="<c:out value='${user.email}'/>" class="form-control" name="email">
                    </fieldset>

                    <fieldset class="form-group m-3">
                        <label>User Country</label> <input type="text" value="<c:out value='${user.country}' />" class="form-control" name="country">
                    </fieldset>

                    <button type="submit" class="btn btn-success m-3">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
