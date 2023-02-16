package com.example.todo;

import com.example.todo.Repsitry.MyUserRepository;
import com.example.todo.Repsitry.TodoRepository;
import com.example.todo.Service.TodoService;
import com.example.todo.model.MyUser;
import com.example.todo.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    TodoService TodoService;

    @Mock
    TodoRepository todoRepository;
    @Mock
    MyUserRepository myUserRepository;

    MyUser myUser;

    Todo todo1, todo2, todo3;

    List<Todo> todos;

    @BeforeEach
    void setUp() {
        myUser = new MyUser(null, "Maha", "12345", "ADMIN", null);
        todo1 = new Todo(null, "todo1", "body1", myUser);
        todo2 = new Todo(null, "todo2", "body2", myUser);
        todo3 = new Todo(null, "todo3", "body3", myUser);

        todos = new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);
    }
    @Test
    public void getAllTodo(){
        when(todoRepository.findAll()).thenReturn(todos);
        List<Todo> todoList = TodoService.getAllTodo();
        Assertions.assertEquals(3,todoList.size());
        verify(todoRepository,times(1)).findAll();
    }
    @Test
    public void getAllTodoById(){
        when(myUserRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        when(todoRepository.findAllByMyUser(myUser)).thenReturn(todos);
        List<Todo> todoList = TodoService.getMyTodos(myUser.getId());
        Assertions.assertEquals(todoList,todos);
        verify(myUserRepository,times(1)).findMyUserById(myUser.getId());
        verify(todoRepository,times(1)).findAllByMyUser(myUser);
    }

    @Test
    public void addTodo(){
        when(myUserRepository.findMyUserById(myUser.getId())).thenReturn(myUser);

        TodoService.addTodo(todo1,myUser.getId());
        verify(todoRepository,times(1)).save(todo1);
    }
    @Test
    public void updateTodo(){
        when(myUserRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);

        TodoService.updateTodo(todo1.getId(),todo2,myUser.getId());

        verify(myUserRepository,times(1)).findMyUserById(myUser.getId());
        verify(todoRepository,times(1)).findTodoById(todo1.getId());
        verify(todoRepository,times(1)).save(todo2);
    }

    @Test
    public void delete(){
        // user
        when(myUserRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        //TO do
        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);

        TodoService.deleteTodo(todo1.getId(),myUser.getId());

        verify(myUserRepository,times(1)).findMyUserById(myUser.getId());
        verify(todoRepository,times(1)).findTodoById(todo1.getId());
        verify(todoRepository,times(1)).delete(todo1);

    }
}
