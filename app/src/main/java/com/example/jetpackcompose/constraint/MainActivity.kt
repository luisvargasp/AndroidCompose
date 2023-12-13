package com.example.jetpackcompose.constraint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import androidx.constraintlayout.compose.layoutId
import com.example.jetpackcompose.R
import com.example.jetpackcompose.constraint.ui.theme.JetpackComposeTheme

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

                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MyApp(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (image,text,divider)=createRefs()
        val startGuideLine=createGuidelineFromStart(0.1f)
        val endGuideLine=createGuidelineFromEnd(0.1f)
        val topGuideLine=createGuidelineFromTop(0.2f)

        Image(painterResource(id = R.drawable.sunrise ) , contentDescription = null,
            modifier = Modifier.constrainAs(image){
                //top.linkTo(text.top)
               // start.linkTo(parent.start)
               // end.linkTo(parent.end)
               // bottom.linkTo(parent.bottom)
                linkTo(top=text.top,bottom=parent.bottom, bias = 0.2f, bottomMargin = 10.dp)
                linkTo(start=parent.start,end=parent.end)
                visibility= Visibility.Visible


            }
        )
       Text(textAlign = TextAlign.Center,text = "Hola", modifier = Modifier
           .padding(top = 0.dp)
           .background(Color.Red)
           .constrainAs(text) {
               start.linkTo(startGuideLine)
               end.linkTo(endGuideLine)
               top.linkTo(topGuideLine)
               width = Dimension.fillToConstraints

           })
        Divider(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .constrainAs(divider) {
                top.linkTo(image.bottom, goneMargin = 200.dp)

            }, color = Color.Red)


    }
}

private fun constrains1(margin: Dp):ConstraintSet=
    ConstraintSet{

        val startGuideLine=createGuidelineFromStart(0.1f)
        val endGuideLine=createGuidelineFromEnd(0.1f)
        val topGuideLine=createGuidelineFromTop(0.2f)


        val image=createRefFor("image")
        val text=createRefFor("text")
        val divider=createRefFor("divider")
        constrain(image){
            linkTo(top=text.top,bottom=parent.bottom, bias = 0.2f, bottomMargin = 10.dp)
            linkTo(start=parent.start,end=parent.end)
            visibility= Visibility.Visible

        }
        constrain(text){
            start.linkTo(startGuideLine)
            end.linkTo(endGuideLine)
            top.linkTo(topGuideLine)
            width = Dimension.fillToConstraints

        }
        constrain(divider){
            top.linkTo(image.bottom, goneMargin = 300.dp)
        }
    }

@Composable
@Preview(showBackground = true)
fun ConstraintSet(){
    BoxWithConstraints {
        val constraints= constrains1(20.dp)
        ConstraintLayout(modifier = Modifier.fillMaxSize(), constraintSet = constraints) {
            Image(painterResource(id = R.drawable.sunrise ) , contentDescription = null,
                modifier = Modifier.layoutId("image")
            )
            Text(textAlign = TextAlign.Center,text = "Hola", modifier = Modifier
                .padding(top = 0.dp)
                .background(Color.Red)
                .layoutId("text")
            )
            Divider(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .layoutId("divider")
                , color = Color.Red)

        }
    }


}



