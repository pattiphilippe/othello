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
class MenuOthello extends MenuBar {

    private final Menu menuFile;
    private final MenuItem exit;
    private final MenuItem edit;

    MenuOthello() {
        super();
        //TODO faire plus d'options dans le menu : help,  change animation?

        menuFile = new Menu("_File");
        
        edit = new MenuItem("E_dit");
        edit.setAccelerator(KeyCombination.valueOf("Ctrl+E"));
        
        exit = new MenuItem("E_xit");
        exit.setAccelerator(KeyCombination.valueOf("Ctrl+X"));
        exit.setOnAction(event -> Platform.exit());

        menuFile.getItems().addAll(edit, exit);
        this.getMenus().add(menuFile);
    }
}
