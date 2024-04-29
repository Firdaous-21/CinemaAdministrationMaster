package com.example.MovieAdministartion.controller;
import java.util.ArrayList;
import java.util.List;

import com.example.MovieAdministartion.model.Client;
import com.example.MovieAdministartion.service.ClientService;
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
@RequestMapping("clients")
public class ClientController {

    private ClientService customerService;

    @Autowired
    public void setCustomerService(ClientService  customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String index() {
        return "redirect:/clients/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Client> page = customerService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "clients/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("clients", new Client());
        return "clients/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("clients", customerService.get(id));
        return "clients/form";

    }

    @PostMapping(value = "/save")
    public String save(Client customer, final RedirectAttributes ra) {

      Client save = customerService.save(customer);
        ra.addFlashAttribute("successFlash", "Client register successful");
        return "redirect:/clients";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        customerService.delete(id);
        return "redirect:/clients";

    }

    @GetMapping(path="/api/list", produces = "application/json")
    public @ResponseBody List<Client> getAllPersons() {
        List<Client> allCustomers = new ArrayList<Client>();
        allCustomers = customerService.getListAll();
        return allCustomers;
    }
}

