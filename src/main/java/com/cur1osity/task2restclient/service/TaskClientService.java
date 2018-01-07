package com.cur1osity.task2restclient.service;

import com.cur1osity.task2restclient.controller.ServiceUnavailableEx;
import com.cur1osity.task2restclient.domain.TaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskClientService.class);

    @Value("${resource.tasks}")
    private String resource;

    @Value("${resource.tasks}/{id}")
    private String idResource;

    @Autowired
    private RestTemplate restTemplate;


    public List<TaskDto> findAll() throws ResourceAccessException, HttpClientErrorException, ServiceUnavailableEx {

            try {
                Arrays.stream(restTemplate.getForObject(resource, TaskDto[].class)).collect(Collectors.toList());
            } catch (ResourceAccessException ex) {
                LOGGER.error(ex.getMessage(), ex);
                throw new ServiceUnavailableEx();
            }

            return Arrays.stream(restTemplate.getForObject(resource, TaskDto[].class)).collect(Collectors.toList());
        }



    public List<TaskDto> findAllifServiceUnavailable() throws ResourceAccessException, HttpClientErrorException {

        try {
            Arrays.stream(restTemplate.getForObject(resource, TaskDto[].class)).collect(Collectors.toList());
        } catch (ResourceAccessException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return new ArrayList<>();
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

    public TaskDto findTask(Long id) throws ResourceAccessException, ServiceUnavailableEx {

        try {
            restTemplate.getForObject(idResource, TaskDto.class, id);
        } catch (ResourceAccessException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new ServiceUnavailableEx();
        }
        return restTemplate.getForObject(idResource, TaskDto.class, id);
    }

    public TaskDto findTaskifServiceUnavailable(Long id) throws ResourceAccessException {

        try {
            restTemplate.getForObject(idResource, TaskDto.class, id);
        } catch (ResourceAccessException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return new TaskDto();
        }
        return restTemplate.getForObject(idResource, TaskDto.class, id);
    }
}
