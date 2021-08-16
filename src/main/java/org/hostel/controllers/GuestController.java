package org.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.hostel.Exceptions.ApartmentNotFoundException;
import org.hostel.Exceptions.CategoryNotFoundException;
import org.hostel.Exceptions.GuestNotFoundException;
import org.hostel.domains.Guest;
import org.hostel.dto.GuestDto;
import org.hostel.service.ApartmentService;
import org.hostel.service.GuestService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/guest")
@RequiredArgsConstructor
@RestController
public class GuestController {

    private final GuestService guestService;
    private final ApartmentService apartmentService;

    @PostMapping()
    public String add(@ModelAttribute("guest") GuestDto guestDto) {
        guestService.add(guestDto);
        return "redirect:/guest";
    }

    @GetMapping("/new")
    public String newGuest(@ModelAttribute("guest") GuestDto guestDto) {
        return "guest/new";
    }


    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        guestService.remove(id);
        return "redirect:/guest";
    }

    @PostMapping("/{guestId}")
    public String setCategory(Model model, @PathVariable("guestId") int guestId, @RequestParam("apartmentId") int apartmentId) throws GuestNotFoundException {
        model.addAttribute(guestService.setApartment(guestId, apartmentId));
        return "redirect:/editGuestCategory";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws GuestNotFoundException {
        model.addAttribute("guest", guestService.getById(id));
        return "guest/editGuestCategory";
    }

    @PostMapping("/{apartmentId}")
    public String editGuest(Model model, @ModelAttribute("guest") GuestDto guest, @PathVariable("apartmentId") int apartmentId, @RequestParam("categoryId") int categoryId) throws ApartmentNotFoundException, CategoryNotFoundException {
        model.addAttribute(guestService.editGuest(apartmentId, guest));
        return "redirect:edit";
    }

    @GetMapping("/{id}/edit")
    public String getApartment(Model model, @PathVariable("id") int id) throws GuestNotFoundException {
        model.addAttribute("guest", guestService.getById(id));
        return "guest/edit";
    }

    @GetMapping("/getAll")
    public String getAllGuests(Model model) {
        Iterable<Guest> guests = guestService.getAll();
        model.addAttribute("guests", guests);
        return "guest/guestList";
    }
}
