/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest2;

import java.util.ArrayList;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jonas
 */
public class GUITest2 extends Application {
    ContextMenu popup;
    TextField currentParentField;
    private ArrayList<String> ListOfStrings = new ArrayList();
    private ArrayList<MenuItem> ListOfMenuItems = new ArrayList<>();
    private String filter = "";
    private String[] test = {"Abacate", "Abacaxi", "Ameixa", "Amora", "Araticum", "Atemoia", "Avocado",
			"Banana prata", "Caju", "Cana descascada", "Caqui", "Caqui Fuyu", "Carambola", "Cereja", "Coco verde",
			"Figo", "Figo da Índia", "Framboesa", "Goiaba", "Graviola", "Jabuticaba", "Jambo", "Jambo rosa", "Jambolão",
			"Kino (Kiwano)", "Kiwi", "Laranja Bahia", "Laranja para suco", "Laranja seleta", "Laranja serra d’água",
			"Laranjinha kinkan", "Lichia", "Lima da pérsia", "Limão galego", "Limão Taiti", "Maçã argentina",
			"Maçã Fuji", "Maçã gala", "Maçã verde", "Mamão formosa", "Mamão Havaí", "Manga espada", "Manga Haden",
			"Manga Palmer", "Manga Tommy", "Manga Ubá", "Mangostim", "Maracujá doce", "Maracujá para suco", "Melancia",
			"Melancia sem semente", "Melão", "Melão Net", "Melão Orange", "Melão pele de sapo", "Melão redinha",
			"Mexerica carioca", "Mexerica Murcote", "Mexerica Ponkan", "Mirtilo", "Morango", "Nectarina",
			"Nêspera ou ameixa amarela", "Noni", "Pera asiática", "Pera portuguesa", "Pêssego", "Physalis", "Pinha",
			"Pitaia", "Romã", "Tamarilo", "Tamarindo", "Uva red globe", "Uva rosada", "Uva Rubi", "Uva sem semente",
			"Abobora moranga", "Abobrinha italiana", "Abobrinha menina", "Alho", "Alho descascado",
			"Batata baroa ou cenoura amarela", "Batata bolinha", "Batata doce", "Batata inglesa", "Batata yacon",
			"Berinjela", "Beterraba", "Cebola bolinha", "Cebola comum", "Cebola roxa", "Cenoura", "Cenoura baby",
			"Couve flor", "Ervilha", "Fava", "Gengibre", "Inhame", "Jiló", "Massa de alho", "Maxixe", "Milho",
			"Pimenta biquinho fresca", "Pimenta de bode fresca", "Pimentão amarelo", "Pimentão verde",
			"Pimentão vermelho", "Quiabo", "Repolho", "Repolho roxo", "Tomate cereja", "Tomate salada",
			"Tomate sem acidez", "Tomate uva", "Vagem", "Agrião", "Alcachofra", "Alface", "Alface americana",
			"Almeirão", "Brócolis", "Broto de alfafa", "Broto de bambu", "Broto de feijão", "Cebolinha", "Coentro",
			"Couve", "Espinafre", "Hortelã", "Mostarda", "Rúcula", "Salsa", "Ovos brancos", "Ovos de codorna",
			"Ovos vermelhos"
        
    };
            

