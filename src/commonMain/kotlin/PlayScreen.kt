import Scene.Select_map

import com.soywiz.klock.*
import com.soywiz.klogger.BaseConsole
import com.soywiz.korag.AGOpengl
import com.soywiz.korau.sound.readSound
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korev.Key
import com.soywiz.korge.animate.animate
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onClick
import com.soywiz.korge.service.Share
import com.soywiz.korge.time.delay
import com.soywiz.korge.time.frameBlock
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tiles.BaseTileMap
import com.soywiz.korge.view.tiles.TileMap
import com.soywiz.korge.view.tiles.TileSet
import com.soywiz.korge.view.tiles.tileMap
import com.soywiz.korge.view.tween.hide
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.bitmap.resized
import com.soywiz.korim.bitmap.slice
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launch
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.dynamic.KDynamic.Companion.toDouble
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Anchor
import com.soywiz.korma.geom.ScaleMode
import com.soywiz.korma.geom.shape.ops.collidesWith
import com.soywiz.korma.geom.vector.rect
import com.soywiz.korma.interpolation.Easing
import kotlinx.coroutines.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.round
class PlayScreen() : Scene() {
    override suspend fun Container.sceneInit() {
        lateinit var spriteMapRun: Bitmap
        lateinit var tileset1:TileSet
        lateinit var tilemap1:TileMap
        lateinit var job:Job
        var stageMoving = true
        var score=Score()
        SharedData.playerAlive=true
        var canMove: Boolean = false
        var obstacles: MutableList<Image> = mutableListOf<Image>()
        var obstaclesHitbox: MutableList<Image> = mutableListOf<Image>()
        job=launch{
            repeat(100) {
                var rand = (680..760).random()
                var randX = (980..1000).random()
                obstacles.add(Enemy(randX, rand).create())
           }
        }

        when(SharedData.pick_map){
            1->{tileset1 = TileSet(bitmap("background01.png")
                    .toBMP32()
                    .scaleLinear(1.0, 1.0).slice())
                tilemap1 = tileMap(
                        Bitmap32(1000,10),
                        repeatX = BaseTileMap.Repeat.REPEAT,
                        tileset = tileset1)
            }
            2->{
               tileset1 = TileSet(bitmap("background02.png")
                        .toBMP32()
                        .scaleLinear(0.5, 1.0).slice())
               tilemap1 = tileMap(
                        Bitmap32(1,1),
                        repeatX = BaseTileMap.Repeat.REPEAT,
                        tileset = tileset1)
            }
            3->{
                tileset1 = TileSet(bitmap("background03.png")
                        .toBMP32()
                        .scaleLinear(0.5, 1.0).slice())
                tilemap1 = tileMap(
                        Bitmap32(1,1),
                        repeatX = BaseTileMap.Repeat.REPEAT,
                        tileset = tileset1)
            }
            4->{
                tileset1 = TileSet(bitmap("background04.png")
                        .toBMP32()
                        .scaleLinear(0.5, 1.0).slice())
                tilemap1 = tileMap(
                        Bitmap32(1,1),
                        repeatX = BaseTileMap.Repeat.REPEAT,
                        tileset = tileset1)
            }

        }
        var i=0
            launchImmediately {
                frameBlock(144.timesPerSecond) {
                    while (stageMoving) {
                        job.start()
                        addChild(score)
                        tilemap1.x-=0.1
                        addChild(obstacles.get(i))
                       //var test=solidRect(obstaclesHitbox.get(i).width,obstaclesHitbox.get(i).height,Colors.BLUE).xy(obstaclesHitbox.get(i).x,obstaclesHitbox.get(i).y)
                            obstacles.get(i).x -= 1.5
                            if(obstacles.get(i).x<=-10){
                                removeChild(obstacles.get(i))
                                //removeChild(obstaclesHitbox.get(i))
                                i++
                            }
                        frame()
                    }

                }
            }

        when(SharedData.choose_player){
                1->{spriteMapRun = resourcesVfs["run01.png"].readBitmap()}
                2->{spriteMapRun = resourcesVfs["run02.png"].readBitmap()}
                3->{spriteMapRun = resourcesVfs["run03.png"].readBitmap()}
                4->{spriteMapRun = resourcesVfs["run04.png"].readBitmap()}
            }

            val runAnimation = SpriteAnimation(
                    spriteMap = spriteMapRun,
                    spriteWidth = 64,
                    spriteHeight = 64,
                    marginLeft = 0,
                    marginTop = 0,
                    columns = 12,
                    rows = 1,
                    offsetBetweenColumns = 0,
                    offsetBetweenRows = 0
            )
            val player: Sprite = sprite(runAnimation).xy(300, 625)
            player.playAnimationLooped(spriteDisplayTime = 80.milliseconds)


            player.addUpdater {
                if(collidesWith(obstacles)) {
                    if (SharedData.playerAlive) {
                        SharedData.playerAlive = false
                        canMove = false
                        stageMoving = false
                        launchImmediately{
                            delay(TimeSpan(30.0))
                            //channel.stop()
                            sceneContainer.changeTo<GameOver>()
                        }
                    }
                }

                if (SharedData.playerAlive) {
                    /*obstacles.forEach {
                         addChild(it)
                     }*/
                    if(views.input.keys[Key.UP] && player.y >625) {
                        player.y -= 3
                        //testplayer.y-=3
                    }
                    if(views.input.keys[Key.DOWN] && player.y<730) {
                        player.y += 3
                        //testplayer.y+=3
                    }
                    if(views.input.keys[Key.RIGHT] && player.x <1000) {
                        player.x += 3
                       // testplayer.x+=3
                    }
                    if(views.input.keys[Key.LEFT] && player.x >0 ) {
                        //testplayer.x-=3
                        player.x -= 3
                    }
                }
            }
    }
}