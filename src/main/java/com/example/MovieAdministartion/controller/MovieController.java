package com.example.MovieAdministartion.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.example.MovieAdministartion.util.FileUploadUtil;
import com.example.MovieAdministartion.model.Movie;
import com.example.MovieAdministartion.model.Media;
import com.example.MovieAdministartion.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    private TypeService typeService;

    private PersonService personService;
    private NationalityService natService;
    private MediaService mediaService;


    private final String UPLOAD_DIR = "/src/main/resources/static/photos/films/";
    @Autowired
    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

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

        System.out.println("Taille de la page : ");
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
    public String save(@RequestParam("file") MultipartFile file, Movie movie, final RedirectAttributes ra) {
        // check if is there a file
        if (!file.isEmpty()) {
            // normalize the file path
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            // save the file on the local file system
            try {
                String uuid = UUID.randomUUID().toString();
                String uploadDir = UPLOAD_DIR;
                FileUploadUtil.saveFile(uploadDir, uuid + fileName, file);
                Media media = new Media();
                media.setMedia("/photos/films/" + uuid + fileName);
                media.setMovie(movie);
                mediaService.save(media);
            } catch (IOException e) {
                System.out.println("#####\nUpload Error:\n" + e);
                e.printStackTrace();
            }
        }

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
            System.out.println("#########\nAdd Actors Error:\n" + e);
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
        List<Movie> allMovies = new ArrayList<Movie>();
        allMovies = movieService.getListAll();
        System.out.println("Size of List allPersons : " + allMovies.size());
        return allMovies;
    }
}


