package uz.juo.ussd.models

 class Tariflar {
     var code: String= ""
     var info: String= ""
     var info_min: String= ""
     var mont_mb: String= ""
     var month_min: String= ""
     var month_sms: String= ""
     var name: String= ""
     var sum_mont: String= ""

     constructor()
     constructor(
         code: String,
         info: String,
         info_min: String,
         mont_mb: String,
         month_min: String,
         month_sms: String,
         name: String,
         sum_mont: String
     ) {
         this.code = code
         this.info = info
         this.info_min = info_min
         this.mont_mb = mont_mb
         this.month_min = month_min
         this.month_sms = month_sms
         this.name = name
         this.sum_mont = sum_mont
     }
 }