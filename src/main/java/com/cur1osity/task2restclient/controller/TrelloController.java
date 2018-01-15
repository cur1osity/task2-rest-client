package com.cur1osity.task2restclient.controller;

import com.cur1osity.task2restclient.domain.TaskDto;
import com.cur1osity.task2restclient.domain.TrelloCardDto;
import com.cur1osity.task2restclient.service.TaskClientService;
import com.cur1osity.task2restclient.service.TrelloClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
        } catch (TaskServiceUnavailableEx ex) {
            noTaskService(model);
        } catch (TrelloServiceUnavailableEx ex) {
            noTrelloService(model);
        }
        return "trello";
    }

    @GetMapping({"/noTaskService"})
    public String noTaskService(Model model) {
        model.addAttribute("tasks", new ArrayList<>());
        model.addAttribute("newTask", new TaskDto());
        model.addAttribute("trelloBoards", new ArrayList<>());
        model.addAttribute("noTaskService", "serviceUnvailable");
        return "trello";
    }

    @GetMapping({"/noTrelloService"})
    public String noTrelloService(Model model) {
        model.addAttribute("tasks", new ArrayList<>());
        model.addAttribute("newTask", new TaskDto());
        model.addAttribute("trelloBoards", new ArrayList<>());
        model.addAttribute("noTrelloService", "serviceUnvailable");
        return "trello";
    }

    @GetMapping("/{id}/{boardId}")
    public String findOne(Model model, @PathVariable Long id, @PathVariable String boardId) {
        try {
            model.addAttribute("tasks", service.findAll());
            model.addAttribute("newTask", new TaskDto());
            model.addAttribute("trelloBoard", trelloClientService.findOne(boardId));
            model.addAttribute("trelloBoards", trelloClientService.findAll());
            model.addAttribute("task",service.findTask(id));
        } catch (TaskServiceUnavailableEx ex) {
            noTaskService(model);
        } catch (TrelloServiceUnavailableEx ex) {
            noTrelloService(model);
        }
        return "trello";
    }

    @PostMapping
    public String create(@ModelAttribute("newTrelloCard") TrelloCardDto trelloCardDto, Model model) {

        try {
            model.addAttribute("tasks", service.findAll());
            model.addAttribute("newTask", new TaskDto());
            model.addAttribute("trelloBoards", trelloClientService.findAll());
            model.addAttribute("trelloCard", trelloClientService.createTrelloCard(trelloCardDto));
        } catch (TaskServiceUnavailableEx ex) {
            noTaskService(model);
        } catch (TrelloServiceUnavailableEx ex) {
            noTrelloService(model);
        }
        return "trello";
    }
}
