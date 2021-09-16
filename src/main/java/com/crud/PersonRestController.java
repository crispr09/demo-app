package com.crud;

import java.util.List;

import com.crud.model.Person;
import com.crud.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@SpringBootApplication
@RequestMapping("/person/api/v1")
public class PersonRestController {

	@Autowired
	private IPersonService personService;

	@RequestMapping(path = "/people")
	public ResponseEntity<List<Person>> people() {
		List<Person> list = personService.listPerson();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/search/{name}")
	public ResponseEntity<List<Person>> search(
			@PathVariable(name = "name", required = true) String name) {
		List<Person> list = personService.search(name);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(path = { "/inAdd" })
	public String inAdd(Model model) {
		return "form";
	}

	@PostMapping(path = { "/add" })
	public String add(Model model, Person p) {
		personService.add(p);
		return "redirect:/";
	}

	@GetMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable(name = "id") int id) {
		personService.delete(id);
		return "redirect:/";
	}

	@GetMapping(path = "/iniEdit/{id}")
	public String iniEdit(Model model, @PathVariable(name = "name") String name) {
		List<Person> list = personService.search(name);
		Person obj = new Person();
		for (Person element : list) {
			obj = element;
			break;
		}
		model.addAttribute("person", obj);
		return "form";
	}

	@PostMapping(path = "/edit")
	public String edit(Model model,Person p) {
		personService.edit(p);
		return "redirect:/";
	}

}
