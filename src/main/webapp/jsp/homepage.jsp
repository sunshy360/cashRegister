<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <title>Bootstrap 实例 - 面包屑导航</title>
   <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>

<ol class="breadcrumb">
  <li><a href="#">Home</a></li>
  <li><a href="#">2013</a></li>
  <li class="active">十一月</li>
</ol>
${id} <br/>
${hello}
<br/>
<c:forEach var="item" items="${lst}">
   ${item} <br/>
</c:forEach>

<br/>
product name:
${proudct.name}

</body>
</html>