package com.homework.bookcontacts.controllers;

import com.homework.bookcontacts.model.Contact;
import com.homework.bookcontacts.services.PhoneBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PhoneBookController {

    private final PhoneBookService service;

    @GetMapping("/")
    public String contacts(Model model) {
        model.addAttribute("contacts", service.findAll());
        model.addAttribute("contact", new Contact());
        return "index";
    }

    @PostMapping("/contacts/save")
    public String saveContact (@ModelAttribute Contact contact) {
        service.save(contact);

        return "redirect:/";
    }

    @GetMapping("/contacts/update/{id}")
    public String inputEditData(@PathVariable Long id, Model model) {
        Contact contact = service.findById(id);
        if (contact != null) {
            model.addAttribute("contact", contact);
            model.addAttribute("contacts", service.findAll());
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/contacts/delete/{id}")
    public String deleteContact (@PathVariable Long id) {
        service.deleteById(id);

        return "redirect:/";
    }

}
