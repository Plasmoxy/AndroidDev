package com.shardbytes.plasmoxy.juncc.loginlifecycle.model

// NOPE FUCK THAT, use SharedPreferences !
// simple class by map delegation so I can use exceptions to handle badly parsed Gson
class SettingsData(map: Map<String, String>) {
    
    private val REQUIRED_MEMBERS = arrayOf(
            "name", "passwordHash"
    )
    
    init {
        for(name in REQUIRED_MEMBERS) {
            if (!map.containsKey(name)) {
                throw Exception("Uncomplete settings map !")
                break
            }
        }
    }
    
    val name: String by map
    val passwordHash: String by map
}