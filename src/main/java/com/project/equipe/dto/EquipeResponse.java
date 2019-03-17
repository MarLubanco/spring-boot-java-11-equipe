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
public class EquipeResponse {

  private Integer id;
  private String nome;
  private Gerente gerente;
  private List<Usuario> usuarios;

  public static EquipeResponse converTo(Equipe equipe) {
    EquipeResponse response =  new EquipeResponse();
    BeanUtils.copyProperties(response, equipe);
    return response;
  }
}
