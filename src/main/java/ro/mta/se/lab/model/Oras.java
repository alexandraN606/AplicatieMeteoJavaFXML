package ro.mta.se.lab.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * This Model class Oras contains parameters from in.txt
 * and a get method for each parameter
 * @author Alexandra Naicu
 */

public class Oras {
    StringProperty ID;
    /**
     *
     * @param ID city id from file
     */
    StringProperty nm;
    /**
     *
     * @param nm country name from file
     */
    StringProperty lat;
    /**
     *
     * @param lat city latitude from file
     */
    StringProperty lon;
    /**
     *
     * @param lon city longitude from file
     */
    StringProperty countryCode;
    /**
     *
     * @param countryCode country code from file
     */


    public Oras(String ID, String nm, String lat, String lon, String countryCode)
    {
        this.ID= new SimpleStringProperty(ID);
        this.nm=new SimpleStringProperty(nm);
        this.lat=new SimpleStringProperty(lat);
        this.lon=new SimpleStringProperty(lon);
        this.countryCode=new SimpleStringProperty(countryCode);

    }
    public Oras(String countryCode)
    {
        this.countryCode=new SimpleStringProperty(countryCode);
    }
    public String getID()
    {
        return ID.get();//id
    }
    public String getName()
    {
        return nm.get();//nume orase
    }
    public String getCountry()
    {
        return countryCode.get();//nume tari
    }
    public String getLat()
    {
        return lat.get();//latitudine
    }
    public String getLon()
    {
        return lon.get();//longitudine
    }


}
