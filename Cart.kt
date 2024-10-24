package com.thuydev.asmok.GUI


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thuydev.asmok.Extention.colorFromHex
import com.thuydev.asmok.Extention.formatNumber
import com.thuydev.asmok.ViewModel.BillViewModel
import com.thuydev.asmok.ViewModel.CartViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun CardView(pro: Product, cart: Cart, itemCate: Category,mua:()->Unit,xoa:()->Unit){
    Row(
        Modifier
            .height(220.dp)
            .padding(10.dp, 8.dp)
            .background(Color(0xFFC7C7C7), RoundedCornerShape(10.dp))
            .padding(start = 5.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)) {
        Column( Modifier.width(150.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
           AsyncImage(model = pro.Image, contentDescription = "",
               Modifier
                   .size(150.dp),contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "  Gia: ${formatNumber(pro.Price)}  ",
                Modifier
                    .background(Color.White)
                    .height(20.dp)
                    , fontSize = 13.sp)
        }
        Column (
            Modifier
                .fillMaxSize()
                .padding(start = 10.dp)){
            Text(text = pro.NameProduct, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = itemCate.NameCategory, fontWeight = FontWeight.Bold, fontSize = 20.sp)
           Row(Modifier.fillMaxWidth()) {
               Text(text = "Kich co: ${cart.Size}", fontSize = 15.sp, textAlign = TextAlign.Start, modifier = Modifier.weight(1f))
               Text(text = "So luong: ${cart.Amount}",  fontSize = 16.sp, textAlign = TextAlign.Start, modifier = Modifier.weight(1f))
           }
            Spacer(modifier = Modifier.weight(1f))
            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceAround
                    ){
                Text(text = "   Mua   ",
                    Modifier
                        .background(Color.White)
                        .height(20.dp)
                        .clickable {
                           mua()
                        }
                    , fontSize = 13.sp)
                Text(text = "   Loai bo   ",
                    Modifier
                        .background(Color.White)
                        .height(20.dp)
                        .clickable {
                            xoa()
                        }
                    , fontSize = 13.sp)
            }

        }
    }
}
@Composable
fun CartScreen(
    cartView: CartViewModel,
    product: List<Product>,
    cate: List<Category>,
    user: State<Account>,
    billView: BillViewModel
) {
    val context  = LocalContext.current
    val cart  =cartView.listCart.observeAsState(initial = emptyList())
    var tong:Number by rememberSaveable {
        mutableStateOf(0)
    }
    cartView.GetData(user.value._id!!,{tong=it})
    Column(Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
           items(cart.value){ itemCart: Cart ->
                Log.e("TAG", "CartScreen: "+cartView.listCart.value!!.size )
               product.forEach({itemPro->
                    if(itemCart.IDProduct.equals(itemPro._id)){
                      cate.forEach({
                           itemCate->
                           if(itemPro.IDCategory.equals(itemCate._id)){
                               CardView(itemPro,itemCart,itemCate,{
                                   val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
                                   // Lấy thời gian hiện tại
                                   val currentTime = LocalDateTime.now()
                                   // Chuyển đổi thời gian thành chuỗi định dạng
                                   val formattedTime = currentTime.format(formatter)
                                   var data = HashMap<String,Any>()
                                   data["IDUser"]=user.value._id!!
                                   data["IDSeller"]="?"
                                   data["IDProduct"]= AddtoBill(listOf<Cart>(itemCart),product)
                                   data["Status"]= 0
                                   data["Date"]= formattedTime
                                   data["IDCart"]= listOf<Cart>(itemCart)
                                   billView.AddBill(data,{
                                       Toast.makeText(context,it,Toast.LENGTH_LONG).show()
                                   },{
                                       cartView.GetData(user.value._id!!,{tong=it})
                                   })
                               },{
                                    cartView.DeleteCart(itemCart._id!!,{
                                        Toast.makeText(context,it,Toast.LENGTH_LONG).show()
                                    },{
                                        cartView.GetData(user.value._id!!,{tong=it})
                                    })
                               })
                           }
                       })
                    }
                })
            }
        }
        Text(text = "",modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = colorFromHex(mauCam)))
        Row(
            Modifier
                .padding(20.dp, 5.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                Text(text = "Tong ", fontWeight = FontWeight.Bold, color = colorFromHex(mauCam))
                Text(text = " { Da bao gom thue }",color = colorFromHex(mauCam))
            }
            Text(text = "${formatNumber(tong)} VND",color = colorFromHex(mauCam))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .size(50.dp)
                .padding(20.dp, 5.dp)
                .background(colorFromHex("#FEC389"))
                .padding(10.dp).clickable {
                    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

                    // Lấy thời gian hiện tại
                    val currentTime = LocalDateTime.now()

                    // Chuyển đổi thời gian thành chuỗi định dạng
                    val formattedTime = currentTime.format(formatter)
                    var data = HashMap<String,Any>()
                    data["IDUser"]=user.value._id!!
                    data["IDSeller"]="?"
                    data["IDProduct"]= AddtoBill(cart.value,product)
                    data["Status"]= 0
                    data["Date"]= formattedTime
                    data["IDCart"]= cart.value
                    billView.AddBill(data,{
                        Toast.makeText(context,it,Toast.LENGTH_LONG).show()
                    },{
                        cartView.GetData(user.value._id!!,{tong=it})
                    })
                },horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dat hang", fontWeight = FontWeight.Bold, color = Color.White,)
            Text(text = "-->", fontWeight = FontWeight.Bold, color = Color.White,)
        }
    }

    }

fun AddtoBill(listCart:List<Cart>,listPro:List<Product>):MutableList<Map<String,Any>>{
    var listTemp  = mutableListOf<Map<String,Any>>()

    listCart.forEach({itemCart->
        listPro.forEach({itemPro->
            if (itemCart.IDProduct.equals(itemPro._id)){
                var data = HashMap<String,Any>()
                data["IDProduct"]=itemPro._id
                data["Amount"]= itemCart.Amount
                data["Price"] = itemPro.Price
                data["Size"] = itemCart.Size
                listTemp.add(data)
            }
        })
    })
return listTemp
}
