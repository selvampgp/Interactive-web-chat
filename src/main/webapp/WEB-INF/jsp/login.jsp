<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>
        Chat: Login
    </title>
</head>

<body>
<div align='center'>
    <form method="post">
        <p>

        <h1>Welcome to the chat!</h1>
        <label for="nickNameId">What's your name?:</label>
        <input type="text" id="nickNameId" name="nickName" value="<%=request.getAttribute("userNick")%>"/>
        </p>
        <p>
            <label for="colorSelectionId">Select color:</label>
            <select name="colorSelected" id="colorSelectionId">
                <c:forEach items="${colors}" var="color">
                    <option value="${color}"> ${color.toString()} </option>
                </c:forEach>
            </select>
        </p>
        <p>
            <input type=submit value="Login">
        </p>
    </form>
</div>
</body>
</html>

