<%@page import="models.Teacher"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page session="true" %>
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
                                <a href="index.jsp">Courses</a>
                            </li>
                            <li>
                                <a href="pages/mycourses.html">My Courses</a>
                            </li>
                            <% if(session.getAttribute("user") != null && session.getAttribute("user") instanceof Teacher) { %>
                            <li>
                                <a href="createCourse">Create course</a>
                            </li>
                            <li>
                                <a href="createTest">Create test</a>
                            </li>
                            <% } %>
                            <li>
                                <a href="">Mordem</a>
                            </li>
                            <li>
                                <a href="">Platica</a>
                            </li>
                            <li>
                                <a href="pages/contact.html">Contact</a>
                            </li>
                            <% if(session.getAttribute("user") == null) { %>
                            <li>
                                <a href="login">Log In</a>
                            </li>
                            <li>
                                <a href="register">Register</a>
                            </li>
                            <% } else { %>
                            <li>
                                <a href="logout">Log Out</a>
                            </li>
                            <%}%>
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