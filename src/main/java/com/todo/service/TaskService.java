package com.todo.service;

import com.todo.entity.Status;
import com.todo.entity.Task;


import java.util.List;


public interface TaskService {
    List<Task> getAll(int offset, int limit);
    int getAllCount();
    Task update(int id, String description, Status status);
    Task create(String description, Status status);

    void delete(int id);

}
