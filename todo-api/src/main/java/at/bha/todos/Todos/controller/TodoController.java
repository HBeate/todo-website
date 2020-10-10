package at.bha.todos.Todos.controller;

import at.bha.todos.Todos.model.Todo;
import at.bha.todos.Todos.repositiory.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TodoController {

    @Autowired
    private TodoRepository repo;

    @GetMapping("/todos")
    List<Todo> getAllTodos() {
        List<Todo> todos = repo.findAll();
        return todos;
    }

    @PostMapping(value = "/todos", consumes = "application/json")
    public ResponseEntity insertTodo(@RequestBody Todo todo) {
        repo.save(todo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/todos/{id}", consumes = "application/json")
    public ResponseEntity updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        repo.updateTodo(id,todo.isDone());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/todos")
    void deleteAll() {
        repo.deleteAll();
    }

    @DeleteMapping("/todos/{id}")
    void deleteTodo(@PathVariable int id) {
        repo.deleteById(id);
    }
}
