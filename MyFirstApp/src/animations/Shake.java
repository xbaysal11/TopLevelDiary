package animations;

import javafx.animation.TranslateTransition;

import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition translateTransition;

    public Shake(Node node){
        translateTransition = new TranslateTransition(Duration.millis(70), node);
        translateTransition.setFromX(-10f);
        translateTransition.setByX(10f);
        translateTransition.setFromY(-10f);
        translateTransition.setByY(10f);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
    }

    public void playAnimation(){
        translateTransition.playFromStart();
    }
}
