package thirdtry;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static thirdtry.CSVReader.split;

public class CSVReaderTest {

    @org.junit.Test
    public void Btest() {
        //"ok","ok, ok",ok"ok","nicht"ok,"nicht ok
        //"ok" testing
        assertEquals(5, (split("\"ok\",\"ok, ok\",ok\"ok\",\"nicht\"ok,\"nicht ok")).stream().count());
        // "ok, ok" testing
        //assertEquals(2, split("\"ok\",\"ok, ok\""));


    }
}