package com.touchIon.backend.controller;

import com.touchIon.backend.model.Task;
import com.touchIon.backend.model.User;
import com.touchIon.backend.service.TaskService;
import com.touchIon.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final Logger logger = Logger.getLogger(TaskController.class.getName());

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        Iterable<Task> tasks = taskService.findAll();
        List<Task> taskList = StreamSupport.stream(tasks.spliterator(), false).toList();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID id) {
        Optional<Task> taskOptional = taskService.findById(id);
        return taskOptional.map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestParam("email") String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        task.setUser(user);
        Task createdTask = taskService.save(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody Task taskDetails) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Updating task with id: " + id);
        logger.info("Task details: " + taskDetails);
        logger.info("optionalTask: " + optionalTask);
        Task existingTask = optionalTask.get();
        existingTask.setName(taskDetails.getName());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setStatus(taskDetails.getStatus());
        existingTask.setDeadline(taskDetails.getDeadline());
        existingTask.setTeam(taskDetails.getTeam());
        existingTask.setUser(taskDetails.getUser());
        Task updatedTask = taskService.save(existingTask);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
