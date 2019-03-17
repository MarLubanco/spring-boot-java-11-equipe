package com.project.equipe.controller;

import com.project.equipe.dto.EquipeRequest;
import com.project.equipe.model.Equipe;
import com.project.equipe.service.EquipeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/equipes")
public class EquipeController {

  @Autowired
  private EquipeService service;

  @GetMapping("{id}")
  public Equipe getById(@PathVariable Integer id) throws NotFoundException {
      return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Equipe save(@RequestBody @Valid EquipeRequest equipe) {
    return service.save(equipe);
  }

  @PutMapping("{id}")
  public void update(@RequestBody EquipeRequest equipe) throws NotFoundException {
    service.update(equipe);
  }

  @PutMapping("inativar/equipe/{id}")
  public void inativarEquipe(@PathVariable Integer id) throws NotFoundException {
    service.inativarEquipeUsuarios(id);
  }

}
