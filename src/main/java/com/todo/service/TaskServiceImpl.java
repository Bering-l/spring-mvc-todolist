package com.todo.service;

import com.todo.dao.TaskDAO;
import com.todo.entity.Status;
import com.todo.entity.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDAO taskDAO;

    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }
    @Override
    public List<Task> getAll(int offset, int limit) {
        return taskDAO.getAllTasks(offset, limit);
    }
    @Override
    public int getAllCount() {
        return taskDAO.getAllCount();
    }
    @Override
    @Transactional
    public Task update(int id, String description, Status status) {
        Task task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Not found");
        }
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.createTask(task);
        return task;
    }
    @Override
    public Task create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.createTask(task);
        return task;
    }
    @Override
    @Transactional
    public void delete(int id) {
        Task task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Not found");
        }
        taskDAO.delete(task);
    }
}
