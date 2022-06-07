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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.round
class PlayScreen() : Scene() {
    override suspend fun Container.sceneInit() {
        lateinit var sprite: Sprite
        lateinit var jumpBitmap:Bitmap
        lateinit var spriteMapRun: Bitmap
        lateinit var tileset1:TileSet
        lateinit var tilemap1:TileMap
        val stageEnd = 35000.milliseconds
        var status:STATUS= STATUS.RUN
        // Main hero dog alive or dead, the dog can move and the stage is moving.
        var stageMoving = true
        var speedFactor =10.0
        var playerAlive: Boolean = true
        val yLimit =height/4
        var defaultY = 0.0
        var canMove: Boolean = false
        // List of obstacles.
        var obstacles: MutableList<View> = mutableListOf<View>()

        // Start timer.
        val start = DateTime.now()
        jumpBitmap=resourcesVfs["jump01.png"].readBitmap()
        // Attach updater to this container.
        addUpdater() {
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
        }

        when(SharedData.pick){
            1->{tileset1 = TileSet(bitmap("Background01.png")
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
        var rand=(625..720).random()
        var randX=(980..3000).random()
        var randomPos = 1
        var barrelHitbox1 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand)
        val barrelFg1 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand).scale(0.8)
        //var test=solidRect(barrelHitbox1.width/2, barrelHitbox1.height/2, Colors.BLUE).xy(randX, rand)
        obstacles.add(barrelHitbox1)
        rand=(625..720).random()
        randX=(980..3000).random()
        val barrelHitbox2 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand).scale(0.3)
        val barrelFg2 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand).scale(0.8)
        obstacles.add(barrelHitbox2)
        rand=(625..720).random()
        randX=(980..3000).random()
        val barrelHitbox3 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand).scale(0.3)
        val barrelFg3 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand).scale(0.8)
        obstacles.add(barrelHitbox3)
        rand=(625..720).random()
        randX=(980..3000).random()
        val barrelHitbox4 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand).scale(0.3)
        val barrelFg4 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand).scale(0.8)
        obstacles.add(barrelHitbox4)
        rand=(625..720).random()
        randX=(980..3000).random()
        val barrelHitbox5 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand).scale(0.3)
        val barrelFg5 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(randX,rand).scale(0.8)
        obstacles.add(barrelHitbox5)
        rand=(625..720).random()
        randX=(980..3000).random()
        val barrelHitbox6 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(2300,rand).scale(0.3)
        val barrelFg6= image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(2300,rand).scale(0.8)
        obstacles.add(barrelHitbox6)
        rand=(625..720).random()
        randX=(980..3000).random()
        val barrelHitbox7 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(2700,rand).scale(0.3)
        val barrelFg7 = image(resourcesVfs["barrel_crop.png"].readBitmap()).xy(2700,rand).scale(0.8)
        obstacles.add(barrelHitbox7)
        launchImmediately {
            frameBlock(144.timesPerSecond) {
                while (stageMoving) {
                    tilemap1.x-=0.06
                    barrelHitbox1.x -= 0.55
                    barrelFg1.x -= 0.55
                    //test.x-=0.55
                    barrelHitbox2.x -= 0.55
                    barrelFg2.x -= 0.55
                    barrelHitbox3.x -= 0.55
                    barrelFg3.x -= 0.55
                    barrelHitbox4.x -= 0.55
                    barrelFg4.x -= 0.55
                    barrelHitbox5.x -= 0.55
                    barrelFg5.x -= 0.55
                    barrelHitbox6.x -= 0.55
                    barrelFg6.x -= 0.55
                    barrelHitbox7.x -= 0.55
                    barrelFg7.x -= 0.55
                    frame()

                }
            }
        }
        when(SharedData.choose){
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
        //var testplayer=solidRect(dog.width, dog.height, Colors.BLUE).xy(-50, 625)
        launchImmediately {
            delay(3000.milliseconds)
            animate(completeOnCancel = false) { player.moveTo(60.0, round(player.y), time = 3000.milliseconds)
                 }
            canMove = true
        }
        player.addUpdater {
            if(collidesWith(obstacles)) {
                if (playerAlive) {
                    playerAlive = false
                    canMove = false
                    stageMoving = false
                    launchImmediately{
                        delay(TimeSpan(3000.0))
                        //channel.stop()
                       sceneContainer.changeTo<GameOver>()
                    }
                }
            }

            if (playerAlive && (DateTime.now()-start) > stageEnd ) {
                x += 1.1
            }

            if (playerAlive) {
                if(views.input.keys[Key.UP] && player.y >625) {
                    player.y -= 3
                    //testplayer.y-=3
                }
                if(views.input.keys[Key.DOWN] && player.y<720 ) {
                    player.y += 3
                    //testplayer.y+=3
                }
                if(views.input.keys[Key.RIGHT] && player.x <950) {
                    player.x += 3
                    //testplayer.x+=3
                }
                if(views.input.keys[Key.LEFT] && player.x >0 ) {
                    //testplayer.x-=3
                    player.x -= 3
                }
            }
        }

        val startPopup = image(bitmap("stage_1.png")).alpha(0).xy(tileset1.width/4,tileset1.height/2)
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
        }
    }
}
