package com.project.equipe.dto;

import com.project.equipe.model.Equipe;
import com.project.equipe.model.Gerente;
import com.project.equipe.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipeRequest {

  private String nome;
  private Gerente gerente;
  private List<Usuario> usuarios;

  public static Equipe converTo(EquipeRequest equipeRequest) {
    Equipe response =  new Equipe();
    BeanUtils.copyProperties(response, equipeRequest);
    return response;
  }

}
