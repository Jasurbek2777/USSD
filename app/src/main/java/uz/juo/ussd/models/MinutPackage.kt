package uz.juo.ussd.models

class MinutPackage {
    var code: String = ""
    var count_min: String = ""
    var day_month: String = ""
    var name: String = ""
    var onCode: String = ""
    var summ: String = ""

    constructor()
    constructor(
        code: String,
        count_min: String,
        day_month: String,
        name: String,
        onCode: String,
        summ: String
    ) {
        this.code = code
        this.count_min = count_min
        this.day_month = day_month
        this.name = name
        this.onCode = onCode
        this.summ = summ
    }
}