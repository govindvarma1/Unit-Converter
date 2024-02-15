package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter();
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    val inputValue = remember {
        mutableStateOf("")
    }
    val outputValue = remember {
        mutableStateOf("0")
    }
    val inputUnit = remember {
        mutableStateOf("Meters")
    }
    val outputUnit = remember {
        mutableStateOf("Meters")
    }
    val iExpanded = remember {
        mutableStateOf(false)
    }
    val oExpanded = remember {
        mutableStateOf(false)
    }
    val iconversionFactor = remember {
        mutableStateOf(1.00)
    }
    val oconversionFactor = remember {
        mutableStateOf(1.00)
    }

    fun convertUnits() {
        //?: elvis operator
        val inputValueDouble = inputValue.value.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * iconversionFactor.value * 100.00 / oconversionFactor.value).roundToInt() / 100.00;
        outputValue.value = result.toString();
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge
        );
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue.value, onValueChange = {
            inputValue.value = it;
            convertUnits()
        },
            label = { Text(text = "Enter Value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = {
                    iExpanded.value = true
                }) {
                    Text(text = "${inputUnit.value}")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(
                    expanded = iExpanded.value,
                    onDismissRequest = { iExpanded.value = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        inputUnit.value = "Centimeters"
                        iExpanded.value = false
                        iconversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            inputUnit.value = "Meters"
                            iExpanded.value = false
                            iconversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            inputUnit.value = "Feet"
                            iExpanded.value = false
                            iconversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            inputUnit.value = "Millimeters"
                            iExpanded.value = false
                            iconversionFactor.value = 0.001
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {
                    oExpanded.value = true
                }) {
                    Text(text = "${outputUnit.value}")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(
                    expanded = oExpanded.value,
                    onDismissRequest = { oExpanded.value = false }) {

                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            outputUnit.value = "Centimeters"
                            oExpanded.value = false
                            oconversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            outputUnit.value = "Meters"
                            oExpanded.value = false
                            oconversionFactor.value = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            outputUnit.value = "Feet"
                            oExpanded.value = false
                            oconversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            outputUnit.value = "Millimeters"
                            oExpanded.value = false
                            oconversionFactor.value = 0.001
                            convertUnits()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Result: ${outputValue.value} ${outputUnit.value}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter();
}
