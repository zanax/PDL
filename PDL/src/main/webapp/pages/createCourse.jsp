<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Create Course
</div>

<div class="course">
    <div id="course-content" style="text-align: center;">
        <form method="post" action="">
            <div id="course-content-title">
                <h4>Title</h4>
                <input type="text" placeholder="Title" style="border: 1px solid #00B711; border-radius: 5px 5px 5px 5px; height: 25px; text-align: center; width: 215px;">
            </div>
            <div id="course-content-title">
                <h4>Description</h4>
                <input type="text" placeholder="Description" style="border: 1px solid #00B711; border-radius: 5px 5px 5px 5px; height: 100px; text-align: center; width: 215px;">
            </div>

            <!-- TODO: Dropdown List waar je meerdere opties
            <div class="container"> <input type="checkbox" /> This is checkbox <br/ >
                <input type="checkbox" /> This is checkbox <br />
                <input type="checkbox" /> This is checkbox <br /> 
                <input type="checkbox" /> This is checkbox <br />
                <input type="checkbox" /> This is checkbox <br />
                <input type="checkbox" /> This is checkbox <br /> 
                <input type="checkbox" /> This is checkbox <br />
                <input type="checkbox" /> This is checkbox <br />
                <input type="checkbox" /> This is checkbox <br /> 
                <input type="checkbox" /> This is checkbox <br />
            </div> -->

            <label class="label">
                <span class="form-span">Category</span>
                <select class="form-input-select" name="Category" required>
                    <option value="">Select category...</option>
                    <option value="1">Art</option>
                    <option value="2">Geography</option>
                    <option value="3">History</option>
                    <option value="4">Languages</option>
                    <option value="5">Literacy</option>
                    <option value="6">Music</option>
                    <option value="7">Mathematics</option>
                    <option value="8">Science</option>
                </select>
            </label>

            <div class="course-button info" style="left: 550px;">
                <a href="courseConfig.jsp" class="button" id="button">Create</a>
            </div>
            <div class="course-button info" style="left: 650px;">
                <input type="button" class="button" name="reset_form" value="Reset Form" onclick="this.form.reset();">
            </div>
        </form>
    </div>
</div>

<!--

* Category - Database ophalen
* Gegevens moeten na 'Create' button opgeslagen worden in de Database (inc Teacher)

-->

<%@include file="/includes/footer.jsp" %>