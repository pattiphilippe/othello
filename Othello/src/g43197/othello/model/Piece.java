package g43197.othello.model;

import g43197.othello.model.util.Color;
import java.util.Objects;

/**
 * This class represents a piece of the game. Each side has a different color
 * based on the colors in Color Enum.
 *
 * @author Philippe
 */
public class Piece {

    private Color color;

    /**
     * Creates a new Piece. Color attribute is set to the given color.
     *
     * @param color
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * Creates a copy of the given piece.
     *
     * @param piece
     */
    public Piece(Piece piece) {
        this.color = piece.getColor();
    }

    /**
     * Returns the color of the piece.
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Switches the color of the piece between the 2 first colors in Color Enum.
     */
    public void switchColor() {
        Color[] colors = Color.values();
        if (color == colors[0]) {
            color = colors[1];
        } else {
            color = colors[0];
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        return this.color == other.color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.color);
        return hash;
    }
}
