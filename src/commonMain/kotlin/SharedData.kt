import com.soywiz.korau.sound.readSound
import com.soywiz.korge.view.Image
import com.soywiz.korio.async.launch
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope

object SharedData {
    var pick_map=0
    var choose_player=0
    var playerAlive: Boolean = true
    var hitboxX=0
    var hitBoxY=0
    var start = 0
    var finalscore:Int=0
    var music_isplaying=1

    fun music_on(){

    }
    //println(music.volume)
}
