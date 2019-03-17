package com.project.equipe.predicate;

import com.project.equipe.model.Equipe;
import com.project.equipe.model.Usuario;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.equipe.model.QEquipe.equipe;
import static com.project.equipe.model.QUsuario.usuario;

public class EquipePredicate {

  private EntityManager entityManager;
  JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

  public List<List<Usuario>> getUsuariosById(Integer id) {
    return queryFactory.select(equipe.usuarios)
            .from(equipe)
            .where(equipe.usuarios.any().id.in(
                    JPAExpressions.select(usuario.id)
                            .from(usuario)
                            .where(usuario.id.eq(id))
            ))
            .fetch();
  }

  public List<Equipe> getEquipesAtivas() {
    return queryFactory.select(equipe)
            .from(equipe)
            .where(equipe.dataBaixo.isNull())
            .fetch();
  }
}
