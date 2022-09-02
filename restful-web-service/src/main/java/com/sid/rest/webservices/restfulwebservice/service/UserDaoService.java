package com.sid.rest.webservices.restfulwebservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sid.rest.webservices.restfulwebservice.model.User;

@Service
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;
	static {
		users.add(new User(++userCount, "Siddhant", LocalDate.of(1996, 8, 9)));
		users.add(new User(++userCount, "Shaurya", LocalDate.of(2000, 11, 10)));
		users.add(new User(++userCount, "Ashu", LocalDate.of(1996, 10, 14)));
	}
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User u:users) {
			if(u.getId()==id) {
				return u;
			}
		}
		/* System.out.println(userCount+" "+users.size()+" id = "+id);
		User u = users.stream().filter(i->i.getId()==id).reduce((a, b) -> {
            throw new IllegalStateException("Multiple elements: " + a + ", " + b);
        }).get();
		System.out.println("Some  "+u); */
		return null;
	}
	
	public User deleteUser(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User u = iterator.next();
			if(u.getId() == id) {
				iterator.remove();
				return u;
			}
		}
		return null;
	}
}
