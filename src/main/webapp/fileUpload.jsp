<%--
  Created by IntelliJ IDEA.
  User: ben
  Date: 2022/7/14
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--

文件上传三要素:
    1.表单的提交方式必须是post
    2.表单的enctype必须是：multipart/form-data
    3.表单必须有文件上传项

-->
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload">
    <input type="file" name="upload"><br>
    <input type="submit" value="文件上传"><br>
</form>
<img src="/upload/97587b8ba6ee441487d585f861d09a39_微信图片_20220619063718.jpg">
</body>
</html>
