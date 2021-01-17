package ro.mta.se.lab.controller;

import org.junit.Before;
import org.junit.Test;
import ro.mta.se.lab.model.Oras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * This class MockitoController relize Unit Test with mockito
 * @author Alexandra Naicu
 */

public class MockitoController {
  private Oras oras;
  private String city;

 @Before
  public void setare() throws Exception
 {
   oras=new Oras("RO");
   oras=mock(Oras.class);
 }


@Test
  public void getOrasTest()
{
  when(oras.getName()).thenReturn("Moscow");
  city=oras.getName();
  System.out.println(city);
  assertEquals(city, oras.getName());
  System.out.println(oras.getName());
  System.out.println("Mock unit test is ok ");

}
}
