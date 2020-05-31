package Game;

public enum Colour {
    WHITE, BLACK;

    @Override
    public String toString() {
        if (this.equals(Colour.WHITE)) {
            return "White";
        } else {
            return "Black";
        }
    }
}
