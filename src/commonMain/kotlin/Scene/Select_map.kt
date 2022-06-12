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
        var pre= image(resourcesVfs["prew1.png"].readBitmap())
        pre.xy(0,0)
        val ask=text("Select The Scene", 30.0, Colors.BLACK).xy(scaledWidth/2.5,scaledHeight/5.5)
        val map1= resourcesVfs["pick_bg01.png"].readBitmap()
        val map2= resourcesVfs["pick_bg02.png"].readBitmap()
        val map3= resourcesVfs["pick_bg03.png"].readBitmap()
        val map4= resourcesVfs["pick_bg04.png"].readBitmap()
        var map01=image(map1).xy(scaledWidth*1.5/5,scaledHeight/4)
        var map02=image(map2).xy(scaledWidth*2.75/5,scaledHeight/4)
        var map03=image(map3).xy(scaledWidth*1.5/5,scaledHeight*1.75/4)
        var map04=image(map4).xy(scaledWidth*2.75/5,scaledHeight*1.75/4)
        val ok= resourcesVfs["ok.png"].readBitmap()
        val close=resourcesVfs["close.png"].readBitmap()
        pre.onClick {
            sceneContainer.changeTo<Second_Menu>()
        }
        map01.onClick {
            ook=image(ok).xy(width - 108, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                sceneContainer.changeTo<Select_map>()
            }
            ook.onClick {
                SharedData.pick_map=1
                sceneContainer.changeTo<PlayScreen>()

            }
        }
        map02.onClick {
            SharedData.pick_map=2
            ook=image(ok).xy(width - 108, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {

                sceneContainer.changeTo<Select_map>()
            }
            ook.onClick {
                sceneContainer.changeTo<PlayScreen>()

            }
        }
        map03.onClick {
            ook=image(ok).xy(width - 108, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                sceneContainer.changeTo<Select_map>()
            }
            ook.onClick {
                SharedData.pick_map=3
                sceneContainer.changeTo<PlayScreen>()

            }
        }
        map04.onClick {
            ook=image(ok).xy(width - 108, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                sceneContainer.changeTo<Select_map>()
            }
            ook.onClick {
                SharedData.pick_map=4
                sceneContainer.changeTo<PlayScreen>()

            }

        }


    }
}
