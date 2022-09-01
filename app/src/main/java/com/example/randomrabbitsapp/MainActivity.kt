package com.example.randomrabbitsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.randomrabbitsapp.ui.theme.RandomRabbitsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


@OptIn(ExperimentalCoilApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomRabbitsAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val rabbit = viewModel.state.value.rabbit
                    val isLoading = viewModel.state.value.isLoading

                    rabbit?.let {
                        Image(
                            painter = rememberImagePainter(
                                data = rabbit.imageUrl,
                                builder = { crossfade(true) }
                            ),
                            contentDescription = rabbit.name,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            rabbit.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(rabbit.description)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(
                        onClick = { viewModel.getRandomRabbit() },
                        modifier = Modifier.align(End),
                    ) {
                        Text(text = "Another rabbit!")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if (isLoading) CircularProgressIndicator()
                }
            }
        }
    }
}
