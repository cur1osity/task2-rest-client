package com.cur1osity.task2restclient.controller;

import com.cur1osity.task2restclient.domain.TaskDto;
import com.cur1osity.task2restclient.service.TaskClientService;
import com.cur1osity.task2restclient.service.TrelloClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
