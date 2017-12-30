package com.cur1osity.task2restclient.controller;

import com.cur1osity.task2restclient.domain.TaskDto;
import com.cur1osity.task2restclient.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private ObjectMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("tasks", service.findAll());
        model.addAttribute("newTask", new TaskDto());
        return "tasks";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestParam Long id, TaskDto task) {
        service.update(id, task);
        return "redirect:/";
    }


    @RequestMapping(method = RequestMethod.PATCH)
    public String update2(@RequestParam Long id, TaskDto task) {
        service.update2(id, task);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("newTask") TaskDto task) {
        service.create(task);
        return "redirect:/";
    }

//    @ExceptionHandler(HttpClientErrorException.class)
//    public String handleClientError(HttpClientErrorException ex, Model model) throws IOException {
//        MessageDTO dto = mapper.readValue(ex.getResponseBodyAsByteArray(), MessageDTO.class);
//        model.addAttribute("error", dto.getMessage());
//        return findAll(model);
//    }

}