    @Override
    public void start(Stage primaryStage) {
        addToListOfStrings(test);    
        
       VBox root = new VBox();
     root.setSpacing(10);
     Scene scene = new Scene( root );
     //
     popup = new ContextMenu();
    /* MenuItem item1 = new MenuItem("1111");
     MenuItem item2 = new MenuItem("2222");
     MenuItem item3 = new MenuItem("3333");
     popup.getItems().addAll( item1, item2, item3 );*/
     setMenuItem(ListOfStrings);
    
     popup.setOnAction( popupAction );
     
     
     
    
     //
     TextField textField_1 = new TextField();
     TextField textField_2 = new TextField();
     //textField_1.addEventHandler( KeyEvent.ANY, keyEventHandler );
     textField_1.setOnKeyPressed(this::handleOnKeyPressed);
    // textField_2.addEventHandler( KeyEvent.ANY, keyEventHandler );     //
     root.getChildren().addAll( textField_1, textField_2 );

     primaryStage.setScene(scene);
     primaryStage.setTitle("TextField05");
     primaryStage.setX(300); primaryStage.setY(200); primaryStage.setWidth(300);
     primaryStage.setHeight(320);
     primaryStage.show();
   }
    private void handleOnKeyPressed(KeyEvent e){
         EventTarget target = e.getTarget();
       currentParentField = (TextField)target;
       EventType<? extends Event> type = e.getEventType();
        KeyCode code = e.getCode();
         System.out.println(e.getCode().toString());
       
         if( code == KeyCode.DOWN ){
           popup.show( currentParentField, Side.BOTTOM, 0, 0 ); //<- this
         }
         if (code.isLetterKey()) {
                filter += e.getText();
                System.out.println("Filter: "+filter.toString());
           }
         if (code == KeyCode.ESCAPE) {
			filter = "";
		}
        if (filter.length() == 0) {
            setMenuItem(ListOfStrings);
            popup.hide();
        }
      else{
            ObservableList<MenuItem> filteredList = FXCollections.observableArrayList();
           popup.hide();
           Stream<MenuItem> items = ListOfMenuItems.stream();
           String textUsr = filter.toString().toLowerCase();
            System.out.println("TextUsr: "+textUsr);
             popup.getItems().clear();
            for (int i = 0; i <ListOfStrings.size(); i++) {
                if (ListOfStrings.get(i).toLowerCase().contains(textUsr)) {
                    setMenuItemOne(ListOfStrings.get(i));
                }
            }
           popup.show( currentParentField, Side.BOTTOM, 0, 0 );
            /*
                        Stream<T> itens = cmd.getItems().stream();
			String txtUsr = filter.toString().toLowerCase();
			itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);
			cmd.getTooltip().setText(txtUsr);
     
     */
         }
    }
   //
   EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
     public void handle(KeyEvent e){
       EventTarget target = e.getTarget();
       currentParentField = (TextField)target;
       EventType<? extends Event> type = e.getEventType();
        KeyCode code = e.getCode();
         System.out.println(e.getCode().toString());
       
         if( code == KeyCode.DOWN ){
           popup.show( currentParentField, Side.BOTTOM, 0, 0 ); //<- this
         }
         if (code.isLetterKey()) {
                filter += e.getText();
                System.out.println("Filter: "+filter.toString());
		}
       /*  if (code == KeyCode.ESCAPE) {
			filter = "";
		}
		if (filter.length() == 0) {
                    setMenuItem(ListOfStrings);
                    popup.hide();
		}
         else{
           popup.hide();
           Stream<MenuItem> items = ListOfMenuItems.stream();
           String textUsr = filter.toString().toLowerCase();
           items.filter(el -> el.toString().toLowerCase().contains(textUsr)).forEach(popup.getItems()::add);
            /*
                        Stream<T> itens = cmd.getItems().stream();
			String txtUsr = filter.toString().toLowerCase();
			itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);
			cmd.getTooltip().setText(txtUsr);
     
     */
        // }
       }
     
   };
   //
   EventHandler<ActionEvent> popupAction = new EventHandler<ActionEvent>() {
     public void handle( ActionEvent e ){
       MenuItem src = (MenuItem)e.getTarget();
       String text = src.getText();
       currentParentField.setText( text );
    }
     
   };
   
   public void setMenuItem(ArrayList<String> temp){
       popup.getItems().clear();
       for (int i = 0; i < temp.size(); i++) {
            MenuItem item = new MenuItem(temp.get(i));
            popup.getItems().add(item);
       }
   }
   
   public void setMenuItemOne(String temp){      
       MenuItem item = new MenuItem(temp);
       popup.getItems().add(item);
   }
   
   public void addToListOfStrings(String[] temp){
       for (int i = 0; i < temp.length; i++) {
           ListOfStrings.add(temp[i]);
       }
   }

    public static void main(String[] args) {
        launch(args);
    }
    
   
       }
