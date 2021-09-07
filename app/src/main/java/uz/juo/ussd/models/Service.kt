package uz.juo.ussd.models

 class Service{
    var info1: String= ""
    var name: String= ""
    var offCode: String= ""
    var onCode: String= ""

     constructor()
     constructor(info1: String, name: String, offCode: String, onCode: String) {
         this.info1 = info1
         this.name = name
         this.offCode = offCode
         this.onCode = onCode
     }
 }