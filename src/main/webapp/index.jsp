<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form method="post" action="<%=request.getContextPath()%>/user/send.do">
    手机号：<input type="text" name="phone"><br>
    验证码：<input type="text" name="mobile_code">
    <input type="submit" value="发送验证码">
</form>
</body>
</html>
