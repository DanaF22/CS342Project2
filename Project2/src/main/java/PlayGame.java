//public class PlayGame {
//}

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Popup;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.scene.paint.Color;
import javafx.scene.layout.*;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.scene.image.ImageView;
import javafx.scene.Group;

public class PlayGame extends Application {

    private Button b1;
    private TextField t1, t2;
    private HBox h1, h2, h3, h4;
    private VBox v1,v2, v3;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //sets up image to put on screen
        InputStream stream = new FileInputStream("src/extraPics/cardchips.png");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(275);
        imageView.setPreserveRatio(false);

        Group addImage = new Group(imageView); //adds image to object of type group

        primaryStage.setTitle("Welcome to BlackJack");

        Text header = new Text("PLAY BLACKJACK"); //header for starting screen

        header.setFont(Font.font ("Georgia", FontWeight.BOLD,55));
        HBox headerBox = new HBox(header);
        headerBox.setAlignment(Pos.CENTER);

        //start button to start game
        b1 = new Button("START");
        b1.setStyle("-fx-background-radius: 40;");
        b1.setPrefWidth(300);
        b1.setPrefHeight(60);

        b1.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //wallet and betting amount inputs
        t1 = new TextField();
        t2 = new TextField();

        t1.setPrefWidth(402);
        t2.setPrefWidth(400);
        t1.setPrefHeight(30);
        t2.setPrefHeight(30);

        Text text1 = new Text(10, 20, "Wallet Amount:  ");
        Text text2 = new Text(10, 20, "Betting Amount:");

        text1.setFont(Font.font ("Georgia", 25));
        text2.setFont(Font.font ("Georgia", 25));

        //the two textfields
        h1 = new HBox(10, text1, t1);
        h2 = new HBox(10, text2, t2);
        //the start button
        h3 = new HBox(10,b1);
        //the image
        h4 = new HBox(0,addImage);

        h1.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        h3.setAlignment(Pos.CENTER);
        h4.setAlignment(Pos.TOP_LEFT);

        v1 = new VBox(20, h1, h2, h3);
        v2 = new VBox(100,headerBox,v1);
        v3 = new VBox(20, v2, h4);
        v3.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(v3);
        pane.setPadding(new Insets(30)); //makes sure stuff does not touch the border

        BorderWidths newBorderWidth = new BorderWidths(10);
        BorderStroke newBorderStroke = new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, newBorderWidth);
        Border newBorder = new Border(newBorderStroke);
        pane.setBorder(newBorder);

        primaryStage.setResizable(false);
        Scene scene = new Scene(pane,700,700);
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.setStyle("-fx-background-color: #8FBC8F;");
    }
}

