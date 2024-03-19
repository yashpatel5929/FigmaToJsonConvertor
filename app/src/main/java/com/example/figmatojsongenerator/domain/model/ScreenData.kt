package com.example.figmatojsongenerator.domain.model

import com.example.figmatojsongenerator.data.remote.dto.Background
import com.example.figmatojsongenerator.data.remote.dto.Style

data class ScreenData(
    val nodes : Frames = Frames()
)

data class Frames(
    var text: MutableList<ScreenElement> = mutableListOf(),
    var button : MutableList<ScreenElement> = mutableListOf(),
    var editext : MutableList<ScreenElement> = mutableListOf()
)
data class ScreenElement(
    var type : String?=null,
    var property: Properties ?= null
)

data class Properties(
    var background: Background?=null,
    var height : Double?=null,
    var width : Double ?=null,
    var textColor : String ?= null,
    var style : Style?= null,
    var marginLeft : Int ?=null,
    var marginRight : Int ?= null,
    var buttonText : String ?= null,
    var hintColor : String?=null,
    var leftIcon : String ?=null,
    var hintText : String ?=null,
    var backgroundColorType : String ?= null,
    var text : String ?= null,
    var backgroundColor : String ?= null
)
