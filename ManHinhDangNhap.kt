package com.thuydev.asmok.GUI

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.thuydev.asmok.Extention.colorFromHex
import com.thuydev.asmok.R
import com.thuydev.asmok.ViewModel.AccountViewModel

val mauCam: String = "#E6903B"



@Composable
fun ManHinhDangNhap1(navMain: NavController, user: State<Account>, account: AccountViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Logo(size = 450f, id = R.drawable.logo123)
        TextTilte(textL = "Login", size = 50f, color = colorFromHex(mauCam), TextAlign.Center)
        InputText(navMain,user,account)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputText(navMain: NavController, user: State<Account>, account: AccountViewModel) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(20.dp, 5.dp)) {
        OutlinedTextField(value = name,
            onValueChange = { name = it },
            label = { Text(text = "User name", fontWeight = FontWeight.Bold) },
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
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(
                    text = "Please enter user name",
                    color = colorFromHex(mauCam)
                )
            })
        OutlinedTextField(value = pass,
            onValueChange = { pass = it },
            label = { Text(text = "Password", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White, focusedLabelColor = colorFromHex(mauCam),
                unfocusedLabelColor = colorFromHex(mauCam),
                focusedBorderColor = colorFromHex(mauCam),
                unfocusedBorderColor = colorFromHex(mauCam),
                unfocusedTextColor = colorFromHex(mauCam),
                focusedTextColor = colorFromHex(mauCam)
            ),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(
                    text = "Please enter password",
                    color = colorFromHex(mauCam)
                )
            })
       Row (horizontalArrangement = Arrangement.SpaceAround,
           modifier = Modifier.fillMaxWidth()) {
           Button(
               onClick = {
                        account.Login(name,pass,{
                            if (!user.value.FullName.isEmpty()){
                            navMain.navigate(Screens.Main.screen)
                            }else{
                            Toast.makeText(context,"Login fail , try again please !!",Toast.LENGTH_LONG).show()
                        }},{Toast.makeText(context,it,Toast.LENGTH_LONG).show()})

                         },
               modifier = Modifier
                   .size(160.dp, 60.dp)
                   .height(60.dp)
                   .padding(0.dp, 10.dp, 0.dp, 0.dp),
               shape = RoundedCornerShape(10.dp),
               colors = ButtonDefaults.buttonColors(
                   containerColor = Color.White
               ),
                border = BorderStroke(width = 1.dp, color = colorFromHex(mauCam))

           ) {
               Text(text = "Login",
                   fontWeight = FontWeight.Bold,
                   fontSize = 20.sp,
                   color = colorFromHex(mauCam))
           }
           Button(
               onClick = {
                navMain.navigate(Screens.Sigin.screen)
               },
               modifier = Modifier
                   .size(160.dp, 60.dp)
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
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            , horizontalArrangement = Arrangement.End){
            Text(text = "You forgot password",
            textDecoration = TextDecoration.Underline,
                color = colorFromHex(mauCam),
                modifier = Modifier.clickable { Toast.makeText(context,"Chức năng chưa hoàn thiện :)))",Toast.LENGTH_LONG).show() }
            )        }
    }

}
