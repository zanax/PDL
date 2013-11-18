<%-- 
    Document   : register
    Created on : Nov 11, 2013, 10:22:03 AM
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
					Register
				</div>
				<div class="course">
					<div id="course-content" style="text-align: center;">
						<form method="post" action="/PDL/register">
                                                    <label class="label">
                                                    </label>
                                                    <div class="form-input-radio">
                                                        <span class="form-span">Gender:</span>
                                                        M<input name="gender" type="radio" value="m">
                                                        F<input name="gender" type="radio" value="f">
                                                    </div>
                                                    <label class="label">
                                                        <span class="form-span">Firstname:</span>
                                                        <input type="text" name="firstname" placeholder="firstname" class="form-input">
                                                    </label>
                                                    <label class="label">
                                                        <span class="form-span">Surname:</span>
                                                        <input type="text" name="surname" placeholder="surename" class="form-input">
                                                    </label>
                                                    <label class="label">
                                                        <span class="form-span">Address:</span>
                                                        <input type="text" name="address" placeholder="address" class="form-input">
                                                    </label>
                                                    <label class="label">
                                                        <span class="form-span">Zipcode:</span>
                                                        <input type="text" name="zipcode" placeholder="zipcode" class="form-input">
                                                    </label>
                                                    <label class="label">
                                                        <span class="form-span">Country:</span>
                                                        <select class="form-input-select" name="country">
                                                            <option value="">Select country...</option>
                                                            <option value="1">United Kindom</option>
                                                            <option value="2">Nederland</option>
                                                        </select>
                                                    </label>
                                                    <label class="label">
                                                        <span class="form-span">City:</span>
                                                        <input type="text" name="city" placeholder="city" class="form-input">
                                                    </label>
                                                    <label class="label">
                                                        <span class="form-span">Language:</span>
                                                        <select class="form-input-select" name="language">
                                                            <option value="">Select language...</option>
                                                            <option value="1">English (UK)</option>
                                                            <option value="2">Nederlands</option>
                                                        </select>
                                                    </label>
                                                    <label class="label">
                                                        <span class="form-span">E-mail:</span>
                                                        <input type="text" name="email" placeholder="you@mail.com" class="form-input">
                                                    </label>
                                                    <input type="submit" class="button" id="button" value="Register">
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

