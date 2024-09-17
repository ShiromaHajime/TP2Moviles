package com.example.tp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp2.ui.theme.TP2Theme
import kotlin.random.Random
import androidx.compose.ui.platform.LocalContext

class Juego : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JuegoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

fun generarNumeroAleatorio(): Int {
    return Random.nextInt(1, 6) // Cambiado a 1-5 inclusive
}

@Composable
fun JuegoScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current  // Obtenemos el contexto

    var numeroAleatorio by remember { mutableStateOf(generarNumeroAleatorio()) }
    var puntaje by remember { mutableStateOf(0) }
    var perdidas by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Adiviná el número")

        Text(
            text = "Puntaje: $puntaje",
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Número generado: $numeroAleatorio",
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Perdidas seguidas: $perdidas",
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            numeroAleatorio = generarNumeroAleatorio()
        }) {
            Text(text = "Regenerar número")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(5) { index ->
                Button(
                    onClick = {
                        if (index + 1 == numeroAleatorio) {
                            perdidas = 0
                            puntaje += 10
                            numeroAleatorio = generarNumeroAleatorio()
                        } else {
                            if (perdidas == 4) {
                                val intent = Intent(context, Resultado::class.java).apply {
                                    putExtra("PUNTAJE_FINAL", puntaje)
                                }
                                context.startActivity(intent)
                            }
                            perdidas += 1
                        }
                    },
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(text = "${index + 1}")
                }
            }
        }
    }
}
