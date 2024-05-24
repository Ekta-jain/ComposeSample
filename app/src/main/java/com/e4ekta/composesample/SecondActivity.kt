package com.e4ekta.composesample

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.e4ekta.composesample.ui.theme.ComposeSampleTheme

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
//            ComposeSampleTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize()
//                    .background(Color.Green)
//                ) {
//                    Greeting(name = "Ekta")
//                }
//            }

            SecondPage()
        }
    }


    @Composable
    fun Greeting(name: String) {
        Text(text = "Hiiello $name!",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.size(18.dp))
    }
    @Preview
    @Composable
    fun NotificationScreen() {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(1f)
        ) {
            NotificationCounter()
        }
    }
    
    
    @Composable
    fun NotificationCounter(){
        var count = remember{ mutableStateOf(0) }
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "You have sent ${count.value} notifications")
            Button(onClick = {
                count.value++
                Log.i("stateCheck","Button Clicked")
            }) {
                Text(text = "Send Notification")
            }
            
        }
    }

    @Composable
    fun SecondPage() {
        Column(
            modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight()
                .fillMaxWidth()
                // .padding(top = 50.dp)
                .border(5.dp, color = Color.Magenta)
                .padding(15.dp)
                .border(5.dp, color = Color.Blue)
                .padding(25.dp)
                .border(5.dp, color = Color.Red)
            // .width(800.dp)
            // .requiredWidth(600.dp)
        ) {
            Text("Hello", modifier = Modifier.offset(50.dp, 20.dp))
            // Spacer(modifier = Modifier.height(50.dp))
            Text("world")

        }

    }

}