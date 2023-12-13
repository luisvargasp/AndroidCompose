package com.example.jetpackcompose.tip_app.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val IconButtonSizeModifier=Modifier.size(40.dp)
@Composable
fun RoundIconButton(imageVector: ImageVector,onClick:()->Unit,modifier:Modifier=Modifier,tint:Color= Color.Black.copy(0.8f),
                    backgroundColor:Color=MaterialTheme.colorScheme.background,elevation: Dp =4.dp
){

    Card(modifier= modifier
        .padding(all = 4.dp)
        .size(40.dp)
        .clickable {
            onClick()
        }
        .then(IconButtonSizeModifier),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation=CardDefaults.cardElevation(defaultElevation=elevation)


    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
            Icon(imageVector =imageVector , contentDescription ="Icon",tint=tint )

        }

    }

}