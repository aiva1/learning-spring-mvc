<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
	<h3>Hello ${name}</h3>
	<br/>		
	<table class="table table-striped">
		<caption>Your todos are</caption>
		<thead>
			<th>Description</th>
			<th>Target Date</th>
			<th>Completed?</th>
			<th></th>
		</thead>
		<tbody>
		
			<c:forEach items="${todos}" var="todo">
				<tr>
					<!-- desc, targetDate, isDone are taken from Tofo.java -->
					<td>${todo.desc}</td> 
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}"/></td>
					<td>${todo.done}</td>
					<td>
						<a class="btn btn-primary" href="/update-todo?id=${todo.id}">Update</a>
						<a class="btn btn-danger" href="/delete-todo?id=${todo.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	
	</br>
	
	Your todos are (outside of the table): <br/>
	<!-- for (Todo todo : todos) -->		
	<c:forEach items="${todos}" var="todo">
		${todo.id} ${todo.desc} ${todo.user} <br/>
	</c:forEach>
	
	<br/>
	<div>
		<a class="btn btn-success" href="/add-todo">Add new TODO</a>
	</div>
</div>

<%@ include file="common/footer.jspf" %>
	