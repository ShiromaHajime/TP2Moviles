package com.example.tp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tp2.ui.theme.TP2Theme

class Resultado : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val puntos = intent.getIntExtra("PUNTAJE_FINAL", 0)

        setContent {
            TP2Theme {
                ResultadoScreen(
                    puntos = puntos,
                    volverInicio = {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    },
                    volverAJugar = {
                        startActivity(Intent(this, Juego::class.java))
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun ResultadoScreen(
    puntos: Int,
    volverInicio: () -> Unit,
    volverAJugar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Perdiste", modifier = Modifier.padding(bottom = 16.dp))

        Text(text = "Puntos: $puntos", modifier = Modifier.padding(bottom = 32.dp))

        Button(
            onClick = volverInicio,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Volver al inicio")
        }

        Button(
            onClick = volverAJugar
        ) {
            Text(text = "Jugar otra vez")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultadoScreenPreview() {
    TP2Theme {
        ResultadoScreen(
            puntos = 1,
            volverInicio = {},
            volverAJugar = {}
        )
    }
}
