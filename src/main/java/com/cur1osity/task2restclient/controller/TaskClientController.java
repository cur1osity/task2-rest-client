package com.cur1osity.task2restclient.controller;

import com.cur1osity.task2restclient.domain.TaskDto;
import com.cur1osity.task2restclient.service.TaskClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/tasks")
public class TaskClientController {

    @Autowired
    private TaskClientService service;

    @GetMapping
    public String findAll(Model model) {
        try {
            model.addAttribute("tasks", service.findAll());
        } catch (ServiceUnavailableEx ex) {
            model.addAttribute("tasks", service.findAllifServiceUnavailable());
        }
        model.addAttribute("newTask", new TaskDto());

        return "tasks";
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public String findOne(Model model, @PathVariable Long id) {
        try {
            model.addAttribute("tasks", service.findAll());
            model.addAttribute("task", service.findTask(id));
        } catch (ServiceUnavailableEx ex) {
            model.addAttribute("tasks", service.findAllifServiceUnavailable());
            model.addAttribute("task", service.findTaskifServiceUnavailable(id));
            model.addAttribute("newTask", new TaskDto());
        }
        model.addAttribute("newTask", new TaskDto());
        return "tasks";
    }


    @PatchMapping({"/{id}"})
    public String update(@PathVariable Long id, TaskDto task, RedirectAttributes model) {
        model.addFlashAttribute("message_update", "success");
        service.update(id, task);
        return "redirect:/tasks";
    }

    @DeleteMapping({"/{id}"})
    public String delete(@PathVariable Long id, RedirectAttributes model) {
        model.addFlashAttribute("message_delete", "success");
        service.delete(id);
        return "redirect:/tasks";
    }

    @DeleteMapping
    public String deleteAll(RedirectAttributes model) {
        model.addFlashAttribute("message_deleteAll", "success");
        service.deleteAllTask();
        return "redirect:/tasks";
    }

    @PostMapping
    public String create(@ModelAttribute("newTask") TaskDto task, RedirectAttributes model) {
        model.addFlashAttribute("message_post", "success");
        service.create(task);
        return "redirect:/tasks";
    }

}
