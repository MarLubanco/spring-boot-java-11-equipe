package com.project.equipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EQUIPE")
@Entity
public class Equipe {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipe_sequence")
  @SequenceGenerator(name = "equipe_sequence", sequenceName = "equipe_sequence", allocationSize = 1)
  private Integer id;

  @Column
  private String nome;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Gerente gerente;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Usuario> usuarios;

  @Column
  private LocalDateTime dataCadastro;

  @Column
  private LocalDateTime dataBaixo;

}
