package com.sid.rest.webservices.restfulwebservice.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sid.rest.webservices.restfulwebservice.model.User;
import com.sid.rest.webservices.restfulwebservice.model.UserNotFoundException;
import com.sid.rest.webservices.restfulwebservice.service.UserDaoService;


@RestController
@RequestMapping("/api/users")
public class UserResource {
	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("")
	public CollectionModel<User> retrieveAllUsers(){
		System.out.println("All Users");
		List<User> userList = userDaoService.findAll();
		CollectionModel<User> model = CollectionModel.of(userList);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("user"));
		return model;
	}
	
	@GetMapping("/{userid}")
	public EntityModel<User> retrieveUser(@PathVariable("userid") int id) {
		User u = userDaoService.findOne(id);
		if(u==null)
			throw new UserNotFoundException("id-"+id);
		EntityModel<User> em = EntityModel.of(u);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveUser(id));
		em.add(linkToUsers.withRel("all-users"));
		return em;
	}
	
	@PostMapping
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		user = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
									.path("/{id}")
									.buildAndExpand(user.getId())
									.toUri();
		return ResponseEntity.created(location).build();
	}
	
	/*@DeleteMapping("{id}")
	public ResponseEntity deleteUser(@PathVariable("id") int id) {
		User u = userDaoService.deleteUser(id);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{/id}")
				.buildAndExpand(u.getId()).toUri();
		if(u==null) {
			return ResponseEntity.noContent();
		}
		
		return ResponseEntity.created(location);
	}
	*/
	 
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable("id") int id) {
		User u = userDaoService.deleteUser(id);
		if(u==null) {
			throw new UserNotFoundException("id-"+id);
		}
		return u;
	}
	
}
