<%@page import="models.Admin"%>
<%@page import="models.User"%>
<%@page import="models.Helper"%>
<%@page import="models.Teacher"%>
<%@page import="models.Student"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page session="true" %>
<% int language = Helper.getLanguage(session);%>
<% User user = (User) session.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>3 2 Learn</title>

        <script type="text/javascript" src="plugins/mootools/mootools-core-1.4.5-full-nocompat-yc.js"></script>
        <script type="text/javascript" src="plugins/mootools/mootools-more-1.4.0.1.js"></script>

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
                                <a href="courseCatalog"><%= Helper.translateWord(language, "Courses")%></a>
                            </li>
                            <% if (Helper.isStudent(user)) {%>
                            <li>
                                <a href="studentPanel"><%= Helper.translateWord(language, "Student Panel")%></a>
                            </li>
                            <% }%>
                            <li>
                                <a href="contact"><%= Helper.translateWord(language, "Contact")%></a>
                            </li>
                            <% if (Helper.isTeacher(user)) {%>
                            <li>
                                <a href="teacherPanel"><%= Helper.translateWord(language, "Teacher")%></a>
                            </li>
                            <% }%>
                            <% if (Helper.isAdmin(user)) {%>
                            <li>
                                <a href="adminPanel"><%= Helper.translateWord(language, "Administrator")%></a>
                            </li>
                            <% }%>
                            <% if (user != null) {%>
                            <li>
                                <a href="chat"><%= Helper.translateWord(language, "Chat")%></a>
                            </li>
                            <li>
                                <a href="editUser"><%= Helper.translateWord(language, "Profile")%></a>
                            </li>
                            <%}%>
                            <% if (user == null) {%>
                            <li>
                                <a href="login"><%= Helper.translateWord(language, "Log In")%></a>
                            </li>              
                            <li>
                                <a href="register"><%= Helper.translateWord(language, "Register")%></a>
                            </li>
                            <% } else {%>
                            <li>
                                <a href="logout"><%= Helper.translateWord(language, "Log Out")%></a>
                            </li>
                            <%}%>
                            <li>
                                <div id="nav-search">
                                    <form method="get" action="searchCourses">
                                        <input type="image" value="search" src="img/toolbar_find.png">
                                        <input type="text" name="keyword" placeholder="<%= Helper.translateWord(language, "Search for courses")%>">
                                    </form>
                                </div>
                            </li>
                            <li>
                                <div id="nav-search">
                                    <form method="post" action="changeLanguage">
                                        <select name="language" class="select">
                                            <option value="0" <c:if test="${language == 0}">selected</c:if>>
                                                    English (UK)
                                                </option>
                                                <option value="1" <c:if test="${language == 1}">selected</c:if>>
                                                Nederlands
                                            </option>
                                        </select>
                                        <input type="submit" value="Go">
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </nav>
                </header>
                <div class="line-break"></div>
                <div class="main-page">
                    <div class="content">