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
        task.setDescription("model 1");
        task.setCreateDate(LocalDate.of(2018, 1, 28));
        task.setDueDate(LocalDate.of(2018, 1, 28));
        task.setCategory("best processor");

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
        task.setDescription("model 1");
        task.setCreateDate(LocalDate.of(2018, 1, 28));
        task.setDueDate(LocalDate.of(2018, 1, 28));
        task.setCategory("best processor");

        task = taskerDao.createTask(task);

        task.setDueDate(LocalDate.of(2019, 1, 28));
        task.setCategory("asd");

        taskerDao.updateTask(task);

        Task task1 = taskerDao.getTask(task.getId());
        assertEquals(task, task1);
    }

    // tests getAllTasks()
    @Test
    public void getAllTasks() {

        Task task = new Task();
        task.setDescription("model 1");
        task.setCreateDate(LocalDate.of(2018, 1, 28));
        task.setDueDate(LocalDate.of(2018, 1, 28));
        task.setCategory("best processor");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("model 1");
        task.setCreateDate(LocalDate.of(2018, 1, 28));
        task.setDueDate(LocalDate.of(2018, 1, 28));
        task.setCategory("best processor");

        taskerDao.createTask(task);

        List<Task> tList = taskerDao.getAllTasks();
        assertEquals(2, tList.size());
    }

    // tests getTasksByCategory()
    @Test
    public void getTasksByCategory() {

        Task task = new Task();
        task.setDescription("model 1");
        task.setCreateDate(LocalDate.of(2018, 1, 28));
        task.setDueDate(LocalDate.of(2018, 1, 28));
        task.setCategory("cat 1");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("model 1");
        task.setCreateDate(LocalDate.of(2018, 1, 28));
        task.setDueDate(LocalDate.of(2018, 1, 28));
        task.setCategory("cat 2");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("model 1");
        task.setCreateDate(LocalDate.of(2018, 1, 28));
        task.setDueDate(LocalDate.of(2018, 1, 28));
        task.setCategory("cat 1");

        taskerDao.createTask(task);

        List<Task> tList = taskerDao.getTasksByCategory("cat 1");
        assertEquals(2, tList.size());

        tList = taskerDao.getTasksByCategory("cat 2");
        assertEquals(1, tList.size());

        tList = taskerDao.getTasksByCategory("cat 3");
        assertEquals(0, tList.size());

    }


}
