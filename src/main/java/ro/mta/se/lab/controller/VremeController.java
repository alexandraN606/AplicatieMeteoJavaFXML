package ro.mta.se.lab.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.mta.se.lab.model.Oras;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
/**
 *
 * This class VremeController contains method to connect
 * To APIWhetherMAP
 * and connection with Label, ComboBox and Button from fxml file
 * @author Alexandra Naicu
 */

public class VremeController implements Initializable {


    private ObservableList<Oras> CityList;
    private ActionEvent event;

    public VremeController(ObservableList<Oras> CityList) {
        this.CityList = CityList;
    }

    /**
     *
     * This function search cities for country selected
     * and print just cities from this country
     */

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
    public Label orasL;
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
    /**
     *
     * This function print only once country from in.txt file
     */

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
    /**
     *
     * This function connect to APIWheterMap
     * parse JSON receive from Server
     * and write in a file a history with all the information
     */

    public void getVreme() throws IOException,ParseException
{   //scriem in fisierul istoric toate inf pe care le punem si in label-uri

    BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/istoric.txt", true));
    JSONParser jsonParser = new JSONParser();
    try
    {
        URL url=new URL("http://api.openweathermap.org/data/2.5/weather?q="+orase.getValue()+"&APPID=e2732a95198e8f9956d11917bf10dd6b\n");
        URLConnection conexiune = url.openConnection();
        BufferedReader readerJson = new BufferedReader( new InputStreamReader( conexiune.getInputStream() ) );
        JSONObject jsonObject = (JSONObject)jsonParser.parse(readerJson);

        JSONArray vremeVector= (JSONArray) jsonObject.get("weather");
        JSONObject wheter= (JSONObject) vremeVector.get(0);
        String vreme= (String) wheter.get("main");
        String name = (String)jsonObject.get("name");
        if(name != null) {
            orasL.setText( name.toString()+ ": "+ vreme.toString());
        }

        JSONObject main = (JSONObject)jsonObject.get( "main" );
        Double temperatura = (Double)main.get("temp");
        Double temperaturaCelsius = (Double)main.get("temp")-273;
        int tempC=temperaturaCelsius.intValue();
        if(temperatura != null) {
            writer.newLine();
            temperaturaL.setText("Temperatura: " + tempC + "C");
            writer.write("Temperatura: " + tempC + "C");
            writer.newLine();
        }

        Long presiune = (Long)main.get("pressure");
        if(presiune != null)
        {
            presiuneL.setText("Presiunea: " + presiune.toString() + "Pa");
            writer.append("Presiunea: " + presiune.toString() + "Pa");
            writer.newLine();

        }
        Long umiditate = (Long)main.get("humidity");
        if(umiditate != null)
        {
            umiditateL.setText("Umiditate: " + umiditate.toString() + "RH");
            writer.append("Umiditatea: " + umiditate.toString() + "RH");
            writer.newLine();

        }
       // System.out.println(temperatura.toString());
        //System.out.println(umiditate.toString());
       // System.out.println(presiune.toString());

        JSONObject wind = (JSONObject)jsonObject.get( "wind" );
        Object vitezaVant = wind.get( "speed" );
        if(vitezaVant != null)
        {
            vitezaVantL.setText("Viteza Vant: " + vitezaVant.toString() + "Vh");
            writer.append("Viteza Vant: " + vitezaVant.toString() + "Vh");
            writer.newLine();


        }
        //System.out.println(vitezaVant.toString());
        writer.close();
    }
    catch(FileNotFoundException | ParseException e) {
        e.printStackTrace();
    }
}
    @FXML
    public void button_hendler() throws IOException, ParseException{
       // System.out.println("Da-mi vreme!");
        getVreme();
    }

}

