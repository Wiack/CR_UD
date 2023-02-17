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

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "detailsNewUser";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }


    @GetMapping("/editInfo/{id}")
    public String editInfoUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editInfoUser";
    }


    @PatchMapping("/updeteInfo")
    public String saveEditInfoUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }
}