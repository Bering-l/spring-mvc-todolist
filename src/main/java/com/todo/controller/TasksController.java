package com.todo.controller;

import com.todo.entity.Task;
import com.todo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;


@Controller
@RequestMapping("/")
public class TasksController {
    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String tasks(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<Task> tasks = taskService.getAll((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        model.addAttribute("current_page", page);
        int totalPages = (int) Math.ceil(1.0 * taskService.getAllCount() / limit);
        if (totalPages > 1) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("page_number", pages);
        }
        return "todo";
    }

    @PatchMapping("/{id}")
    public String update(Model model,
                         @PathVariable Integer id,
                         @RequestBody TaskInfo info) {
        if (isNull(id) || id > 0) {
            throw new RuntimeException("Invalid id");
        }
        Task task = taskService.update(id, info.getDescription(), info.getStatus());
        return tasks(model, 1, 10);
    }

    @PostMapping("/")
    public String save(Model model,
                       @PathVariable Integer id,
                       @RequestBody TaskInfo info) {
        Task task = taskService.create(id, info.getDescription(), info.getStatus());
        return tasks(model, 1, 10);
    }

    @DeleteMapping("/{id}")
    public String delete(Model model,
                         @PathVariable Integer id) {
        if (isNull(id) || id > 0) {
            throw new RuntimeException("Invalid id");
        }
        taskService.delete(id);
        return tasks(model, 1, 10);
    }
}

