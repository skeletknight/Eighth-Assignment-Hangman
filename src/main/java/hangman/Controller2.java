package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static hangman.DatabaseManager.*;


public class Controller2 {
    int count_of_winning=0;
    int wrong_gusses = 0;

    String name ="ss";
    static long endTime;
    static long startTime =0;
    long result = (endTime-startTime)/1000000000;

    @FXML
    ImageView img;
    Image image2 = new Image(getClass().getResourceAsStream("images/2.png"));
    Image image3 = new Image(getClass().getResourceAsStream("images/3.png"));
    Image image4 = new Image(getClass().getResourceAsStream("images/4.png"));
    Image image5 = new Image(getClass().getResourceAsStream("images/5.png"));
    Image image6 = new Image(getClass().getResourceAsStream("images/6.png"));
    Image image7 = new Image(getClass().getResourceAsStream("images/7.png"));
    Image imagewon = new Image(getClass().getResourceAsStream("images/won.jpg"));

    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    @FXML
    TextField tf3;
    @FXML
    TextField tf4;
    @FXML
    TextField tf5;
    @FXML
    TextField tf6;
    @FXML
    TextField tf7;
    @FXML
    TextField tf8;
    @FXML
    TextField input;
    @FXML
    Label hint;
    @FXML
    Label letter_count;
    @FXML
    Label hint_label;

    
    String word = getRandomWordFromApi ();


    //String hint_str = split[1];
    int letter_size = word.length();
    public void initialize(){
        setHint();
    }
    public void setHint(){
        //hint.setText(hint_str);
        letter_count.setText(letter_size+" Letters");

        if(letter_size==7){
            tf8.setVisible(false);
        }
        if(letter_size==6){
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if(letter_size==5){
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if(letter_size==4){
            tf5.setVisible(false);
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
    }

    public boolean win(){
        String nword =tf1.getText()+tf2.getText()+tf3.getText()+tf4.getText()+tf5.getText()+tf6.getText()+tf7.getText()+tf8.getText();
        if(nword.contains(word)){
            System.out.println("won");

            System.out.println(result);
            //setImage();
            count_of_winning++;
            img.setImage(imagewon);
            endTime = System.nanoTime();
            result = (endTime-startTime)/1000000000;
            saveGameInfo(name,word,wrong_gusses,result,true);
            System.out.println(endTime);
            System.out.println(endTime-startTime);
            //System.out.println(startTime+" "+endTime);
            //System.out.println((endTime-startTime));
            return true;
        }
        return false;
    }
    
    
    public void CheckInput(){
        if(startTime ==0){
            startTime = System.nanoTime();
        }

        System.out.println(startTime);
        String str = input.getText();
         
        if (word.contains(str)) {
            int index = 0;
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (String.valueOf(c).equals(str)) {
                    setLetter(index, Character.toString(c));
                }
                index++;
            }
        }
        else {
            setImage();
        }
    }
    public void setLetter(int index,String str){
        if(index==0)
            tf1.setText(str);
        else if(index==1)
            tf2.setText(str);
        else if(index==2)
            tf3.setText(str);
        else if(index==3){
            tf4.setText(str);
            win();}
        else if(index==4){

            tf5.setText(str);
            win();  }
        else if(index==5){

            tf6.setText(str);
            win();  }
        else if(index==6){

            tf7.setText(str);
            win();  }
        else if(index==7){

            tf8.setText(str);
            win();  }

    }

    int life=6;
    public void setImage(){

        if(win()){
            img.setImage(imagewon);
            endTime = System.nanoTime();
        }
         else if(life==6){
             wrong_gusses++;
            img.setImage(image2);}
        else if(life==5){
            wrong_gusses++;
            img.setImage(image3);}
        else if(life==4) {
            wrong_gusses++;
            img.setImage(image4);
        }
        else if(life==3){
            wrong_gusses++;
            img.setImage(image5);}

        else if(life==2) {
            wrong_gusses++;
            img.setImage(image6);
        }
        else if(life==1){
            wrong_gusses++;
            endTime = System.nanoTime();
            System.out.println((endTime-startTime)/1000000000);
            saveGameInfo(name,word,wrong_gusses,result,false);

            img.setImage(image7);}
        life--;
    }






    public void changeScene(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameScene.fxml")));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Hangman Game");
        window.setScene(new Scene(parent, 800, 650));
        window.show();
    }
}
