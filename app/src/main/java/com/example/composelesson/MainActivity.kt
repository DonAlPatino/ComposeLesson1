package com.example.composelesson


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import com.example.composelesson.ui.theme.ComposeLesson1Theme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLesson1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //Greeting("Android")
                    ScreenViewViewModelSample()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun ScreenViewViewModelSample(viewModel: SocialNetworksListViewModel = SocialNetworksListViewModel() )
{
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.social_networks_list_title)) },
            )
        },
    ) {
        val socialNetworksState = viewModel.state.collectAsState()

        val allSocialNetworks = socialNetworksState.value.allSocialNetworks
        val favourite = socialNetworksState.value.favourite

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            allSocialNetworks.forEach {
                ListItem(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                    ) {
                        viewModel.setFavourite(it)
                    },
                    icon = {
                        SocialNetworkIcon(
                            text = it.name.take(1),
                            backgroundColor = Color(it.backgroundColorHex),
                        )
                    },
                    text = {
                        Text(it.name)
                    },
                    secondaryText = {
                        Text(it.url)
                    },
                    trailing = {
                        if (it == favourite) {
                            FavouriteIcon()
                        }
                    }
                )
            }
        }
    }
}

/*@Composable
private fun FavouriteIcon() {
    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = stringResource(R.string.favourite),
        tint = Color.Red,
    )
}*/

@Preview
@Composable
private fun FavouriteIcon(
    visible: Boolean = true,
) {
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing,
        )
    )

    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = stringResource(R.string.favourite),
        tint = Color.Red,
        modifier = Modifier.scale(scale),
    )
}

@Composable
private fun SocialNetworkIcon(
    text: String = "A",
    backgroundColor: Color,
) {
    Box(
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                shadow = Shadow(
                    color = Color.DarkGray,
                    offset = Offset(6f, 4f),
                    blurRadius = 6f,
                )
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLesson1Theme {
        Greeting("Android")
    }
}