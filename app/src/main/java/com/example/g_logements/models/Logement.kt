package com.example.g_logements.models

class Logement(
    var type_log:String,
    var surface: Float,
    var prix : Float,
    var address: String,
    var nbre_piece:Int,
    var image:Int
) {
    override fun toString(): String {
        return "Logement(type_log='$type_log', surface=$surface, prix=$prix, address='$address', nbre_piece=$nbre_piece, image=$image)"
    }
}