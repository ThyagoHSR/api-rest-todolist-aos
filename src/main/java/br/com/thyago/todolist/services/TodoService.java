package br.com.thyago.todolist.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.thyago.todolist.entity.Todo;
import br.com.thyago.todolist.repository.TodoRepository;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo){
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list(){
        Sort.by("prioridade").descending().and(
            Sort.by("nome").ascending());
        return todoRepository.findAll();
    }

    public List<Todo> update(Long id, Todo todo) {
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> {
          todo.setId(id);
          todoRepository.save(todo);
        }, () -> {
          throw new BadRequestException("Todo %d não existe! ".formatted(id));
        });
    
        return list();
    
      }

      public List<Todo> delete(Long id) {
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> todoRepository.delete(existingTodo), () -> {
          throw new BadRequestException("Todo %d não existe! ".formatted(id));
        });
        return list();
      }
    
}
