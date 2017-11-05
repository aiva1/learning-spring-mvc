package com.tutorial.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") //use name here, it becomes part of the model and accessible via .jsp as ${name}
public class TodoController { //this is spring mvc controller. it will handle web requests

	@Autowired
	TodoService todoService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		//the initBinder allows to use same date format for entire application
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET) 
	public String showAllTodos(ModelMap model) {
		//String userName = (String) model.get("name");
		model.addAttribute("todos", todoService.retrieveTodos(retrieveLoggedUsername()));
		
		return "list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET) 
	public String showTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo());
		return "todo"; //redirect to todo.jsp
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST) 
	public String addNewTodo (ModelMap model, @Valid Todo todo, BindingResult result) { //desc comes from form in .jsp
		
		//the err message from @Size in Todo will be stored in result if there is any
		if(result.hasErrors()) {
			return "todo"; //return user to todo page is there are any issues, prevent from adding new todo
		}
		
		todoService.addTodo(retrieveLoggedUsername(), todo.getDesc(), new Date(), false);
		model.clear(); //it will not pass any params as url - we just do not need those params in this case as we are returning back
		return "redirect:/list-todos"; //re-execute /list-todos link. that way we don't need to add params to Model again
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam int id) {
		Todo todoToUpdate = todoService.retrieveTodoById(id);
		
		//make this object available to the screen
		model.addAttribute("todo", todoToUpdate);
		
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		//the err message from @Size in Todo will be stored in result if there is any
		if(result.hasErrors()) {
			return "todo"; //return user to todo page is there are any issues
		}
		
		todo.setUser(retrieveLoggedUsername()); //(String) model.get("user")); 
		todoService.updateTodo(todo);
		
		model.clear(); //to prevent request parameter "name" to be passed
				
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET) 
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		todoService.deleteTodo(id);
		model.clear();
		return "redirect:/list-todos";
	}
	
	
	private String retrieveLoggedUsername() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
}
