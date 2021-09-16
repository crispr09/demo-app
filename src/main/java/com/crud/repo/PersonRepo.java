package com.crud.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.crud.model.Person;

import org.springframework.stereotype.Repository;

@Repository
public class PersonRepo {

	private List<Person> list = new ArrayList<Person>();

	public void getAll() {
		List<Person> list = new ArrayList<>();
		
		list.add( new Person(1, "Rohan", "Developer", true, "1999-02-01"));
		list.add( new Person(1, "Tri", "Developer", true, "1999-02-01"));
		list.add( new Person(1, "Joshua", "Developer", true, "1999-02-01"));
		list.add( new Person(1, "Crig", "Developer", true, "1999-02-01"));
		list.add( new Person(1, "D Yang", "Developer", true, "1999-02-01"));
		list.add( new Person(1, "Mustafa", "Developer", true, "1999-02-01"));
		

		for (Person person : list) {
			list.add(person);
		}

	}

	public List<Person> listPerson() {
		return list;
	}

	public List<Person> search(String name) {
		return list.stream().filter(x -> x.getName().startsWith(name)).collect(Collectors.toList());
	}

	public String add(Person p) {
		Person obj = new Person();
		obj.setId(p.getId());
		obj.setName(p.getName());
		obj.setJob(p.getJob());
		obj.setGender(p.isGender());
		obj.setBirthDay(p.getBirthDay());

		list.add(obj);
		return null;
	}

	public String delete(Integer id) {
		list.removeIf(x -> x.getId() == (id));
		return null;
	}

	public String edit(Person person) {
		int idx = 0;
		int id = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == (person.getId())) {
				id = person.getId();
				idx = i;
				break;
			}
		}

		Person p = new Person();
		p.setId(id);
		p.setName(person.getName());
		p.setJob(person.getJob());
		p.setGender(person.isGender());
		p.setBirthDay(person.getBirthDay());
		list.set(idx, p);

		return null;
	}
}
