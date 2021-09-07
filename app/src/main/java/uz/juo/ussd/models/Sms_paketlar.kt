package uz.juo.ussd.models

 class SmsPaketlar {
     var activeCode: String = ""
     var count_sms: String = ""
     var day_sms: String = ""
     var name: String = ""
     var offCode: String = ""
     var price: String = ""
     var summ_user: String = ""

     constructor()
     constructor(
         activeCode: String,
         count_sms: String,
         day_sms: String,
         name: String,
         offCode: String,
         price: String,
         summ_user: String
     ) {
         this.activeCode = activeCode
         this.count_sms = count_sms
         this.day_sms = day_sms
         this.name = name
         this.offCode = offCode
         this.price = price
         this.summ_user = summ_user
     }
 }