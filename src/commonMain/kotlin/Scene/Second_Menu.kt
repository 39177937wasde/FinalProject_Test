package Scene

import PlayScreen
import SharedData
import com.soywiz.klock.milliseconds
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.resized
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Anchor
import com.soywiz.korma.geom.ScaleMode

class Second_Menu() : Scene() {
    override suspend fun Container.sceneInit() {
        lateinit var ook:Image
        lateinit var closee:Image
        val background= resourcesVfs["background01.png"].readBitmap()
        image(background)
        val ask=text("Select Your Character", 30.0,Colors.BLACK).xy(scaledWidth/2.5,scaledHeight/4)
        //val ask2=text("width:${height} scaled:${scaledHeight}", 30.0,Colors.BLACK).xy(scaledWidth/2.5,scaledHeight/4+100)
        lateinit var go:String
        val character1= resourcesVfs["pinkman_idle.png"].readBitmap()
        val character2= resourcesVfs["frogman_idle.png"].readBitmap()
        val character3= resourcesVfs["maskman_idle.png"].readBitmap()
        val character4= resourcesVfs["VRman_idle.png"].readBitmap()
        val character1_idle = SpriteAnimation(
                spriteMap = character1,
                spriteWidth = 128,
                spriteHeight = 128,
                marginLeft = 0,
                marginTop = 0,
                columns = 11,
                rows = 1,
                offsetBetweenColumns = 0,
                offsetBetweenRows = 0
        )
        val character2_idle = SpriteAnimation(
                spriteMap = character2,
                spriteWidth = 128,
                spriteHeight = 128,
                marginLeft = 0,
                marginTop = 0,
                columns = 11,
                rows = 1,
                offsetBetweenColumns = 0,
                offsetBetweenRows = 0
        )
        val character3_idle = SpriteAnimation(
                spriteMap = character3,
                spriteWidth = 128,
                spriteHeight = 128,
                marginLeft = 0,
                marginTop = 0,
                columns = 11,
                rows = 1,
                offsetBetweenColumns = 0,
                offsetBetweenRows = 0
        )
        val character4_idle = SpriteAnimation(
                spriteMap = character4,
                spriteWidth = 128,
                spriteHeight = 128,
                marginLeft = 0,
                marginTop = 0,
                columns = 11,
                rows = 1,
                offsetBetweenColumns = 0,
                offsetBetweenRows = 0
        )
        val pinkman: Sprite = sprite(character1_idle).xy(scaledWidth*0.75/5, 625.0)
        pinkman.playAnimationLooped(spriteDisplayTime = 80.milliseconds)
        val frogman: Sprite = sprite(character2_idle).xy(scaledWidth*1.75/5, 625.0)
        frogman.playAnimationLooped(spriteDisplayTime = 80.milliseconds)
        val maskman: Sprite = sprite(character3_idle).xy(scaledWidth*2.75/5, 625.0)
        maskman.playAnimationLooped(spriteDisplayTime = 80.milliseconds)
        val VRman: Sprite = sprite(character4_idle).xy(scaledWidth*3.75/5, 625.0)
        VRman.playAnimationLooped(spriteDisplayTime = 80.milliseconds)
        val ok= resourcesVfs["ok.png"].readBitmap().resized(100,100, ScaleMode.FIT, Anchor.CENTER,native=true)
        val close=resourcesVfs["close.png"].readBitmap().resized(100,100, ScaleMode.FIT, Anchor.CENTER,native=true)
        pinkman.onClick {
            ook=image(ok).xy(width - 100, height - 350)
            closee=image(close).xy(0.0, height - 350)
                closee.onClick {
                    sceneContainer.changeTo<Second_Menu>()
                }
            ook.onClick {
                SharedData.choose=1
                sceneContainer.changeTo<Select_map>()

            }
        }

        frogman.onClick{
            ook=image(ok).xy(width - 100, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                sceneContainer.changeTo<Second_Menu>()
            }
            ook.onClick {
                SharedData.choose=2
                sceneContainer.changeTo<Select_map>()

            }
        }
        maskman.onClick{
            ook=image(ok).xy(width - 100, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                sceneContainer.changeTo<Second_Menu>()
            }
            ook.onClick {
                SharedData.choose=3
                sceneContainer.changeTo<Select_map>()
            }
        }
        VRman.onClick{
            ook=image(ok).xy(width - 100, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                sceneContainer.changeTo<Second_Menu>()
            }
            ook.onClick {
                SharedData.choose=4
                sceneContainer.changeTo<Select_map>()
            }
        }
        //go.onClick{}
    }
}
