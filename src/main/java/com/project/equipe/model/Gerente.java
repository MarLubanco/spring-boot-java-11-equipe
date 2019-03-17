package com.project.equipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GERENTE")
@Entity
public class Gerente {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerente_sequence")
  @SequenceGenerator(name = "gerente_sequence", sequenceName = "gerente_sequence", allocationSize = 1)
  private Integer id;

  @Column
  private String nome;

  @Column
  private LocalDateTime dataCadastro;

  @Column
  private LocalDateTime dataBaixo;

  @ManyToOne
  private Equipe equipe;


}
