<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>服务器端消息推送技术</title>
    <style>
    </style>
</head>
<body>
<p>SSE测试....</p>
<div id="msgFromPush">

</div>
<script src="../static/jquery-3.4.1.min.js"></script>
<script>
    if (!!window.EventSource){
        var source = new EventSource("push");
        s = '';
        source.addEventListener("message", function (ev) {
            s += ev.data + "<br/>";
            $("#msgFromPush").html(s);
        });

        source.addEventListener("open", function (ev) {
            console.log("连接打开....");
        }, false);

        source.addEventListener("error",function (ev) {
            if (ev.readyState == EventSource.CLOSED){
                console.log("连接关闭...");
            } else{
                console.log('状态：' + ev.readyState);
            }
        }, false);

    } else {
        alert("您的浏览器不知道SSE！");
    }
</script>
</body>
</html>
