package Scene

import PlayScreen
import SharedData
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korgw.sdl2.SDLKeyCode
import com.soywiz.korim.bitmap.resized
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.dynamic.Dyn.Companion.global
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Anchor
import com.soywiz.korma.geom.ScaleMode

class Select_map() : Scene() {

    override suspend fun Container.sceneInit() {
        lateinit var ook:Image
        lateinit var closee:Image

        val background= resourcesVfs["background01.png"].readBitmap()
        image(background)
        val bg= resourcesVfs["bg.png"].readBitmap().resized(600,600, ScaleMode.FIT, Anchor.CENTER,native=true)
        image(bg).centerOnStage()
        val ask=text("Select The Scene", 30.0, Colors.BLACK).xy(scaledWidth/2.5,scaledHeight/5.5)
        val map1= resourcesVfs["background01.png"].readBitmap().resized(150,150, ScaleMode.FIT, Anchor.CENTER,native=true)
        val map2= resourcesVfs["background02.png"].readBitmap().resized(150,150, ScaleMode.FIT, Anchor.CENTER,native=true)
        val map3= resourcesVfs["background03.png"].readBitmap().resized(150,150, ScaleMode.FIT, Anchor.CENTER,native=true)
        val map4= resourcesVfs["background04.png"].readBitmap().resized(150,150, ScaleMode.FIT, Anchor.CENTER,native=true)
        var map01=image(map1).xy(scaledWidth*1.5/5,scaledHeight/4)
        var map02=image(map2).xy(scaledWidth*2.75/5,scaledHeight/4)
        var map03=image(map3).xy(scaledWidth*1.5/5,scaledHeight*1.75/4)
        var map04=image(map4).xy(scaledWidth*2.75/5,scaledHeight*1.75/4)
        val ok= resourcesVfs["ok.png"].readBitmap().resized(100,100, ScaleMode.FIT, Anchor.CENTER,native=true)
        val close=resourcesVfs["close.png"].readBitmap().resized(100,100, ScaleMode.FIT, Anchor.CENTER,native=true)
        map01.onClick {
            ook=image(ok).xy(width - 100, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                sceneContainer.changeTo<Select_map>()
            }
            ook.onClick {
                SharedData.pick=1
                sceneContainer.changeTo<PlayScreen>()

            }
        }
        map02.onClick {
            SharedData.pick=2
            ook=image(ok).xy(width - 100, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {

                sceneContainer.changeTo<Select_map>()
            }
            ook.onClick {
                sceneContainer.changeTo<PlayScreen>()

            }
        }
        map03.onClick {
            ook=image(ok).xy(width - 100, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                sceneContainer.changeTo<Select_map>()
            }
            ook.onClick {
                SharedData.pick=3
                sceneContainer.changeTo<PlayScreen>()

            }
        }
        map04.onClick {
            ook=image(ok).xy(width - 100, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                sceneContainer.changeTo<Select_map>()
            }
            ook.onClick {
                SharedData.pick=4
                sceneContainer.changeTo<PlayScreen>()

            }
        }


    }
}
