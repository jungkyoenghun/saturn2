
package com.saturn.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saturn.domain.User;
import com.saturn.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	private List<User> users = new ArrayList<User>();

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/loginForm")
	private String loginForm() {
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			System.out.println("Login Failure!!");
			return "redirect:/users/loginForm";
		}

		if (!user.matchPassword(password)) {
			System.out.println("Login Failure!!");
			return "redirect:/users/loginForm";
		}

		System.out.println("Login Success!!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);

		return "redirect:/";
	}

	@GetMapping("logout")
	private String logout(HttpSession session) {
		// session.removeAttribute("sessionedUser");
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);

		System.out.println("Log out!!!!!");
		return "redirect:/";
	}

	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@PostMapping("")
	// @RequestMapping(value = "/user/create", method = { RequestMethod.GET,
	// RequestMethod.POST })
	// public String create(String userId, String Password, String username){
	public String create(User user) {
		System.out.println("userId : " + user);

		users.add(user);
		userRepository.save(user);
		// return "form";
		return "redirect:/users";
	}

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	@GetMapping("/{id}/form")
	public String udpateForm(@PathVariable Long id, Model model, HttpSession session) {
		if (HttpSessionUtils.isLoginUser(session)) {
			return "redierct:/users/loginForm";
		}

		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!sessionedUser.matchId(id)) {
			throw new IllegalStateException("You can update another id");
		}

		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "/user/updateForm";
	}

	// @PostMapping("/{id}")
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {


		if (HttpSessionUtils.isLoginUser(session)) {
			return "redierct:/users/loginForm";
		}

		// 사용자 확인 체크

		User sessionedUser = HttpSessionUtils.getUserFromSession(session);

		if (!sessionedUser.matchId(id)) {
			throw new IllegalStateException("You can update another id");
		}

		User user = userRepository.findOne(id);
		user.update(updatedUser);
		userRepository.save(user);
		return "redirect:/users";
	}

}