package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TaskerDaoJdbcTemplateImpl implements TaskerDao {

    //??????????????????????????????????
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // prepared statements

    public static final String INSERT_TASK =
            "insert into task (task_description, create_date, due_date, category) values (?, ?, ?, ?)";
    public static final String SELECT_TASK_BY_ID =
            "select * from task where task_id = ?";
    public static final String SELECT_ALL_TASKS =
            "select * from task";
    public static final String SELECT_TASKS_BY_CATEGORY =
            "select * from task where category = ?";
    public static final String UPDATE_TASK =
            "update task set task_description = ?, create_date = ?, due_date = ?, category = ? where task_id = ?";
    public static final String DELETE_TASK =
            "delete from task where task_id = ?";

    // constructor

    public TaskerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // implement methods

    // adds a task to the database
    @Override
    @Transactional
    public Task createTask(Task task) {
        jdbcTemplate.update(
                INSERT_TASK,
                task.getDescription(),
                task.getCreateDate(),
                task.getDueDate(),
                task.getCategory()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        task.setId(id);

        return task;
    }

    // retrieves a task from the databased based on an id
    @Override
    public Task getTask(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TASK_BY_ID, this::mapRowToTask, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this task id, return null
            return null;
        }
    }

    // retrieves a list of all tasks
    @Override
    public List<Task> getAllTasks() {
        return jdbcTemplate.query(SELECT_ALL_TASKS, this::mapRowToTask);
    }

    // retrieves a list of tasks with a matching category
    @Override
    public List<Task> getTasksByCategory(String category) {
        return jdbcTemplate.query(SELECT_TASKS_BY_CATEGORY, this::mapRowToTask, category);
    }

    // updates a task with a matching id
    @Override
    @Transactional
    public void updateTask(Task task) {
        int largestId = jdbcTemplate.queryForObject("SELECT MAX(task_id) FROM task", Integer.class);

        if (task.getId() > largestId) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }

        jdbcTemplate.update(
                UPDATE_TASK,
                task.getDescription(),
                task.getCreateDate(),
                task.getDueDate(),
                task.getCategory(),
                task.getId()
        );
    }

    // deletes a task with a matching id
    @Override
    @Transactional
    public void deleteTask(int id) {
        int largestId = jdbcTemplate.queryForObject("SELECT MAX(task_id) FROM task", Integer.class);

        if (id > largestId) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }

        jdbcTemplate.update(DELETE_TASK, id);
    }

    // Row Mapper

    private Task mapRowToTask(ResultSet rs, int rowNum) throws SQLException {
        Task console = new Task();
        console.setId(rs.getInt("task_id"));
        console.setDescription(rs.getString("task_description"));
        console.setCreateDate(rs.getDate("create_date").toLocalDate());
        console.setDueDate(rs.getDate("due_date").toLocalDate());
        console.setCategory(rs.getString("category"));

        return console;
    }
}




