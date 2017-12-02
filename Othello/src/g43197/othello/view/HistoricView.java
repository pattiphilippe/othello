package g43197.othello.view;

import g43197.othello.model.Move;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;

/**
 *
 * @author Philippe
 */
public class HistoricView extends TableView {

    private final TableColumn<Move, Integer> id;
    private final TableColumn<Move, String> name;
    private final TableColumn<Move, String> action;
    private final TableColumn<Move, String> pos;
    private final TableColumn<Move, Integer> nbTakes;

    public HistoricView(ObservableList<Move> historic) {
        super(historic);
        this.setEffect(new DropShadow());

        id = new TableColumn<>("Id");
        id.setCellValueFactory((CellDataFeatures<Move, Integer> param)
                -> new SimpleIntegerProperty(param.getValue().getId()).asObject());

        name = new TableColumn<>("Name");
        name.setCellValueFactory((CellDataFeatures<Move, String> param)
                -> new SimpleStringProperty(param.getValue().getName()));

        action = new TableColumn<>("Action");
        action.setCellValueFactory((CellDataFeatures<Move, String> param)
                -> new SimpleStringProperty(param.getValue().getActionDescr()));

        pos = new TableColumn<>("Coordinate");
        pos.setCellValueFactory((CellDataFeatures<Move, String> param)
                -> new SimpleStringProperty(param.getValue().getPos()));

        nbTakes = new TableColumn<>("Nb Takes");
        nbTakes.setCellValueFactory((CellDataFeatures<Move, Integer> param)
                -> new SimpleIntegerProperty(param.getValue().getNbTakes()).asObject());

        this.getColumns().addAll(id, name, action, pos, nbTakes);
    }
}
