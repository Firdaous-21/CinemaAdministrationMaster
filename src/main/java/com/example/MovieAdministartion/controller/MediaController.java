package com.example.MovieAdministartion.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.MovieAdministartion.model.Media;
import com.example.MovieAdministartion.model.Person;
import com.example.MovieAdministartion.service.MediaService;

import com.example.MovieAdministartion.service.MovieService;
import com.example.MovieAdministartion.service.NationalityService;
import com.example.MovieAdministartion.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("media")
public class MediaController {

    @Autowired
    private MediaService mediaService;
     private MovieService movieService;
    private final String UPLOAD_DIR = "/src/main/resources/static/photos/films/";

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public String index() {
        return "redirect:/media/1";
    }


      @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
     Page<Media> page = mediaService.getList(pageNumber);
        System.out.println("Taille de la page : ");
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        model.addAttribute("mediaList", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "media/list";
    }
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String add(Model model) {
        model.addAttribute("media", new Media());
        model.addAttribute("listMovies" ,movieService.getListAll());
        return "media/form";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("media", mediaService.get(id));
        model.addAttribute("listMovies", movieService.getListAll());
        return "media/form";
    }
    @PostMapping("/save")
    public String save(@RequestParam("file") MultipartFile file, Media media, final RedirectAttributes ra) {
        //check if is there a file
        if (!file.isEmpty()) {
            // normalize the file path
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            // save the file on the local file system
            try {
                String uuid = UUID.randomUUID().toString();
                String uploadDir = UPLOAD_DIR;
                FileUploadUtil.saveFile(uploadDir, uuid + fileName, file);
                media.setMedia("/photos/films/" + uuid + fileName);
            } catch (IOException e) {
                System.out.println("#####\nUpload Error:\n" + e);
                e.printStackTrace();
            }
        }
        Media save= mediaService.save(media);
        ra.addFlashAttribute("successFlash", "media " + save + " Ajoutée avec succès");
        return "redirect:/media";
    }

        @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        mediaService.delete(id);
        return "redirect:/media/1";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        Media media = mediaService.get(id);
        model.addAttribute("media", media);
        return "media/details";
    }

}
