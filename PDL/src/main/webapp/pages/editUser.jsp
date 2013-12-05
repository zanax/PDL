<%@include file="/includes/header.jsp" %>

<form method="post" action="editUser">

    <div class="content-header">
        Edit user information
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
                    U have successfully edited your information!    
                </div>

            </c:if>

            <c:if test="${show != null}">
                <label class="label">
                </label>
                <div class="form-input-radio">
                    <span class="form-span">Gender:</span>
                    Male<input name="gender" type="radio" value="f" required <c:if test="${gender == 'f'}">selected</c:if>>
                    Female<input name="gender" type="radio" value="m" required <c:if test="${gender == 'm'}">selected</c:if>>
                        <input type="hidden" name="gender" value="empty">
                    </div>
                    <label class="label">
                        <span class="form-span">Firstname:</span>
                        <input name="firstname" type="text" value="${user.firstname}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span">Surname:</span>
                    <input name="surname" type="text" value="${user.surname}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span">Country:</span>
                    <select class="form-input-select" name="country" required>
                        <option value="">Select country...</option>
                        <option value="1">United Kindom</option>
                        <option value="2">Nederland</option>
                        <!-- doet niks omdat t een object is -->
                        <!--<option value="${user.country}" selected>${user.country}</option>-->
                    </select>
                </label>
                <label class="label">
                    <span class="form-span">City:</span>
                    <select class="form-input-select" name="city" required>
                        <option value="">Select city...</option>
                        <option value="1">Winkel</option>
                        <option value="2">Amsterdam</option>
                        <!-- doet niks omdat t een object is -->
                        <!--<option value="${user.city}" selected>${user.city}</option>-->
                    </select>
                </label>
                <label class="label">
                    <span class="form-span">Zipcode:</span>
                    <input name="zipcode" type="text" value="${user.zipcode}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span">Address:</span>
                    <input name="address" type="text" value="${user.address}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span">Language:</span>
                    <select class="form-input-select" name="language" required>
                        <option value="">Select language...</option>
                        <option value="1">English (UK)</option>
                        <option value="2">Nederlands</option>
                        <!-- doet niks omdat t een object is -->
                        <!--<option value="${user.language}" selected>${user.language}</option>-->
                    </select>
                </label>
                <label class="label">
                    <span class="form-span">E-mail:</span>
                    <input name="email" type="text" value="${user.email}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span">Password:</span>
                    <input name="password" type="password" placeholder="required" value="" class="form-input" required>
                </label>

            </div>
        </div>
        <div class="content-header">
            Change your password
        </div>
        <div class="course">
            <div id="course-content">

                <label class="label">
                    <span class="form-span">New password:</span>
                    <input name="new_password" type="password" placeholder="This is optional" value="" class="form-input">
                </label>
                <label class="label">
                    <span class="form-span">Confirm password:</span>
                    <input name="confirm_new_password" type="password" placeholder="This is optional" value="" class="form-input">
                </label>       

            </div>
        </div>

        <div class="course">
            <div id="course-content">
                <input type="submit" class="button" id="button" value="Save changes">
            </div>
        </div>

    </c:if>
</form>



<%@include file="/includes/footer.jsp" %>

