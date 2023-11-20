package com.todo.dao;

import com.todo.entity.Task;

import java.util.List;


public interface TaskDAO {
    List<Task> getAllTasks(int offset, int limit);
    Task getById(int id);
    int getAllCount();
    void createTask(Task task);
    void delete(Task task);

}
