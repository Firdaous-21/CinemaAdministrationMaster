package com.example.MovieAdministartion.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.example.MovieAdministartion.model.Room;
import com.example.MovieAdministartion.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("rooms")
public class RoomController {

        private RoomService salleService;

        @Autowired
        public void setSalleService(RoomService roomService) {
            this.salleService = roomService;
        }
        @GetMapping
        public String index() {
            return "redirect:/rooms/1";
        }
        @GetMapping(value = "/{pageNumber}")
        public String list(@PathVariable Integer pageNumber, Model model) {
            Page<Room> page = salleService.getList(pageNumber);
          //  System.out.println("**************************\nTaille de la page : "+page.getNumber());
            int current = page.getNumber() + 1;
            int begin = Math.max(1, current - 5);
            int end = Math.min(begin + 10, page.getTotalPages());

            model.addAttribute("listSalles", page);
            model.addAttribute("beginIndex", begin);
            model.addAttribute("endIndex", end);
            model.addAttribute("currentIndex", current);

            return "rooms/list";
        }
        @GetMapping("/add")
        public String add(Model model) {
            model.addAttribute("room", new Room());
            return "rooms/form";

        }
        @GetMapping("/edit/{id}")
        public String edit(@PathVariable Long id, Model model) {

            model.addAttribute("room", salleService.get(id));
            return "rooms/form";

        }
        @PostMapping(value = "/save")
        public String save(Room salle, final RedirectAttributes ra) {

            Room save = salleService.save(salle);
            ra.addFlashAttribute("successFlash", "room "+save+" Ajoutée avec succès");
            return "redirect:/rooms";

        }

        @GetMapping("/delete/{id}")
        public String delete(@PathVariable Long id) {

            salleService.delete(id);
            return "redirect:/rooms";
        }

        @GetMapping("/list")
        public String showSalles() {
            return "rooms/list";
        }

        @GetMapping(path = "/NG/listp", produces = "application/json")
        public @ResponseBody List<Room> getAllPersons() {
            List<Room> allSalles = new ArrayList<Room>();
//    	for (int i = 0; i < 5; i++) {
//    		Personne p = new Personne();
//    		p.setId((long) i);
//    		p.setNom("Nom_"+i);
//    		p.setPrenom("Prenom _"+i);
//    		p.setTypePersonne("Type_"+1);
//    		allPersons.add(p);
//		}
            allSalles = salleService.getListAll();
            return allSalles;
        }

        @RequestMapping(value = "/ng/get", params = { "page", "size" }, method = RequestMethod.GET)
        public @ResponseBody Page<Room> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {

            Page<Room> resultPage = salleService.findPaginated(page, size);
            if (page > resultPage.getTotalPages()) {
                //throw new MyResourceNotFoundException();
                System.err.println("Numero de page incorrect");
            }
            return resultPage;
        }
}
