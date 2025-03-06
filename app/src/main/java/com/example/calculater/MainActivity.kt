package com.example.calculater

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculator()
        }
    }
}

@Composable
fun Calculator(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var num1 by remember {
        mutableStateOf("")
    }
    var num2 by remember {
        mutableStateOf("")
    }
    Column(
        modifier.padding(32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Calculator", fontSize = 24.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif)
        Spacer(modifier = modifier.height(4.dp))
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Enter First Number") },

            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.DarkGray,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = modifier.height(4.dp))
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Enter Second Number") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.DarkGray,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray
            )

            )
        Spacer(modifier = modifier.height(4.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                calculateResult(context, num1, num2, "+")
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
                ) {
                Text(text = "+")
            }
            Spacer(modifier = modifier.width(4.dp))
            Button(onClick = {
                calculateResult(context, num1, num2, "-")
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )) {
                Text(text = "-")
            }
            Spacer(modifier = modifier.width(4.dp))
            Button(onClick = {
                calculateResult(context, num1, num2, "*")
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )) {
                Text(text = "*")
            }
            Spacer(modifier = modifier.width(4.dp))
            Button(onClick = {
                calculateResult(context, num1, num2, "/")
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )) {
                Text(text = "/")
            }
        }
    }

}


fun calculateResult(
    context: android.content.Context,
    num1: String,
    num2: String,
    operation: String
) {
    try {
        val firstNumber = num1.toIntOrNull()
        val secondNumber = num2.toIntOrNull()

        if (firstNumber == null || secondNumber == null) {
            Toast.makeText(context, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> {
                if (secondNumber == 0) {
                    Toast.makeText(context, "Cann't divide by 0", Toast.LENGTH_SHORT).show()
                    return
                } else {
                    firstNumber / secondNumber
                }
            }

            else -> 0
        }

        Toast.makeText(context, "Result is : $result", Toast.LENGTH_SHORT).show()

    } catch (e: Exception) {
        Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CalculatorPreview() {
    Calculator()
}