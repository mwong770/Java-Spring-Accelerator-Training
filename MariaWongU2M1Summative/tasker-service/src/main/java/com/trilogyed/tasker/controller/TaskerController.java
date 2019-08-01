package com.trilogyed.tasker.controller;

import com.trilogyed.tasker.exception.NotFoundException;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class TaskerController {

    @Autowired
    TaskerServiceLayer taskService;

    public TaskerController(TaskerServiceLayer taskService) {
        this.taskService = taskService;
    }

    // creates a new task
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskViewModel createTask(@RequestBody @Valid TaskViewModel task) {
        return taskService.newTask(task);
    }

    // handles requests to update a task with a matching id
    @RequestMapping(value = "/tasks", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@RequestBody @Valid TaskViewModel taskViewModel) {
        taskService.updateTask(taskViewModel);
    }

    // handles requests to retrieve a task by id
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TaskViewModel getTask(@PathVariable("id") int taskId) {
        TaskViewModel task = taskService.fetchTask(taskId);
        if (task == null)
            throw new NotFoundException("Item could not be retrieved for id " + taskId);
        return task;
    }

    // handles requests to retrieve a list of all tasks
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel> getAllTasks() {
        return taskService.fetchAllTasks();
    }

    // handles requests to retrieve a list of tasks with a matching category
    @RequestMapping(value = "/tasks/category/{category}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel> findTasksByCategory(@PathVariable String category) {
        return taskService.fetchTasksByCategory(category);
    }

    // handles requests to delete a task by id
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") int taskId) {
        taskService.deleteTask(taskId);
    }

}