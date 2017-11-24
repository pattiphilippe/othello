package g43197.othello.view;

import g43197.othello.model.Facade;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This class gets the title and the rest of the output.
 *
 * @author Philippe
 */
public class VBoxOthello extends VBox implements Observer {

    private final Label title;
    private final Region region1;
    private final HBoxOthello hbox;
    private final Region region2;

    /**
     * Creates a new Window for the Othello game.
     *
     * @param game
     */
    public VBoxOthello(Facade game) {
        super();

        game.addObserver(this);

        title = new Label("----------OTHELLO----------");
        title.setFont(Font.font(55));
        title.setMinHeight(OthelloApp.HEIGHT * 1 / 8);
        this.setAlignment(Pos.CENTER);

        region1 = new Region();
        VBox.setVgrow(region1, Priority.ALWAYS);

        hbox = new HBoxOthello(OthelloApp.HEIGHT * 3 / 5, game);

        region2 = new Region();
        VBox.setVgrow(region2, Priority.ALWAYS);

        this.getChildren().addAll(title, region1, hbox, region2);
    }

    @Override
    public void update(Observable o, Object arg) {
        Facade game = (Facade) o;
        if(game.isFinished()){
            
        }
        hbox.update();
    }
}
