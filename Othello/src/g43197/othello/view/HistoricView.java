package g43197.othello.view;

import g43197.othello.model.Move;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;

/**
 *
 * @author Philippe
 */
class HistoricView extends TableView {

    private final List<Move> moves;
    private final ObservableList<MoveItem> moveItems;
    private final TableColumn<MoveItem, Integer> id;
    private final TableColumn<MoveItem, String> name;
    private final TableColumn<MoveItem, String> action;
    private final TableColumn<MoveItem, String> pos;
    private final TableColumn<MoveItem, Integer> nbTakes;

    /**
     * Creates a new view for the historic.
     *
     * @param moves a unmodifiableList of the moves
     */
    HistoricView(List<Move> moves) {
        super();
        this.setEffect(new DropShadow());

        this.moves = moves;
        this.moveItems = FXCollections.observableArrayList();
        this.setEditable(true);
        this.setItems(this.moveItems);

        id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory("id"));

        name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory("name"));

        action = new TableColumn<>("Action");
        action.setCellValueFactory(new PropertyValueFactory("action"));

        pos = new TableColumn<>("Coordinate");
        pos.setCellValueFactory(new PropertyValueFactory("pos"));

        nbTakes = new TableColumn<>("Nb Takes");
        nbTakes.setCellValueFactory(new PropertyValueFactory("nbTakes"));

        this.getColumns().addAll(id, name, action, pos, nbTakes);
        updateMoveItems();
    }

    private void updateMoveItems() {
        while (moves.size() > moveItems.size()) {
            moveItems.add(new MoveItem(moves.get(moveItems.size())));
        }
    }

    void update() {
        updateMoveItems();
    }
}
