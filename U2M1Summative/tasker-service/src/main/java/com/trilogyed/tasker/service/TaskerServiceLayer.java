package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskerServiceLayer {

    TaskerDao dao;

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${adServiceName}")
    private String adServiceName;

    @Value("${serviceProtocol}")
    private String serviceProtocol;

    @Value("${servicePath}")
    private String servicePath;

    // controller

    @Autowired
    public TaskerServiceLayer(TaskerDao dao) {
        this.dao = dao;
    }

    public String getAd() {

        // retrieves an add from the ad server
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances(adServiceName);

            String adServiceUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath;

            String ad = restTemplate.getForObject(adServiceUri, String.class);

            return ad;
        }
        // returns null if an ad is not returned
        catch (NullPointerException e) {
            return null;
        }

    }

    public TaskViewModel fetchTask(int id) {

        Task task = dao.getTask(id);

        if (task == null)
            return null;
        else
            return buildTaskViewModel(task);
    }

    public List<TaskViewModel> fetchAllTasks() {

        List<Task> tasks = dao.getAllTasks();

        List<TaskViewModel> taskViewModels = new ArrayList<>();

        for (Task t : tasks) {
            taskViewModels.add(buildTaskViewModel(t));
        }
        return taskViewModels;
    }

    public List<TaskViewModel> fetchTasksByCategory(String category) {

        List<Task> tasks = dao.getTasksByCategory(category);

        List<TaskViewModel> taskViewModels = new ArrayList<>();

        for (Task t : tasks) {
            taskViewModels.add(buildTaskViewModel(t));
        }
        return taskViewModels;
    }

    public TaskViewModel newTask(TaskViewModel taskViewModel) {

        Task task = new Task();
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        task = dao.createTask(task);
        taskViewModel.setId(task.getId());

        // gets an ad and stores it in view model
        taskViewModel.setAdvertisement(getAd());

        return taskViewModel;
    }

    public void deleteTask(int id) {
        dao.deleteTask(id);
    }

    public void updateTask(TaskViewModel taskViewModel) {

        Task task = new Task();
        task.setId(taskViewModel.getId());
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        dao.updateTask(task);

    }

    // Build View Model

    private TaskViewModel buildTaskViewModel(Task task) {
        TaskViewModel taskViewModel = new TaskViewModel();
        taskViewModel.setId(task.getId());
        taskViewModel.setDescription(task.getDescription());
        taskViewModel.setCreateDate(task.getCreateDate());
        taskViewModel.setDueDate(task.getDueDate());
        taskViewModel.setCategory(task.getCategory());

        // gets an ad and stores it in view model
        taskViewModel.setAdvertisement(getAd());

        return taskViewModel;
    }

}
