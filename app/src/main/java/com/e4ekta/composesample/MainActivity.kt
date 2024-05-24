package com.e4ekta.composesample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //   NotificationScreen()
             FirstPage()
            //  HasSideEffect()
            //we need sideEffect only on Controller manner,we can use sideeffect handler - Launched
            // ListComposable()

            //Example 2
            //  Counter()

            //Example 3
            //LaunchEffectComposable()
        }
    }

    /*TODO : there are two ways to run side-Effect
    *  1- Launched Effect
    *  2- RememberCoroutineScope */

    //Our scope is bound to composable method, once Composable(think as VM) removes our scope( VM scope)
    //would also remove

    @Composable
    fun Counter() {
        var count = remember { mutableStateOf(0) }
        var key = count.value % 3 == 0 //jab bhi key ki value true se false or false se true hogi,
        //means key me badlav hoga us waqt log print hoga

        //0%3 == 0 true --0
        //1%3 ==0 false ---1
        //2%3 ==0 false (2 print nahi hoga)
        //3%3==0 true --3 as value changed to true, so it will print


        LaunchedEffect(key1 = key) {
            Log.d("Counter", "Current count: ${count.value}")
        }
        Button(onClick = { count.value++ }) {
            Text("Increment count")
        }
    }

    @Composable
    fun LaunchEffectComposable() {
        val counterNew = remember { mutableStateOf(0) }

        LaunchedEffect(key1 = Unit) {
            Log.d("LaunchEffectComposable", "Started...")
            try {
                for (i in 1..10) {
                    counterNew.value++
                    delay(1000)
                }
            } catch (e: Exception) {
                Log.d("LaunchEffectComposable", "Exception- ${e.message.toString()}")
            }
        }

        var text = "Counter is running ${counterNew.value}"
        if(counterNew.value == 10){
            text = "Counter stopped"
        }
        Text(text = text)
    }

        @Composable
        fun ListComposable() {
            val categoryState = remember { mutableStateOf(emptyList<String>()) }
            //TODO: CASE 1 categoryState.value = fetchCategories() //assuming FetchCategories doing network call
            //there is 2 problem in that scenario, we don't know bcz of recomposition,
            //How much time that ListComposable would call, whenever ListComposable function call,
            // each time that network request would call

            /*P2 - if fetchCateories function taking time, Our UI won't render untill we get API response*/


            //TODO:CASE 2 we use LauchedSideeffect for doing network call at once
            //TODO Another Benfits of handlers : Coroutine Scope starts krte hai so Work on seprate thread
            LaunchedEffect(key1 = Unit) {
                // now ListComposable will call multiple time but our fetchCategories method would call only once
                // when key goes update

                categoryState.value = fetchCategories()
            }
            LazyColumn {
                items(categoryState.value) {
                    Text(text = it)
                }

            }
        }

        private fun fetchCategories(): List<String> {

            return listOf("one", "two", "three")
        }


        ///Learn about side effect also
        var counter = 1

        /*todo: that counter variable will
       increase whenever that hasSideEffect method would call.
       if that counter consume by any other method then would would be unpredictable */
        @Composable
        fun HasSideEffect() {
            counter++
            Text(text = "Cheezy code")
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
        fun NotificationCounter() {
            var count = rememberSaveable { mutableStateOf(0) }
            /* rememberSaveable - store the state and when orientation got changed
        * Remember --> It helps to store state
        *
       */
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "You have sent ${count.value} notifications")
                Button(onClick = {
                    count.value++
                    Log.i("stateCheck", "Button Clicked")
                }) {
                    Text(text = "Send Notification")
                }

            }
        }


        @Composable
        fun FirstPage() {
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
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Yellow)
                )

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .size(100.dp)
                        .background(Color.Red)
                )

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .requiredSizeIn(100.dp)
                        .background(Color.Blue)
                )

                val mContext = LocalContext.current
                Button(
                    onClick = {
                        mContext.startActivity(Intent(mContext, SecondActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58)),
                ) {
                    Text("Go to Second Activity", color = Color.White)
                }


                val painter = painterResource(id = R.drawable.ic_shoes_1)
                val description = "kermit playing in the snow"
                val title = "description"
                ImageCard(painter = painter, contentDescription = description, title = title)
//            var studentList = getAllStudent()
//            LazyColumn( ){
//                items(items = studentList){
//                        st -> StudentEntityView(student = st)
//                }
//            }
            }

        }


        @Composable
        fun StudentEntityView(student: Student) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color.Yellow),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                //Text("", text = ""+student.name, fontSize = 20.dp, modifier = Modifier.padding(5.dp))
                Text(student.name, modifier = Modifier.padding(5.dp))
                Text(student.schoolName, modifier = Modifier.padding(5.dp))
            }
        }

        fun getAllStudent(): List<Student> {
            return listOf<Student>(
                Student("ekta", "abce"),
                Student("ekta2", "abce"),
                Student("ekta3", "abce"),
                Student("ekta4", "abce"),
                Student("ekta5", "abce"),

                )
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
        Text(
            text = "Hiiello $name!",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.size(18.dp)
        )
    }
// All view and widgets are composable function here
//Composable function starts with Capital letter

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Greeting("Android")
    }

    @Composable
    fun ImageCard(
        painter: Painter,
        contentDescription: String,
        title: String,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(text = title, style = TextStyle(color = Color.Blue, fontSize = 16.sp))
                }
            }
        }
    }