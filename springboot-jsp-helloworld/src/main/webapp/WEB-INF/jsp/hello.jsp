<%@ page import="main.java.com.JwtResponse" %>
<%  String token =  JwtResponse.jwttoken;%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <h2 class="hello-title">Hello ${name}!</h2>
    <script src="/js/main.js"></script>
    <div class="container">
        <h3 id="token">${tokenString}
        <a id="redirect" href="http://localhost:8080/redirect" >Redirect</a>
    </div>
</body>
</html>

