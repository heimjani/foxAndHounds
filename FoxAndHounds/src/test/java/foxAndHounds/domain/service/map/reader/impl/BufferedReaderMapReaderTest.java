package foxAndHounds.domain.service.map.reader.impl;

import foxAndHounds.domain.service.exceptions.MapReadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BufferedReaderMapReaderTest {

    @Mock
    private BufferedReader bufferedReader;

    private BufferedReaderMapReader underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BufferedReaderMapReader(bufferedReader);
    }

    @Test
    public void testReadShouldReturnCorrectResultWhenCalled() throws IOException {
        // given
        given(bufferedReader.readLine()).willReturn("test", "test2", null);

        // when
        List<String> result = underTest.read();

        // then
        List<String> expected = List.of("test", "test2");
        assertEquals(expected, result);
    }

    @Test
    public void testReadShouldThrowExceptionInCaseOfIOException() throws IOException {
        // given
        given(bufferedReader.readLine()).willThrow(IOException.class);

        // when-then
        assertThrows(MapReadException.class, () -> {
           underTest.read();
        });

    }

}
