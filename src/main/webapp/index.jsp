<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<script type="text/javascript" src="/jquery-1.10.2.min.js"></script>
<form method="post" action="<%=request.getContextPath()%>/api/user/send_login_code.html">
    手机号：<input type="text" name="phone"><br>
    验证码：<input type="text" name="mobile_code">
    <input type="submit" value="发送验证码">
</form>
</body>
</html>
