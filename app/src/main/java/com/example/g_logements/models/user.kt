package com.example.g_logements.models

class user(
           var nom:String,
           var username:String,
           var password:String,
           var email:String,
           var img:Int,
            var isAdminn : Boolean
            ){
    override fun toString(): String {
        return "user( nom='$nom', username='$username', password='$password', img=$img, isAdminn=$isAdminn)"
    }

}