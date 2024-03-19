package com.example.figmatojsongenerator.data.remote.mappers

import android.util.Log
import com.example.figmatojsongenerator.data.remote.dto.Children
import com.example.figmatojsongenerator.data.remote.dto.FigmaResponseDto

import com.example.figmatojsongenerator.domain.model.Properties
import com.example.figmatojsongenerator.domain.model.ScreenData
import com.example.figmatojsongenerator.domain.model.ScreenElement
import com.example.figmatojsongenerator.presentation.utils.rgbaToHex

class DtoToDomainMapping(val response: FigmaResponseDto) {


    val screenData : ScreenData = ScreenData()

    fun mapData(screenName : String?=null) {


        if (response.document?.children?.get(0)?.type.equals("canvas", true)) {
            response.document?.children?.get(0)?.let {
                it.children?.forEach {
                    if (it.type.equals("Frame", true) && it.name.equals(screenName, true)) {
                        it.children?.forEach {
                            if(it.type.equals("frame",true)){
                                signUpData(it , it.name)
                            }

                        }
                    }
                }
            }
        }
    }

    private fun signUpData(signUpChild: Children , name : String ?=null) {
        try {
            signUpChild.children?.forEach {
                if (it.type.equals("TEXT", true)) {
                    screenData.nodes.text.add(
                        ScreenElement(
                            type = it.type,
                            property = Properties(
                                width = it.absoluteBoundingBox?.width,
                                height = it.absoluteBoundingBox?.height,
                                text = it.name,
                                backgroundColorType = it.fills?.getOrNull(0)?.type,
                                style = it.style,
                                textColor = it.fills?.get(0)?.color?.let {
                                    rgbaToHex(it.r, it.g, it.b, it.a)
                                }

                            )
                        )
                    )
                } else if (it.type.equals("Rectangle", true) && name.equals("button", true)) {
                    screenData.nodes.button.add(
                        ScreenElement(
                            type = it.type,
                            property = Properties(
                                width = it.absoluteBoundingBox?.width,
                                height = it.absoluteBoundingBox?.height,
                                style = it.style,
                                backgroundColorType = it.fills?.get(0)?.type,
                                backgroundColor = it.fills?.get(0)?.color?.let {
                                    rgbaToHex(it.r, it.g, it.b, it.a)
                                },
                                text = it.children?.get(0)?.name
                            ),
                        )
                    )
                } else if(it.name.equals("Input:EditText",true)){
                    screenData.nodes.editext.add(
                        ScreenElement().copy().apply {
                            type = it.name
                            property = Properties().apply {
                                width = it.absoluteBoundingBox?.width
                                height = it.absoluteBoundingBox?.height
                                backgroundColor = it.fills?.getOrNull(0)?.color?.let {
                                    rgbaToHex(it.r,it.g,it.b,it.a)
                                }
                                it.children?.forEach { childrntNam ->
                                    if(childrntNam.type.equals("text",true)){
                                        hintText = childrntNam.name
                                        hintColor = childrntNam.fills?.getOrNull(0)?.color?.let {
                                            rgbaToHex(it.r,it.g,it.b,it.a)
                                        }
                                        style = childrntNam.style
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }catch (e:Exception) {
            Log.d("TAG", "signUpData: ${e.message}")
        }
    }
}