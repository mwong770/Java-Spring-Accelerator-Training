package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskerDaoJdbcTemplateImplTest {

    @Autowired
    TaskerDao taskerDao;

    // clear task table in database
    @Before
    public void setUp() throws Exception {
        List<Task> tasks = taskerDao.getAllTasks();
        for (Task t : tasks) {
            taskerDao.deleteTask(t.getId());
        }
    }

    // tests createTask(), getTask() and deleteTask()
    @Test
    @Transactional
    public void addGetDeleteTask() {

        Task task = new Task();
        task.setDescription("go to doctor for physical");
        task.setCreateDate(LocalDate.of(2019, 8, 01));
        task.setDueDate(LocalDate.of(2019, 8, 14));
        task.setCategory("wellness");

        task = taskerDao.createTask(task);

        Task task1 = taskerDao.getTask(task.getId());
        assertEquals(task, task1);

        taskerDao.deleteTask(task.getId());
        task1 = taskerDao.getTask(task.getId());
        assertNull(task1);
    }

    // tests updateTask()
    @Test
    @Transactional
    public void updateTask() {

        Task task = new Task();
        task.setDescription("make yaml");
        task.setCreateDate(LocalDate.of(2019, 8, 01));
        task.setDueDate(LocalDate.of(2019, 8, 07));
        task.setCategory("work");

        task = taskerDao.createTask(task);

        task.setDueDate(LocalDate.of(2019, 8, 14));
        task.setCategory("school");

        taskerDao.updateTask(task);

        Task task1 = taskerDao.getTask(task.getId());
        assertEquals(task, task1);
    }

    // tests getAllTasks()
    @Test
    public void getAllTasks() {

        Task task = new Task();
        task.setDescription("go to doctor for physical");
        task.setCreateDate(LocalDate.of(2019, 8, 01));
        task.setDueDate(LocalDate.of(2019, 8, 14));
        task.setCategory("wellness");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("make yaml");
        task.setCreateDate(LocalDate.of(2019, 8, 01));
        task.setDueDate(LocalDate.of(2019, 8, 07));
        task.setCategory("work");

        taskerDao.createTask(task);

        List<Task> tList = taskerDao.getAllTasks();
        assertEquals(2, tList.size());
    }

    // tests getTasksByCategory()
    @Test
    public void getTasksByCategory() {

        Task task = new Task();
        task.setDescription("go to doctor for physical");
        task.setCreateDate(LocalDate.of(2019, 8, 01));
        task.setDueDate(LocalDate.of(2019, 8, 14));
        task.setCategory("wellness");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("make yaml");
        task.setCreateDate(LocalDate.of(2019, 8, 01));
        task.setDueDate(LocalDate.of(2019, 8, 07));
        task.setCategory("work");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("buy better toothbrush");
        task.setCreateDate(LocalDate.of(2019, 8, 01));
        task.setDueDate(LocalDate.of(2019, 8, 07));
        task.setCategory("wellness");

        taskerDao.createTask(task);

        List<Task> tList = taskerDao.getTasksByCategory("wellness");
        assertEquals(2, tList.size());

        tList = taskerDao.getTasksByCategory("work");
        assertEquals(1, tList.size());

        tList = taskerDao.getTasksByCategory("school");
        assertEquals(0, tList.size());

    }

}
