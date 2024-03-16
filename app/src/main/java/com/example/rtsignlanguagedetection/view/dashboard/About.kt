package com.example.rtsignlanguagedetection.view.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rtsignlanguagedetection.R
import com.example.rtsignlanguagedetection.component.checkfun
import com.example.rtsignlanguagedetection.navigation.MainActions
import com.example.rtsignlanguagedetection.theme.*

@Composable
fun About(actions: MainActions) {

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
                    modifier = Modifier.size(12.dp) .clickable { checkfun(0)
                        actions.gotoOnDashboard() },
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
                painter = painterResource(id = R.drawable.nav_about),
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
                    text = "RTSLD",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
            ) {
                Text(
                        text = "RTSLD.Inc is a cutting-edge technology company specializing in the development of advanced machine learning and deep learning solutions. As a leading innovator in the field of artificial intelligence, we are committed to pushing the boundaries of what is possible and creating groundbreaking applications that have a positive impact on society.\n" +
                                "\n" +
                                "Our flagship project is the Real-Time Sign Language Detection (RTSLD) system. Leveraging advanced computer vision techniques and deep learning models, this groundbreaking system enables real-time interpretation and recognition of sign language Signals, Sign language serves as a fundamental means of communication for individuals with hearing impairments, enabling them to express their thoughts, emotions, and ideas. Despite its importance, the language gap between the hearing-impaired and the broader community frequently creates barriers to social inclusion and successful communication. The gap is further widened by the fact that learning and being fluent in sign language is a challenging task. This project offers the creation and application of a real-time sign language recognition Android application as a solution to this problem and as a means of facilitating seamless communication between these two communities.\n" +
                                "\n" +
                                "Beyond sign language detection, our expertise extends to various other machine learning and deep learning projects across diverse domains. From natural language processing and computer vision to speech recognition and recommendation systems, our team excels in developing customized AI solutions that cater to the unique needs of our clients.\n" +
                                "\n" +
                                "At RTSLD.Inc, we pride ourselves on our commitment to excellence, innovation, and ethical AI practices. We place a strong emphasis on research and development, fostering a culture of continuous learning and staying at the forefront of the ever-evolving AI landscape.\n" +
                                "\n" +
                                "Whether you are a business seeking to optimize your processes, a researcher exploring AI applications, or an individual with unique requirements, RTSLD.Inc is your trusted partner in the world of AI-driven solutions. Together, we are shaping a future where AI not only transforms industries but also empowers individuals and communities, making the world a more accessible and inclusive place for all.",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Justify
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "Date",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "05 July,2023",
                        fontSize = 14.sp,
                        color = gold,
                    )
                }
            }
        }
    }
}
