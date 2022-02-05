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


    @GetMapping(value = "/test3/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllHistorique(@PathVariable String id) {
        return new ResponseEntity<String>(this.readJson.getAllHistorique(id).toString(), HttpStatus.OK);
    }




}
