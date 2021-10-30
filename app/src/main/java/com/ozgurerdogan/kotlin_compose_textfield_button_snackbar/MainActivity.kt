package com.ozgurerdogan.kotlin_compose_textfield_button_snackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozgurerdogan.kotlin_compose_textfield_button_snackbar.ui.theme.Kotlin_Compose_TextField_Button_SnackbarTheme
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            SimpleAnimation()
        }
    }
}

@Composable
fun SimpleAnimation() {
    var sizeState by remember{
        mutableStateOf(200.dp)
    }

    val size by animateDpAsState(
        targetValue = sizeState,

        tween(
            durationMillis = 1000
        )
        /*
        keyframes {
            durationMillis=5000
            sizeState at 0 with LinearEasing
            sizeState * 1.5f at 1000 with FastOutLinearInEasing
            sizeState * 2f at 5000
        }

         */
        /*
        spring(
            dampingRatio = Spring.DampingRatioHighBouncy
        )

         */
        /*
        tween(
            durationMillis = 1000,
            delayMillis = 1000,
            easing = LinearEasing
        )

         */
    )

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = {sizeState+=50.dp}) {
            Text(text = "Increase Size")

        }
    }
}




@Composable
fun ListLazyColumn() {
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        itemsIndexed(
            listOf("This","Is","Jetpack","Compose")
        ){index: Int, item: String ->
            Text(
                text=item,
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 30.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 18.sp,
                    color=Color.Green,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }

}

@Composable
fun ScaffoldLearn() {


    val scaffoldState = rememberScaffoldState()
    var textValue by remember{
        mutableStateOf("")
    }
    val scope= rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ){
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = textValue,
                onValueChange = {
                    textValue=it

                },
                label = { Text(text = "Enter Name")},
                singleLine = true,
                modifier=Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "Hello $textValue",actionLabel = "Click",SnackbarDuration.Short
                        )
                    }

                },


                ) {
                Text(text="Click")

            }

        }

    }


}