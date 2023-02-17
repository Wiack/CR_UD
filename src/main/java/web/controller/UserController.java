package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showAllUsers(ModelMap model) {
        List<User> list = userService.getUsers();
        model.addAttribute("listUsers", list);
        return "allUser";
    }

    @GetMapping("/add_user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit_user/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PatchMapping("/save_edit_user")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @DeleteMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }
}