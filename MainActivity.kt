@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.thuydev.asmok.GUI

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thuydev.asmok.Extention.colorFromHex
import com.thuydev.asmok.R
import com.thuydev.asmok.ViewModel.AccountViewModel
import com.thuydev.asmok.ViewModel.BillDetailViewModel
import com.thuydev.asmok.ViewModel.BillViewModel
import com.thuydev.asmok.ViewModel.CartViewModel
import com.thuydev.asmok.ViewModel.CategoryViewModel
import com.thuydev.asmok.ViewModel.ProductDetailViewModel
import com.thuydev.asmok.ViewModel.ProductViewModel
import com.thuydev.asmok.ui.theme.ASMOKTheme

data class BottomNaviItem(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNew: Boolean,
    val badgeCount: Int? = null
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ASMOKTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ControllerView()
                }
            }
        }
    }
}

@Composable
fun ControllerView(){
    val productDetailView: ProductDetailViewModel = viewModel()
    val cartView:CartViewModel  = viewModel()
    val naviMainCtrl = rememberNavController()
    val account :AccountViewModel = viewModel()
    var user = account.account.observeAsState(Account())
    var tempPro : Product = Product("","","","",0)
    NavHost(navController = naviMainCtrl, startDestination =Screens.Login.screen ) {
        composable(Screens.Login.screen){ ManHinhDangNhap1(naviMainCtrl,user,account)}
        composable(Screens.ViewDetailProduct.screen){ ManHinhSanPhamChiTiet1(naviMainCtrl,tempPro,productDetailView,user,cartView)}
        composable(Screens.Sigin.screen){ ManHinhDangKy1(naviMainCtrl,account)}
        composable(Screens.Main.screen){ MainView(naviMainCtrl,{tempPro=it},cartView,user)}
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainView(
    naviMain: NavHostController,
    data: (Product) -> Unit,
    cartView: CartViewModel,
    user: State<Account>
) {
    val productView :ProductViewModel = viewModel()
    val categoryView : CategoryViewModel = viewModel()

    val billView :BillViewModel = viewModel()
    val billDetailView : BillDetailViewModel = viewModel()
    var product  = productView.listProduct.observeAsState(initial = emptyList())
    var category = categoryView.listCategory.observeAsState(emptyList())
    var cart  =cartView.listCart.observeAsState(initial = emptyList())
    var bill  =billView.listBill.observeAsState()
    var billDetail  =billDetailView.listBillDetail.observeAsState(initial = emptyList())



    val items = listOf(
        BottomNaviItem(
            title = "Store",
            selectedIcon = R.drawable.shoes,
            unselectedIcon = R.drawable.shoes,
            hasNew = false
        ),
        BottomNaviItem(
            title = "Cart",
            selectedIcon = R.drawable.shoppingcart,
            unselectedIcon = R.drawable.shoppingcart,
            hasNew = false
        ),
        BottomNaviItem(
            title = "Bill",
            selectedIcon = R.drawable.bill_kh,
            unselectedIcon = R.drawable.bill_kh,
            hasNew = false
        )
    )
    var title by rememberSaveable {
        mutableStateOf("Store")
    }
    val naviController = rememberNavController()
    Scaffold(
        topBar = {
            ToolBar(title = title)
        },
        content = {
            Content(naviController = naviController, paddingValues =it ,naviMain,product.value,category.value,cartView,{data(it)},user,billView,billDetailView)
        },
        bottomBar = {
            NaviBottom(items = items, onClicks = {
                title = it
            },naviController)
        },
    )
}

@Composable
fun Content(
    naviController: NavHostController,
    paddingValues: PaddingValues,
    navMain: NavController,
    product: List<Product>,
    cate: List<Category>,
    cartView: CartViewModel,
    data: (Product) -> Unit,
    user: State<Account>,
    billView: BillViewModel,
    billDetailView: BillDetailViewModel
) {
        NavHost(navController = naviController,
            startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)){
            composable(Screens.Home.screen){ Home(navMain,product,cate,{data(it)})}
            composable(Screens.Cart.screen){ CartScreen(cartView,product,cate,user,billView)}
            composable(Screens.Bill.screen){ Bill(user,billView,billDetailView)}
        }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ASMOKTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           ControllerView()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToolBar(title : String) {
            TopAppBar(
                title = { Text(text = title, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorFromHex(mauCam),
                    titleContentColor = Color.White
                )
            )
}

@Composable
fun NaviBottom(
    items: List<BottomNaviItem>,
    onClicks: (String) -> Unit,
    naviController: NavHostController) {
    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    var screens = mutableListOf(String())
    screens.add(Screens.Home.screen)
    screens.add(Screens.Cart.screen)
    screens.add(Screens.Bill.screen)
    NavigationBar(containerColor = colorFromHex(mauCam)) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    onClicks(item.title)
                    naviController.navigate(screens.get(index+1))
                },
                icon = {
                    BadgedBox(
                        badge = {
                        }) {
                        Icon(
                            painterResource(id = item.selectedIcon),
                            contentDescription = item.title
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    indicatorColor = colorFromHex(mauCam)
                ),
                modifier = if (selectedIndex == index) {
                    Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp)
                } else {
                    Modifier.padding(0.dp)
                }
            )
        }
    }
}