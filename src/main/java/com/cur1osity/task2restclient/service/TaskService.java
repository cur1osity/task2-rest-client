package com.cur1osity.task2restclient.service;

import com.cur1osity.task2restclient.domain.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Value("${resource.tasks}")
    private String resource;

    @Value("${resource.tasks}/{id}")
    private String idResource;

    @Autowired
    private RestTemplate restTemplate;

    private HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public List<TaskDto> findAll() throws ResourceAccessException, HttpClientErrorException {

        try {

             Arrays.stream(restTemplate.getForObject(resource, TaskDto[].class)).collect(Collectors.toList());

        } catch (ResourceAccessException ex) {

            return null;
        }

        return Arrays.stream(restTemplate.getForObject(resource, TaskDto[].class)).collect(Collectors.toList());
    }

    public TaskDto update(Long id, TaskDto task) {
        return restTemplate.exchange(idResource, HttpMethod.PATCH, new HttpEntity<>(task), TaskDto.class, id).getBody();
    }

    public void delete(Long id) {
        restTemplate.delete(idResource, id);
    }

    public TaskDto create(TaskDto task) {

        return restTemplate.postForObject(resource, task, TaskDto.class);
    }

    public void deleteAllTask(){
        restTemplate.delete(resource);
    }

    public TaskDto findTask(Long id) throws TaskNotFoundException {

        try {

            return restTemplate.getForObject(idResource, TaskDto.class, id);

        } catch (HttpClientErrorException e) {

            throw new TaskNotFoundException();
        }
    }

}
