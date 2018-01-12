<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<script type="text/javascript" src="/jquery-1.10.2.min.js"></script>
<form method="post" action="<%=request.getContextPath()%>/api/user/code_login.html">
    手机号：<input type="text" name="phone"><br>
    验证码：<input type="text" name="mobile_code">
    <input type="button" value="发送验证码" onclick="test()">
</form>
<script type="text/javascript">
    function test() {
        $.ajax({
            url: "",
            data: "",
            type: "post",
            success: function (data) {

            }
        })
    }
</script>
</body>
</html>
