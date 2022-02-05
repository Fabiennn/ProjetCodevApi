package com.codev.projetodev.Service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class ReadJson {

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public HashMap<Integer, ArrayList<String>> getHashMapForMap() throws IOException, JSONException {
        HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();

        HashMap<String, String> hashMapPays = new HashMap<>();
        hashMapPays = this.initHashMap();
        for (Map.Entry<String, String> m : hashMapPays.entrySet()) {
            InputStream inputStream = new URL("https://api.waqi.info/feed/" + m.getKey() + "/?token=43f1f4ff275908d10000e224ddae40a4b86a6892").openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            // Remplissage avec valeur des 4 composantes de la qualité de l'air
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(m.getValue());
            if (json.getJSONObject("data").getJSONObject("iaqi").has("pm10")) {
                arrayList.add(json.getJSONObject("data").getJSONObject("iaqi").getJSONObject("pm10").getString("v"));
            } else if (json.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").has("pm10")) {
                arrayList.add(json.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").getJSONArray("pm10").getJSONObject(0).getString("avg"));
            } else {
                arrayList.add("Unknown");
            }
            if (json.getJSONObject("data").getJSONObject("iaqi").has("o3")) {
                arrayList.add(json.getJSONObject("data").getJSONObject("iaqi").getJSONObject("o3").getString("v"));
            } else if (json.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").has("o3")) {
                arrayList.add(json.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").getJSONArray("o3").getJSONObject(0).getString("avg"));
            } else {
                arrayList.add("Unknown");
            }

            if (json.getJSONObject("data").getJSONObject("iaqi").has("no2")) {
                arrayList.add(json.getJSONObject("data").getJSONObject("iaqi").getJSONObject("no2").getString("v"));
            } else if (json.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").has("no2")) {
                arrayList.add(json.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").getJSONArray("no2").getJSONObject(0).getString("avg"));
            } else {
                arrayList.add("Unknown");
            }

            if (json.getJSONObject("data").getJSONObject("iaqi").has("so2")) {
                arrayList.add(json.getJSONObject("data").getJSONObject("iaqi").getJSONObject("so2").getString("v"));
            } else if (json.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").has("so2")) {
                arrayList.add(json.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").getJSONArray("so2").getJSONObject(0).getString("avg"));
            } else {
                arrayList.add("Unknown");
            }

            arrayList.add(json.getJSONObject("data").getString("aqi"));
            arrayList.add(this.assignedColor(json.getJSONObject("data").getInt("aqi")));
            hashMap.put(hashMap.size(), arrayList);
            /*
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);

             */
        }
        return hashMap;
    }


    public HashMap<String, String> initHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("paris", "FR");
        hashMap.put("berlin", "DE");
        hashMap.put("bruxelles", "BE");
        hashMap.put("autriche", "AT");
        hashMap.put("zagreb", "HR");
        hashMap.put("rome", "IT");

        return hashMap;
    }

    public String assignedColor(int valeur) {
        if (valeur >= 0 && valeur <= 50) {
            return "3AE81F";
        } else if (valeur <= 100) {
            return "EBF016";
        } else if (valeur <= 150) {
            return "F48812";
        } else if (valeur <= 200) {
            return "EE2E16";
        } else if (valeur <= 300) {
            return "7D1598";
        } else return "90141F";


    }
}
