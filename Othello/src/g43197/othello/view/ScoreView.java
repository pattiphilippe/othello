package g43197.othello.view;

import g43197.othello.model.Facade;
import g43197.othello.model.Player;
import g43197.othello.model.util.Color;
import static g43197.othello.view.OthelloApp.ICON;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

/**
 *
 * @author G43197
 */
public class ScoreView extends Stage implements Observer {

    private Scene scene;
    private Facade game;
    private LineChart scores;
    private Series white;
    private Series black;
    private int currentTurn;

    public ScoreView(Facade game) {

        this.setTitle("Othello - Scores");
        this.getIcons().add(ICON);

        init(game);
    }

    private void init(Facade game) {
        this.game = game;
        this.game.addObserver(this);

        scores = new LineChart(new NumberAxis(), new NumberAxis());
        white = new Series();
        white.setName("White");
        black = new Series();
        black.setName("Black");
        scores.getData().addAll(white, black);

        currentTurn = 0;

        addScore();

        this.scene = new Scene(scores);
        this.setScene(scene);
    }

    void restart(Facade game) {
        init(game);
    }

    private void addScore() {
        List<Player> players = game.getPlayers();
        int blackIdx = players.get(0).getColor() == Color.BLACK ? 0 : 1;
        int whiteIdx = 1 - blackIdx;

        white.getData().add(new Data<>(currentTurn, players.get(whiteIdx).getScore()));
        black.getData().add(new Data<>(currentTurn, players.get(blackIdx).getScore()));
        currentTurn++;
    }

    @Override
    public void update(Observable o, Object arg) {
        addScore();
    }

}
