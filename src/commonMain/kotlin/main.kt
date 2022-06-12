import Scene.Menu
import Scene.Second_Menu
import Scene.Select_map
import com.soywiz.korge.*
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.SizeInt

suspend fun main() = Korge(Korge.Config(module = ConfigModule))

object ConfigModule : Module() {
	override val bgcolor = Colors["#2b2b2b"]
	override val  clipBorders=false
	override val size = SizeInt(1024, 800)
	override val windowSize = SizeInt(980, 720)
	//override val fullscreen = true
	override val mainScene =Menu::class
	override suspend fun AsyncInjector.configure() {
		mapPrototype { Menu() }
		mapPrototype { Second_Menu() }
		mapPrototype { Select_map() }
		mapPrototype { PlayScreen() }
		mapPrototype { GameOver() }

	}


}
suspend fun bitmap(path: String) = resourcesVfs[path].readBitmap()