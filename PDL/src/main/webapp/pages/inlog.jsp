<%-- 
    Document   : inlog
    Created on : Nov 11, 2013, 10:20:13 AM
    Author     : Bono
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>3 2 Learn</title>
		
		<script type="text/javascript" src="../plugins/mootools/mootools-core-1.4.5-full-nocompat-yc.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../css/main.css" />
		<link rel="stylesheet" type="text/css" href="../css/header.css" />
		<link rel="stylesheet" type="text/css" href="../css/course.css" />
	</head>
	
	<body>

<div id="container">
	<div id="container-header"></div>
	<div id="container-middle">
		<header id="header-container">
			<div class="container">
				<div class="container-image">
				</div>
				<div class="breaker"></div>
			</div>
			<nav id="main-nav">
				<ul>
					<li>
						<a href="../index.html">Courses</a>
					</li>
					<li>
						<a href="mycourses.html">My Courses</a>
					</li>
					<li>
						<a href="">Ipsum</a>
					</li>
					<li>
						<a href="">Mordem</a>
					</li>
					<li>
						<a href="">Straticum</a>
					</li>
					<li>
						<a href="">Platica</a>
					</li>
					<li>
						<a href="">Merinum</a>
					</li>
					<li>
						<a href="contact.html">Contact</a>
					</li>
					<li>
						<a href="inlog.html">Log In</a>
					</li>
					<li>
						<div id="nav-search">
							<a href="" style="vertical-align: middle; display: inline;"><img src="../img/toolbar_find.png"></a><input type="text" placeholder="Search">
						</div>
					</li>
				</ul>
			</nav>
		</header>
		<div class="line-break"></div>
		<div class="main-page">
			<div class="content">
				<div class="content-header">
					Log in
				</div>
				<div class="course">
					<div id="course-content" style="text-align: center;">
						<form method="post" action="">
							<div id="course-content-title">
								<h4>Username</h4>
								<input type="text" placeholder="Username" style="border: 1px solid #00B711; border-radius: 5px 5px 5px 5px; height: 25px; text-align: center; width: 215px;">
							</div><br/>
							<div id="course-content-title">
								<h4>Password</h4>
								<input type="password" placeholder="Password" style="border: 1px solid #00B711; border-radius: 5px 5px 5px 5px; height: 25px; text-align: center; width: 215px;">
							</div>
							<div class="course-button info" style="left: 654px;">
								<a href="register.jsp" class="button" id="button">Register</a>
							</div>
							<div class="course-button info" style="left: 745px;">
								<a href="test.html" class="button" id="button">Log in</a>
							</div> <br/>
                                                </form>
					</div>
				</div>
			</div>
		</div>
	</div>	
</div>
	
<script>
	window.addEvent('domready', function(){
		$$('.button').each(function(el){
			var color = el.getStyle('backgroundColor');
			
			el.addEvents({
				mouseenter: function(){
			    	this.morph({
			        	'background-color': '#00B711',
			        	'color': '#ffffff' 
			      	});
			    },
			    
			    mouseleave: function(){
			      	this.morph({
			        	backgroundColor: color,
			        	color: '#575757'
			      	});
			    }
			});
		});
	});
</script>
