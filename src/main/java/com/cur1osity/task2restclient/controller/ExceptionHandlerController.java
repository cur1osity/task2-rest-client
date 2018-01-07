package com.cur1osity.task2restclient.controller;

import com.cur1osity.task2restclient.domain.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TaskClientController taskController;

    @ExceptionHandler(HttpStatusCodeException.class)
    public String handleClientError(HttpStatusCodeException ex, Model model) throws ServiceUnavailableEx, IOException {
        if(ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
            MessageDto dto = mapper.readValue(ex.getResponseBodyAsByteArray(), MessageDto.class);
            model.addAttribute("bad_request_error", dto.getMessages());
            return taskController.findAll(model);
        }
        if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            MessageDto dto = mapper.readValue(ex.getResponseBodyAsByteArray(), MessageDto.class);
            model.addAttribute("not_found_error", dto.getMessages());
        }
        if(ex.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
            MessageDto dto = mapper.readValue(ex.getResponseBodyAsByteArray(), MessageDto.class);
            model.addAttribute("service_unavailable_error", dto.getMessages());
            return taskController.findAll(model);
        }

        return taskController.findAll(model);
    }
}
