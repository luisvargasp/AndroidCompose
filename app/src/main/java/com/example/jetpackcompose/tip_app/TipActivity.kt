package com.example.jetpackcompose.tip_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.tip_app.components.CustomInputField
import com.example.jetpackcompose.tip_app.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.tip_app.widgets.RoundIconButton
import kotlinx.coroutines.flow.callbackFlow

class TipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {

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
        TotalPerPersonCard()
    }
}

@Composable
fun TotalPerPersonCard(value:Double=140.00){
    Card (modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth()
        .height(150.dp)

        , elevation = CardDefaults.cardElevation( defaultElevation = 0.dp),
        shape = RoundedCornerShape(corner = CornerSize(
            12.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE9D7F7) )

    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Total per Person", style = TextStyle(color = Color.Black), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            val total="%.2f".format(value)
            Text(text = "$ $total",style = TextStyle(color = Color.Black), fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
        }

    }

}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun MainContent(){

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        BillForm(onValueChange = {billAmount->


        })

    }

}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier=Modifier,

             onValueChange:(String)->Unit={})
    {
        val totalBillState= rememberSaveable{
            mutableStateOf("")
        }
        val splitBy=rememberSaveable{
            mutableIntStateOf(1)
        }

        val sliderPositionState = rememberSaveable {
            mutableFloatStateOf(0f)

        }
        val tipPercentage= rememberSaveable(sliderPositionState.floatValue) {
            "%.2f".format(sliderPositionState.floatValue)
        }

        val tipAmountState= rememberSaveable {
            mutableDoubleStateOf(0.0)

        }
        val totalPerPersonState= rememberSaveable {
            mutableDoubleStateOf(0.0)
        }
        val validState=rememberSaveable(totalBillState.value){
            try {
               val value= totalBillState.value.trim()
                value.toDouble()
                tipAmountState.doubleValue="%.2f".format(totalBillState.value.toDouble()*tipPercentage.toFloat()/100.0).toDouble()

                totalPerPersonState.doubleValue=calculateTotalPerPerson(totalBillState.value.toDouble(),splitBy.intValue,tipPercentage.toFloat())
                true

            }catch (e:Exception){
                false
            }
        }
        val keyboardController=LocalSoftwareKeyboardController.current
        TotalPerPersonCard(totalPerPersonState.doubleValue)

        Surface(modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ){
            Column(modifier=Modifier.padding(6.dp), verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start) {
                CustomInputField(valueState =totalBillState , labelId = "Enter Bill", enabled = true, isSingleLine = true,
                    onAction = KeyboardActions{
                        if(!validState) return@KeyboardActions
                        onValueChange(totalBillState.value.trim())


                        keyboardController?.hide()
                    }
                )
                if(validState){
                    Row (modifier= Modifier.padding(3.dp),
                        horizontalArrangement = Arrangement.Start
                    ){
                        Text(text = "Split",modifier=Modifier.align(alignment = Alignment.CenterVertically))
                        Spacer(modifier = Modifier.width(120.dp))
                        Row(modifier=Modifier.padding(horizontal = 3.dp),
                            horizontalArrangement = Arrangement.End){
                            RoundIconButton(imageVector = Icons.Default.Remove, onClick = {
                                if(splitBy.intValue==1) return@RoundIconButton
                                splitBy.intValue=splitBy.intValue-1
                                totalPerPersonState.doubleValue=calculateTotalPerPerson(totalBillState.value.toDouble(),splitBy.intValue,tipPercentage.toFloat())

                            })
                            Text(text = "${splitBy.intValue}",modifier= Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp))
                            RoundIconButton(imageVector = Icons.Default.Add, onClick = {
                                splitBy.intValue=splitBy.intValue+1
                                totalPerPersonState.doubleValue=calculateTotalPerPerson(totalBillState.value.toDouble(),splitBy.intValue,tipPercentage.toFloat())

                            })

                        }

                    }

                    //TipRow
                    Row (modifier= Modifier.padding(horizontal = 3.dp, vertical = 12.dp)){
                        Text(text = "Tip",modifier=Modifier.align(alignment = Alignment.CenterVertically))
                        Spacer(modifier = Modifier.width(200.dp))
                        Text(text = "$ ${tipAmountState.doubleValue}",modifier=Modifier.align(alignment = Alignment.CenterVertically))

                    }
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,


                        ) {
                        Text(text = "${tipPercentage}%")
                        Spacer(modifier = Modifier.height(14.dp))
                        Slider(value = sliderPositionState.floatValue, onValueChange ={
                            sliderPositionState.floatValue=it
                            tipAmountState.doubleValue="%.2f".format(totalBillState.value.toDouble()*it/100.0).toDouble()
                            totalPerPersonState.doubleValue=calculateTotalPerPerson(totalBillState.value.toDouble(),splitBy.intValue,it)

                        } , modifier=Modifier.padding(start = 16.dp, end = 16.dp)
                            , steps=99,valueRange = 0f..100f

                        )

                    }

                }else{
                    Box(){

                    }
                }
            }
        }


 }
fun calculateTotalPerPerson(totalBill:Double, splitBy:Int,tipPercentage:Float):Double
{
    val bill=totalBill*(1+(tipPercentage/100.0))
    return bill/splitBy
}