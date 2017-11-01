<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">

	<h3>Add new TODO item: <br/></h3>
	
	
	
	<!-- if form has no action it will redirect to the same action it came in -->
	<!--  if we added commandName, we have to make that todo available in the model by adding ModelMap to Controller -->
	<form:form method="POST" commandName="todo"> 
	
		<!-- store hidden variables to be returned. they will not appear on screen -->
		<form:hidden path="id"/>
		
		<fieldset class="form-group"> <!--  combo of label+element -->
			<form:label path="desc">Description</form:label>
			<form:input path="desc" class="form-control" type="text" required="required"/> 
			<form:errors path="desc" cssClass="text-warning"></form:errors>
		</fieldset>
		
		<fieldset class="form-group"> <!--  combo of label+element -->
			<form:label path="targetDate">Target Date</form:label>
			<form:input path="targetDate" class="form-control" type="text" required="required"/> 
			<form:errors path="targetDate" cssClass="text-warning"></form:errors>
		</fieldset>
		
		<input class="btn btn-success" type="submit" value="Submit (acts as add/update)"/>
	</form:form>
	
</div>

<%@ include file="common/footer.jspf" %>