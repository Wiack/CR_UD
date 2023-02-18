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

    //Методы должны быть глаголами ...  (в смешанном регистре с первой строчной буквой, с первой буквой каждого внутреннего слова с заглавной буквы)

    @GetMapping("/newUser")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User()); //создаем нового пользователя
        return "detailsNewUser";
    }

    @PostMapping()
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user); //сохраняем нового пользователя
        return "redirect:/";
    }


    @GetMapping("/editInfo/{id}")
    public String editInfoUser(@PathVariable("id") Long id, Model model) {  //редактируем информацию о пользователе
        model.addAttribute("user", userService.getUserById(id));
        return "editInfoUser";
    }


    @PatchMapping("/updeteInfo")
    public String saveEditInfoUser(@ModelAttribute("user") User user) { //сохраняем отредактированную информацию о пользователе
        userService.update(user);
        return "redirect:/";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }
}