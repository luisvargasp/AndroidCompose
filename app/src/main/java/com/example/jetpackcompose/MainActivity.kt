package com.example.jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun CreateBizCard(){
    val buttonClickedState= remember {
        mutableStateOf(true)
    }
    Surface(modifier= Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        Card(modifier= Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape= RoundedCornerShape(corner= CornerSize(15.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )

        ){
            Column (modifier = Modifier.height(300.dp),
                verticalArrangement =  Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CreateImageProfile()
                Divider(thickness = 4.dp)
                CreateInfo()
                Button(onClick = {
                    buttonClickedState.value=buttonClickedState.value.not()
                }
                    ) {
                    Text(text = "Portfolio", style = MaterialTheme.typography.headlineSmall)
                }

            }
            if(buttonClickedState.value){
                Content()
            }else{
                Box {

                }

            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Luis F.",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(text = "Android Developer")
        Text(text = "lvar@gmail.com")
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier=Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "img",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop

        )

    }
}
@Composable
private fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(Color.White)
        .padding(5.dp)
    ){
        Surface (modifier = Modifier
            .padding(3.dp)
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(), shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.White), shadowElevation = 4.dp
        ){
            Portfolio(data= listOf("Project1" ,"Project2","Project3","Project4","Project5","Project6","Project7"))
        }

    }

}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn(Modifier.background(Color.White)){
        items(data){
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                    containerColor = Color.White
                    ), elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                shape = RectangleShape) {
                Row (modifier = Modifier
                    .padding(8.dp)
                    .background(Color.White)
                    .padding(16.dp),

                    ){
                    CreateImageProfile(modifier = Modifier.size(70.dp))
                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = CenterVertically)) {
                        Text(text = it, fontWeight = FontWeight.Bold)
                        Text(text = "A nice project")

                    }


                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTheme {
        CreateBizCard()
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CreateCircle(){
    val moneyCounter =  rememberSaveable {
        mutableStateOf(0)
    }

    Card (modifier = Modifier
        .padding(3.dp)
        .size(145.dp)
        .background(Color.White)
        ,
        shape = CircleShape, elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ), onClick = {
            Log.d("LogD", "CreateCircle ${moneyCounter.value}")
            moneyCounter.value=moneyCounter.value+1

        }
    ){
            Box(contentAlignment = Alignment.Center , modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()){
                Text(text = "Tap me  ${moneyCounter.value}", color = Color.Blue,
                    style = TextStyle(color = Color.Blue, fontSize = 24.sp))
            }
    }
}

