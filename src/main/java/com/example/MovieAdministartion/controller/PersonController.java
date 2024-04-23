package com.example.MovieAdministartion.controller;
import com.example.MovieAdministartion.model.Person;
import com.example.MovieAdministartion.util.FileUploadUtil;
import com.example.MovieAdministartion.model.Movie;
import com.example.MovieAdministartion.model.Media;
import com.example.MovieAdministartion.service.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("persons")
public class PersonController {
    private PersonService personService;
    private NationalityService natService;
    private final String UPLOAD_DIR = "/src/main/resources/static/photos/personnes/";


    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setNatService(NationalityService natService) {
        this.natService = natService;
    }

    @GetMapping
    public String index() {
        return "redirect:/personne/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Person> page = personService.getList(pageNumber);
        System.out.println("Taille de la page : ");
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listPersonnes", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "person/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("personne", new Person());
        model.addAttribute("listeNationalites", natService.getListAll());
        return "person/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("personne", personService.get(id));
        model.addAttribute("listeNationalites", natService.getListAll());
        return "person/form";
    }

    @PostMapping(value = "/save")
    public String save(@RequestParam("file") MultipartFile file, Person person, final RedirectAttributes ra) {
        //check if is there a file
        if (!file.isEmpty()) {
            // normalize the file path
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            // save the file on the local file system
            try {
                String uuid = UUID.randomUUID().toString();
                String uploadDir = UPLOAD_DIR;
                FileUploadUtil.saveFile(uploadDir, uuid + fileName, file);
                person.setPhoto("/photos/personnes/" + uuid + fileName);
            } catch (IOException e) {
                System.out.println("#####\nUpload Error:\n" + e);
                e.printStackTrace();
            }
        }


        Person save = personService.save(person);
        ra.addFlashAttribute("successFlash", "Personne " + save + " Ajoutée avec succès");
        return "redirect:/personne";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        personService.delete(id);
        return "redirect:/person";


    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        model.addAttribute("personne", personService.get(id));
        return "person/details";
    }
    @GetMapping("/show/list")
    public String showPersons() {
        return "/personne/listNG";
    }

    @GetMapping(path="/NG/listp", produces = "application/json")
    public @ResponseBody List<Person> getAllPersons() {
        List<Person> allPersons = new ArrayList<Person>();
        allPersons = personService.getListAll();
        System.out.println("Size of List allPersons : "+allPersons.size());
        return allPersons;
    }
}