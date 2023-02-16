package com.example.todo.Service;

import com.example.todo.ApiException.ApiException;
import com.example.todo.Repsitry.MyUserRepository;
import com.example.todo.Repsitry.TodoRepository;
import com.example.todo.model.MyUser;
import com.example.todo.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MyUserRepository myUserRepository;

    public List getAllTodo(){
        return todoRepository.findAll();
    }

    public Todo getTodoById(Integer id , Integer auth){
        Todo todo=todoRepository.findTodoById(id);
        if (todo==null){
            throw new ApiException("Todo not Found");
        }
        if (todo.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to get this Todo!");
        }
        return todo;
    }

    public List getMyTodos(Integer auth){
        MyUser myUser=myUserRepository.findMyUserById(auth);
        List todos=todoRepository.findAllByMyUser(myUser);
        if (todos.isEmpty()){
            throw new ApiException("Empty!");
        }
        return todos;
    }

    public void addTodo(Todo todo , Integer auth){
        MyUser myUser=myUserRepository.findMyUserById(auth);
        todo.setMyUser(myUser);

        todoRepository.save(todo);
    }

    public void updateTodo(Integer id , Todo newTodo , Integer auth){
        Todo oldTodo=todoRepository.findTodoById(id);
        MyUser myUser=myUserRepository.findMyUserById(auth);
        if (oldTodo==null){
            throw new ApiException("Todo not found");
        }else if(oldTodo.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to update this Todo!");
        }

        newTodo.setId(id);
        newTodo.setMyUser(myUser);

        todoRepository.save(newTodo);
    }

    public void deleteTodo(Integer id, Integer auth){
        Todo todo=todoRepository.findTodoById(id);
        if (todo==null){
            throw new ApiException("Todo not found");
        }else if(todo.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to delete this Todo!");
        }

        todoRepository.delete(todo);
    }
}
