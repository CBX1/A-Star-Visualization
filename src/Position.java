import java.util.Objects;

public final class Position {
    private  int row;
    private  int col;

    public Position(int x, int y) {
        this.row = x;
        this.col = y;
    }
    public Position(Position p) {
    	this.row = p.row;
    	this.col = p.col;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + "]";
	}

  
}
