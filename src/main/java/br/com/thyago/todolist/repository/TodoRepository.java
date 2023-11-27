package br.com.thyago.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thyago.todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

    
    
}
