package com.example.news

object ColorPicker {

    val colors = arrayOf(
        "#000000",
        "#FFE4E1",
        "#800000",
        "#191970",
        "#00CED1",
        "#008080",
        "#BDB76B",
        "#FF4500",
        "#8B0000",
        "#4B0082",
        "#C71585"
    )

    var colorIndex = 1

    fun getColor():String{
        return colors[colorIndex++ % colors.size]
    }

}