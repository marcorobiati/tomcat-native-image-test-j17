<%@page import="com.mycompany.tomcatnativeimagetest.init.InitClass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hi, I'm page.jsp</h1>
        <a href="index.html">index.html</a>
        <p>aNumberStatic from InitClass is: <%=InitClass.aNumberStatic%><p>
        <p>ICReference from InitClass is: <%=InitClass.ICReference%><p>

    </body>
</html>
