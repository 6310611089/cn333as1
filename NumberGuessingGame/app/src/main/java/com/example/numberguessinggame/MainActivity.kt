package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberguessinggame.ui.theme.NumberGuessingGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NumberGuessingGame()
                }
            }
        }
    }
}

@Composable
fun NumberGuessingGame() {
    var answer by remember { mutableStateOf(Random.nextInt(1, 1001)) }
    var guess by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var round by remember { mutableStateOf(1) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.game_description),
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 15.dp)
        )
        Spacer(Modifier.height(200.dp))
        TextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text(stringResource(R.string.your_guess)) },
            textStyle = TextStyle(fontSize = 18.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            )
        )
        Spacer(Modifier.height(100.dp))
        Text(
            text = message,
            style = TextStyle(fontSize = 22.sp, color = Color.Gray),
            modifier = Modifier
                .padding(vertical = 8.dp),
            )
        Button(
            onClick = {
                val guessNum = guess.toIntOrNull()
                if (guessNum != null) {
                    if (guessNum < answer) {
                        message = "Hint: It's higher!"
                        guess = ""
                        round++
                    } else if (guessNum > answer) {
                        message = "Hint: It's lower!"
                        guess = ""
                        round++
                    } else {
                        message = "Correct! You won in $round round(s)"
                        round = 0
                        answer = Random.nextInt(1, 1001)
                    }
                }
            },
            modifier = Modifier.padding(horizontal = 8.dp),

        ) {
            Text(stringResource(R.string.play_again))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNumberGuessingGame() {
    NumberGuessingGameTheme{
        NumberGuessingGame()
    }
}
