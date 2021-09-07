package uz.juo.ussd.models

 class Internetpaketlar{
    var activeCode: String = ""
    var day_count: String = ""
    var day_text: String = ""
    var gb: String = ""
    var mb: String = ""
    var mb_count: String = ""
    var mb_text: String = ""
    var name_price: String = ""
    var price: String = ""

    constructor(
       activeCode: String,
       day_count: String,
       day_text: String,
       gb: String,
       mb: String,
       mb_count: String,
       mb_text: String,
       name_price: String,
       price: String
    ) {
       this.activeCode = activeCode
       this.day_count = day_count
       this.day_text = day_text
       this.gb = gb
       this.mb = mb
       this.mb_count = mb_count
       this.mb_text = mb_text
       this.name_price = name_price
       this.price = price
    }

    constructor()
 }