package ro.mta.se.lab;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.VremeController;
import ro.mta.se.lab.model.Oras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    private ObservableList<Oras> CityList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    public void citire_fisier() {
        try {
            File myObj = new File("src/main/resources/in.txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                //System.out.println(data);
                String[] vector = data.split(" ");
                //System.out.println(vector[1]);
                Oras city = new Oras(vector[0], vector[1], vector[2], vector[3], vector[4]);
                //System.out.println(city.getName());
                //System.out.println(vector[1]);
                CityList.add(city);

            }
        } catch (FileNotFoundException e) {
            System.out.println("eroare la deschidere fisier");
            e.printStackTrace();
        }

    }

    public void start(Stage primaryStage) {
        citire_fisier();
        FXMLLoader loader = new FXMLLoader();

        try {
            loader.setLocation(this.getClass().getResource("view/VremeView.fxml"));
            loader.setController(new VremeController(CityList));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}