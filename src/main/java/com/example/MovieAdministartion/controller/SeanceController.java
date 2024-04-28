package com.example.MovieAdministartion.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.MovieAdministartion.model.Movie;
import com.example.MovieAdministartion.model.Seance;
import com.example.MovieAdministartion.service.MovieService;
import com.example.MovieAdministartion.service.PersonService;
import com.example.MovieAdministartion.service.RoomService;
import com.example.MovieAdministartion.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("seances")
public class SeanceController {
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
    public void setSalleService(RoomService salleService) {
        this.salleService = salleService;
    }

    @Autowired
    public void setPersonneService(PersonService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public String index() {
        return "redirect:/seances/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Seance> page = seanceService.getList(pageNumber);

        //System.out.println("Taille de la page : ");
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listSeances", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "seances/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("seance", new Seance());
        model.addAttribute("listeSeances", seanceService.getListAll());
        model.addAttribute("listFilms", filmService.getListAll());
        model.addAttribute("listSalles", salleService.getListAll());
        return "seances/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("seance", seanceService.get(id));
        model.addAttribute("listeSeances", seanceService.getListAll());
        model.addAttribute("listFilms", filmService.getListAll());
        model.addAttribute("listSalles", salleService.getListAll());
        return "seances/form";

    }

    @PostMapping(value = "/save")
    public String save(Seance seance, final RedirectAttributes ra) {

        Seance save = seanceService.save(seance);
        ra.addFlashAttribute("success", save + " : Séance Ajoutée avec succès");
        return "redirect:/seances/1";
    }

    @PostMapping(value = "/addActors")
    public String addActors(Movie film, Model model, final RedirectAttributes ra) {
       Movie filmToUpdate = filmService.get(film.getId());
        try {
            filmToUpdate.setActeurs(film.getActeurs());
        } catch (Exception e) {
            System.out.println("#########\nAdd Actors Error:\n" + e);
            e.printStackTrace();
        }
       Movie save = filmService.save(filmToUpdate);
        model.addAttribute("film", save);
        model.addAttribute("listPersonnes", personneService.getListAll());

        ra.addFlashAttribute("success", " Acteurs Ajoutés avec succès dans " + save);
        return "movies/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        seanceService.delete(id);
        return "redirect:/seances";

    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        model.addAttribute("seance", seanceService.get(id));
        model.addAttribute("listSeances", seanceService.getListAll());
        return "seances/details";

    }

    @GetMapping("/show/list")
    public String showPersons() {
        return "/film/listNG";
    }

    @GetMapping(path = "/NG/listp", produces = "application/json")
    public @ResponseBody List<Movie> getAllFilms() {
        List<Movie> allFilms = new ArrayList<Movie>();
        allFilms = filmService.getListAll();
        System.out.println("Size of List allPersons : " + allFilms.size());
        return allFilms;
    }
}



