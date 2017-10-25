package g43197.othello.model;

import java.util.Objects;

/**
 * This class represents a piece of the game. Each side has a different color based on the colors in Color Enum.
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
        if (color == null) {
            throw new IllegalArgumentException("Color can't be null!");
        }

        this.color = color;
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
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

}
