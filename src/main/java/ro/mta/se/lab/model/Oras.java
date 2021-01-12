package ro.mta.se.lab.model;
import javafx.beans.property.*;

public class Oras {
    StringProperty ID;
    StringProperty nm;
    StringProperty lat;
    StringProperty lon;
    StringProperty countryCode;

    public Oras(String ID, String nm, String lat, String lon, String countryCode)
    {
        this.ID= new SimpleStringProperty(ID);
        this.nm=new SimpleStringProperty(nm);
        this.lat=new SimpleStringProperty(lat);
        this.lon=new SimpleStringProperty(lon);
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
