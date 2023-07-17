package ru.bk6.SpingBoot_312.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bk6.SpingBoot_312.model.User;
import ru.bk6.SpingBoot_312.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        //Получим всех людей и отправим на представление
        model.addAttribute("users", userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // Получим одного человека и отправим на представление
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new-user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        //Создадим нового юсера и сохраним в базе данный
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update-user";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUserById(id,user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
