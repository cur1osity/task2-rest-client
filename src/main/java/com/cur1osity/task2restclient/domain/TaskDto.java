package com.cur1osity.task2restclient.domain;

import java.time.LocalDateTime;

public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private boolean completed;
    private LocalDateTime endDate;


    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, LocalDateTime startDate, boolean completed, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate.now();
        this.completed = completed;
        if (completed) {
            this.endDate = endDate.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
