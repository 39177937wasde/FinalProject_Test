import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Enemy ( var Objectx:Int, var Objecty:Int):Container(){
    suspend fun create(): Image {
        lateinit var bitmap:Bitmap
        lateinit var img:Image
        when (SharedData.pick_map){
            1->{
               bitmap = resourcesVfs["barrel_crop.png"].readBitmap()
               img = image(bitmap).xy(Objectx, Objecty).scale(0.8)
            }
            2->{bitmap = resourcesVfs["barrel_crop.png"].readBitmap()
                img = image(bitmap).xy(Objectx, Objecty).scale(0.8)}
            3->{bitmap = resourcesVfs["barrel_crop.png"].readBitmap()
                img = image(bitmap).xy(Objectx, Objecty).scale(0.8)}
            4->{bitmap = resourcesVfs["barrel_crop.png"].readBitmap()
                img = image(bitmap).xy(Objectx, Objecty).scale(0.8)}
        }
        return img
    }
    suspend fun createhitbox(): Image {
        lateinit var bitmap:Bitmap
        lateinit var img:Image
        when (SharedData.pick_map){
            1->{
                bitmap = resourcesVfs["barrel_crop.png"].readBitmap()
                img = image(bitmap).xy(Objectx, Objecty).scale(0.5)
            }
            2->{ bitmap = resourcesVfs["barrel_crop.png"].readBitmap()
                img = image(bitmap).xy(Objectx, Objecty).scale(0.3)}
            3->{ bitmap = resourcesVfs["barrel_crop.png"].readBitmap()
                img = image(bitmap).xy(Objectx, Objecty).scale(0.3)}
            4->{ bitmap = resourcesVfs["barrel_crop.png"].readBitmap()
                img = image(bitmap).xy(Objectx, Objecty).scale(0.3)}
        }
        return img
    }
}