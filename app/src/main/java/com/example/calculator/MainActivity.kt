package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.compose.BlinkingCaret
import com.example.calculator.ui.compose.PrepareUI
import com.example.calculator.ui.theme.CalculaTorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculaTorTheme {
                PrepareUI {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.15f)
                                .padding(horizontal = 4.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = "123+321",
                                style = MaterialTheme.typography.h3,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.End
                            )
                            BlinkingCaret()
                        }
                    }
                }
            }
        }
    }


}


