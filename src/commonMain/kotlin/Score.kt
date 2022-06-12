import com.soywiz.klock.timesPerSecond
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Text
import com.soywiz.korge.view.addFixedUpdater
import com.soywiz.korge.view.text
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors

class Score() : Container() {
    private var score: Int = 0;
    private var scoreView: Text = text("SCORE $score",30.0, Colors.BLACK).xy(10.0, 10.0)
    init {
        addChild(scoreView)
        addFixedUpdater(2.timesPerSecond) {
            if (!SharedData.playerAlive) {
                restartScore()
            } else if (SharedData.playerAlive) {
                updateScore()
            }
        }
    }

    fun updateScore() {
        score++
        SharedData.finalscore=score
        scoreView.text = "SCORE $score"
    }

    fun restartScore() {
        //SharedData.finalscore=0
        score = 0
        scoreView.text = "SCORE $score"
    }

}
