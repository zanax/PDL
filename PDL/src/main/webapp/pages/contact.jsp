<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Contact
</div>

<div class="course">
    <div id="course-content">

            <form method="post" action="contact">
                <label class="label">
                    <span class="form-span">Name:</span>
                    <input name="name" type="text" placeholder="Name" value="${name}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span">Subject:</span>
                    <input name="subject" type="text" placeholder="Subject" value="${subject}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span">Message:</span>
                    <textarea name="message" placeholder="Message" class="form-input-textarea" required>${message}</textarea>
                </label>
                <label class="label">
                    <span class="form-span">E-mail:</span>
                    <input type="text" name="email" placeholder="you@mail.com" class="form-input" required value="${email}">
                </label>
                <input type="submit" class="button" id="button" name="contact" value="Send">
            </form>
    </div>

    <div id="course-content" style="max-height: 100%;">
        <div id="course-content-title">
            <h4>Contact information</h4>
        </div>
        <br/>
        Phone: 075-1234567<br/>
        E-mail: 32Learn.contact@gmail.com<br/>
        Address: Duivendrechtsekade 36-38<br/>
        City: Amsterdam<br/>
        ZIP code: 1111 AA<br/><br/><br/><br/>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>