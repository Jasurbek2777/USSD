package uz.juo.ussd.models

 class Ussd {
     var name: String= ""
     var code: String= ""

     constructor()
     constructor(name: String, code: String) {
         this.name = name
         this.code = code
     }
 }