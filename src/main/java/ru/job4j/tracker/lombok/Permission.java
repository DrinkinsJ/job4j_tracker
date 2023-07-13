package ru.job4j.tracker.lombok;

import lombok.Builder;

import java.util.List;

@Builder
public class Permission {
    private int id;
    private String name;
    private List<String> rules;
}

