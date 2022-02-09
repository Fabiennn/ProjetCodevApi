package com.codev.projetodev.Service;

import com.codev.projetodev.Translate.SwitchLang;
import com.sun.java.accessibility.util.Translator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
public class JsonService {

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
        hashMap.put("madrid", "ES");
        hashMap.put("autriche", "AT");
        hashMap.put("londres", "GB");
        hashMap.put("zagreb", "HR");
        hashMap.put("rome", "IT");
        hashMap.put("copenhague", "DK");
        hashMap.put("athenes", "GR");
        hashMap.put("dublin", "IE");
        hashMap.put("reykjavik", "IS");
        hashMap.put("luxembourg", "LU");
        hashMap.put("oslo", "NO");
        hashMap.put("lisbonne", "PT");
        hashMap.put("prague", "CZ");
        hashMap.put("bucarest", "RO");
        hashMap.put("stockholm", "SE");
        hashMap.put("kiev", "UA");
        hashMap.put("kaboul", "AF");
        hashMap.put("alger", "DZ");
        hashMap.put("ottawa", "CA");
        hashMap.put("santiago", "CL");
        hashMap.put("washington", "US");
        hashMap.put("canberra", "AU");
        hashMap.put("brasilia", "BR");
        hashMap.put("pekin", "CN");
        hashMap.put("tallinn", "EE");
        hashMap.put("tbilissi", "GE");
        hashMap.put("budapest", "HU");
        hashMap.put("tokyo", "JP");
        hashMap.put("vilnius", "LT");
        hashMap.put("wellington", "NZ");
        hashMap.put("amsterdam", "NL");
        hashMap.put("panama", "PA");
        hashMap.put("varsovie", "PL");
        hashMap.put("moscou", "RU");
        hashMap.put("belgrade", "RS");
        hashMap.put("bratislava", "SK");
        hashMap.put("ljubljana", "SK");
        hashMap.put("berne", "SE");

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


    public JSONArray getHistorique(String pays) {
        String url = "https://www.climatewatchdata.org/api/v1/data/historical_emissions?regions=" + pays;
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json.getJSONArray("data").getJSONObject(15).getJSONArray("emissions");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public JSONArray getAllHistorique(String pays, int annee) {

        String url = "https://www.climatewatchdata.org/api/v1/data/historical_emissions?regions=" + pays;
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            JSONArray jsonArray = new JSONArray();
            for (int i = 16; i < 25; i++) {
                JSONArray jsonArray1 = new JSONArray();
                String sector = json.getJSONArray("data").getJSONObject(i).getString("sector");
                sector = sector.replaceAll("\\s+", "_");
                sector = sector.replaceAll("\\p{Pd}", "_");
                sector = sector.replaceAll("/", "_");
                String secteur = SwitchLang.valueOf(sector).getTranslate();
                jsonArray1.put(secteur);
                jsonArray1.put(json.getJSONArray("data").getJSONObject(i).getJSONArray("emissions").getJSONObject(annee - 1990));
                jsonArray.put(jsonArray1);
            }
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getHistoriqueByCategorie(String pays, String categorie) {
        String url = "https://www.climatewatchdata.org/api/v1/data/historical_emissions?regions=" + pays;
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            JSONArray jsonArray = new JSONArray();
            for (int i = 15; i < 25; i++) {
                String sector = json.getJSONArray("data").getJSONObject(i).getString("sector");
                sector = sector.replaceAll("\\s+", "_");
                sector = sector.replaceAll("\\p{Pd}", "_");
                sector = sector.replaceAll("/", "_");
                categorie = categorie.replaceAll("'", " ");
                String secteur = SwitchLang.valueOf(sector).getTranslate();
                if (secteur.equals(categorie)) {
                    return json.getJSONArray("data").getJSONObject(i).getJSONArray("emissions");
                }
            }
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<String> getClassement() throws IOException, JSONException {


        HashMap<String, Double> hashMap = new HashMap<>();

        HashMap<String, String> hashMapPays = new HashMap<>();
        hashMapPays = this.initHashMap();
        for (Map.Entry<String, String> m : hashMapPays.entrySet()) {
            InputStream inputStream = new URL("https://api.waqi.info/feed/" + m.getKey() + "/?token=43f1f4ff275908d10000e224ddae40a4b86a6892").openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            // Remplissage avec valeur des 4 composantes de la qualité de l'air
            hashMap.put(m.getValue(), json.getJSONObject("data").getDouble("aqi"));
        }


        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        Locale local = new Locale("", list.get(0).getKey());
        arrayList.add(local.getDisplayCountry());
        arrayList.add(list.get(0).getValue().toString());
        local = new Locale("", list.get(1).getKey());
        arrayList.add(local.getDisplayCountry());
        arrayList.add(list.get(1).getValue().toString());
        local = new Locale("", list.get(3).getKey());
        arrayList.add(local.getDisplayCountry());
        arrayList.add(list.get(2).getValue().toString());
        local = new Locale("", list.get(list.size()-1).getKey());
        arrayList.add(local.getDisplayCountry());
        arrayList.add(list.get(list.size()-1).getValue().toString());
        local = new Locale("", list.get(list.size()-2).getKey());
        arrayList.add(local.getDisplayCountry());
        arrayList.add(list.get(list.size()-2).getValue().toString());
        local = new Locale("", list.get(list.size()-3).getKey());
        arrayList.add(local.getDisplayCountry());
        arrayList.add(list.get(list.size()-3).getValue().toString());

        return arrayList;
    }

    public List<String> getPays() {
        List<String> list = new ArrayList<>();
        HashMap<String, String> hashMapPays = new HashMap<>();
        hashMapPays = this.initHashMap();
        for (Map.Entry<String, String> m : hashMapPays.entrySet()) {
            list.add(m.getValue());
        }
        return list;
    }
}
