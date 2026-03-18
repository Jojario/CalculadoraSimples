package com.example.calculadorasimples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadorasimples.ui.theme.CalculadoraSimplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraSimplesTheme {
                Calculadora()
            }
        }
    }
}

@Composable
fun Calculadora(modifier: Modifier = Modifier) {
    var display by remember { mutableStateOf("0") }
    var operacao by remember { mutableStateOf("") }
    var primeiroNumero by remember { mutableStateOf(0.0) }
    var novoNumero by remember { mutableStateOf(true) }

    fun adicionarNumero(numero: String) {
        println(numero)
        if (novoNumero) {
            display = numero
            novoNumero = false
        } else {
            display = if (display == "0") numero else display + numero
        }
    }

    fun selecionarOperacao(op: String) {
        primeiroNumero = display.toDoubleOrNull() ?: 0.0
        operacao = op
        novoNumero = true
    }

    fun calcular() {
        val segundoNumero = display.toDoubleOrNull() ?: 0.0
        val resultado = when (operacao) {
            "+" -> primeiroNumero + segundoNumero
            "-" -> primeiroNumero - segundoNumero
            "*" -> primeiroNumero * segundoNumero
            "/" -> if (segundoNumero != 0.0) primeiroNumero / segundoNumero else 0.0
            else -> segundoNumero
        }
        display = if (resultado % 1 == 0.0) resultado.toInt().toString() else resultado.toString()
        operacao = ""
        novoNumero = true
    }

    fun limpar() {
        display = "0"
        operacao = ""
        primeiroNumero = 0.0
        novoNumero = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF2a2a2a)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Display
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1a1a1a))
                .padding(16.dp)
                .height(120.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Text(
                text = display,
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.End,

            )
        }

        // Botões - Grade 4x4
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Linha 1: 7, 8, 9, /
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotaoCalculadora(
                    texto = "7",
                    onClick = { adicionarNumero("7") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "8",
                    onClick = { adicionarNumero("8") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "9",
                    onClick = { adicionarNumero("9") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "/",
                    onClick = { selecionarOperacao("/") },
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(0xFF5B7BA3)
                )
            }

            // Linha 2: 4, 5, 6, *
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotaoCalculadora(
                    texto = "4",
                    onClick = { adicionarNumero("4") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "5",
                    onClick = { adicionarNumero("5") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "6",
                    onClick = { adicionarNumero("6") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "*",
                    onClick = { selecionarOperacao("*") },
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(0xFF5B7BA3)
                )
            }

            // Linha 3: 1, 2, 3, -
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotaoCalculadora(
                    texto = "1",
                    onClick = { adicionarNumero("1") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "2",
                    onClick = { adicionarNumero("2") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "3",
                    onClick = { adicionarNumero("3") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "-",
                    onClick = { selecionarOperacao("-") },
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(0xFF5B7BA3)
                )
            }

            // Linha 4: 0, C, =, +
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotaoCalculadora(
                    texto = "0",
                    onClick = { adicionarNumero("0") },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "C",
                    onClick = { limpar() },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "=",
                    onClick = { calcular() },
                    modifier = Modifier.weight(1f)
                )
                BotaoCalculadora(
                    texto = "+",
                    onClick = { selecionarOperacao("+") },
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(0xFF5B7BA3)
                )
            }
        }
    }
}

@Composable
fun BotaoCalculadora(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF4A6B88)
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxHeight(),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(64.dp)
    ) {
        Text(
            text = texto,
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    CalculadoraSimplesTheme {
        Calculadora()
    }
}