package g43197.othello.view;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

/**
 *
 * @author Philippe
 */
public class MenuOthello extends MenuBar {

    private final Menu menuFile;
    private final MenuItem exit;

    public MenuOthello() {
        super();
        //TODO faire plus d'options dans le menu : export historic, save/load?, help, change pictures?, change animation?, IA / 2 players, nom des joueurs...

        menuFile = new Menu("File");
        exit = new MenuItem("Exit");
        exit.setAccelerator(KeyCombination.valueOf("Ctrl+X"));
        exit.setOnAction(event -> {
            System.out.println("Exit handler");
            Platform.exit();
        });

        menuFile.getItems().add(exit);
        this.getMenus().add(menuFile);
    }
}
