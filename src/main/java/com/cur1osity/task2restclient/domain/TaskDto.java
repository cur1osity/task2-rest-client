package com.cur1osity.task2restclient.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private String startDate;
    private String updatedDate;
    private boolean completed;
    private String endDate;
}
