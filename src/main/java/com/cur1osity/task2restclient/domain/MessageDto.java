package com.cur1osity.task2restclient.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MessageDto {

    private List<String> messages;
}
