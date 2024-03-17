package lolnews.skydev.peter.free.util;

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

class ServLang {

    var jsonServLan: JSONArray = JSONArray().put(JSONObject()
            .put("server", "North America")
            .put("use", "us")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "English")
                            .put("use", "en"))
            )).put(JSONObject()
            .put("server", "EU West")
            .put("use", "euw")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "English")
                            .put("use", "en"))
                    .put(JSONObject()
                            .put("name", "Español")
                            .put("use", "es"))
                    .put(JSONObject()
                            .put("name", "Italiano")
                            .put("use", "it"))
                    .put(JSONObject()
                            .put("name", "Deutsch")
                            .put("use", "de"))
                    .put(JSONObject()
                            .put("name", "Français")
                            .put("use", "fr"))
            )).put(JSONObject()
            .put("server", "EU Nordic & East")
            .put("use", "eune")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "English")
                            .put("use", "en"))
                    .put(JSONObject()
                            .put("name", "Ελληνικά")
                            .put("use", "el"))
                    .put(JSONObject()
                            .put("name", "Magyar")
                            .put("use", "hu"))
                    .put(JSONObject()
                            .put("name", "Polski")
                            .put("use", "pl"))
                    .put(JSONObject()
                            .put("name", "Română")
                            .put("use", "ro"))
                    .put(JSONObject()
                            .put("name", "Čeština")
                            .put("use", "cs"))
            )).put(JSONObject()
            .put("server", "Latin America North")
            .put("use", "lan")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "Español")
                            .put("use", "es"))
            )).put(JSONObject()
            .put("server", "Latin America South")
            .put("use", "las")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "Español")
                            .put("use", "es"))
            )).put(JSONObject()
            .put("server", "Brazil")
            .put("use", "br")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "Português")
                            .put("use", "pt"))
            )).put(JSONObject()
            .put("server", "Japan")
            .put("use", "jp")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "日本語")
                            .put("use", "ja"))
            )).put(JSONObject()
            .put("server", "Russia")
            .put("use", "ru")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "Русский")
                            .put("use", "ru"))
            )).put(JSONObject()
            .put("server", "Turkey")
            .put("use", "tr")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "Türkçe")
                            .put("use", "tr"))
            )).put(JSONObject()
            .put("server", "Oceania")
            .put("use", "oce")
            .put("languages", JSONArray()
                    .put(JSONObject()
                            .put("name", "English")
                            .put("use", "en"))
            ))
    val TAG = "ServLang"

    fun getServLang(): JSONArray {
        return this.jsonServLan
    }

    fun getServers(): ArrayList<String> {
        var servers = ArrayList<String>()

        for (i in 0..this.jsonServLan.length() - 1) {
            Log.d(TAG, "ASD: " + this.jsonServLan.get(i))
            val jsonOB: JSONObject = this.jsonServLan.get(i) as JSONObject
            servers.add(jsonOB.getString("server"))
        }

        return servers
    }

    fun loadLanguages(server: String): ArrayList<String> {
        var languageList = ArrayList<String>()
        for (i in 0..this.jsonServLan.length() - 1) {
            val jsonOb: JSONObject = this.jsonServLan.get(i) as JSONObject
            if (jsonOb.getString("server")!!.equals(server)) {
                val jsonLang = jsonOb.getJSONArray("languages")
                for (j in 0..jsonLang.length() - 1) {
                    val json = jsonLang.get(j) as JSONObject
                    languageList.add(json.getString("name"))
                }
            }
        }
        return languageList
    }

    fun getServerByString(server: String): String {
        var str = "na"

        for (i in 0..this.jsonServLan.length() - 1) {
            val jsonOb: JSONObject = this.jsonServLan.get(i) as JSONObject
            if (jsonOb.getString("server")!!.equals(server)) {
                str = jsonOb.getString("use")!!
            }
        }
        return str
    }

    fun getLangByString(server: String, language: String): String {
        var lan = "en"

        for (i in 0..this.jsonServLan.length() - 1) {
            val jsonOb: JSONObject = this.jsonServLan.get(i) as JSONObject
            if (jsonOb.getString("server")!!.equals(server)) {
                val jsonLang = jsonOb.getJSONArray("languages")
                for (j in 0..jsonLang.length() - 1) {
                    val jsonLang = jsonLang.get(j) as JSONObject
                    if (jsonLang.getString("name")!!.equals(language)) {
                        lan = jsonLang.getString("use")!!
                    }
                }
            }
        }
        return lan
    }
}
