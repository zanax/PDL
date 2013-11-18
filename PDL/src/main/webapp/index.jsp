<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>3 2 Learn</title>
		
		<script type="text/javascript" src="plugins/mootools/mootools-core-1.4.5-full-nocompat-yc.js"></script>
		
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<link rel="stylesheet" type="text/css" href="css/header.css" />
		<link rel="stylesheet" type="text/css" href="css/course.css" />
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
						<a href="test.html">Courses</a>
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
						<a href="pages/inlog.jsp">Log In</a>
					</li>
					<li>
						<div id="nav-search">
							<a href="" style="vertical-align: middle; display: inline;"><img src="img/toolbar_find.png"></a><input type="text" placeholder="Search">
						</div>
					</li>
				</ul>
			</nav>
		</header>
		<div class="line-break"></div>
		<div class="main-page">
			<div class="content">
				<div class="content-header">
					Popular courses
				</div>
				
				<div class="course">
					<div id="course-content">
						<div id="course-content-image">
							<a href="course_html5.html?id=1&name=HTML_5"><img src="http://2.bp.blogspot.com/_opS9Z5vqQYQ/TTYrMHXNiRI/AAAAAAAAAVA/H0gJ2oCoBTg/s1600/html5_bg_no_icons.png"></a>
						</div>
						<div id="course-content-title">
							<h4><a href="course_html5.html?id=1&name=HTML_5">HTML 5</a></h4>
						</div>
						<div id="course-content-description">
							Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget …
						</div>
						<div class="course-button info">
							<a href="course_html5.html?id=1&name=HTML_5" class="button" id="button">More info</a>
						</div>
					</div>
				</div>
				
				<div class="course">
					<div id="course-content">
						<div id="course-content-image">
							<a href="course_english.html"><img src="http://www.klankbord.nu/Images/640px-Flag_of_English_Language.svg_.png">
						</div>
						<div id="course-content-title">
							<h4><a href="course_english.html">English</a></h4>
						</div>
						<div id="course-content-description">
							Curabitur ligula sapien, tincidunt non, euismod vitae, posuere imperdiet, leo. Maecenas malesuada. Praesent congue erat at massa. Sed cursus turpis vitae tortor. Donec posuere vulputate arcu. Phasellus accumsan cursus velit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed aliquam, nisi quis porttitor …
						</div>
						<div class="course-button info">
							<a href="course_english.html" class="button" id="button">More info</a>
						</div>
					</div>
				</div>
				
				<div class="course">
					<div id="course-content">
						<div id="course-content-image">
							<a href="course_economics.html"><img src="http://images.ctrustnetwork.com/static_pages/business_investment/stock_market/market_analysis/Stock.Line.chart.jpg">
						</div>
						<div id="course-content-title">
							<h4><a href="course_economics.html">Economics</a></h4>
						</div>
						<div id="course-content-description">
							Praesent vestibulum dapibus nibh. Etiam iaculis nunc ac metus. Ut id nisl quis enim dignissim sagittis. Etiam sollicitudin, ipsum eu pulvinar rutrum, tellus ipsum laoreet sapien, quis venenatis ante odio sit amet eros. Proin magna. Duis vel nibh at velit scelerisque suscipit. Curabitur turpis. Vestibulum suscipit nulla quis orci. Fusce ac felis sit amet ligula pharetra condimentum. Maecenas egestas arcu quis ligula mattis placerat. Duis lobortis massa imperdiet quam. Suspendisse potenti. 
						</div>
						<div class="course-button info">
							<a href="course_economics.html" class="button" id="button">More info</a>
						</div>
					</div>
				</div>
				
				<div class="pagination">
					<a href="">Previous</a> &nbsp <a href="">1</a> | <a href=""><u>2</u></a> | <a href="">3</a> &nbsp <a href="">Next</a>
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