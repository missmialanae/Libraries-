
package edu.xavier.csci;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class appTest {
   private RomanNumeral sut;


    @Before
    public void setup() {
      sut = new RomanNumeral();
    }

    @Test

    public void encodeNine() {
        assertThat(sut.encode(9),is("IX"));
    }

    @Test
    public void decodeIX(){
        assertEquals(Integer.valueOf(9), sut.decode(("IX")));
    }

    @Test
    public void decodeMMMMCMXCIX(){
        assertEquals(Integer.valueOf(4999), sut.decode("MMMMCMXCIX"));
    }

    @Test
    public void encodeFourThousand(){
        assertThat(sut.encode(400), is("CD"));
    }



}
