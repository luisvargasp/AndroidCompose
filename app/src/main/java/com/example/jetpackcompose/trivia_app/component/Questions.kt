package com.example.jetpackcompose.trivia_app.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.query
import com.example.jetpackcompose.trivia_app.model.Question
import com.example.jetpackcompose.trivia_app.screens.QuestionsViewModel
import com.example.jetpackcompose.trivia_app.util.AppColors

@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions=viewModel.data.value.data
    val questionIndex= remember {
        mutableIntStateOf(0)
    }
    if(viewModel.data.value.loading==true){
        val strokeWidth = 5.dp

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp),
                color = Color.LightGray,
                strokeWidth = strokeWidth
            )
        }
        Log.d("QuestionsData","Loading ")
    }else{
        val question = try {
            questions?.get(questionIndex.intValue)
        }catch (exception:Exception){
            null
        }
        Log.d("QuestionsData","${questions?.size}")
        if(!questions.isNullOrEmpty()){
            QuestionDisplay(question = question!!,  onNextClick ={
                  questionIndex.intValue=questionIndex.intValue+1

            } , questionIndex = questionIndex, viewModel = viewModel)
        }
    }

}
@Composable
//@Preview
fun QuestionDisplay(question: Question
                    ,questionIndex:MutableState<Int>,
                    viewModel: QuestionsViewModel,
                    onNextClick:(Int)->Unit){
    val pathEffect=PathEffect.dashPathEffect(floatArrayOf(10f,10f),0f)

    val choices = remember(question){
        question.choices.toMutableList()
    }
    val answerState= remember(question) {
        mutableStateOf<Int?>(null)

    }
    val correctAnswerState = remember (question){
        mutableStateOf<Boolean?>(null)

    }
    val updateAnswer:(Int)-> Unit = remember(question) {
        {
            answerState.value=it
            correctAnswerState.value=choices[it]==question.answer
        }

    }
    Surface (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        ,
        color =AppColors.mDarkPurple

    ){
        Column (modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){if(questionIndex.value>=3){
                            ShowProgress(questionIndex.value+1)

                         }
            QuestionTracker(counter = questionIndex.value+1, outOf = viewModel.getTotalQuestions()!!)
            DrawDottedLine(pathEffect)
            Column {
                Text(text = question.question, fontSize = 17.sp,
                    modifier= Modifier
                        .padding(6.dp)
                        .align(Alignment.Start)
                        .fillMaxHeight(0.3f),
                    fontWeight = FontWeight.Bold, lineHeight = 22.sp, color = AppColors.mOffWhite

                )

                //Choices
                choices.forEachIndexed{index, alternative ->
                    Row(modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .border(
                            width = 4.dp, brush = Brush.linearGradient(
                                colors =
                                listOf(AppColors.mOffDarkPurple, AppColors.mDarkPurple)
                            ), shape = RoundedCornerShape(15.dp)
                        )
                        .clip(
                            RoundedCornerShape(
                                topStartPercent = 50,
                                topEndPercent = 50,
                                bottomEndPercent = 50,
                                bottomStartPercent = 50
                            )
                        )
                        .background(
                            Color.Transparent
                        ), verticalAlignment = CenterVertically
                    ){
                        RadioButton(selected = (answerState.value==index), onClick = {
                            updateAnswer(index)
                        }, modifier = Modifier.padding(start = 16.dp), colors = RadioButtonDefaults.colors(selectedColor = if(correctAnswerState.value==true&&index==answerState.value) {Color.Green}  else if(correctAnswerState.value==false&&index==answerState.value) {Color.Red } else {AppColors.mOffWhite}))

                        Text(text = buildAnnotatedString {

                            withStyle(SpanStyle(fontWeight = FontWeight.Light,color=if(correctAnswerState.value==true&&index==answerState.value) {Color.Green}  else if(correctAnswerState.value==false&&index==answerState.value) {Color.Red } else {AppColors.mOffWhite}, fontSize = 17.sp)){
                                 append(alternative)



                            }
                        })


                    }

                }
                //Button
                Button(modifier= Modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally), shape = RoundedCornerShape(34.dp), colors =ButtonDefaults.buttonColors(
                        containerColor = AppColors.mLightBlue
                    ) , onClick = {
                        onNextClick(questionIndex.value)
                }) {
                    Text(text = "Next", modifier = Modifier.padding(4.dp), color = AppColors.mOffWhite, fontSize = 17.sp)
                    
                }

            }

        }



    }

}



@Composable
fun QuestionTracker(counter:Int=10,outOf:Int=100){
    Text(buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)){
            withStyle(style = SpanStyle(color = AppColors.mLightGray, fontWeight = FontWeight.Bold, fontSize = 27.sp)){
                append("Question $counter/")
            }
            withStyle(style = SpanStyle(color = AppColors.mLightGray, fontWeight = FontWeight.Light, fontSize = 14.sp)){
                append("$outOf")
            }
        }

    }, modifier = Modifier.padding(20.dp)   )

}
@Composable
fun DrawDottedLine(pathEffect: PathEffect){
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp), onDraw ={
        drawLine(color = AppColors.mLightGray, start = Offset(0f,0f),
            end= Offset(size.width,0f),pathEffect=pathEffect
        )

    } )

}
@Preview
@Composable
fun ShowProgress(score:Int=12){
    val gradient=Brush.linearGradient(listOf(Color(0xFFF95075), Color(0xFFBE6BE5)))

    val progressFactor= remember(score) {
        mutableFloatStateOf(score*0.005f)


    }
    Row(modifier= Modifier
        .padding(3.dp)
        .fillMaxWidth()
        .height(45.dp)
        .border(
            width = 4.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    AppColors.mLightPurple,
                    AppColors.mLightPurple
                )
            ),
            shape = RoundedCornerShape(34.dp)
        )
        .clip(
            RoundedCornerShape(
                topStartPercent = 50,
                topEndPercent = 50,
                bottomEndPercent = 50,
                bottomStartPercent = 50
            )
        )
        .background(Color.Transparent), verticalAlignment = Alignment.CenterVertically

    ) {
        Button(contentPadding = PaddingValues(1.dp), onClick = { },
            modifier = Modifier
                .fillMaxWidth(progressFactor.floatValue)

                .background(brush = gradient), enabled = false,
            elevation = null, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, disabledContainerColor = Color.Transparent)

        ) {
            Text(text = (score*10).toString(),modifier=Modifier.clip(shape= RoundedCornerShape(23.dp)).fillMaxHeight(0.87f).fillMaxWidth().padding(6.dp),
                color=AppColors.mOffWhite, textAlign = TextAlign.Center)
            
        }

    }
}