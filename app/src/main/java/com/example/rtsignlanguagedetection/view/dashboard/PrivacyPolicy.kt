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
import androidx.compose.material.Surface
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
import com.example.rtsignlanguagedetection.theme.lightBlue
import com.example.rtsignlanguagedetection.theme.water

data public class contenttabel (
        val Title: String,
        val sent: String
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun PrivacyPolicy(actions: MainActions) {
    val tenses = listOf(contenttabel("Introduction", "a. This Privacy Policy outlines how RTSLD.Inc collects, uses, and protects your personal information when you use the real-time sign language detection App (\"RTSLD\").\n" + "b. By using the App, you consent to the practices described in this Privacy Policy."),
            contenttabel("Information Collection", "a. We may collect personal information such as your name, email address, and other relevant data when you use the App.\n" + "b. We may also collect non-personal information, such as device information, app usage data, and analytics."),
            contenttabel("Use of Information", "a. We may use the information collected to provide and improve the App's functionality and user experience.\n" +
                    "b. Personal information may be used to communicate with you, respond to inquiries, and provide customer support.\n" +
                    "c. Non-personal information may be used for analytics and to improve the App's performance and features."),
            contenttabel("Data Security", "a. We implement reasonable security measures to protect your personal information from unauthorized access, alteration, or disclosure.\n" +
                    "b. Despite our efforts, we cannot guarantee the security of your information, and you use the App at your own risk."),
            contenttabel("Third-Party Services", "a. The App may contain links to third-party websites or services. We are not responsible for the privacy practices or content of these third-party services."),
            contenttabel("Children's Privacy", "a. The App is not intended for children under the age of 13. We do not knowingly collect personal information from children."),
            contenttabel("Changes to the Privacy Policy", "a. We may update or modify this Privacy Policy from time to time. Any changes will be effective immediately upon posting the updated Privacy Policy on the App."),
            contenttabel("Contact Us", "a. If you have any questions or concerns about this Privacy Policy, please contact us at rtsld.app@gmail.com"))
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
                    modifier = Modifier.size(180.dp),
                    painter = painterResource(id = R.drawable.privacy_tip),
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
                        text = "Privacy Policy",
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