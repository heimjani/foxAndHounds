package foxAndHounds.domain;

import foxAndHounds.domain.model.MapVO;
import foxAndHounds.domain.service.map.parser.MapParser;
import foxAndHounds.domain.service.map.reader.MapReader;
import foxAndHounds.domain.service.map.reader.impl.BufferedReaderMapReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map/map.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        MapReader mapReader = new BufferedReaderMapReader(reader);
        List<String> rawMap = mapReader.read();

        MapParser mapParser = new MapParser(8,8);
        MapVO mapVO = mapParser.parse(rawMap);

        System.out.println(mapVO);
    }
}
