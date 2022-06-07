package com.example.composelesson


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelesson.ui.theme.ComposeLesson1Theme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button

class MainActivityOLD : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLesson1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //Greeting("Android")
                    ScrollableColumnSample()
                }
            }
        }
    }
}

@Preview(widthDp = 300, heightDp = 600)
@Composable
fun ButtonSample() {
    val counter = remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Text(
            text = "Counter value: ${counter.value}",
            style = MaterialTheme.typography.h4,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = {
                counter.value++
            },
        ) {
            Text("Increment", color = Color.White)
        }
    }
}

@Preview(widthDp = 320, heightDp = 300)
@Composable
fun LazyColumnSample() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        items(100) { position ->

            println("Build item at position $position")

            Row {
                MyListItem(
                    position = position,
                    color = Color.Gray,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
 //   @Preview(widthDp = 320, heightDp = 50)
    @Composable
    fun MyListItem(
        position: Int = 0,
        color: Color = Color.Gray,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.9f)
                .padding(4.dp)
                .height(42.dp)
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1.0f, matchHeightConstraintsFirst = true)
                    .clip(CircleShape)
                    .background(color)
            ) {
                Text(
                    text = position.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.6f)
                        .height(8.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color)
                )
            }
        }
    }

@Preview(widthDp = 320, heightDp = 600)
@Composable
fun ScrollableColumnSample() {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .background(Color.White),
    ) {
        repeat(50) { position ->

            println("Build item at position $position")

            Row {
                MyListItem(
                    position = position,
                    color = Color.Gray,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}


@Preview(widthDp = 180, heightDp = 200)
@Composable
fun ColumnSample() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.9f)
                    .height(36.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(Color.Gray)
            ){
                Text(text = "Hello!",
                     modifier = Modifier
                         .background(Color.Red)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview
@Composable
fun BoxSample() {
    Box(
        modifier = Modifier
            .background(Color.Red)
            .size(200.dp)
            .padding(32.dp)
            .background(Color.Green)
            .padding(32.dp)
            .background(Color.Blue)
    )
}

@Preview
@Composable
fun HelloCompose(
    title: String = "Hello compose",
    content: String = "Text from Jetpack compose",
) {
    Text(text = title)
    Text(text = content)
}