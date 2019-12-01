<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>converter</title>
    <style>
        .model {
            font-family: 华文宋体;
            color: black;
            font-size: 20px;
        }
    </style>
</head>
<body>
<div id="resp"></div>
<div class="model">
    <p>&emsp;student ID：<input type="number" id="id"></p>
    <p>student Name:<input type="text" id="name"/></p>
    <input type="button" onclick="req()" value="请求">
</div>

<script src="../static/jquery-3.4.1.min.js"></script>
<script>
    function req() {
        console.log($("#id").val() + '-' + $("#name").val());
        $.ajax({
            url: "/converter",
            data: $("#id").val() + '-' + $("#name").val(),
            type:"POST",
            contentType:"application/x-wisely",
            success:function (data) {
                $("#resp").html(data);
            }
        });
    }
</script>
</body>
</html>
