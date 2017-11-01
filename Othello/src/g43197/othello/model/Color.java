package g43197.othello.model;

/**
 * Possible colors of the pieces.
 * @author Philippe
 */
public enum Color {

    /**
     *
     */
    BLACK("X"),

    /**
     *
     */
    WHITE("O");
    
    private final String descr;
    
    private Color(String descr){
        this.descr = descr;
    }
    
    @Override
    public String toString(){
        return descr;
    }
}
