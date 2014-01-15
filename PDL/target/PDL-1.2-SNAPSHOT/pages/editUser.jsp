<%@include file="/includes/header.jsp" %>

<form method="post" action="editUser">

    <div class="content-header">
        <%= Helper.translateWord(language, "Edit user information")%>
    </div>
    <div class="course">
        <div id="course-content">
            <c:forEach var="error" items="${errors}">
                <div class="error_message">
                    ${error}
                </div>
            </c:forEach>
            <c:if test="${success != null && success}">

                <div class="success_message">
                    <%= Helper.translateWord(language, "You have successfully edited your information")%>.
                </div>

            </c:if>

            <label class="label">
            </label>
            <div class="form-input-radio">
                <span class="form-span"><%= Helper.translateWord(language, "Gender")%>:</span>
                <%= Helper.translateWord(language, "Male")%><input name="gender" type="radio" value="m" required <c:if test="${user.gender == 109}">checked</c:if>> <!-- 109 == ASCII 'm' -->
                <%= Helper.translateWord(language, "Female")%><input name="gender" type="radio" value="f" required <c:if test="${user.gender == 102}">checked</c:if>> <!-- 102 == ASCII 'f' -->
                    <input type="hidden" name="gender" value="empty">
                </div>
                <label class="label">
                    <span class="form-span"><%= Helper.translateWord(language, "Firstname")%>:</span>
                <input name="firstname" type="text" value="${user.firstname}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Surname")%>:</span>
                <input name="surname" type="text" value="${user.surname}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "City")%>:</span>
                <input type="text" name="city" placeholder="City" class="form-input" required value="${user.city}">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Country")%>:</span>
                <input type="text" name="country" placeholder="Country" class="form-input" required value="${user.country}">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Zipcode")%>:</span>
                <input name="zipcode" type="text" value="${user.zipcode}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Address")%>:</span>
                <input name="address" type="text" value="${user.address}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Language")%>:</span>
                <select class="form-input-select" name="language" required autocomplete="off">
                    <option value=""><%= Helper.translateWord(language, "Select language")%>...</option>
                    <option value="0" <c:if test="${user.language == 0}">selected</c:if>>English (UK)</option>
                    <option value="1" <c:if test="${user.language == 1}">selected</c:if>>Nederlands</option>
                    </select>
                </label>
                <label class="label">
                    <span class="form-span"><%= Helper.translateWord(language, "E-mail")%>:</span>
                <input name="email" type="text" value="${user.email}" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Password")%>:</span>
                <input name="password" type="password" placeholder="<%= Helper.translateWord(language, "Required")%>" value="" class="form-input" required>
            </label>

        </div>
    </div>
    <div class="content-header">
        <%= Helper.translateWord(language, "Change your password")%>
    </div>
    <div class="course">
        <div id="course-content">

            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "New password")%>:</span>
                <input name="new_password" type="password" placeholder="<%= Helper.translateWord(language, "Optional")%>" value="" class="form-input">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Confirm password")%>:</span>
                <input name="confirm_new_password" type="password" placeholder="<%= Helper.translateWord(language, "Optional")%>" value="" class="form-input">
            </label>

        </div>
    </div>

    <div class="course">
        <div id="course-content">
            <input type="submit" class="button" id="button" value="<%= Helper.translateWord(language, "Save")%>">
            <% if (Helper.isStudent(user)) {%>
                <a href="studentPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
            <% }%>
            <% if (Helper.isTeacher(user)) {%>
                <a href="teacherPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
            <% }%>
            <% if (Helper.isAdmin(user)) {%>
                <a href="adminPanel" class="button cancel" id="button"><%= Helper.translateWord(language, "Back")%></a>
            <% } %>
        </div>
    </div>
</form>



<%@include file="/includes/footer.jsp" %>