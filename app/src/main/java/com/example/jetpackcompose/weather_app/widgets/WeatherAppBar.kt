package com.example.jetpackcompose.weather_app.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcompose.weather_app.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(title:String="Lima , Peru",
                  icon:ImageVector?=null,
                  isMainScreen:Boolean=true,
                  elevation:Dp=10.dp,
                  navController: NavController,
                  onAddActionClicked:()->Unit={},
                  onButtonClicked:()->Unit = {}

){
    val showDialog= remember {
        mutableStateOf(false)
    }
    if(showDialog.value){
        ShowSettingDropDownMenu(showDialog=showDialog,navController)
    }

    CenterAlignedTopAppBar(title = {
        Text(text = title, color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
        )

    },   colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray),
        actions = {
                  if(isMainScreen){
                      IconButton(onClick = {}) {
                          Icon(imageVector = Icons.Default.Search, contentDescription =null )
                          
                      }
                      IconButton(onClick = {

                          showDialog.value=showDialog.value.not()
                      }) {
                          Icon(imageVector = Icons.Rounded.MoreVert, contentDescription =null )

                      }
                  }


        }, navigationIcon ={
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription =null , tint = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.clickable {
                    onButtonClicked()
                })



        }, modifier = Modifier
            .padding(6.dp)
            .shadow(
                elevation = elevation, spotColor = Color.Blue,
                shape = RoundedCornerShape(10.dp)
            )
    )

}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
        val expanded= remember {
            mutableStateOf(true)
        }
    val items= listOf("About","Favourites","Settings")
        Column (modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)){

            DropdownMenu(expanded = expanded.value, onDismissRequest = {
                expanded.value=false

            }, modifier = Modifier
                .width(140.dp)
                .background(Color.White)) {
                items.forEachIndexed{index,text->
                    DropdownMenuItem(text = {
                        Row {
                            Icon(imageVector = when(text){
                                        "About"->Icons.Default.Info
                                        "Favourites"->Icons.Default.FavoriteBorder
                                        "Settings"->Icons.Default.Settings


                                else -> Icons.Default.Settings
                            } , contentDescription =null, tint = Color.LightGray )
                            Text(
                                text = text,
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        when(text){
                                            "About"->WeatherScreens.AboutScreen.name
                                            "Favourites"->WeatherScreens.FavoriteScreen.name
                                            "Settings"->WeatherScreens.SettingsScreen.name
                                            else -> WeatherScreens.AboutScreen.name
                                        }
                                    )


                                },
                                fontWeight = FontWeight.W300
                            )


                        }
                    }, onClick = {
                        expanded.value=false
                        showDialog.value=false


                    })


                }


                
            }

        }

}
