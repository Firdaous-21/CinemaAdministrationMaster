package com.example.MovieAdministartion.controller;
import com.example.MovieAdministartion.service.MovieService;
import com.example.MovieAdministartion.service.PersonService;
import com.example.MovieAdministartion.service.RoomService;
import com.example.MovieAdministartion.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.MovieAdministartion.util.FileUploadUtil;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.Date;
@Controller
public class WebpageController {
    private MovieService filmService;
    private RoomService salleService;
    private PersonService personneService;
    private SeanceService seanceService;
    @Autowired
    public void setFilmService(MovieService filmService) {
        this.filmService = filmService;
    }

    @Autowired
    public void setSeanceService(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @Autowired
    public void setPersonneService(PersonService personneService) {
        this.personneService = personneService;
    }

    @Autowired
    public void setSalleService(RoomService salleService) {
        this.salleService = salleService;
    }


    @GetMapping("/wb")
    public String index(Model model) throws ParseException{
        Integer nbFilms, nbPersonnes, nbSalles, nbSeances;
        nbFilms = filmService.getListAll().size();
        nbPersonnes = personneService.getListAll().size();
        nbSalles = salleService.getListAll().size();
        nbSeances = seanceService.getSeancesParDate(new Date()).size();
        ;

        model.addAttribute("nbFilms", nbFilms);
        model.addAttribute("nbPersonnes", nbPersonnes);
        model.addAttribute("nbSalles", nbSalles);
        model.addAttribute("nbSeances", nbSeances);
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}



