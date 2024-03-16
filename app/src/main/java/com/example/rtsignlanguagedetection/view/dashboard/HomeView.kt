package com.example.rtsignlanguagedetection.view.dashboard

import android.content.Intent
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rtsignlanguagedetection.R
import com.example.rtsignlanguagedetection.navigation.MainActions
import com.example.rtsignlanguagedetection.theme.*


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rtsignlanguagedetection.component.checkfun
import com.example.rtsignlanguagedetection.view.welc.WordActivity

var navboxcoloring = Color.DarkGray
var navtext = Color.White
var menuicon = R.drawable.menuw
var cameraicon = R.drawable.cameraw
var Bgcol = Color.White

@Composable
fun SideMenu(): Int {
    var navHomeisClicked by remember { mutableStateOf(false) }
    var navSGisClicked by remember { mutableStateOf(false) }
    var navPrivate by remember { mutableStateOf(false) }
    var navTerms by remember { mutableStateOf(false) }
    var navAboutisClicked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth(0.93f)
            .fillMaxHeight(1f)
            .padding(vertical = 16.dp)
            //.background(Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Add menu items and styling here
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(navboxcoloring)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(130.dp),
                                painter = painterResource(R.drawable.logo),
                                contentDescription = "logo",
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Real-Time Sign Language Detection",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = navtext,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable { navHomeisClicked = !navHomeisClicked },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(navboxcoloring)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(35.dp),
                                painter = painterResource(R.drawable.nav_home),
                                contentDescription = "home",
                            )
                            Spacer(modifier = Modifier.width(28.dp))
                            Text(
                                text = "Home",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = navtext,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable { navSGisClicked = !navSGisClicked },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(navboxcoloring)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(35.dp),
                                painter = painterResource(R.drawable.nav_guide),
                                contentDescription = "guide",
                            )
                            Spacer(modifier = Modifier.width(28.dp))
                            Text(
                                text = "Dictionary",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = navtext,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable { navPrivate = !navPrivate },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(navboxcoloring)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(35.dp),
                                painter = painterResource(R.drawable.privacy_tip),
                                contentDescription = "privacy",
                            )
                            Spacer(modifier = Modifier.width(28.dp))
                            Text(
                                text = "Privacy Policy",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = navtext,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable { navTerms = !navTerms },
            ) {
                Box(
                        modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f)
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(16.dp))
                                .background(navboxcoloring)
                ) {
                    Column(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                    ) {
                        Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Image(
                                    modifier = Modifier
                                            .size(35.dp),
                                    painter = painterResource(R.drawable.services),
                                    contentDescription = "Terms of Service",
                            )
                            Spacer(modifier = Modifier.width(28.dp))
                            Text(
                                    text = "Terms of Service",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = navtext,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                    modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clickable { navAboutisClicked = !navAboutisClicked },
            ) {
                Box(
                        modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f)
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(16.dp))
                                .background(navboxcoloring)
                ) {
                    Column(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                    ) {
                        Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Image(
                                    modifier = Modifier
                                            .size(35.dp),
                                    painter = painterResource(R.drawable.nav_about),
                                    contentDescription = "about",
                            )
                            Spacer(modifier = Modifier.width(28.dp))
                            Text(
                                    text = "About Us",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = navtext,
                            )
                        }
                    }
                }
            }
        }
    }
    if (navHomeisClicked)
    {
        return 1
    }
    else if (navSGisClicked)
    {
        return 2
    }
    else if (navPrivate)
    {
        return 3
    }
    else if (navTerms)
    {
        return 4
    }
    else if (navAboutisClicked)
    {
        return 5
    }

    return 0
}
data public class Instructions (
        val imagein: Int,
        val Title: String,
        val sent: String
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun HomeView(actions: MainActions)  {
    val isSideMenuOpen = remember { mutableStateOf(false) }
    val toastMessageA = "The fingers are completely curved, forming a fist and thumb is not tucked into the palm."
    val toastMessageP = "The index points to the right and thumb points to the floor, while middle is curled up towards it."
    val toastMessageK = "A Hand raised such that the thumb is in the center of fully stretched index and middle finger."
    val toastMessageI = "The fingers are curled to form a fist, except the pinky finger."
    val toastMessageS = "The fingers are curled to form a fist, in S the thumb is curled in front of the curled fingers."
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result if needed
    }
    val tenses = listOf(Instructions(R.drawable.positionhand,"Positioning the Hand","When using the camera to identify a hand sign, make sure the hand is clearly visible and well-lit. Place the hand against a contrasting background to improve accuracy."),
            Instructions(R.drawable.steadycamera,"Steady Camera","Hold the camera steady while attempting to detect the sign. A stable camera ensures better recognition results."),
            Instructions(R.drawable.lighting,"Good Lighting","Adequate lighting is crucial for accurate sign detection. Avoid using the app in low-light conditions as it may affect the results."),
            Instructions(R.drawable.gesture,"Clear Hand Gestures"," Make deliberate and distinct hand gestures. Avoid excessive movement or obscured hand positions."),
            Instructions(R.drawable.natbackground,"Neutral Background","When trying to detect signs, it's beneficial to use a plain and neutral background without any distracting objects."),
            Instructions(R.drawable.gesturespeed,"Gesture Speed","Try to maintain a natural and moderate speed while making gestures. Rapid or overly slow movements might affect recognition accuracy."),
            Instructions(R.drawable.rule,"Practice","If you're new to sign language, practice making clear and precise hand signs to improve detection accuracy over time."),
            Instructions(R.drawable.loop,"Retry if Needed","If the app fails to identify a sign correctly, don't hesitate to retry. Adjust your hand position or gesture slightly for better results."))
    var tmptitle = ""
    var tmpsentence = ""
    var tmpimage = 0
    var checkClick = 0;
    if (isSystemInDarkTheme()) {
        navboxcoloring = Color.DarkGray
        menuicon = R.drawable.menud
        cameraicon = R.drawable.cameraw
        Bgcol = Color.Black
        navtext = Color.White
    } else {
        navboxcoloring = cultured
        menuicon = R.drawable.menuw
        cameraicon = R.drawable.camerad
        Bgcol = Color.White
        navtext = Color.Black
    }
    Box()
    {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {

                    Image(
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                if (isSideMenuOpen.value == false) {
                                    isSideMenuOpen.value = true
                                } else {
                                    isSideMenuOpen.value = false
                                }
                            },
                        painter = painterResource(id = menuicon),
                        contentDescription = "Menu Image"

                    )

                    val cameraElement = Image(
                        modifier = Modifier
                            .size(37.dp)
                            .clickable {
                                val intent = Intent(context, WordActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                launcher.launch(intent)
                            },
                        painter = painterResource(id = cameraicon),
                        contentDescription = "camera",
                        contentScale = ContentScale.Crop
                    )
                }
                if (isSideMenuOpen.value) {
                    checkClick = SideMenu()
                    if (checkClick == 1)
                    {
                        isSideMenuOpen.value = false
                        checkfun(0)
                        actions.gotoOnDashboard()
                    }
                    else if (checkClick == 2)
                    {
                        isSideMenuOpen.value = false
                        checkfun(1)
                        actions.gotoOnDashboard()
                    }
                    else if (checkClick == 3)
                    {
                        isSideMenuOpen.value = false
                        actions.gotoOnPrivacy()
                    }
                    else if (checkClick == 4)
                    {
                        isSideMenuOpen.value = false
                        actions.gotoOnTermss()
                    }
                    else if (checkClick == 5)
                    {
                        isSideMenuOpen.value = false
                        actions.gotoAbout()
                    }

                } else {
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(
                            text = "Real-Time Sign",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                            text = "Language Detection",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                            text = "Empowering Inclusivity, Bridging the Communication Divide.",
                            textAlign = TextAlign.Left,
                            color = Color.Gray,
                            fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                    .fillMaxWidth()
                    ) {
                        Text(
                                text = "Common Signs",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                    ) {
                        Box(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.5f)
                                        .wrapContentHeight()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(navajoWhite)
                                        .clickable {
                                            Toast.makeText(context, toastMessageP, Toast.LENGTH_LONG).show()
                                        }
                        ) {
                            Column(
                                    modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                            ) {
                                Row(
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                                .fillMaxWidth()
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                            modifier = Modifier
                                                    .size(100.dp),
                                            painter = painterResource(R.drawable.p),
                                            contentDescription = "P",
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))
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
                                                text = "P",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = black,
                                        )
                                        Text(
                                                text = "ASL Alphabet 16",
                                                fontSize = 12.sp,
                                                color = black,
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.5f)
                                        .wrapContentHeight()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(water)
                                        .clickable {
                                            Toast.makeText(context, toastMessageA, Toast.LENGTH_LONG).show()
                                        }
                        ) {
                            Column(
                                    modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                            ) {
                                Row(
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                                .fillMaxWidth()
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                            modifier = Modifier
                                                    .size(100.dp),
                                            painter = painterResource(R.drawable.a),
                                            contentDescription = "A",
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))
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
                                                text = "A",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = black,
                                        )
                                        Text(
                                                text = "ASL Alphabet 1",
                                                fontSize = 12.sp,
                                                color = black,
                                        )
                                    }
                                }
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                    ) {
                        Box(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.5f)
                                        .wrapContentHeight()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(seashell)
                                        .clickable {
                                            Toast.makeText(context, toastMessageK, Toast.LENGTH_LONG).show()
                                        }
                        ) {
                            Column(
                                    modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                            ) {
                                Row(
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                                .fillMaxWidth()
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                            modifier = Modifier
                                                    .size(100.dp),
                                            painter = painterResource(R.drawable.k),
                                            contentDescription = "K",
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))
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
                                                text = "K",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = black,
                                        )
                                        Text(
                                                text = "ASL Alphabet 11",
                                                fontSize = 12.sp,
                                                color = black,
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.5f)
                                        .wrapContentHeight()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(cultured)
                                        .clickable {
                                            Toast.makeText(context, toastMessageI, Toast.LENGTH_LONG).show()
                                        }
                        ) {
                            Column(
                                    modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                            ) {
                                Row(
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                                .fillMaxWidth()
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                            modifier = Modifier
                                                    .size(100.dp),
                                            painter = painterResource(R.drawable.i),
                                            contentDescription = "I",
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))
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
                                                text = "I",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = black,
                                        )
                                        Text(
                                                text = "ASL Alphabet 9",
                                                fontSize = 12.sp,
                                                color = black,
                                        )
                                    }
                                }
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                            modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .wrapContentHeight()
                    ) {
                        Box(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.5f)
                                        .wrapContentHeight()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(aliceBlue)
                                        .clickable {
                                            Toast.makeText(context, toastMessageS, Toast.LENGTH_LONG).show()
                                        }
                        ) {
                            Column(
                                    modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                            ) {
                                Row(
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                                .fillMaxWidth()
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                            modifier = Modifier
                                                    .size(100.dp),
                                            painter = painterResource(R.drawable.s),
                                            contentDescription = "S",
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))
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
                                                text = "S",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = black,
                                        )
                                        Text(
                                                text = "ASL Alphabet 19",
                                                fontSize = 12.sp,
                                                color = black,
                                        )
                                    }
                                }
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                    .fillMaxWidth()
                    ) {
                        Text(
                                text = "Instructions",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    for(item in tenses){
                        tmpimage = item.imagein
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
                            Box(
                                    modifier = Modifier
                                            .weight(0.2f)
                                            .height(80.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(lightBlue),
                                    contentAlignment = Alignment.Center
                            ) {
                                Image(
                                        modifier = Modifier.padding(8.dp)
                                                .size(43.dp),
                                        painter = painterResource(tmpimage),
                                        contentDescription = "Instructions",
                                )
                            }
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
                                        fontSize = 14.sp,
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                        text = tmpsentence,
                                        fontSize = 12.sp,
                                        color = Color.Gray,
                                )
                            }
                        }
                    }
                }
                    Spacer(modifier = Modifier.height(24.dp))
                }
                if(isSideMenuOpen.value) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 90.dp)
                    ) {
                        Text(
                            text = "Powered by RTSLD.Inc",
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }
            }
        }
    }
}