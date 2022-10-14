package foxAndHounds.domain.service.map.parser;

import foxAndHounds.domain.model.MapVO;
import foxAndHounds.domain.service.exceptions.MapReadException;

import java.util.List;

public class MapParser {

    private final int numberOfRows;
    private final int numberOfColumns;

    public MapParser(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public MapVO parse(List<String> rawMap) {
        checkNumberOfRows(rawMap);
        checkNumberOfColumns(rawMap);


        int[][] values = getValues(rawMap);
        boolean[][] usable = getUsable(values);


        return new MapVO(8,8, values, usable);
    }

    private int[][] getValues(List<String> rawMap) {
        int[][] result = new int[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            result[i] = new int[numberOfRows];

            String row = rawMap.get(i);
            String[] numberAsString = row.split("");

            for (int j = 0; j < numberOfColumns; j++) {
                int n = Integer.parseInt(numberAsString[j]);
                result[i][j] = n;
            }
        }
        return result;
    }

    private boolean[][] getUsable(int[][] map) {
        boolean[][] usable = new boolean[8][8];

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                usable[x][y] = map[x][y] == 1;

            }
            
        }

        return usable;
    }

    public void checkNumberOfRows(List<String> rawMap) {
        if(rawMap.size() != numberOfRows) {
            throw new MapReadException("number of rows are incorrect, it was " + rawMap.size());
        }

    }

    public void checkNumberOfColumns(List<String> rawMap) {
        if(rawMap.size() != numberOfColumns) {
            throw new MapReadException("number of columns are incorrect, it was " + rawMap.size());
        }

    }
}
