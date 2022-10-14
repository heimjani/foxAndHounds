package foxAndHounds.domain.model;

import java.util.Arrays;
import java.util.Objects;

public class MapVO {

    private final int numberOfRows;
    private final int numberOfColumns;
    private final int[][] position;
    private final boolean[][] usable;

    public MapVO(int numberOfRows, int numberOfColumns, int[][] position, boolean[][] usable) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.position = deepCopy(position);
        this.usable = usable;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int[][] getPosition() {
        return deepCopy(position);
    }

    public boolean[][] getUsable() {
        return usable;
    }

    private int[][] deepCopy(int[][] array) {
        int[][] result = null;

        if (array != null) {
            result = new int[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapVO mapVO = (MapVO) o;
        return numberOfRows == mapVO.numberOfRows && numberOfColumns == mapVO.numberOfColumns && Arrays.equals(position, mapVO.position) && Arrays.equals(usable, mapVO.usable);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.hashCode(position);
        result = 31 * result + Arrays.hashCode(usable);
        return result;
    }

    @Override
    public String toString() {
        return  "numberOfRows=" + numberOfRows +
                ", numberOfColumns=" + numberOfColumns + '\n' +
                "position=" + Arrays.deepToString(position) + '\n' +
                "usable=" + Arrays.deepToString(usable);
    }
}
