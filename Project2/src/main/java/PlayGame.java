/**------------------------------------------
 Project 2: BlackJack
 Course: CS 342, Spring 2024
 System: IntelliJ and Windows 11 and macOS
 Student Author: Dana Fakhreddine and Viviana Lopez
 ---------------------------------------------**/

import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.scene.paint.Color;
import javafx.scene.layout.*;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.ImageView;


public class PlayGame extends Application {
    Scene startScene;
    Scene playScene;
    Scene pushScene;
    Scene winScene;
    Scene blackJackScene;
    Scene bustScene;
    Scene brokeScene;
    Scene loseScene;
    HBox dealersCardBox;
    BlackjackGame game = new BlackjackGame();

    public static void main(String[] args) {
        launch(args);
    }

    //calls beginningScreen
    //parameters: Stage primaryStage
    //return: void
    @Override
    public void start(Stage primaryStage) throws Exception {
        //call starting screen
        beginningScreen(primaryStage);
    }

    //this function returns the image view of a card that will be displayed in the game
    //parameters: Card card
    //return: ImageView
    public ImageView addCards(Card card){
        //creates name for the path of image based off the card that is passed in
        String cardName = "src/card images/" + String.valueOf(card.value+card.suit) +".png";

        //creates a stream to add path and be able to access image
        InputStream stream = null;
        try {
            stream = new FileInputStream(cardName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //create image
        Image image = new Image(stream);
        ImageView imageView = new ImageView();

        //sets the width and height of the image
        imageView.setImage(image);
        imageView.setFitWidth(130);
        imageView.setFitHeight(170);
        imageView.setPreserveRatio(false);

        return imageView;
    }

    //this is the screen where the user will input their wallet/betting amounts and start the game
    //parameters: Stage primaryStage
    //return: void
    public void beginningScreen(Stage primaryStage)throws Exception{
        //sets up image to put on screen
        InputStream stream = new FileInputStream("src/extraPics/cardchips.png");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();

        //sets the width and height of the image
        imageView.setImage(image);
        imageView.setFitWidth(250);
        imageView.setFitHeight(275);
        imageView.setPreserveRatio(false);

        primaryStage.setTitle("Welcome to BlackJack"); //title for screen
        Text header = new Text("PLAY BLACKJACK"); //header for starting screen

        //positioning the header in the center and setting its font
        header.setFont(Font.font ("Georgia", FontWeight.BOLD,55));
        HBox headerBox = new HBox(header);
        headerBox.setAlignment(Pos.CENTER);

        //creating the start button that starts game
        Button startButton = new Button("START");
        startButton.setStyle("-fx-background-radius: 40;");
        startButton.setPrefWidth(300);
        startButton.setPrefHeight(60);

        //setting font for text on button
        startButton.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //wallet and betting amount inputs
        TextField walletField = new TextField();
        TextField bettingField = new TextField();

        //set the text fields to specific sizes
        walletField.setPrefWidth(402);
        bettingField.setPrefWidth(400);
        walletField.setPrefHeight(30);
        bettingField.setPrefHeight(30);

        //creating text to put next to the text fields
        Text text1 = new Text(10, 20, "Wallet Amount:  ");
        Text text2 = new Text(10, 20, "Betting Amount:");

        //setting font for the text
        text1.setFont(Font.font ("Georgia", 25));
        text2.setFont(Font.font ("Georgia", 25));

        //hboxes that contains a text field and their respective text to the left of them
        HBox h1 = new HBox(10, text1, walletField);
        HBox h2 = new HBox(10, text2, bettingField);

        HBox h3 = new HBox(10,startButton);  //adding the start button to hbox
        HBox h4 = new HBox(30,imageView, h3); //the card and start button

        //positioning the hboxes
        h1.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        h3.setAlignment(Pos.CENTER);
        h4.setAlignment(Pos.TOP_LEFT);

        //adding the hboxes to vboxes
        VBox v1 = new VBox(20, h1, h2);
        VBox v2 = new VBox(100,headerBox,v1);
        VBox v3 = new VBox(20, v2, h4);
        v3.setAlignment(Pos.CENTER);

        //creating BorderPane to add everything
        BorderPane pane = new BorderPane();
        pane.setCenter(v3);
        pane.setPadding(new Insets(30)); //makes sure stuff does not touch the border

        //adding a border color
        BorderWidths newBorderWidth = new BorderWidths(10);
        BorderStroke newBorderStroke = new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, newBorderWidth);
        Border newBorder = new Border(newBorderStroke);
        pane.setBorder(newBorder);

        //if player presses the start button
        startButton.setOnAction(e->{

            //check if input is valid
            boolean checkBet = false;
            boolean checkWalletInput = false;
            double betAmount = 0.0;
            double walletAmount = 0.0;

            //this is checking to make sure input for wallet is a double
            try {
                walletAmount = Double.parseDouble(walletField.getText());
                checkWalletInput = true;
            } catch (NumberFormatException nfe) {
                checkWalletInput = false;
            }

            //this is checking to make sure input for betting is a double
            try {
                betAmount = Double.parseDouble(bettingField.getText());
                checkBet = true;
            } catch (NumberFormatException nfe) {
                checkBet = false;
            }

            //check if walletInput was a double and is greater than 0
            if(checkWalletInput && walletAmount > 0){
                game.totalWinnings = walletAmount;
            }

            //if the bet amount is less than 0 or wallet amount is less than or equal to 0, then clear the text fields and set totalWinnings to 0.0
            if ((betAmount < 0 && checkBet) || (checkWalletInput && walletAmount <= 0)){
                bettingField.clear();
                walletField.clear();
                game.totalWinnings = 0.0;
            }

            //if checkWalletInput is valid and the betAmount is greater than wallet, clear the betting text field and set totalWinnings to 0.0
            else if(checkWalletInput && betAmount > game.totalWinnings){
                bettingField.clear();
                game.totalWinnings = 0.0;
            }
            //if checkBet or checkWalletInput were not valid inputs, clear the text fields and set the totalWinnings to 0.0
            else if(!checkBet || !checkWalletInput){
                walletField.clear();
                bettingField.clear();
                game.totalWinnings = 0.0;
            }
            //if all inputs are valid, set betAmount and go to play screen
            else{
                game.currentBet = betAmount;
                try{
                    //create deck and shuffle cards
                    game.theDealer.shuffleDeck();

                    //get first two cards for the player and dealer
                    game.playerHand = game.theDealer.dealHand();
                    game.bankerHead = game.theDealer.dealHand();

                    playScene(primaryStage);

                    //if the dealer and the player both got a blackjack, evaluate the winnings and go to push screen
                    if (game.gameLogic.gotBlackJack(game.playerHand, game.bankerHead).equals("push")){
                        game.evaluateWinnings();
                        pushScreen(primaryStage);
                    }
                    //if only player got a blackjack, then go to blackjack screen
                    else if (game.gameLogic.gotBlackJack(game.playerHand, game.bankerHead).equals("player")) {
                        game.evaluateWinnings();
                        blackJackScreen(primaryStage);
                    }
                    //if only dealer got a blackjack, then go either go to broke or lose scene
                    else if (game.gameLogic.gotBlackJack(game.playerHand, game.bankerHead).equals("dealer")) {
                        game.evaluateWinnings();
                        //if the totalWinnings is 0.0, that means the player does not have any money left in their wallet, so go to you're broke scene
                        if(game.totalWinnings == 0.0){
                            try {
                                youreBroke(primaryStage);
                            } catch (Exception exception) {
                                throw new RuntimeException(exception);
                            }
                        }
                        //if totalWinnings is not 0, call you lose scene
                        else{
                            youLose(primaryStage);
                        }
                    }
                }catch(Exception ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        //creating scene
        primaryStage.setResizable(false);
        startScene = new Scene(pane,700,700);
        primaryStage.setScene(startScene);
        primaryStage.show();

        //background color of scene
        pane.setStyle("-fx-background-color: #8FBC8F;");
    }

    //this method creates the main scene where player competes against the dealer and plays the game
    //parameters: Stage primaryStage
    //return: void
    public void playScene(Stage primaryStage) throws Exception{
        //creating image to add
        InputStream stream = new FileInputStream("src/extraPics/backgroundImage.jpg");
        Image image = new Image(stream, 3000 ,3000, true, true);

        ImageView backgroundImageView = new ImageView(image);
        //set image dimensions to the same size as the scene to fill background
        backgroundImageView.setFitWidth(1000);
        backgroundImageView.setFitHeight(700);

        //creating hit button with size, font, and shape
        Button hitButton = new Button("HIT");
        hitButton.setPrefWidth(100);
        hitButton.setPrefHeight(100);
        hitButton.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        hitButton.setStyle("-fx-background-radius: 100;");

        //creating stay button with size, font, and shape
        Button stayButton = new Button("STAY");
        stayButton.setPrefWidth(100);
        stayButton.setPrefHeight(100);
        stayButton.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        stayButton.setStyle("-fx-background-radius: 100;");

        //vbox that contains both buttons
        VBox buttons = new VBox(20, hitButton, stayButton);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);

        //creates pane for scene
        StackPane pane = new StackPane(backgroundImageView);
        pane.setPadding(new Insets(40));

        //circle to show amount in players wallet
        Circle walletCircle = new Circle(72);

        //set design of circle
        walletCircle.setFill(Color.DARKRED);
        walletCircle.setStroke(Color.BLACK);
        walletCircle.setStrokeWidth(10);

        //add circle to vbox and position to the left
        VBox circleVbox = new VBox(10,walletCircle);
        circleVbox.setAlignment(Pos.TOP_LEFT);

        //create string to put on top of circle
        String message = String.valueOf(game.totalWinnings);
        Text text1 = new Text("Wallet Amount " + "\n            " + message);

        //set color and font of text
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font("Georgia", FontWeight.BOLD, 15));

        //add text to vbox and position with setTranslateY and setTranslateX
        VBox walletMessage = new VBox(60,text1);
        walletMessage.setTranslateY(62);
        walletMessage.setTranslateX(18);

        //circle to display amount player is currently betting
        Circle bettingCircle = new Circle(72);

        //set design of circle
        bettingCircle.setFill(Color.DARKRED);
        bettingCircle.setStroke(Color.BLACK);
        bettingCircle.setStrokeWidth(10);

        //add circle to vbox and position to the left
        VBox circle2Vbox = new VBox(10,bettingCircle);
        circle2Vbox.setAlignment(Pos.BOTTOM_LEFT);

        //create string to put on top of circle
        String message2 = String.valueOf(game.currentBet);
        Text text2 = new Text("Betting Amount " + "\n            " + message2);

        //set color and font of text
        text2.setFill(Color.WHITE);
        text2.setFont(Font.font("Georgia", FontWeight.BOLD, 15));

        //add message to vbox and position
        VBox bettingMessage = new VBox(60,text2);
        bettingMessage.setTranslateY(530);
        bettingMessage.setTranslateX(16);

        //adding first two cards on the screen
        HBox playersCardBox = new HBox(-80);
        dealersCardBox = new HBox(-80);

        //manually enter dealers face down card
        InputStream stream2 = new FileInputStream("src/card images/facedown.png");
        Image image2 = new Image(stream2);
        ImageView faceDownView = new ImageView();

        //sets the width and height of the card
        faceDownView.setImage(image2);
        faceDownView.setFitWidth(130);
        faceDownView.setFitHeight(170);
        faceDownView.setPreserveRatio(false);

        //add facedown card
        dealersCardBox.getChildren().add(faceDownView);
        dealersCardBox.setAlignment(Pos.TOP_CENTER);

        //add face up cards
        dealersCardBox.getChildren().add(addCards(game.bankerHead.get(1)));
        playersCardBox.getChildren().add(addCards(game.playerHand.get(0)));
        playersCardBox.getChildren().add(addCards(game.playerHand.get(1)));
        playersCardBox.setAlignment(Pos.BOTTOM_CENTER);

        //add vboxes to the pane
        pane.getChildren().addAll(circleVbox, walletMessage, circle2Vbox, bettingMessage, dealersCardBox, playersCardBox,buttons);

        //if user presses the hit button, evaluate value of cards and add cards to screen, check for bust or 0 in totalWinnings
        hitButton.setOnAction(e -> {
            //drawing another card
            Card playerDrawOne = game.theDealer.drawOne();
            game.playerHand.add(playerDrawOne);
            playersCardBox.getChildren().add(addCards(playerDrawOne)); //adding the card drawn to the scene(GUI)

            //if player can no longer hit, meaning they reached(won) or went over 21(lost)
            if (game.gameLogic.whilePlaying(game.playerHand)) {
                //do not allow them to press the buttons anymore
                hitButton.setDisable(true);
                stayButton.setDisable(true);

                //if player got over 21, they bust
                if (game.gameLogic.playerOver21) {
                    try {
                        game.evaluateWinnings();
                        //if the player does not have any money left in their wallet, then go to youreBroke scene
                        if(game.totalWinnings == 0.0){
                            try {
                                youreBroke(primaryStage);
                            } catch (Exception exception) {
                                throw new RuntimeException(exception);
                            }
                        }
                        //otherwise, they are not broke so go to the bust scene
                        else{
                            bust(primaryStage);
                        }
                    }
                    catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }

                //playing dealer's turn
                else{
                    dealerTurn(primaryStage);}
                }
        });

        //if player decides to hit the stay button
        stayButton.setOnAction(e -> {
            //disable both buttons
            hitButton.setDisable(true);
            stayButton.setDisable(true);
            dealerTurn(primaryStage); //play dealer's turn
        });

        //create and show scene
        playScene = new Scene(pane,1000, 700);
        primaryStage.setScene(playScene);
        primaryStage.show();
    }

    //this method carries out the dealer's turn and evaluates their card while also determining if player or dealer won(or a tie)
    //parameters: Stage primaryStage
    //return: void
    public void dealerTurn(Stage primaryStage){
        //keeps drawing cards for the dealer until they get 17 or above
        while (game.gameLogic.evaluateBankerDraw(game.bankerHead) == true) {
            //draw card for dealer
            Card dealerDrawOne = game.theDealer.drawOne();
            game.bankerHead.add(dealerDrawOne);
            dealersCardBox.getChildren().add(addCards(dealerDrawOne)); //add card drawn to the scene
        }

        //check whether dealer or player won or if there was a push
        //if player won, evaluate winnings and call winScreen
        if (game.gameLogic.whoWon(game.playerHand, game.bankerHead).equals("player")) {
            game.evaluateWinnings();
            try {
                winScreen(primaryStage);;
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
        //if dealer won, evaluate winnings, check if totalWinnings is 0 or call youLose
        else if(game.gameLogic.whoWon(game.playerHand, game.bankerHead).equals("dealer")){
            game.evaluateWinnings();

            //if totalWinnings is 0, show the youreBroke scene
            if(game.totalWinnings == 0.0){
                try {
                    youreBroke(primaryStage);
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            }
            //otherwise, they lose money so go to youLose scene
            else{
                try {
                    youLose(primaryStage);
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }

            }

        }
        //if the player and banker tie, then it is a push
        else if(game.gameLogic.whoWon(game.playerHand, game.bankerHead).equals("push")){
            game.evaluateWinnings();

            //call push screen
            try {
                pushScreen(primaryStage);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }

        }
    }

    //this method creates the scene for when the dealer and player tie(push)
    //parameters: Stage primaryStage
    //return: void
    public void pushScreen(Stage primaryStage) throws Exception{

        Text header = new Text("YOU TIED"); //header for starting screen

        //positioning the header in the center and setting its font
        header.setFont(Font.font ("Georgia", FontWeight.BOLD,55));
        header.setTranslateY(-70);

        //creating the play again button
        Button playAgain = new Button("PLAY AGAIN");
        playAgain.setStyle("-fx-background-radius: 40;");
        playAgain.setPrefWidth(300);
        playAgain.setPrefHeight(60);

        //setting font for text on button
        playAgain.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the play again button, take them to the screen where you can insert new starting bet
        playAgain.setOnAction(e->{
            try{
                startingBetScene(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //creating the home button
        Button home = new Button("HOME");
        home.setStyle("-fx-background-radius: 40;");
        home.setPrefWidth(300);
        home.setPrefHeight(60);

        //setting font for text on button
        home.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the home button, take them to the home screen
        home.setOnAction(e->{
            try{
                beginningScreen(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //hboxes that contains the two buttons
        HBox h1 = new HBox(10, playAgain, home);

        //positioning the hboxes
        h1.setAlignment(Pos.CENTER);

        //adding the hboxes to vboxes
        VBox v1 = new VBox(20, header, h1);
        v1.setAlignment(Pos.CENTER);

        //creating a pane
        BorderPane pane = new BorderPane();
        pane.setCenter(v1);
        pane.setPadding(new Insets(30)); //makes sure stuff does not touch the border

        //adding a border color
        BorderWidths newBorderWidth = new BorderWidths(10);
        BorderStroke newBorderStroke = new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, newBorderWidth);
        Border newBorder = new Border(newBorderStroke);
        pane.setBorder(newBorder);

        //creating scene
        primaryStage.setResizable(false);
        pushScene = new Scene(pane,700,700);
        primaryStage.setScene(pushScene);
        primaryStage.show();

        //background color of scene
        pane.setStyle("-fx-background-color: #8FBC8F;");
    }

    //this method creates a scene for when the player wins
    //parameters: Stage primaryStage
    //return: void
    public void winScreen(Stage primaryStage) throws Exception{

        Text header = new Text("YOU WON!!!"); //header for starting screen

        //positioning the header in the center and setting its font
        header.setFont(Font.font ("Georgia", FontWeight.BOLD,55));
        header.setTranslateY(-70);

        //creating play again button
        Button playAgain = new Button("PLAY AGAIN");
        playAgain.setStyle("-fx-background-radius: 40;");
        playAgain.setPrefWidth(300);
        playAgain.setPrefHeight(60);

        //setting font for text on button
        playAgain.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the play again button, take them to the screen where you can insert new starting bet
        playAgain.setOnAction(e->{
            try{
                startingBetScene(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //create home button
        Button home = new Button("HOME");
        home.setStyle("-fx-background-radius: 40;");
        home.setPrefWidth(300);
        home.setPrefHeight(60);

        //setting font for text on button
        home.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the home button, take them to the home screen
        home.setOnAction(e->{
            try{
                beginningScreen(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //hbox that contains the play again button and home button
        HBox h1 = new HBox(10, playAgain, home);

        //positioning the hbox
        h1.setAlignment(Pos.CENTER);

        //adding the hbox and header to vbox
        VBox v1 = new VBox(20, header, h1);
        v1.setAlignment(Pos.CENTER);

        //creating a pane
        BorderPane pane = new BorderPane();
        pane.setCenter(v1);
        pane.setPadding(new Insets(30)); //makes sure stuff does not touch the border

        //adding a border color
        BorderWidths newBorderWidth = new BorderWidths(10);
        BorderStroke newBorderStroke = new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, newBorderWidth);
        Border newBorder = new Border(newBorderStroke);
        pane.setBorder(newBorder);

        //creating scene
        primaryStage.setResizable(false);
        winScene = new Scene(pane,700,700);
        primaryStage.setScene(winScene);
        primaryStage.show();

        //background color of scene
        pane.setStyle("-fx-background-color: #8FBC8F;");
    }

    //this method creates the scene if player gets a blackjack
    //parameter: Stage primaryStage
    //return: void
    public void blackJackScreen(Stage primaryStage) throws Exception{
        Text header = new Text("BLACKJACK!!!"); //header for starting screen

        //positioning the header in the center and setting its font
        header.setFont(Font.font ("Georgia", FontWeight.BOLD,55));
        header.setTranslateY(-70);

        //creating play again button
        Button playAgain = new Button("PLAY AGAIN");
        playAgain.setStyle("-fx-background-radius: 40;");
        playAgain.setPrefWidth(300);
        playAgain.setPrefHeight(60);

        //setting font for text on button
        playAgain.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the play again button, take them to the screen where you can insert new starting bet
        playAgain.setOnAction(e->{
            try{
                startingBetScene(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //creating home button
        Button home = new Button("HOME");
        home.setStyle("-fx-background-radius: 40;");
        home.setPrefWidth(300);
        home.setPrefHeight(60);

        //setting font for text on button
        home.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the home button, take them to the home screen
        home.setOnAction(e->{
            try{
                beginningScreen(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //hbox that contains two buttons
        HBox h1 = new HBox(10, playAgain, home);

        //positioning the hbox
        h1.setAlignment(Pos.CENTER);

        //adding the hbox and header to vbox
        VBox v1 = new VBox(20, header, h1);
        v1.setAlignment(Pos.CENTER);

        //creating a pane
        BorderPane pane = new BorderPane();
        pane.setCenter(v1);
        pane.setPadding(new Insets(30)); //makes sure stuff does not touch the border

        //adding a border color
        BorderWidths newBorderWidth = new BorderWidths(10);
        BorderStroke newBorderStroke = new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, newBorderWidth);
        Border newBorder = new Border(newBorderStroke);
        pane.setBorder(newBorder);

        //creating scene
        primaryStage.setResizable(false);
        blackJackScene = new Scene(pane,700,700);
        primaryStage.setScene(blackJackScene);
        primaryStage.show();

        //background color of scene
        pane.setStyle("-fx-background-color: #8FBC8F;");
    }

    //this method creates the scene if the player busts
    //parameter: Stage primaryStage
    //return: void
    public void bust(Stage primaryStage) throws Exception{
        Text header = new Text("BUST :("); //header for starting screen

        //positioning the header in the center and setting its font
        header.setFont(Font.font ("Georgia", FontWeight.BOLD,55));
        header.setTranslateY(-70);

        //creating play again button
        Button playAgain = new Button("PLAY AGAIN");
        playAgain.setStyle("-fx-background-radius: 40;");
        playAgain.setPrefWidth(300);
        playAgain.setPrefHeight(60);

        //setting font for text on button
        playAgain.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the play again button, take them to the screen where you can insert new starting bet
        playAgain.setOnAction(e->{
            try{
                startingBetScene(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //creating home button
        Button home = new Button("HOME");
        home.setStyle("-fx-background-radius: 40;");
        home.setPrefWidth(300);
        home.setPrefHeight(60);

        //setting font for text on button
        home.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the home button, take them to the home screen
        home.setOnAction(e->{
            try{
                beginningScreen(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //hbox that contains the two buttons
        HBox h1 = new HBox(10, playAgain, home);

        //positioning the hbox
        h1.setAlignment(Pos.CENTER);

        //adding the hbox and header to vbox
        VBox v1 = new VBox(20, header, h1);
        v1.setAlignment(Pos.CENTER);

        //creating a pane
        BorderPane pane = new BorderPane();
        pane.setCenter(v1);
        pane.setPadding(new Insets(30)); //makes sure stuff does not touch the border

        //adding a border color
        BorderWidths newBorderWidth = new BorderWidths(10);
        BorderStroke newBorderStroke = new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, newBorderWidth);
        Border newBorder = new Border(newBorderStroke);
        pane.setBorder(newBorder);

        //creating scene
        primaryStage.setResizable(false);
        bustScene = new Scene(pane,700,700);
        primaryStage.setScene(bustScene);
        primaryStage.show();

        //background color of scene
        pane.setStyle("-fx-background-color: #8FBC8F;");
    }

    //this method creates the scene if the player loses
    //parameter: Stage primaryStage
    //return: void
    public void youLose(Stage primaryStage) throws Exception{
        Text header = new Text("YOU LOSE :("); //header for starting screen

        //positioning the header in the center and setting its font
        header.setFont(Font.font ("Georgia", FontWeight.BOLD,55));
        header.setTranslateY(-70);

        //creating a play again button
        Button playAgain = new Button("PLAY AGAIN");
        playAgain.setStyle("-fx-background-radius: 40;");
        playAgain.setPrefWidth(300);
        playAgain.setPrefHeight(60);

        //setting font for text on button
        playAgain.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the play again button, take them to the screen where you can insert new starting bet
        playAgain.setOnAction(e->{
            try{
                startingBetScene(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //creating home button
        Button home = new Button("HOME");
        home.setStyle("-fx-background-radius: 40;");
        home.setPrefWidth(300);
        home.setPrefHeight(60);

        //setting font for text on button
        home.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the home button, take them to the home screen
        home.setOnAction(e->{
            try{
                beginningScreen(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //hbox that contains the two buttons
        HBox h1 = new HBox(10, playAgain, home);

        //positioning the hbox
        h1.setAlignment(Pos.CENTER);

        //adding the hbox and header to vbox
        VBox v1 = new VBox(20, header, h1);
        v1.setAlignment(Pos.CENTER);

        //creating a pane
        BorderPane pane = new BorderPane();
        pane.setCenter(v1);
        pane.setPadding(new Insets(30)); //makes sure stuff does not touch the border

        //adding a border color
        BorderWidths newBorderWidth = new BorderWidths(10);
        BorderStroke newBorderStroke = new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, newBorderWidth);
        Border newBorder = new Border(newBorderStroke);
        pane.setBorder(newBorder);

        //creating scene
        primaryStage.setResizable(false);
        loseScene = new Scene(pane,700,700);
        primaryStage.setScene(loseScene);
        primaryStage.show();

        //background color of scene
        pane.setStyle("-fx-background-color: #8FBC8F;");
    }

    //this method creates a scene for when the user's totalWinnings is 0 and takes them to the home screen
    //parameters: Stage primaryStage
    //return: void
    public void youreBroke(Stage primaryStage) throws Exception{
        Text header = new Text("YOU'RE BROKE!!!"); //header for starting screen

        //positioning the header in the center and setting its font
        header.setFont(Font.font ("Georgia", FontWeight.BOLD,55));
        header.setTranslateY(-70);

        //creating home button
        Button home = new Button("HOME");
        home.setStyle("-fx-background-radius: 40;");
        home.setPrefWidth(300);
        home.setPrefHeight(60);

        //setting font for text on button
        home.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        //if they click the home button, take them to the home screen
        home.setOnAction(e->{
            try{
                beginningScreen(primaryStage);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        });

        //adding b1 and header to v1
        VBox v1 = new VBox(20, header, home);
        v1.setAlignment(Pos.CENTER);

        //creating a pane
        BorderPane pane = new BorderPane();
        pane.setCenter(v1);
        pane.setPadding(new Insets(30)); //makes sure stuff does not touch the border

        //adding a border color
        BorderWidths newBorderWidth = new BorderWidths(10);
        BorderStroke newBorderStroke = new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, newBorderWidth);
        Border newBorder = new Border(newBorderStroke);
        pane.setBorder(newBorder);

        //creating scene
        primaryStage.setResizable(false);
        brokeScene = new Scene(pane,700,700);
        primaryStage.setScene(brokeScene);
        primaryStage.show();

        //background color of scene
        pane.setStyle("-fx-background-color: #8FBC8F;");
    }

    //this scene allows the user to input a new starting bet
    //parameters: Stage primaryStage
    //return: void
    public void startingBetScene(Stage primaryStage) throws Exception{
        Text header = new Text("STARTING BET"); //header for starting screen

        //positioning the header in the center and setting its font
        header.setFont(Font.font ("Georgia", FontWeight.BOLD,55));
        header.setTranslateY(-70);

        //creating betting textfield and setting width and height
        TextField betTextField = new TextField();
        betTextField.setMaxWidth(450);
        betTextField.setPrefHeight(50);

        //creating text to put next to the text fields
        Text walletText = new Text(10, 20, "(Wallet Amount:  " + game.totalWinnings+")");
        //setting font for the text
        walletText.setFont(Font.font ("Georgia", 25));

        //creating enter button, setting font, size, and positioning
        Button enterButton = new Button("Enter");
        enterButton.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
        enterButton.setAlignment(Pos.CENTER);

        //making the button round
        enterButton.setStyle("-fx-background-radius: 40;");
        enterButton.setPrefWidth(300);
        enterButton.setPrefHeight(60);

        //if enter button is pressed, check if input is valid
        enterButton.setOnAction(e->{
            boolean checkBet = false;
            double betAmount = 0.0;

            //this is checking to make sure input is a double
            try {
                betAmount = Double.parseDouble(betTextField.getText());
                checkBet = true;
            } catch (NumberFormatException nfe) {
                checkBet = false;
            }

            //checking to make sure betAmount is not negative and a double
            if (betAmount < 0 && checkBet){
                betTextField.clear();
            }
            //if checkBet is valid and the betAmount is greater than wallet, clear the bet text field
            else if(checkBet && betAmount > game.totalWinnings){
                betTextField.clear();
            }
            //checks to see if the betting amount value type is a double
            else if(!checkBet){
                betTextField.clear();
            }
            //if everything is valid, set betAmount and go to play screen
            else{
                game.currentBet = betAmount;
                //if they click the start button, take them to the playing game scene
                try{
                    //first create deck and shuffle cards
                    game.theDealer.shuffleDeck();

                    //get first two cards for the player and banker
                    game.playerHand = game.theDealer.dealHand();
                    game.bankerHead = game.theDealer.dealHand();

                    playScene(primaryStage);

                    //if the dealer and the player got a blackjack, then go to push screen
                    if (game.gameLogic.gotBlackJack(game.playerHand, game.bankerHead).equals("push")){
                        game.evaluateWinnings();
                        pushScreen(primaryStage);

                    }
                    //if only the player got a blackjack and dealer did not, evaluate winnings then go to blackJackScreen
                    else if (game.gameLogic.gotBlackJack(game.playerHand, game.bankerHead).equals("player")) {
                        game.evaluateWinnings();
                        blackJackScreen(primaryStage);

                    }
                    //if the dealer got a blackjack and the player did not
                    else if (game.gameLogic.gotBlackJack(game.playerHand, game.bankerHead).equals("dealer")) {
                        game.evaluateWinnings();

                        //if player's totalWinnings equal 0 after losing, go to youreBroke screen
                        if(game.totalWinnings == 0.0){
                            try {
                                youreBroke(primaryStage);
                            } catch (Exception exception) {
                                throw new RuntimeException(exception);
                            }
                        }
                        //else, go to youLose screen
                        else{
                            youLose(primaryStage);
                        }
                    }
                }catch(Exception ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        //header and wallet amount message
        VBox v0 = new VBox(-50, header, walletText);
        v0.setAlignment(Pos.CENTER);

        //adding header, walletText, and textField together
        VBox v1 = new VBox(60, v0, betTextField);
        v1.setAlignment(Pos.CENTER);
        v1.setTranslateY(10);

        //putting everything including the button together
        VBox v2 = new VBox(65, v1, enterButton);
        v2.setAlignment(Pos.CENTER);

        //creating a pane
        BorderPane pane = new BorderPane();
        pane.setCenter(v2);
        pane.setPadding(new Insets(30)); //makes sure stuff does not touch the border

        //adding a border color
        BorderWidths newBorderWidth = new BorderWidths(10);
        BorderStroke newBorderStroke = new BorderStroke(Color.DARKGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, newBorderWidth);
        Border newBorder = new Border(newBorderStroke);
        pane.setBorder(newBorder);

        //creating scene
        primaryStage.setResizable(false);
        Scene startingBetScene = new Scene(pane,700,700);
        primaryStage.setScene(startingBetScene);
        primaryStage.show();

        //background color of scene
        pane.setStyle("-fx-background-color: #8FBC8F;");
    }
}

