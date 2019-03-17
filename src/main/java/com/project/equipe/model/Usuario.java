package com.project.equipe.model;

import com.project.equipe.enums.ECargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO")
@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
  @SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence", allocationSize = 1)
  private Integer id;

  @Column
  private String nome;

  @Column
  private ECargo cargo;

  @Column
  private Double salario;

  @Column
  private LocalDateTime dataCadastro;

  @Column
  private LocalDateTime dataBaixo;

  @ManyToOne
  private Equipe equipe;

}
