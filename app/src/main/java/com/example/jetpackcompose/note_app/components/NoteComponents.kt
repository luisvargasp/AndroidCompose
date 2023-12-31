package com.example.jetpackcompose.note_app.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(modifier: Modifier=Modifier,text:String,label:String,maxLines:Int=1,

                  onTextChange:(String)->Unit,
                  onImeiAction:()->Unit={},
                  ){
    val keyboardController=LocalSoftwareKeyboardController.current
    TextField(modifier=modifier,value = text, onValueChange =onTextChange, colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent) ,
        maxLines=maxLines, label = {Text(text=label)}, keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(onDone = {onImeiAction()
            keyboardController?.hide()

        })

    )

}
@Composable
fun NoteButton(modifier: Modifier=Modifier,
               text: String,
               onClick:()->Unit,
               enabled:Boolean=true

               ){
    Button(onClick = { onClick()}, shape = CircleShape, enabled = enabled,
        modifier = modifier) {
        Text(text = text)


    }



}