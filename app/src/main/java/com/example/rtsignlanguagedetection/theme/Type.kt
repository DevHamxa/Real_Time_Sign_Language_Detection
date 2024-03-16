package com.example.rtsignlanguagedetection.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.rtsignlanguagedetection.R

private val dmSans = FontFamily(
    Font(R.font.dmsansregular),
    Font(R.font.dmsansmedium, FontWeight.W500),
    Font(R.font.dmsansbold, FontWeight.Bold)
)


val typography = Typography(defaultFontFamily = dmSans)
