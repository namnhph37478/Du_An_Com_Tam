package com.thuydev.asmok.GUI

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.thuydev.asmok.Extention.colorFromHex
import com.thuydev.asmok.ViewModel.CartViewModel
import com.thuydev.asmok.ViewModel.ProductDetailViewModel


@Composable
fun ManHinhSanPhamChiTiet1(
    naviMainCtrl: NavHostController,
    tempPro: Product,
    productDetailView: ProductDetailViewModel,
    user: State<Account>,
    cartView: CartViewModel
) {
    val context = LocalContext.current
    var productDetail = productDetailView.GetProductDetail(tempPro._id)
    var idUser=user.value._id

    var soLuong by rememberSaveable {
        mutableStateOf(0)
    }
    var kichCo:String by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
    ) {
        ImageProduct(tempPro.Image)
        TextTilte(textL = tempPro.NameProduct, size = 40f, color = Color.Black, TextAlign.Start)
        Text(
            text = "Giá: ${tempPro.Price} đ",
            color = Color.Red,
            fontSize = 25.sp
        )
        Text(
            text = "Ngày phát hành: ${productDetail.Date}",
            fontSize = 20.sp, color = Color(0xB0FF0000)
        )
        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color(0xFF000000))
        )
        ListSize(productDetail.Size,{kichCo=it})
        SoLuong({soLuong = it})
     Column (modifier = Modifier.fillMaxHeight(),
         verticalArrangement = Arrangement.Bottom){
         Button(onClick = {
            cartView.AddCart(idUser!!,tempPro._id,soLuong,kichCo,{Toast.makeText(context,it,Toast.LENGTH_LONG).show()})
         },
             modifier = Modifier
                 .fillMaxWidth()
                 .height(50.dp),
             shape = RoundedCornerShape(0.dp),
             colors = ButtonDefaults.buttonColors(containerColor = colorFromHex(mauCam)
             )
         ) {
             Text(text = "Thêm vào giỏ hàng",
                 fontWeight = FontWeight.Bold,
                 fontSize = 20.sp,
             )
         }
     }
    }
}

@Composable
fun SoLuong(soluong:(Int)->Unit) {
var i by rememberSaveable {
    mutableStateOf(0)
}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "Số lượng: ")

        ButtonSize(size = 30, soluong = {
            if(i>0)i--
            soluong(i)
        }, image =Icons.Filled.KeyboardArrowLeft )
       Text(text = "$i",
           fontWeight = FontWeight.Bold,
           color = colorFromHex(mauCam),
           fontSize = 20.sp,
           modifier = Modifier.size(80.dp,25.dp),
           textAlign = TextAlign.Center

       )
        ButtonSize(size = 30, soluong = {
            if(i<30)i++
            soluong(i)
        }, image =Icons.Filled.KeyboardArrowRight )

    }
}
@Composable
fun ButtonSize(size:Int,soluong:() -> Unit,image:ImageVector){
    Box (
        modifier =
        Modifier
            .size(size.dp)
            .background(colorFromHex(mauCam))
            .clickable {
                soluong()
            },
        contentAlignment = Alignment.Center) {
        Image(imageVector = image, contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White))
    }
}
@Composable
fun ListSize(listSize:List<String>,kichCo:(String)->Unit) {
   var choice by rememberSaveable {
       mutableStateOf("")
   }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Text(
            text = "Size : ",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        // làm list ở đây
        LazyRow() {
            items(listSize){item->
                Box(
                    Modifier
                        .size(50.dp)
                        .padding(2.dp)
                        .background(
                            color = if (choice.equals(item)) Color.Red else colorFromHex(mauCam),
                            RoundedCornerShape(10.dp),
                        )
                        .clickable {
                            kichCo(item)
                            choice = item
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = item,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold)
                }

            }
        }
    }
}

@Composable
fun ImageProduct(url: String) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = "Image Item",
        loading = {
            CircularProgressIndicator(
                color = colorFromHex(mauCam)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
    )

}






