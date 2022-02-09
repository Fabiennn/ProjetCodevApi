package com.codev.projetodev.Controller;

import com.codev.projetodev.Domain.LoginParam;
import com.codev.projetodev.Domain.UtilisateurEntity;
import com.codev.projetodev.Service.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/authentification")
public class AuthentificationController {

    @Autowired
    private AuthentificationService authentificationService;

    @PostMapping("/login")
    @ResponseBody
    public UtilisateurEntity controleLogin(@RequestBody LoginParam loginParam) throws Exception {

        return authentificationService.authentification(loginParam);
    }

    @PostMapping("/ajout")
    @ResponseBody
    public UtilisateurEntity ajouterLogin(@RequestBody UtilisateurEntity utilisateurEntity) throws Exception {


        return authentificationService.ajouter(utilisateurEntity);
    }
}
