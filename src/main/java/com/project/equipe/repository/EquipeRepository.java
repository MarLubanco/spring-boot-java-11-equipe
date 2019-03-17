package com.project.equipe.repository;

import com.project.equipe.model.Equipe;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EquipeRepository extends QuerydslPredicateExecutor<Equipe>, CrudRepository<Equipe, Integer> {
}
