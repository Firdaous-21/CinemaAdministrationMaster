package com.example.MovieAdministartion.controller;


import com.example.MovieAdministartion.model.Type;
import com.example.MovieAdministartion.model.Nationality;
import com.example.MovieAdministartion.service.NationalityService;
import com.example.MovieAdministartion.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("parametre")
public class ParametreController {
    private TypeService typeService;
    private NationalityService nationalityService;

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Autowired
    public void setNationalityService(NationalityService nationalityService) {
        this.nationalityService = nationalityService;
    }

    @GetMapping
    public String index() {
        return "redirect:/parametre/nationality/1";
    }

    @GetMapping("nationality")
    public String indexNationalite() {
        return "redirect:/parametre/nationality/1";
    }

    @GetMapping("type")
    public String indexGenre() {
        return "redirect:/parametre/type/1";
    }

    @GetMapping(value = "nationality/{pageNumber}")
    public String listNationalites(@PathVariable Integer pageNumber, Model model) {
        Page<Nationality> page = nationalityService.getList(pageNumber);
        System.out.println("**************************\nTaille de la page : " + page.getNumber());
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listNationalites", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "parametre/nationality/list";


    }

    @GetMapping(value = "type/{pageNumber}")
    public String listGenres(@PathVariable Integer pageNumber, Model model) {
        Page<Type> page = typeService.getList(pageNumber);
        System.out.println("Taille de la page : ");
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listGenres", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "parametre/type/list";

    }

    @GetMapping("nationality/add")
    public String addNationality(Model model) {

        model.addAttribute("nationality", new Nationality());
        return "parametre/nationality/form";
    }

    @GetMapping("type/add")
    public String addType(Model model) {

        model.addAttribute("genre", new Type());
        return "parametre/type/form";

    }

    @GetMapping("nationality/edit/{id}")
    public String editNationality(@PathVariable Long id, Model model) {

        model.addAttribute("nationality", nationalityService.get(id));
        return "parametre/nationality/form";

    }

    @GetMapping("type/edit/{id}")
    public String editType(@PathVariable Long id, Model model) {

        model.addAttribute("genre", typeService.get(id));
        return "parametre/type/form";

    }

    @PostMapping(value = "nationality/save")
    public String save(Nationality nationality, final RedirectAttributes ra) {

        Nationality save = nationalityService.save(nationality);
        ra.addFlashAttribute("successFlash", "Nationalité " + save + "Ajoutée avec succès");
        return "redirect:/parametre/nationality/1";

    }

    @PostMapping(value = "type/save")
    public String saveGenre(Type type, final RedirectAttributes ra) {

        Type save = typeService.save(type);
        ra.addFlashAttribute("successFlash", "type " + save + " Ajouté avec succès");
        return "redirect:/parametre/type";

    }
    @GetMapping("nationality/delete/{id}")
    public String deleteNationality(@PathVariable Long id) {

        nationalityService.delete(id);
        return "redirect:/parametre/nationality/1";

    }
    @GetMapping("type/delete/{id}")
    public String deleteType(@PathVariable Long id) {

       typeService.delete(id);
        return "redirect:/parametre/type/1";

    }
    @GetMapping(value = "genreNG")
    public String listGenresNG() {
        return "parametre/genre/listNG";
    }

    @GetMapping(path="genreNG/listGenre", produces = "application/json")
    public @ResponseBody List<Type> getAllGenres() {
        List<Type> allGenres = new ArrayList<Type>();
        allGenres = typeService.getListAll();
        return allGenres;
    }
    @GetMapping(value = "genreNG/{pageNumber}", produces = "application/json")
    public  @ResponseBody List<Type> listGenresNG(@PathVariable Integer pageNumber) {
        Page<Type> page = typeService.getList(pageNumber);
        System.out.println("Taille de la page : ");
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

//        model.addAttribute("listGenres", page);
//        model.addAttribute("beginIndex", begin);
//        model.addAttribute("endIndex", end);
//        model.addAttribute("currentIndex", current);

        return page.toList();

    }

}
