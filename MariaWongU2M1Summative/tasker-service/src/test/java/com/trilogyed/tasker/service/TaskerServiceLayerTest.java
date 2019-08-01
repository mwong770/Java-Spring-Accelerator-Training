package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.dao.TaskerDaoJdbcTemplateImpl;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TaskerServiceLayerTest {

    TaskerServiceLayer taskerService;

    TaskerDao taskerDao;

    @Before
    public void setUp() throws Exception {

        // Configures mock objects
        setUpTaskerDaoMock();

        // Passes mock objects
        taskerService = new TaskerServiceLayer(taskerDao);

    }

    // tests newTask() and fetchTask()
    @Test
    public void saveFetchTask() {

        TaskViewModel taskVM = new TaskViewModel();

        taskVM.setDescription("go to doctor for physical");
        taskVM.setCreateDate(LocalDate.of(2019, 8, 01));
        taskVM.setDueDate(LocalDate.of(2019, 8, 14));
        taskVM.setCategory("wellness");

        taskVM = taskerService.newTask(taskVM);

        TaskViewModel fromService = taskerService.fetchTask(taskVM.getId());
        assertEquals(taskVM, fromService);

    }

    // tests fetchAllTasks()
    @Test
    public void fetchAllTasks() {
        //L(TVM) fetchAllTasks()
        TaskViewModel taskVM = new TaskViewModel();

        taskVM.setDescription("go to doctor for physical");
        taskVM.setCreateDate(LocalDate.of(2019, 8, 01));
        taskVM.setDueDate(LocalDate.of(2019, 8, 14));
        taskVM.setCategory("wellness");

        taskVM = taskerService.newTask(taskVM);

        taskVM = new TaskViewModel();

        taskVM.setDescription("make yaml");
        taskVM.setCreateDate(LocalDate.of(2019, 8, 01));
        taskVM.setDueDate(LocalDate.of(2019, 8, 07));
        taskVM.setCategory("work");

        taskVM = taskerService.newTask(taskVM);

        List<TaskViewModel> fromService = taskerService.fetchAllTasks();

        assertEquals(2, fromService.size());


    }

    // tests fetchTasksByCategory()
    @Test
    public void fetchTasksByCategory() {
        //L(TVM) fetchTasksByCategory(category)

        TaskViewModel taskVM = new TaskViewModel();

        taskVM.setDescription("go to doctor for physical");
        taskVM.setCreateDate(LocalDate.of(2019, 8, 01));
        taskVM.setDueDate(LocalDate.of(2019, 8, 14));
        taskVM.setCategory("wellness");

        taskVM = taskerService.newTask(taskVM);

        taskVM = new TaskViewModel();

        taskVM.setDescription("make yaml");
        taskVM.setCreateDate(LocalDate.of(2019, 8, 01));
        taskVM.setDueDate(LocalDate.of(2019, 8, 07));
        taskVM.setCategory("work");

        taskVM = taskerService.newTask(taskVM);

        taskVM = new TaskViewModel();

        taskVM.setDescription("buy better toothbrush");
        taskVM.setCreateDate(LocalDate.of(2019, 8, 01));
        taskVM.setDueDate(LocalDate.of(2019, 8, 07));
        taskVM.setCategory("wellness");

        taskVM = taskerService.newTask(taskVM);

        List<TaskViewModel> tList = taskerService.fetchTasksByCategory("wellness");
        assertEquals(2, tList.size());
        assertEquals("wellness", tList.get(0).getCategory());

        tList = taskerService.fetchTasksByCategory("work");
        assertEquals(1, tList.size());
        assertEquals("work", tList.get(0).getCategory());

        tList = taskerService.fetchTasksByCategory("school");
        assertEquals(0, tList.size());


    }

    // create mocks

    public void setUpTaskerDaoMock() {

        taskerDao =  mock(TaskerDaoJdbcTemplateImpl.class);

        Task task = new Task();
        task.setDescription("go to doctor for physical");
        task.setCreateDate(LocalDate.of(2019, 8, 01));
        task.setDueDate(LocalDate.of(2019, 8, 14));
        task.setCategory("wellness");

        Task task2 = new Task();
        task2.setId(1);
        task2.setDescription("go to doctor for physical");
        task2.setCreateDate(LocalDate.of(2019, 8, 01));
        task2.setDueDate(LocalDate.of(2019, 8, 14));
        task2.setCategory("wellness");

        Task task3 = new Task();
        task3.setDescription("make yaml");
        task3.setCreateDate(LocalDate.of(2019, 8, 01));
        task3.setDueDate(LocalDate.of(2019, 8, 07));
        task3.setCategory("work");

        Task task4 = new Task();
        task4.setId(2);
        task4.setDescription("make yaml");
        task4.setCreateDate(LocalDate.of(2019, 8, 01));
        task4.setDueDate(LocalDate.of(2019, 8, 07));
        task4.setCategory("work");

        Task task5 = new Task();
        task5.setDescription("buy better toothbrush");
        task5.setCreateDate(LocalDate.of(2019, 8, 01));
        task5.setDueDate(LocalDate.of(2019, 8, 07));
        task5.setCategory("wellness");

        Task task6 = new Task();
        task6.setId(3);
        task6.setDescription("buy better toothbrush");
        task6.setCreateDate(LocalDate.of(2019, 8, 01));
        task6.setDueDate(LocalDate.of(2019, 8, 07));
        task6.setCategory("wellness");

        List<Task> tasksList = new ArrayList<>();
        tasksList.add(task2);
        tasksList.add(task4);

        List<Task> wellnessList = new ArrayList<>();
        wellnessList.add(task2);
        wellnessList.add(task6);

        List<Task> workList = new ArrayList<>();
        workList.add(task4);

        List<Task> emptyList = new ArrayList<>();

        doReturn(task2).when(taskerDao).createTask(task);
        doReturn(task4).when(taskerDao).createTask(task3);
        doReturn(task6).when(taskerDao).createTask(task5);
        doReturn(task2).when(taskerDao).getTask(1);
        doReturn(tasksList).when(taskerDao).getAllTasks();
        doReturn(wellnessList).when(taskerDao).getTasksByCategory("wellness");
        doReturn(workList).when(taskerDao).getTasksByCategory("work");
        doReturn(emptyList).when(taskerDao).getTasksByCategory("school");

    }

}
