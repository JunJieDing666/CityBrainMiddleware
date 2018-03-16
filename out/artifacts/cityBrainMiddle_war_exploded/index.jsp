<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/5
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<button id="send">点击</button>
$END$
</body>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
    document.getElementById("send").onclick = function () {
        var params = {};
        params.InterSectionID = "123";
        $.ajax({
            url: "radarDataCollect/RealtimeDataResource",
            type: "post",
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(params),
            success: function (msg) {
                console.log(msg);
            },
            error: function (xhr, textstatus, thrown) {

            },

        });
    };
</script>
</html>
