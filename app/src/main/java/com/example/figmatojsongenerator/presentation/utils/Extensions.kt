package com.example.figmatojsongenerator.presentation.utils

fun rgbaToHex(r: Double, g: Double, b: Double, a: Double): String {
    // Convert each component to hexadecimal format
    val redHex = (r * 255).toInt().coerceIn(0, 255).toString(16).padStart(2, '0')
    val greenHex = (g * 255).toInt().coerceIn(0, 255).toString(16).padStart(2, '0')
    val blueHex = (b * 255).toInt().coerceIn(0, 255).toString(16).padStart(2, '0')
    val alphaHex = (a * 255).toInt().coerceIn(0, 255).toString(16).padStart(2, '0')

    // Concatenate the components into a hex color code
    return "#$redHex$greenHex$blueHex$alphaHex"
}