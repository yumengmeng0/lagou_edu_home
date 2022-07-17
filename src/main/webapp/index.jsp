<%--
  Created by IntelliJ IDEA.
  User: ben
  Date: 2022/7/13
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/test?methodName=addCourse">添加课程</a><br>
<a href="${pageContext.request.contextPath}/test?methodName=findByName">根据课程名查找课程</a><br>
<a href="${pageContext.request.contextPath}/test?methodName=findByStatus">根据课程状态查找课程</a><br>
</body>
</html>
