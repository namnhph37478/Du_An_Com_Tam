package com.thuydev.asmok.GUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thuydev.asmok.ViewModel.BillDetailViewModel
import com.thuydev.asmok.ViewModel.BillViewModel
import com.thuydev.asmok.ViewModel.LogOK

@Preview
@Composable
fun Bill(user: State<Account>,
         billView: BillViewModel,
         billDetailView: BillDetailViewModel){
    billDetailView.GetBillDetail(user.value._id!!)
    var listBill = billDetailView.listBillDetail.observeAsState(emptyList())
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn {
            items(listBill.value){item->
                    ItemCardBill(bill = item)
              }
        }

    }

}

@Composable
fun ItemCardBill(bill: BillDetail) {
    var status by rememberSaveable {
        mutableStateOf("")
    }
    if(bill.Status.toInt()==0){
        status = "Await"
    }else if (bill.Status.toInt()==1){
        status = "Done"
    }else{
        status = "Canceled"
    }
    LogOK(bill)
    Column(
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color(0xFFC2C2C2), RoundedCornerShape(10.dp))
            .padding(10.dp)) {
        Text(text = "ID Bill: ${ bill.IDBill}", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Row(Modifier.fillMaxWidth()) {
            Text(text = "Total: ${ bill.Total}", fontWeight = FontWeight.Normal , modifier = Modifier.weight(1f) )
            Text(text = "Amount: ${ bill.Amount}", fontWeight = FontWeight.Normal, modifier = Modifier.weight(1f) )
        }
        Text(text = "Date: ${ bill.Date}", fontWeight = FontWeight.Normal  )
        Row {
            Text(text = "Status: ", fontWeight = FontWeight.Normal  )
            Text(text = status, fontWeight = FontWeight.Normal, color =
            if(bill.Status.toInt()==0){
                Color(0xFFFFE500)
            }else if(bill.Status.toInt()==1){
                Color(0xFF00A216)
            }else{
                Color(0xFFFF0000)
            }
            )
        }
    }
}


