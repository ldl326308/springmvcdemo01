<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        .upload{
            font-family: 华文宋体;
            color: red;
            font-size: 20px;
        }
    </style>
</head>
<body>
    <div class="upload">
         <form action="upload" enctype="multipart/form-data" method="post">
             <input type="file" name="file">
             <br/>
             <input type="submit" value="上传">
         </form>
    </div>
</body>
</html>
