<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="shortcut icon" href="${base}/resources/ico/favicon.ico" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- Bootstrap core CSS -->
<link href="${base}/resources/css/bootstrap.css" rel="stylesheet" />
<link href="${base}/resources/css/font-awesome.css" rel="stylesheet"
	type="text/css" />
<!-- Custom styles for this template -->
<link href="${base}/resources/css/main.css" rel="stylesheet">
<link rel="stylesheet" href="${base}/resources/<spring:theme code="css"/>"
	type="text/css" />

<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<tiles:insertAttribute name="navbar" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-2 col-sm-3 col-md-2 sidebar">
				<tiles:insertAttribute name="sidebar" />
			</div>
			<div class="col-xs-10 col-sm-9 col-md-10 main-col">
				<div class="main">
					<tiles:insertAttribute name="breadcrumb" />
					<tiles:insertAttribute name="body" />
				</div>
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>
	<script src="${base}/resources/js/jquery.js"></script>
	<script src="${base}/resources/js/bootstrap.min.js"></script>
	<script src="${base}/resources/js/main.js"></script>
</body>
</html>