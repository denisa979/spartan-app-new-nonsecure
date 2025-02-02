package com.spartan.controller;

import com.spartan.dto.SpartanDTO;
import com.spartan.enums.Gender;
import com.spartan.service.SpartanService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/web/spartans")
public class WebController {

    private final SpartanService spartanService;

    public WebController(SpartanService spartanService) {
        this.spartanService = spartanService;
    }

    @GetMapping("/add")
    public String createSpartan(Model model) {
        model.addAttribute("spartan", new SpartanDTO());
        model.addAttribute("genders", Gender.values());
        return "spartan-add";
    }

    @PostMapping("/add")
    public String saveSpartan(@Valid @ModelAttribute("spartan") SpartanDTO spartanDTO,
                              BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Gender.values());
            return "spartan-add";
        }

        spartanService.createSpartan(spartanDTO);
        return "redirect:/web/spartans";
    }

    @GetMapping
    public String spartanList(Model model) {
        model.addAttribute("spartans", spartanService.readAllSpartans());
        return "spartan-list";
    }

    @GetMapping("/{id}")
    public String viewSpartan(@PathVariable("id") UUID uuid, Model model) {
        SpartanDTO spartan = spartanService.readSpartanByUuid(uuid);
        model.addAttribute("spartan", spartan);
        return "spartan-view";
    }

    @GetMapping("/edit/{id}")
    public String editSpartan(@PathVariable("id") UUID uuid, Model model) {

        SpartanDTO spartan = spartanService.readSpartanByUuid(uuid);

        model.addAttribute("spartan", spartan);
        model.addAttribute("genders", Gender.values());

        return "spartan-edit";

    }

    @PostMapping("/update/{id}")
    public String updateSpartan(@PathVariable("id") UUID uuid, @Valid @ModelAttribute("spartan") SpartanDTO spartanDTO,
                                BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Gender.values());
            return "spartan-edit";
        }

        spartanService.updateSpartan(spartanDTO, uuid);
        return "redirect:/web/spartans";
    }

    @GetMapping("/delete/{id}")
    public String deleteSpartan(@PathVariable("id") UUID uuid) {
        spartanService.deleteSpartanByUuid(uuid);
        return "redirect:/web/spartans";
    }

}
