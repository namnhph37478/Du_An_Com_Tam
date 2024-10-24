@file:OptIn(ExperimentalMaterial3Api::class)

package com.thuydev.asmok.GUI

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thuydev.asmok.Extention.colorFromHex
import com.thuydev.asmok.R
import com.thuydev.asmok.ViewModel.AccountViewModel

val ON_PASS = "ONPASS"
val OFF_PASS = "OFFPASS"

@Composable
fun ManHinhDangKy1(naviMainCtrl: NavHostController, account: AccountViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Logo(size = 450f, id = R.drawable.logo123)
        TextTilte(textL = "Sign In", size = 50f, color = colorFromHex(mauCam), TextAlign.Center)
        InputText2(naviMainCtrl,account)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputText2(naviMainCtrl: NavHostController, account: AccountViewModel) {
    val context =LocalContext.current;
    var name by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var repass by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var numberPhone by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(20.dp, 5.dp)) {
        InputText(input = name,"Email", place ="Please enter user name" ,{name=it},OFF_PASS)
        InputText(input = pass,"Password", place ="Please enter password" ,{pass=it},ON_PASS)
        InputText(input = repass,"Re-Password","Please re-enter password",{repass=it},ON_PASS)
        InputText(input = fullname,"Full Name","Please enter full name",{fullname=it},OFF_PASS)
        InputText(input = numberPhone,"Number Phone","Please enter number phone",{numberPhone=it},OFF_PASS)
       Row (horizontalArrangement = Arrangement.SpaceAround,
           modifier = Modifier.fillMaxWidth()) {

           Button(
               onClick = {
                        account.Reg(name,pass,repass,fullname,numberPhone,{
                            Toast.makeText(context,it,Toast.LENGTH_LONG).show()},
                            {naviMainCtrl.navigate(Screens.Login.screen)}
                        )
               },
               modifier = Modifier
                   .fillMaxWidth()
                   .height(60.dp)
                   .padding(0.dp, 10.dp, 0.dp, 0.dp),
               shape = RoundedCornerShape(10.dp),
               colors = ButtonDefaults.buttonColors(
                   containerColor = Color.White
               ),
               border = BorderStroke(width = 1.dp, color = colorFromHex(mauCam))

           ) {
               Text(text = "Sign up", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = colorFromHex(
                   mauCam))
           }
       }

    }
}

private fun SignUpSystem() {

}
@Composable
private fun InputText(input:String,lab:String,place:String,onChange:(String)->Unit,style:String){
    OutlinedTextField(value = input,
        onValueChange = { onChange(it) },
        label = { Text(text = lab, fontWeight = FontWeight.Bold) },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White, focusedLabelColor = colorFromHex(mauCam),
            unfocusedLabelColor = colorFromHex(mauCam),
            focusedBorderColor = colorFromHex(mauCam),
            unfocusedBorderColor = colorFromHex(mauCam),
            unfocusedTextColor = colorFromHex(mauCam),
            focusedTextColor = colorFromHex(mauCam)
        ), singleLine = true,
        visualTransformation = if(style.equals(ON_PASS))PasswordVisualTransformation() else VisualTransformation.None ,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        placeholder = {
            Text(
                text = place,
                color = colorFromHex(mauCam)
            )
        })
}




