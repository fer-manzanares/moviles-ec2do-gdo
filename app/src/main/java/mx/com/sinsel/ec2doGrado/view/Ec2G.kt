package mx.com.sinsel.ec2doGrado.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.com.sinsel.ec2doGrado.ui.theme.Ec2doGradoTheme
import kotlin.math.sqrt

@Composable
fun Ec2G (){
    var coeficienteA by remember { mutableStateOf("") }
    var coeficienteB by remember { mutableStateOf("") }
    var coeficienteC by remember { mutableStateOf("") }
    var x1 by remember { mutableStateOf("") }
    var x2 by remember { mutableStateOf("") }

    var showResults by remember { mutableStateOf(false) }

    fun calcularRaices() {
        val a= coeficienteA.toDoubleOrNull() ?: 0.0
        val b = coeficienteB.toDoubleOrNull() ?: 0.0
        val c = coeficienteC.toDoubleOrNull() ?: 0.0
        val radicando = b * b - (4.0 * a * c)
        var result1 = 0.0
        var result2 = 0.0
        println("Radicando = $radicando")

        when {
            radicando < 0.0 -> {
                x1 = "La ecuación no tiene raices reales"
                x2 = ""
            }

            radicando > 0.0 -> {
                result1 = -b + (sqrt(radicando) / (2.0 * a))
                result2 = -b - (sqrt(radicando) / (2.0 * a))
                x1 = result1.toString()
                x2 = result2.toString()
            }

            else -> {
                result1 = -b
                x1 = result1.toString()
                x2 = "La ecucación solo tiene una raiz"
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Ecuaciones de 2do Grado")
        Spacer(modifier = Modifier.height(60.dp))
        Box {
            OutlinedTextField(value = coeficienteA, onValueChange ={
                coeficienteA = it
            }, label = { Text(text = "Coeficiente A:") } )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box {
            OutlinedTextField(value = coeficienteB, onValueChange ={
                coeficienteB = it
            }, label = { Text(text = "Coeficiente B:") } )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box {
            OutlinedTextField(value = coeficienteC, onValueChange ={
                coeficienteC = it
            }, label = { Text(text = "Coeficiente C:") } )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Button(onClick = {
                calcularRaices()
                showResults = true
            })
            {
                Text(text = "Raices")
                Icon(Icons.Rounded.ArrowForward, "")
            }
            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Gráfica")
            }
        }
        if (showResults){
            run {
                AlertDialog(

                    title = {
                        Text(text = "Solución de la ecuación")
                    },
                    text = {
                        Column {
                            Text(text = x1)
                            Text(text = x2)
                        }
                    },
                    onDismissRequest = {
                        showResults = false;
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showResults = false
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showResults = false
                            }
                        ) {
                            Text("Dismiss")
                        }
                    }
                )
            }
        }
    }
}
