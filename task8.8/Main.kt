fun main() {
    println(2.pow(3)) //task1
    var lam = {x:Int -> println(x)} //task2
    2.powl(0, lam)
    2.displayTypeInfo() //task3
    "a".displayTypeInfo()
    true.displayTypeInfo()
    DataType.UnitType().displayDataTypeInfo() //task4
    DataType.DoubleType(1.5).displayDataTypeInfo()
    DataType.UnitType().displayTypeInfo()
    DataType.DoubleType(1.5).displayTypeInfo()
}

fun Int.pow(n: Int): Int{  //task1
    var res = this
    for(i in 1 until n){
        res *= this
    }
    if (n == 0) {
        return 1
    }else {
        return res
    }
}

fun Int.powl(n: Int, lam: (Int) -> Unit){ //task2
    var res = this
    for(i in 1 until n){
        res *= this
    }
    if (n == 0) res = 1
    lam(res)
}

fun <T> T.displayTypeInfo(){ //task3
    if(this is Int){
        println("Это Int")
    }else if(this is String){
        println("Это String")
    }else if(this is DataType){
        this.displayDataTypeInfo()
    }else{
        println("Тип у $this неизвестен")
    }
}

fun DataType.displayDataTypeInfo(){ //task4
    when (this) {
        is DataType.DoubleType -> println("Это DoubleType со значением $value")
        is DataType.UnitType -> println("Это Unit")
    }
}
