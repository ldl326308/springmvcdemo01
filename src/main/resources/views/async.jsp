<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>一步方法支持</title>
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
<div id="message" class="model">

</div>

<script src="../static/jquery-3.4.1.min.js"></script>
<script>
    deferred();

    function deferred() {
        $.get('/defer', function (data) {
            console.log(data);
            $("#message").html(data);
            deferred();
        });
    }

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
