<%@page import="models.User"%>
<%@page import="models.Helper"%>
<%@page import="models.Teacher"%>
<%@page import="models.Student"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page session="true" %>
<% int language = Helper.getLanguage(session);%>
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
                                <a href="popularCourses">Home</a>
                            </li>
                            <li>
                                <a href="courseCatalog"><% out.println(Helper.translateWord(language, "Courses")); %></a>
                            </li>
                            <% if (session.getAttribute("user") instanceof Student) {%>
                            <li>
                                <a href="myCourses"><% out.println(Helper.translateWord(language, "My Courses")); %></a>
                            </li>
                            <li>
                                <a href="myGrades"><% out.println(Helper.translateWord(language, "My Grades")); %></a>
                            </li>
                            <% }%>
                            <li>
                                <a href="contact"><% out.println(Helper.translateWord(language, "Contact")); %></a>
                            </li>
                            <% if (session.getAttribute("user") instanceof Teacher) {%>
                            <li>
                                <a href="teacherPanel"><% out.println(Helper.translateWord(language, "Teacher")); %></a>
                            </li>
                            <% }%>
                            <% if (session.getAttribute("user") == null) {%>
                            <li>
                                <a href="login"><% out.println(Helper.translateWord(language, "Log In")); %></a>
                            </li>              
                            <li>
                                <a href="register"><% out.println(Helper.translateWord(language, "Register")); %></a>
                            </li>
                            <% } else {%>
                            <li>
                                <a href="editUser"><% out.println(Helper.translateWord(language, "Profile")); %></a>
                            </li>
                            <li>
                                <a href="logout"><% out.println(Helper.translateWord(language, "Log Out")); %></a>
                            </li>
                            <%}%>
                            <li>
                                <div id="nav-search">
                                    <form method="get" action="searchCourses">
                                    <input type="image" value="search" src="img/toolbar_find.png">
                                    <input type="text" name="keyword" placeholder="<% out.println(Helper.translateWord(language, "Search for courses")); %>">
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </nav>
                </header>
                <div class="line-break"></div>
                <div class="main-page">
                    <div class="content">