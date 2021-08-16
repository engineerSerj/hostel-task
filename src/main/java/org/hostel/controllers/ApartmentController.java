package org.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.hostel.Exceptions.ApartmentNotFoundException;
import org.hostel.Exceptions.CategoryNotFoundException;
import org.hostel.domains.CategoryName;
import org.hostel.dto.ApartmentDto;
import org.hostel.service.ApartmentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RequestMapping("/apartment")
@RequiredArgsConstructor
@RestController
public class ApartmentController {

    private final ApartmentService apartmentService;

    @PostMapping()
    public String add(Model model, @ModelAttribute("apartment") ApartmentDto apartmentDto) throws ParseException {
        apartmentService.add(apartmentDto);
        return "redirect:/apartment";
    }

    @GetMapping("/new")
    public String newApartment(@ModelAttribute("apartment") ApartmentDto apartmentDto) {
        return "apartment/new";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        apartmentService.remove(id);
        return "redirect:/apartment";
    }

    @PostMapping("/{apartmentId}")
    public String setCategory(Model model, @PathVariable("apartmentId") int apartmentId, @RequestParam("categoryId") int categoryId) throws ApartmentNotFoundException, CategoryNotFoundException {
        model.addAttribute(apartmentService.setCategory(apartmentId, categoryId));
        return "redirect:/edit";
    }

    @GetMapping("/{id}/edit")
    public String getApartment(Model model, @PathVariable("id") int id) throws ApartmentNotFoundException {
        model.addAttribute("apartment", apartmentService.getById(id));
        model.addAttribute("categories", CategoryName.values());
        return "apartment/edit";
    }

    @GetMapping("/{id}")
    public String showApartment(Model model, @PathVariable("id") int id) throws ApartmentNotFoundException {
        model.addAttribute("apartment", apartmentService.getById(id));
        return "apartment/show";
    }


}
