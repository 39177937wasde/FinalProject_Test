package Scene

import PlayScreen
import com.soywiz.klogger.AnsiEscape
import com.soywiz.klogger.AnsiEscape.Companion.color
import com.soywiz.klogger.Console.color
import com.soywiz.korge.component.onStageResized
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.resized
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.dynamic.KDynamic.Companion.toDouble
import com.soywiz.korio.dynamic.KDynamic.Companion.toInt
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Anchor
import com.soywiz.korma.geom.ScaleMode.Companion.FIT
import kotlin.math.round

class Menu() : Scene() {
    override suspend fun Container.sceneInit() {
        val background= resourcesVfs["title.png"].readBitmap()
        image(background)
        //var text="Run Away"
        val startbtn= resourcesVfs["play.png"].readBitmap()
        val start =image(startbtn).xy(scaledWidth/2.5,scaledHeight/2)
        start.onClick{
            sceneContainer.changeTo<Second_Menu>()
        }

    }
}
