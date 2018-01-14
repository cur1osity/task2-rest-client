package com.cur1osity.task2restclient.controller;

import com.cur1osity.task2restclient.domain.TaskDto;
import com.cur1osity.task2restclient.domain.TrelloCardDto;
import com.cur1osity.task2restclient.service.TaskClientService;
import com.cur1osity.task2restclient.service.TrelloClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/trello")
public class TrelloController {

    @Autowired
    private TaskClientService service;

    @Autowired
    private TrelloClientService trelloClientService;

    @GetMapping
    public String findAll(Model model) {
        try {
            model.addAttribute("tasks", service.findAll());
            model.addAttribute("newTask", new TaskDto());
            model.addAttribute("trelloBoards", trelloClientService.findAll());
        } catch (ServiceUnavailableEx ex) {
//            noService(model);
        }
        return "trello";
    }

    @GetMapping("/{id}")
    public String findOne(Model model, @PathVariable String id) {
        try {
            model.addAttribute("tasks", service.findAll());
            model.addAttribute("newTask", new TaskDto());
            model.addAttribute("trelloBoard", trelloClientService.findOne(id));
            model.addAttribute("trelloBoards", trelloClientService.findAll());
        } catch (ServiceUnavailableEx ex) {
//            noService(model);
        }
        return "trello";
    }


    @PostMapping
    public String create(@ModelAttribute("newTrelloCard") TrelloCardDto trelloCardDto, RedirectAttributes model) {
        model.addFlashAttribute("message_post", "success");
        trelloClientService.createTrelloCard(trelloCardDto);
        return "redirect:/trello";
    }
}
