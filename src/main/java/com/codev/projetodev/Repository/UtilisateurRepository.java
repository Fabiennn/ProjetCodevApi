package com.codev.projetodev.Repository;

import com.codev.projetodev.Domain.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {

    @Query("select ut from UtilisateurEntity  ut where ut.forname = ?1")
    public UtilisateurEntity rechercheNom(String login);

    public UtilisateurEntity findById(Long id);

    public UtilisateurEntity findBySurname(String surname);
}
