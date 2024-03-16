package com.example.rtsignlanguagedetection.view.dashboard

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rtsignlanguagedetection.R
import com.example.rtsignlanguagedetection.component.checkfun
import com.example.rtsignlanguagedetection.model.Vegetable
import com.example.rtsignlanguagedetection.navigation.MainActions
import com.example.rtsignlanguagedetection.theme.*
import com.example.rtsignlanguagedetection.view.welc.WordActivity

@ExperimentalMaterialApi
@Composable
fun Dictionary(actions: MainActions) {

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result if needed
    }

    var backicon = R.drawable.left_arrow
    var cameraicon = R.drawable.cameraw
    var Bgcol = Color.White
    if (isSystemInDarkTheme()) {
        backicon = R.drawable.left_arrowd
        cameraicon = R.drawable.cameraw
        Bgcol = Color.Black
    } else {
        backicon = R.drawable.left_arrow
        cameraicon = R.drawable.camerad
        Bgcol = Color.White
    }
    var text by remember { mutableStateOf("") }
    val vegetablesItemList = listOf(
        Vegetable(R.drawable.a, "A", "ASL Alphabet 1", seashell),
        Vegetable(R.drawable.b, "B", "ASL Alphabet 2", aliceBlue),
        Vegetable(R.drawable.c, "C", "ASL Alphabet 3", cultured),
        Vegetable(R.drawable.d, "D", "ASL Alphabet 4", azureishWhite),
        Vegetable(R.drawable.e, "E", "ASL Alphabet 5", seashell),
        Vegetable(R.drawable.f, "F", "ASL Alphabet 6", aliceBlue),
        Vegetable(R.drawable.g, "G", "ASL Alphabet 7", aliceBlue),
        Vegetable(R.drawable.h, "H", "ASL Alphabet 8", cultured),
        Vegetable(R.drawable.i, "I", "ASL Alphabet 9", azureishWhite),
        Vegetable(R.drawable.j, "J", "ASL Alphabet 10", seashell),
        Vegetable(R.drawable.k, "K", "ASL Alphabet 11", aliceBlue),
        Vegetable(R.drawable.l, "L", "ASL Alphabet 12", aliceBlue),
        Vegetable(R.drawable.m, "M", "ASL Alphabet 13", cultured),
        Vegetable(R.drawable.n, "N", "ASL Alphabet 14", azureishWhite),
        Vegetable(R.drawable.o, "O", "ASL Alphabet 15", seashell),
        Vegetable(R.drawable.p, "P", "ASL Alphabet 16", aliceBlue),
        Vegetable(R.drawable.q, "Q", "ASL Alphabet 17", aliceBlue),
        Vegetable(R.drawable.r, "R", "ASL Alphabet 18", cultured),
        Vegetable(R.drawable.s, "S", "ASL Alphabet 19", azureishWhite),
        Vegetable(R.drawable.t, "T", "ASL Alphabet 20", seashell),
        Vegetable(R.drawable.u, "U", "ASL Alphabet 21", aliceBlue),
        Vegetable(R.drawable.v, "V", "ASL Alphabet 22", aliceBlue),
        Vegetable(R.drawable.w, "W", "ASL Alphabet 23", cultured),
        Vegetable(R.drawable.x, "X", "ASL Alphabet 24", azureishWhite),
        Vegetable(R.drawable.y, "Y", "ASL Alphabet 25", seashell),
        Vegetable(R.drawable.z, "Z", "ASL Alphabet 26", aliceBlue)
    )


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    Image(
                        modifier = Modifier.size(16.dp) .clickable { checkfun(0)
                            actions.gotoOnDashboard() },
                        painter = painterResource(id = backicon),
                        contentDescription = "Back Arrow Image"
                    )
                    Text(
                        text = "Dictionary",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Image(
                        modifier = Modifier.size(25.dp) .clickable { val intent = Intent(context, WordActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            launcher.launch(intent) },
                        painter = painterResource(id = cameraicon),
                        contentDescription = "word camera",
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
            }
            items(vegetablesItemList.windowed(2, 2, true)) { sublist ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    sublist.forEach { item ->
                        Card(
                            modifier = Modifier
                                .fillParentMaxWidth(0.5f)
                                .padding(4.dp),
                            shape = RoundedCornerShape(16.dp),
                            backgroundColor = item.cardBg,
                            onClick = {
                                //actions.gotoAbout()
                            }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .size(84.dp),
                                        painter = painterResource(item.image),
                                        contentDescription = "Sign",
                                    )
                                }
                                Spacer(modifier = Modifier.height(24.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {

                                    Column(
                                        modifier = Modifier
                                            .wrapContentHeight()
                                    ) {
                                        Text(
                                            text = item.name,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = black,
                                        )
                                        Text(
                                            text = item.price,
                                            fontSize = 11.sp,
                                            color = black,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}