package com.thuydev.asmok.GUI

sealed class Screens(val screen: String){
    data object Home: Screens("home")
    data object Cart: Screens("cart")
    data object Bill: Screens("bill")
    data object Login:Screens("Login")
    data object Sigin:Screens("Sigin")
    data object ViewDetailProduct:Screens("ViewDetailProduct")
    data object Main:Screens("Main")


}