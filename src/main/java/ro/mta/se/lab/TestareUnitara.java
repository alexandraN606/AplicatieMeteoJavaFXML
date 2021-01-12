package ro.mta.se.lab;
import org.junit.Test;
import ro.mta.se.lab.model.Oras;

import static org.junit.Assert.assertNotNull;
/**
 *
 * This class TestareUnitara performs unit testing
 * and checking if a Oras object is null
 * @author Alexandra Naicu
 */

public class TestareUnitara {

    @Test
    public void functieTestareUnitara()
    {
        Oras countryCode= new Oras("RO");
        assertNotNull(countryCode);

    }
}
