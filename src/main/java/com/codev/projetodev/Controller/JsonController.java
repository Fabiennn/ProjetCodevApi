package com.codev.projetodev.Controller;


import com.codev.projetodev.Service.JsonService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/getApi")
public class JsonController {

    @Autowired
    private JsonService jsonService;



    @GetMapping(value = "/getMap", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<Integer, ArrayList<String>> getHashMapForMap() throws IOException, JSONException {

        return this.jsonService.getHashMapForMap();
    }


    @GetMapping(value = "/getHistorique/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHistorique(@PathVariable String id) {
        return new ResponseEntity<String>(this.jsonService.getHistorique(id).toString(), HttpStatus.OK);
    }


    @GetMapping(value = "/getHistoriqueByAnnee/{id}/{annee}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllHistorique(@PathVariable String id, @PathVariable(value = "annee") int annee) {
        return new ResponseEntity<String>(this.jsonService.getAllHistorique(id, annee).toString(), HttpStatus.OK);
    }


    @GetMapping(value = "/getClassement")
    public ArrayList<String> getClassement() throws JSONException, IOException {
        return this.jsonService.getClassement();

    }

    @GetMapping(value = "/getHistoriqueByCategorie/{id}/{categorie}")
    public ResponseEntity<String> getHistoriqueByCategorie(@PathVariable String id, @PathVariable(value = "categorie") String categorie) {
        return new ResponseEntity<String>(this.jsonService.getHistoriqueByCategorie(id, categorie).toString(), HttpStatus.OK);

    }




}
