package com.thuydev.asmok.GUI

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.thuydev.asmok.Extention.colorFromHex
import com.thuydev.asmok.Extention.formatNumber


@Composable
fun Home(
    navMain: NavController,
    product: List<Product>,
    cate: List<Category>,
    data:(Product)->Unit
    ) {
    var isEmpty by rememberSaveable {
        mutableStateOf(false)
    }
    var listPro:List<Product> = product
    var listCate = cate


    val context =  LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
    ) {
        LazyColumn(Modifier.padding(5.dp)) {
            filterCategoriesWithProducts(listCate,listPro)
            items(filterCategoriesWithProducts(listCate,listPro)) { itemCate ->
                    Text(
                        text = itemCate.NameCategory,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = colorFromHex(mauCam)
                    )
                LazyRow {
                    items(listPro) { itemPro ->
                        if (itemCate._id.equals(itemPro.IDCategory)) {
                            isEmpty = false
                            Card(
                                Modifier
                                    .size(200.dp, 300.dp)
                                    .padding(5.dp)
                                    .clickable {
                                        navMain.navigate(Screens.ViewDetailProduct.screen)
                                        data(itemPro)
                                    },
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFE6E6E6)
                                ),
                            ) {
                                Log.e("TAG", "Home: "+itemPro.Image )
                                SubcomposeAsyncImage(
                                    model = itemPro.Image,
                                    contentDescription = "",
                                    loading = {
                                        CircularProgressIndicator(
                                            color = colorFromHex(mauCam)
                                        )
                                    },
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .padding(0.dp, 0.dp, 0.dp, 10.dp)
                                )
                                Text(text = itemPro.NameProduct,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorFromHex(mauCam),
                                    modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp)
                                )

                                Text(text = "${formatNumber(itemPro.Price)}",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Light,
                                    color = Color(0xFFEC3001),
                                    modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp)
                                )
                            }

                        }else{
                            isEmpty=true
                        }
                    }
                }
            }

        }
    }
}
fun filterCategoriesWithProducts(categories: List<Category>, products: List<Product>): List<Category> {
    return categories.filter { category ->
        products.any { product -> product.IDCategory == category._id }
    }
}