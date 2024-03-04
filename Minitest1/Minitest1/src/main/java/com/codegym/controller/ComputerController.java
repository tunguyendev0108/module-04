package com.codegym.controller;
import com.codegym.model.Computer;
import com.codegym.model.ComputerForm;
import com.codegym.service.ComputerService;
import com.codegym.service.IComputerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("computers")
public class ComputerController {
    @Value("${upload}")
    private String upload;
    private IComputerService computerService = new ComputerService();
    @GetMapping("")
    public ModelAndView index(){
        List<Computer> computerList = computerService.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject(computerList);
        return modelAndView;
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("computer",new ComputerForm());
        return "create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute ComputerForm presentForm){
        MultipartFile file = presentForm.getImg();
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        try {
            FileCopyUtils.copy(file.getBytes(),new File(upload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Computer computer = new Computer();
        if (presentForm.getId() != 0){
            computer.setId(presentForm.getId());
        }
        computer.setCode(presentForm.getCode());
        computer.setName(presentForm.getName());
        computer.setProducer(presentForm.getProducer());
        computer.setImg(fileName);
        computerService.save(computer);
        return "redirect:/computers";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        computerService.remove(id);
        redirect.addFlashAttribute("success","delete success");
        return "redirect:/computers";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("computer", computerService.findById(id));
        return "/view";
    }
    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("computer", computerService.findById(id));
        return "/update";
    }
}
