package com.cur1osity.task2restclient.controller;

import com.cur1osity.task2restclient.domain.TaskDto;
import com.cur1osity.task2restclient.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("tasks", service.findAll());
        model.addAttribute("newTask", new TaskDto());
        return "tasks";
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public String findOne(Model model)  {
        model.addAttribute("tasks", service.findAll());
        model.addAttribute("newTask", new TaskDto());
        return "tasks";
    }

    @PatchMapping({"/{id}"})
    public String update(@PathVariable Long id, TaskDto task) {
        service.update(id, task);
        return "redirect:/tasks";
    }

    @DeleteMapping({"/{id}"})
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/tasks";
    }

    @DeleteMapping
    public String deleteAll() {
        service.deleteAllTask();
        return "redirect:/tasks";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("newTask") TaskDto task) {
        service.create(task);
        return "redirect:/tasks";
    }

//    @ExceptionHandler(HttpClientErrorException.class)
//    public String handleClientError(HttpClientErrorException ex, Model model) throws IOException {
//        MessageDTO dto = mapper.readValue(ex.getResponseBodyAsByteArray(), MessageDTO.class);
//        model.addAttribute("error", dto.getMessage());
//        return findAll(model);
//    }

}
