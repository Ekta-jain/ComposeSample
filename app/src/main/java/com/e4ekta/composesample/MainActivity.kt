package com.e4ekta.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.e4ekta.composesample.ui.theme.ComposeSampleTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier
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
                Box(modifier = Modifier
                    .size(50.dp)
                    .background(Color.Yellow))

                Box(modifier = Modifier
                    .size(50.dp)
                    .size(100.dp)
                    .background(Color.Red))

                Box(modifier = Modifier
                    .size(50.dp)
                    .requiredSizeIn(100.dp)
                    .background(Color.Blue))

            }


        }
    }
}
// Part 2 : basics on Modifier under the setContent block
//            Row(modifier = Modifier
//                .width(300.dp).fillMaxHeight()
//                .background(Color.Green),
//                horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text("Hello")
//                Text("world")
//                Text("Hello")
//
//            }
@Composable
fun Greeting(name: String) {
    Text(text = "Hiiello $name!",
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.size(18.dp))
}
// All view and widgets are composable function here
//Composable function starts with Capital letter

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting("Android")
}