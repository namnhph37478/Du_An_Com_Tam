package com.thuydev.asmok.GUI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thuydev.asmok.Extention.colorFromHex
import com.thuydev.asmok.R


class ManHinhChao : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManHinhChao1()
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000)
        }
    }
}
@Preview()
@Composable
fun ManHinhChao1(){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        BackGround1()

    }
}
@Composable
fun BackGround1(){

    Column (modifier = Modifier
        .background(
            colorFromHex("#E6903B"),
            shape = RoundedCornerShape(
                0, 100,
                0, 100
            )
        )
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Title1()
        Logo(450f, R.drawable.logo123)
        TextTilte(textL = "", size = 20f, color =Color.White, TextAlign.Center )
    }
}
@Composable
fun Title1() {
    Column (modifier = Modifier.size(400.dp,80.dp)) {
        TextTilte(textL = "Yummy",60f,Color.White,TextAlign.Center)
    }
}

