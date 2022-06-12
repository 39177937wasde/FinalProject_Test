import Scene.Select_map

import com.soywiz.klock.*
import com.soywiz.klogger.BaseConsole
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
import com.soywiz.korma.interpolation.Easing
import kotlinx.coroutines.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.round
class PlayScreen() : Scene() {
    override suspend fun Container.sceneInit() {
        lateinit var sprite: Sprite
        lateinit var barrel:Image
        lateinit var job:Job
        lateinit var job2: Job
        lateinit var barrelhitbox:Image
        lateinit var spriteMapRun: Bitmap
        lateinit var tileset1:TileSet
        lateinit var tilemap1:TileMap
        val stageEnd = 35000.milliseconds
        var stageMoving = true
        var speedFactor =10.0
        val yLimit =height/4
        var defaultY = 0.0
        var rand=(625..720).random()
        var randX=(980..1000).random()
        var count=0
        var score=Score()
        SharedData.playerAlive=true
        var canMove: Boolean = false
        // List of obstacles.
        var obstacles: MutableList<Image> = mutableListOf<Image>()
        var obstaclesHitbox: MutableList<Image> = mutableListOf<Image>()

        // Start timer.

        job=launch{
            repeat(100){
                var rand=(625..720).random()
                var randX=(980..1000).random()
                SharedData.hitboxX=randX
                SharedData.hitBoxY=rand
                obstacles.add(Enemy(randX,rand).create())
                SharedData.start++
            }
        }
        job2=launch {
            repeat(100) {
                rand = SharedData.hitBoxY
                randX = SharedData.hitboxX
                obstaclesHitbox.add(Enemy(randX, rand).createhitbox())
            }
        }
        // Attach updater to this container.
        /*addUpdater() {
            var now = DateTime.now()
            if((now-start)> stageEnd-600.milliseconds) {
                canMove = false
            }
            if((now-start) > stageEnd) {
                stageMoving = false
                launch {
                    delay(TimeSpan(6000.0))
                    //channel.stop()
                    //sceneContainer.changeTo<Forest>()
                }
            }
        }*/
        //println(SharedData.pick)
        when(SharedData.pick_map){
            1->{tileset1 = TileSet(bitmap("background01.png")
                    .toBMP32()
                    .scaleLinear(0.5, 1.0).slice())
                tilemap1 = tileMap(
                        Bitmap32(1,1),
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
        /*launchImmediately {
            for(i in 0..obstaclesHitbox.size-1){
                println(obstaclesHitbox[i])
            }
        }*/
        var randomPos = 1
        //var testplayer:SolidRect
        var i=0
        var j=0
            launchImmediately {
                frameBlock(144.timesPerSecond) {
                    while (stageMoving) {
                        job.start()
                        job2.start()
                        addChild(score)
                        tilemap1.x-=0.1
                        addChild(obstacles.get(i))
                        println("addChild "+i)
                        println("x:${obstaclesHitbox.get(i).x} "+obstacles.get(i).x)
                        println("y:${obstaclesHitbox.get(i).y} "+obstacles.get(i).y)
                        //var test=solidRect(obstaclesHitbox.get(i).width,obstaclesHitbox.get(i).height,Colors.RED).xy(obstacles.get(i).x,obstacles.get(i).y).scale(0.5)
                            obstacles.get(i).x -= 1.55
                            obstaclesHitbox.get(i).x-=1.55
                            //test.x-=1.55
                            println("start: "+SharedData.start)
                            if(obstacles.get(i).x<=0){
                                removeChild(obstacles.get(i))
                                i++
                                //println(i)
                                //println("obstacles:"+obstacles.size)
                            }

                         //test.x-=0.55
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
            val player: Sprite = sprite(runAnimation).xy(-50, 625)
            player.playAnimationLooped(spriteDisplayTime = 80.milliseconds)
            //var testplayer:SolidRect=SolidRect(player.width,player.height,Colors.RED).xy(0,625)
            //var testplayer=solidRect(player.width, player.height, Colors.BLUE).xy(-50, 625)
            /*launchImmediately {
                delay(3000.milliseconds)
                animate(completeOnCancel = false) { player.moveTo(60.0, round(player.y), time = 3000.milliseconds)
                     }
                canMove = true
            }*/

            player.addUpdater {
                if(collidesWith(obstaclesHitbox)) {
                    if (SharedData.playerAlive) {
                        SharedData.playerAlive = false
                        canMove = false
                        stageMoving = false
                        launchImmediately{
                            delay(TimeSpan(3000.0))
                            //channel.stop()
                            sceneContainer.changeTo<GameOver>()
                        }
                    }
                }

                //if (playerAlive && (DateTime.now()-start) > stageEnd ) {
                    //x += 1.1
                //}

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
                        //testplayer.x+=3
                    }
                    if(views.input.keys[Key.LEFT] && player.x >0 ) {
                        //testplayer.x-=3
                        player.x -= 3
                    }
                }
            }
    }
}
    /*fun addObstacle() {
        if (SharedData.playerAlive) {
            GlobalScope.launch {
                val newObstacle = Enemy(SharedData.randX,SharedData.rand).create()
                addChild(newObstacle)
                obstacles.add(newObstacle)
            }
        }
    }

    private fun addFloorTile() {
        if (game.isRunning) {
            GlobalScope.launch {
                val newFloor = FloorTileV1(1200.0, floorY).create()
                addChild(newFloor)
                floor.add(newFloor)
            }
        }
    }*/










            /* val startPopup = image(bitmap("stage_1.png")).alpha(0).xy(tileset1.width/4,tileset1.height/2)
             addChild(startPopup)
             var startPopupShown = false
             startPopup.addUpdater { time ->
                 var now = DateTime.now()
                 //print((now-start).toString() + "\n")
                 if ((now-start) > 500.milliseconds && (!startPopupShown)) {
                     startPopupShown = true
                     launchImmediately {
                         animate(completeOnCancel = false) { parallel(time = 1000.milliseconds) {
                             startPopup.alpha(1)
                             }
                         }
                         delay(2500.milliseconds)
                         animate(completeOnCancel = false) { parallel(time = 1000.milliseconds) {
                             startPopup.alpha(0)
                             }
                         }

                     }
                 }
             }



             val clearedPopup = image(bitmap("stage_cleared.png")).alpha(0)
             addChild(clearedPopup)
             var clearedPopupShown = false
             clearedPopup.addUpdater { time ->
                 var now = DateTime.now()
                 //print((now-start).toString() + "\n")
                 if ((now-start) > stageEnd && (!clearedPopupShown)) {
                     clearedPopupShown = true
                     launchImmediately {
                         animate(completeOnCancel = false) {
                             parallel(time = 1000.milliseconds) {
                                 clearedPopup.alpha(1)
                             }
                         }
                        /* delay(time=5000.milliseconds)
                         sceneContainer.changeTo<GameOver>()*/
                     }
                 }
             }*/

