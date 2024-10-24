package com.thuydev.asmok.GUI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thuydev.asmok.Extention.colorFromHex
import com.thuydev.asmok.R


class ManHinhBegin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            ManHinhBegin1()
        }
    }
}
@Preview()
@Composable
fun ManHinhBegin1(){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        BackGround()

    }
}
@Composable
fun BackGround(){

    Column (modifier = Modifier
        .background(
            colorFromHex("#E6903B"),
            shape = RoundedCornerShape(
                0, 0,
                0, 100
            )
        )
        .fillMaxSize()
        .padding(10.dp)) {
        Title()
        Login()
        Logo(450f, R.drawable.logo123)
        ContentBottom()
    }
}
@Composable
fun ContentBottom() {
    val context = LocalContext.current
    Row (verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxHeight()){
        Text(text = "You dont have a Account", textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { context.startActivity(Intent(context, MainActivity::class.java)) })
    }
}
@Composable
fun Logo(size:Float,id:Int) {
    Image(
        painterResource(id = id) , contentDescription ="",
        modifier = Modifier
            .fillMaxWidth()
            .size(size.dp).padding(0.dp),
        contentScale = ContentScale.Crop
    )
}
@Composable
fun Login() {
    val context = LocalContext.current
  /*  Button(onClick = {  context.startActivity(Intent(context, ManHinhDangNhap::class.java)) }, colors = ButtonDefaults.buttonColors(
        colorFromHex("#E6903B"),
    ),shape = RoundedCornerShape(10.dp), border = BorderStroke(1.dp, Color.White),
        modifier = Modifier.size(200.dp,60.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 50.dp
        )
    ) {
        Text(text = "Login", fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }*/
}




@Composable
fun Title() {
    Column (modifier = Modifier.size(180.dp,170.dp)) {
        TextTilte(textL = "Wellcome",40f,Color.White,TextAlign.Center)
        TextTilte(textL = "To",40f,Color.White,TextAlign.Center)
        TextTilte(textL = "Yummy",40f,Color.White,TextAlign.Center)
    }
}
@Composable
fun TextTilte(textL:String,size: Float,color:Color,textAlign:TextAlign){
    Text(text = textL,
        fontSize = size.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        textAlign = textAlign,
        modifier = Modifier.fillMaxWidth()

    )
}
