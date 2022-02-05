package com.codev.projetodev.Controller;


import com.codev.projetodev.Service.ReadJson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestJson {

    @Autowired
    private ReadJson readJson;



    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, ArrayList<String>> getHashMapForMap() throws IOException, JSONException {

        return this.readJson.getHashMapForMap();
    }


}
