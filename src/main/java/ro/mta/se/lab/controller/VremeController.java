package ro.mta.se.lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.mta.se.lab.model.Oras;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class VremeController implements Initializable {


    private ObservableList<Oras> CityList;
    private ActionEvent event;

    public VremeController(ObservableList<Oras> CityList) {
        this.CityList = CityList;
    }

    @FXML
    public void changeTara()
    {
        orase.getItems().clear();
        for(Oras i: CityList)
        {
            if (i.getCountry().equals(tari.getValue().toString()))
            {
                orasele.add(i.getName());
            }
        }
        orase.setItems(orasele);
    }
    @FXML
    public Label temperaturaL;
    @FXML
    public Label presiuneL;
    @FXML
    public Label umiditateL;
    @FXML
    public Label vitezaVantL;
    @FXML
    public ComboBox<String> tari;
    @FXML
    public ComboBox<String> orase;

    private ObservableList<String> tarile = FXCollections.observableArrayList();
    private ObservableList<String> orasele = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for ( Oras i : CityList )
        {
            var verif=1;
            for(String j: tarile)
            {
                if(j.equals(i.getCountry()))
                {
                    verif=0;
                }
            }
            if(verif == 1)
            {
                tarile.add(i.getCountry());
            }
        }
        tari.setItems(tarile);

    }
public void getVreme() throws IOException
{
    JSONParser jsonParser = new JSONParser();
    try
    {
        URL url=new URL("http://api.openweathermap.org/data/2.5/weather?q="+orase.getValue()+"&APPID=e2732a95198e8f9956d11917bf10dd6b\n");
        URLConnection conexiune = url.openConnection();
        BufferedReader readerJson = new BufferedReader( new InputStreamReader( conexiune.getInputStream() ) );
        JSONObject jsonObject = (JSONObject)jsonParser.parse(readerJson);

        JSONObject main = (JSONObject)jsonObject.get( "main" );
        Double temperatura = (Double)main.get("temp");
        if(temperatura != null)
        {
            temperaturaL.setText("Temperatura: " + temperatura.toString() + "C");
        }
        Long presiune = (Long)main.get("pressure");
        if(presiune != null)
        {
            presiuneL.setText("Presiunea: " + presiune.toString() + "Pa");
        }
        Long umiditate = (Long)main.get("humidity");
        if(umiditate != null)
        {
            umiditateL.setText("Umiditate: " + umiditate.toString() + "RH");
        }
       // System.out.println(temperatura.toString());
        //System.out.println(umiditate.toString());
       // System.out.println(presiune.toString());

        JSONObject wind = (JSONObject)jsonObject.get( "wind" );
        Object vitezaVant = wind.get( "speed" );
        if(vitezaVant != null)
        {
            vitezaVantL.setText("Viteza Vant: " + vitezaVant.toString() + "Vh");
        }
        //System.out.println(vitezaVant.toString());

    }
    catch(FileNotFoundException | ParseException e) {
        e.printStackTrace();
    }
}
    @FXML
    public void button_hendler() throws IOException {
       // System.out.println("Da-mi vreme!");
        getVreme();
    }

}

