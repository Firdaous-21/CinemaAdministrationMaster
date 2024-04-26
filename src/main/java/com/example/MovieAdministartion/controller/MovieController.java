package com.example.MovieAdministartion.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.MovieAdministartion.model.Movie;
import com.example.MovieAdministartion.service.MovieService;
import com.example.MovieAdministartion.service.NationalityService;
import com.example.MovieAdministartion.service.PersonService;
import com.example.MovieAdministartion.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    private TypeService typeService;

    private PersonService personService;
    private NationalityService natService;

    @Autowired
    public void setNatService(NationalityService natService) {
        this.natService = natService;
    }

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String index() {
        return "redirect:/movies/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Movie> page = movieService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listMovies", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "movies/list";
    }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("listeNationalites", natService.getListAll());
        model.addAttribute("listPersonnes", personService.getListAll());
        model.addAttribute("listeGenres", typeService.getListAll());
        return "movies/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.get(id));
        model.addAttribute("listeGenres", typeService.getListAll());
        model.addAttribute("listPersonnes", typeService.getListAll());
        model.addAttribute("listeNationalites", natService.getListAll());
        return "movies/form";
    }

    @PostMapping(value = "/save")
    public String save(Movie movie, final RedirectAttributes ra) {
        Movie save = movieService.save(movie);
        ra.addFlashAttribute("success", save + " : Film Ajouté avec succès");
        return "redirect:/movies";
    }

    @PostMapping(value = "/addActors")
    public String addActors(Movie movie, Model model, final RedirectAttributes ra) {
        Movie filmToUpdate = movieService.get(movie.getId());
        try {
            filmToUpdate.setActeurs(movie.getActeurs());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Movie save = movieService.save(filmToUpdate);
        model.addAttribute("film", save);
        model.addAttribute("listPersonnes", personService.getListAll());
        ra.addFlashAttribute("success", " Acteurs Ajoutés avec succès dans " + save);
        return "movies/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        movieService.delete(id);
        return "redirect:/movies";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        model.addAttribute("film", movieService.get(id));
        model.addAttribute("listPersonnes", personService.getListAll());
        return "movies/details";
    }

    @GetMapping("/show/list")
    public String showPersons() {
        return "/movies/listNG";
    }

    @GetMapping(path = "/NG/listp", produces = "application/json")
    public @ResponseBody List<Movie> getAllFilms() {
        List<Movie> allMovies = new ArrayList<>();
        allMovies = movieService.getListAll();
        System.out.println("Size of List allPersons : " + allMovies.size());
        return allMovies;
    }
}
