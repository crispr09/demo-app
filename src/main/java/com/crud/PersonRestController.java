package com.crud;

import java.util.List;

import com.crud.model.Person;
import com.crud.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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
public class PersonRestController extends SpringBootServletInitializer {

	@Autowired
	private IPersonService personService;

	@RequestMapping(path = "/people")
	public ResponseEntity<List<Person>> people() {
		List<Person> list = personService.getAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(path = "/search/{name}")
	public ResponseEntity<List<Person>> search(
			@PathVariable(name = "name", required = true) String name) {
		List<Person> list = personService.search(name);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(path = { "/inAdd" })
	public String inAdd(Model model) {
		return "form";
	}

	
	public static void main(String[] args) {
        SpringApplication.run(PersonRestController.class);
    }

}
