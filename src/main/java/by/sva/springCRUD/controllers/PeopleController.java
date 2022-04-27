package by.sva.springCRUD.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import by.sva.springCRUD.dao.PersonDAO;
import by.sva.springCRUD.models.Person;

@Controller
@RequestMapping("/people") // все GetMapping получили префикс /people
public class PeopleController {
	private PersonDAO personDAO;
	private int count; 
	
	public PeopleController (PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	@GetMapping() // здесь пусто, потому что корень задан в @RequestMapping("/people")
				  // т.е. тут уже указан адрес /people
	public String index(Model model) {
		// получает всех людей из DAO и передает в представление
		count = personDAO.index().size()+1;
		model.addAttribute("people", personDAO.index());
		return "people/index";
	}
	
	@GetMapping("/{id}") // принимает параметр с номером, т.е. /people/{id}
	public String show(@PathVariable("id") int id, Model model) {
		// получает одного человека из DAO по его id и передает его в представление
		model.addAttribute("person", personDAO.show(id));
		return "people/show";
	}
	
	@GetMapping("/new") // т.е. /people/new
	// создает новый объект
	public String newPerson(Model model) {
		model.addAttribute("person", new Person());
		return "people/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("person") @Valid Person person, BindingResult br) {
		if(br.hasErrors())
			return "people/new";
		personDAO.save(person, count);
		return "redirect:/people"; // переадресация
	}
	
	@GetMapping("/{id}/edit")
	public String edit (Model model, @PathVariable("id") int id) {
		model.addAttribute("person", personDAO.show(id));
		return "people/edit";
	}
	
	@PatchMapping("/{id}")
	public String update (@ModelAttribute("person") @Valid Person person, BindingResult br, @PathVariable("id") int id) {
		if(br.hasErrors())
			return "people/edit";
		personDAO.update(id, person);
		return "redirect:/people";
	}
	
	@DeleteMapping("/{id}")
	public String delete (@PathVariable("id") int id) {
		personDAO.delete(id);
		return "redirect:/people";
	}

}
