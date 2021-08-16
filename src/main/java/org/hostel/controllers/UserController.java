package org.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.hostel.Exceptions.ApartmentNotFoundException;
import org.hostel.Exceptions.CategoryNotFoundException;
import org.hostel.Exceptions.GuestNotFoundException;
import org.hostel.Exceptions.UserNotFoundException;
import org.hostel.domains.Role;
import org.hostel.domains.User;
import org.hostel.dto.GuestDto;
import org.hostel.dto.UserDto;
import org.hostel.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public String add(@ModelAttribute("user") UserDto userDto) {
        userService.add(userDto);
        return "redirect:/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") UserDto userDto) {
        return "user/new";
    }


    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        userService.remove(id);
        return "redirect:/user";
    }

    @PostMapping("/{userId}")
    public String setUserRole(Model model, @PathVariable("guestId") int userId, @RequestParam("role") Role role) throws UserNotFoundException {
        model.addAttribute(userService.setRole(userId, role));
        return "redirect:/editUserRole";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) throws UserNotFoundException {
        model.addAttribute("user", userService.getById(id));
        return "guest/editUserRole";
    }
}
