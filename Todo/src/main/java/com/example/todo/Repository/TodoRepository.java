package com.example.todo.Repository;

import com.example.todo.Model.MyUser;
import com.example.todo.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findTodosByMyUser(MyUser myUser);

    Todo findTodoById(Integer id);

}