package uz.juo.ussd.models

data class Tariflar(
    var code: String,
    var info: String,
    var info_min: String,
    var mont_mb: String,
    var month_min: String,
    var month_sms: String,
    var name: String,
    var sum_mont: String,
)