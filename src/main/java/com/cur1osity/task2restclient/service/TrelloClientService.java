package com.cur1osity.task2restclient.service;


import com.cur1osity.task2restclient.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloClientService {

    @Value("${resource.trelloBoards}")
    private String resource;

//    @Value("${resource.tasks}/{id}")
//    private String idResource;

    @Autowired
    private RestTemplate restTemplate;


    public List<TrelloBoardDto> findAll()  {

            restTemplate.getForObject(resource, TrelloBoardDto[].class);

        return Arrays.stream(restTemplate.getForObject(resource, TrelloBoardDto[].class)).collect(Collectors.toList());
    }
}
