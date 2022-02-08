package com.codev.projetodev.Controller;


import com.codev.projetodev.Service.ReadJson;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestJson {

    @Autowired
    private ReadJson readJson;



    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<Integer, ArrayList<String>> getHashMapForMap() throws IOException, JSONException {

        return this.readJson.getHashMapForMap();
    }


    @GetMapping(value = "/test2/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHistorique(@PathVariable String id) {
        return new ResponseEntity<String>(this.readJson.getHistorique(id).toString(), HttpStatus.OK);
    }


    @GetMapping(value = "/test3/{id}/{annee}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllHistorique(@PathVariable String id, @PathVariable(value = "annee") int annee) {
        return new ResponseEntity<String>(this.readJson.getAllHistorique(id, annee).toString(), HttpStatus.OK);
    }


    @GetMapping(value = "/getClassement")
    public ArrayList<String> getClassement() throws JSONException, IOException {
        return this.readJson.getClassement();

    }




}
