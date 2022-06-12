import Scene.Menu
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.resized
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Anchor
import com.soywiz.korma.geom.ScaleMode

class GameOver() : Scene() {
    override suspend fun Container.sceneInit() {
        val bitmap = resourcesVfs["1014.jpg"].readBitmap()
        val image = image(bitmap).centerOnStage()
        val menu= resourcesVfs["menu.png"].readBitmap()
        val to_menu=image(menu).xy(width/5, height - 300)
        val restart= image(resourcesVfs["restart.png"].readBitmap()).xy(width*3.5/5, height - 300)
        var scoreView: Text = text("Final Score ${SharedData.finalscore}",30.0, Colors.WHITE).xy(width/2.35, height -270)
        //scoreView.text = "SCORE $score"
        to_menu.onClick {
            SharedData.finalscore=0
            SharedData.start=0
            sceneContainer.changeTo<Menu>()
        }
        restart.onClick {
            sceneContainer.changeTo<PlayScreen>()
        }
    }
}
