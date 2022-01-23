package com.booktracker.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.booktracker.model.PublicUserInformation;
import com.booktracker.model.UserDimention;
import com.booktracker.model.UserPasswordReset;
import com.booktracker.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PublicUserInformation> registerUser(@RequestBody UserDimention userDimention) {
		userService.registerNewUser(userDimention);
		PublicUserInformation userinformation = userService.getUserInformation(userDimention.getUsername());
		return ResponseEntity.ok(userinformation);
	}

	@PostMapping(value = "/{userName}/resetPassword", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> resetPassword(@PathVariable String userName,
			@RequestBody UserPasswordReset userPasswordReset) {
		int updateCount = userService.resetUserPassword(userName, userPasswordReset);

		return ResponseEntity.ok().body(updateCount > 0 ? "Success" : "Failed");
	}

	@GetMapping(value = "/{userName}/login", produces = APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PublicUserInformation> login(@PathVariable String userName,
			@RequestBody String password) {
		PublicUserInformation userInformation;
		if (SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase(userName)) {
			userInformation = userService.getUserInformation(userName);
			return ResponseEntity.ok(userInformation);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

}
