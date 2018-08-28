package io.github.plasmoxy.begin.notetoself

import com.fasterxml.jackson.annotation.JsonProperty

// ! USE JsonProperty annotation instead of autodetect as field reflection is extremely slow on android !
// on Jackson parse, it will try to create an Note object by using its constructors ( Kotlin data class will allow vararg constructor )
// if a field in data class has a default value, it DOESN'T have to be in json and will be initialized by its default value
// however if it is part of the constructor, it will throw JsonMappingException if the field isn't in json as the construction is impossible

data class Note(
        @JsonProperty("title") var title : String,
        @JsonProperty("description") var description: String,
        @JsonProperty("idea") var idea : Boolean = false,
        @JsonProperty("todo") var todo : Boolean = false,
        @JsonProperty("important") var important : Boolean = false
)