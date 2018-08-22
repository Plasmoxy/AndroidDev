package io.github.plasmoxy.begin.notetoself

data class Note(
    var title : String = "",
    var description : String = "",
    var idea : Boolean = false,
    var todo : Boolean = false,
    var important : Boolean = false
)