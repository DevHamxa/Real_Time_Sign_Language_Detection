package com.example.rtsignlanguagedetection.view.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rtsignlanguagedetection.R
import com.example.rtsignlanguagedetection.component.checkfun
import com.example.rtsignlanguagedetection.navigation.MainActions
import com.example.rtsignlanguagedetection.theme.water

@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun TermsAndCondition(actions: MainActions) {
    val tenses = listOf(contenttabel("Introduction","Welcome to our real-time sign language detection application (\"RTSLD\"). By accessing and using the App, you agree to be bound by these Terms of Service and all applicable laws and regulations. If you do not agree with these terms, you may not use the App."),
            contenttabel("License and Use","a. Subject to these Terms of Service, we grant you a non-exclusive, non-transferable, limited license to use the App for personal and non-commercial purposes.\n" +
                    "b. You agree not to reverse engineer, modify, distribute, sell, lease, sublicense, or exploit the App or any part of it for any unauthorized purpose."),
            contenttabel("Privacy","a. Your privacy is essential to us. Please refer to our Privacy Policy to understand how we collect, use, and protect your personal information."),
            contenttabel("Intellectual Property","a. All intellectual property rights, including but not limited to copyrights, trademarks, and patents, related to the App and its content are the property of RTSLD.Inc or its licensors.\n" +
                    "b. You may not use any of our intellectual property without our prior written consent."),
            contenttabel("Disclaimer of Warranty","a. The App is provided \"as is\" and without warranties of any kind, whether expressed or implied. We do not guarantee the accuracy, completeness, or reliability of the App.\n" +
                    "b. Your use of the App is at your sole risk, and we shall not be liable for any damages resulting from the use of the App."),
            contenttabel("Limitation of Liability","a. To the extent permitted by law, RTSLD.Inc shall not be liable for any direct, indirect, incidental, special, or consequential damages arising out of or in connection with the use of the App."),
            contenttabel("Changes to the Terms of Service","a. We reserve the right to update or modify these Terms of Service at any time without prior notice. Any changes will be effective immediately upon posting the updated Terms of Service on the App."),
            contenttabel("Governing Law","a. These Terms of Service shall be governed by and construed in accordance with the laws of Pakistan."))
    var tmptitle = ""
    var tmpsentence = ""
    var backicon = R.drawable.left_arrow
    var Bgcol = Color.White
    if (isSystemInDarkTheme()) {
        backicon = R.drawable.left_arrowd
        Bgcol = Color.Black
    } else {
        backicon = R.drawable.left_arrow
        Bgcol = Color.White
    }

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(water)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
        ) {
            Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                            .clip(CircleShape)
                            .background(Bgcol)
                            .padding(8.dp),
            ) {
                Icon(
                        modifier = Modifier.size(12.dp).clickable {
                            checkfun(0)
                            actions.gotoOnDashboard()
                        },
                        painter = painterResource(id = backicon),
                        contentDescription = stringResource(R.string.text_back_icon),
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
        ) {
            Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = R.drawable.services),
                    contentDescription = "Signature",
                    contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(44.dp))


        Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                        .background(Bgcol)
                        .padding(24.dp)
        )
        {
            Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                            .fillMaxWidth()
            ) {
                Text(
                        text = "Terms of Service",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            for (item in tenses) {
                tmptitle = item.Title
                tmpsentence = item.sent
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                        modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                        shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(8.dp)
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                        .weight(0.55f)
                                        .wrapContentHeight()
                        ) {
                            Text(
                                    text = tmptitle,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                    text = tmpsentence,
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                            )
                        }
                    }
                }
            }
        }
    }
}