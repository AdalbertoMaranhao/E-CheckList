package com.example.e_checklist

import android.graphics.Bitmap
import android.graphics.BitmapFactory


var NotaGlobal = 0
var ErrosGlobal = ""
var AcertosGlobal = ""
var imgGlobal: Bitmap? = null
var text = ""


var currentPath: String? = null
val TAKE_PICTURE = 1
var img: Bitmap? = BitmapFactory.decodeFile(currentPath)


var textR = ""

var umP = ""
var doisP = ""
var tresP = ""
var quatroP = ""
var cincoP = ""
var seisP = ""
var seteP = ""
var oitoP = ""
var noveP = ""
var dezP = ""

val A = "BCDE"
val B = "ACDE"
val C = "ABDE"
val D = "ABCE"
val E = "ABCD"