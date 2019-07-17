package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Request;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ListRepository listRepository;

    @RequestMapping("/")
    public String showList(Model model){
        model.addAttribute("list", listRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String itemForm(Model model){
        model.addAttribute("item", new ToDoList());
        return "itemForm";
    }

    @PostMapping("/process")
    public String processItem(@Valid ToDoList item, BindingResult result){
        if(result.hasErrors()){
            return "itemForm";
        }
        listRepository.save(item);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showItem(@PathVariable("id") long id, Model model){
        model.addAttribute("item", listRepository.findById(id).get());
        return "itemShow";
    }

    @RequestMapping("/update/{id}")
    public String updateItem(@PathVariable("id") long id, Model model){
        model.addAttribute("item", listRepository.findById(id).get());
        return "itemUpdate";
    }

    @RequestMapping("/delete/${id}")
    public String deleteItem(@PathVariable("id") long id){
        listRepository.deleteById(id);
        return "redirect:/";
    }


}
