package Scene

import SharedData
import com.soywiz.klock.milliseconds
import com.soywiz.korau.sound.Sound
import com.soywiz.korau.sound.SoundChannel
import com.soywiz.korau.sound.infinitePlaybackTimes
import com.soywiz.korau.sound.readSound
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.triangle.pathfind.SpatialMeshFind
import kotlinx.coroutines.channels.Channel

class Second_Menu() : Scene() {
    override suspend fun Container.sceneInit() {
        lateinit var ook:Image
        lateinit var closee:Image
        //lateinit var music_off:Image
       // lateinit var music_on:Image
        val background= resourcesVfs["background01.png"].readBitmap()
        image(background)
        val ask=text("Select Your Character", 30.0,Colors.BLACK).xy(scaledWidth/2.5,scaledHeight/4)
        //val ask2=text("width:${height} scaled:${scaledHeight}", 30.0,Colors.BLACK).xy(scaledWidth/2.5,scaledHeight/4+100)
        lateinit var go:String
        var  music = resourcesVfs["test.wav"].readSound()
        var channel = music.play()
        var music_on: Image = image(resourcesVfs["music_on.png"].readBitmap()).xy(width - 108, 0.0)
        //var music_off: Image = image(resourcesVfs["music_off.png"].readBitmap()).xy(width - 108, 0.0)

        music_on.onClick{
            removeChild(music_on)
            //music_off= image(resourcesVfs["music_off.png"].readBitmap()).xy(width - 108, 0.0)
            //music_off.xy(width - 108,0.0)
            channel.stop()
            SharedData.music_isplaying=0
            }
        /*music_off.onClick {
            removeChild(music_off)
            music_on= image(resourcesVfs["music_on.png"].readBitmap()).xy(width - 108, 0.0)
            music_on.xy(width - 108,0.0)
            music = resourcesVfs["test.wav"].readSound()
            channel = music.play()
            SharedData.music_isplaying=1
        }*/

        //SharedData.test()
        val character1= resourcesVfs["pinkman_idle.png"].readBitmap()
        val character2= resourcesVfs["frogman_idle.png"].readBitmap()
        val character3= resourcesVfs["maskman_idle.png"].readBitmap()
        val character4= resourcesVfs["VRman_idle.png"].readBitmap()

        var pre= image(resourcesVfs["prew1.png"].readBitmap())
        pre.xy(0,0)
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
        var ok= resourcesVfs["ok.png"].readBitmap()
        val close=resourcesVfs["close.png"].readBitmap()
        pre.onClick {
            channel.stop()
            sceneContainer.changeTo<Menu>()
        }
        pinkman.onClick {
            ook=image(ok).xy(width - 108, height - 350)//width - 100, height - 350
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                channel.stop()
                sceneContainer.changeTo<Second_Menu>()
            }
            ook.onClick {
                SharedData.choose_player=1
                channel.stop()
                sceneContainer.changeTo<Select_map>()

            }
        }

        frogman.onClick{
            ook=image(ok).xy(width - 108, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                channel.stop()
                sceneContainer.changeTo<Second_Menu>()
            }
            ook.onClick {
                channel.stop()
                SharedData.choose_player=2
                sceneContainer.changeTo<Select_map>()

            }
        }
        maskman.onClick{
            ook=image(ok).xy(width - 108, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                channel.stop()
                sceneContainer.changeTo<Second_Menu>()
            }
            ook.onClick {
                channel.stop()
                SharedData.choose_player=3
                sceneContainer.changeTo<Select_map>()
            }
        }
        VRman.onClick{
            ook=image(ok).xy(width - 108, height - 350)
            closee=image(close).xy(0.0, height - 350)
            closee.onClick {
                channel.stop()
                sceneContainer.changeTo<Second_Menu>()
            }
            ook.onClick {
                channel.stop()
                SharedData.choose_player=4
                sceneContainer.changeTo<Select_map>()
            }

        /*music.onclick{

        } */
        }
        //go.onClick{}
    }
}
