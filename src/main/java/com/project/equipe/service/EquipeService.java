package com.project.equipe.service;

import com.project.equipe.dto.EquipeRequest;
import com.project.equipe.model.Equipe;
import com.project.equipe.model.Usuario;
import com.project.equipe.predicate.EquipePredicate;
import com.project.equipe.repository.EquipeRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipeService {

  @Autowired
  private EquipeRepository equipeRepository;
  private static final String EQUIPE_NAO_ENCONTRADA = "Equipe não encontrada";

  public Equipe getById(Integer id) throws NotFoundException {
    return equipeRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(EQUIPE_NAO_ENCONTRADA));
  }

  public Equipe save(EquipeRequest equipeRequest) {
    Equipe equipe = EquipeRequest.converTo(equipeRequest);
    equipe.setDataCadastro(LocalDateTime.now());
    equipe.getUsuarios().stream()
            .peek(usuario -> usuario.setDataCadastro(LocalDateTime.now()));
    equipe.getGerente().setDataCadastro(LocalDateTime.now());
    setSalarioByCargo(equipe.getUsuarios());
    return equipeRepository.save(equipe);
  }

  private void setSalarioByCargo(List<Usuario> usuarios) {
    usuarios.stream()
            .filter(usuario -> !ObjectUtils.isEmpty(usuario.getDataCadastro()))
            .peek(usuario -> usuario.getCargo().applySalario(2000));
  }

  public Equipe update(EquipeRequest equipeRequest) throws NotFoundException {
    Equipe equipeAtualizada = EquipeRequest.converTo(equipeRequest);
    Equipe equipe = equipeRepository.findById(equipeAtualizada.getId())
            .orElseThrow(() -> new NotFoundException(EQUIPE_NAO_ENCONTRADA));;
    equipe.setUsuarios(inativarUsuariosRemovidos(equipe, equipeAtualizada));
    return equipeRepository.save(equipe);
  }

  public void inativarEquipeUsuarios(Integer id) throws NotFoundException {
    Equipe equipe = equipeRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(EQUIPE_NAO_ENCONTRADA));
    if (ObjectUtils.isEmpty(equipe.getDataBaixo())) {
      equipe.setDataBaixo(LocalDateTime.now());
      equipe.getUsuarios().stream()
              .filter(usuario -> ObjectUtils.isEmpty(usuario))
              .peek(this::inativarUsuarios);
    }
  }

  private void inativarUsuarios(Usuario usuario) {
    if (ObjectUtils.isEmpty(usuario.getDataBaixo())) {
      usuario.setDataBaixo(LocalDateTime.now());
    }
  }

  private List<Usuario> inativarUsuariosRemovidos(Equipe equipe, Equipe equipeAtualizada) {
    return equipe.getUsuarios().stream()
            .filter(user -> !equipeAtualizada.getUsuarios().stream()
                    .anyMatch(usuarioAtualizado -> usuarioAtualizado.getNome().equals(user.getNome())))
            .peek(usuariosExcluidos -> usuariosExcluidos.setDataBaixo(LocalDateTime.now()))
            .collect(Collectors.toList());
  }

}
