package com.thuydev.asmok.GUI

import com.thuydev.asmok.Interface.API
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response

data class Product(
    var _id:String,
    var NameProduct:String,
    var IDCategory:String,
    var Image:String,
    var Price:Number
){
    companion object {

    }
}
data class ProductDetail(
    var IDProduct:String,
    var Size:List<String>,
    var Date:String,
    var Amount:Number
)

data class Account(
    var _id: String?=null,
    var Email :String="",
    var Password:String="",
    var FullName:String="",
    var NumberPhone:String="",
    var Status:Number=0,
    var Credit:Number=0,
    var Level:Number=0,
    var token:String=""
)
data class Bill(
    var _id: String,
    var IDUser:String,
    var IDSeller:String,
    var IDProduct:MutableList<Map<String,Any>>
){
    companion object{
        fun getData() = listOf<Bill>()
    }
}
data class BillDetail(
    var _id: String?=null,
    var IDUser:String,
    var IDBill:String,
    var Status:Number,
    var Date:String,
    var Amount:Number,
    var Total:Number
)

data class Cart(
    var _id: String?=null,
    var IDUser: String="",
    var IDProduct: String="",
    var Size: String="",
    var Amount: Number=0
)
data class Category(
    val _id: String,
    val NameCategory: String
){
    companion object{

    }
}

