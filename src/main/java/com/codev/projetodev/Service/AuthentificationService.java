package com.codev.projetodev.Service;

import com.codev.projetodev.Domain.LoginParam;
import com.codev.projetodev.Domain.UtilisateurEntity;
import com.codev.projetodev.Repository.UtilisateurRepository;
import com.codev.projetodev.mesExceptions.MonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codev.projetodev.utilitaires.MonMotPassHash;

@Service
public class AuthentificationService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    public UtilisateurEntity authentification(LoginParam unUti) throws Exception {
        UtilisateurEntity unUtilisateur;
        String login = unUti.getNomUtil();
        String pwd = unUti.getMotPasse();
        unUtilisateur = utilisateurRepository.rechercheNom(login);
        if (unUtilisateur != null) {
            try {
                // on récupère le sel
                String sel = unUtilisateur.getSalt();
                // on récupère le mot de passe
                String mdp = unUtilisateur.getMdp();
                // on génère le mot de passe avec les données de connexion
                byte[] salt = MonMotPassHash.transformeEnBytes(sel);
                char[] pwd_char = MonMotPassHash.converttoCharArray(pwd);
                byte[] monpwdCo = MonMotPassHash.generatePasswordHash(pwd_char, salt);
                // on récupère le mot de passe enregistré
                byte[] mdp_byte = MonMotPassHash.transformeEnBytes(mdp);
                if (!MonMotPassHash.verifyPassword(monpwdCo, mdp_byte)) {
                    return null;
                }
            } catch (MonException e) {
                throw e;
            } catch (Exception e) {
                throw e;
            }
        }
        return unUtilisateur;
    }

    public UtilisateurEntity ajouter(UtilisateurEntity utilisateurEntity) {
        try {
            utilisateurEntity.setSalt(MonMotPassHash.bytesToString(MonMotPassHash.GenerateSalt()));
            byte[] salt = MonMotPassHash.transformeEnBytes(utilisateurEntity.getSalt());
            char[] pwd_char = MonMotPassHash.converttoCharArray(utilisateurEntity.getMdp());
            utilisateurEntity.setMdp(MonMotPassHash.bytesToString(MonMotPassHash.generatePasswordHash(pwd_char, salt)));
            utilisateurEntity.setRole("apprenant");

            return this.utilisateurRepository.save(utilisateurEntity);
        } catch (Exception e) {
            throw new MonException("Insert", "Sql", e.getMessage());
        }
    }
}