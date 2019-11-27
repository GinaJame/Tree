import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.HashSet;
import java.util.Optional;

public class AVLGUI extends Application {

    @Override 
    public void start(Stage primaryStage) {
        Tree<Integer> AVLTree = new Tree<>(); 
        BorderPane pane = new BorderPane();
        AVLView view1 = new AVLView(AVLTree);
        pane.setCenter(view1);
        view1.setPrefWidth(250);
        pane.setPrefWidth(250);
        BorderPane.setMargin(view1, new Insets(10, 20, 10, 20));

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btClear = new Button("Clear");
        Button btExit = new Button("Exit");
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(new Label("Enter a number: "), tfKey, btInsert, btDelete, btClear, btExit);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
        hBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(10, 10, 10, 10));

        btInsert.setOnAction(e -> {
            try {
                int key = Integer.parseInt(tfKey.getText());
                AVLTree.insert(key);
                view1.displayAVLTree();

                tfKey.setText("");
                tfKey.requestFocus();
            } catch (NumberFormatException ex) {
                invalidKey(tfKey, "Key must be an integer!");
            }
        });

        btDelete.setOnAction(e -> {
            try {
                int key = Integer.parseInt(tfKey.getText());
                AVLTree.delete(key);
                view1.displayAVLTree();

                tfKey.setText("");
            } catch (NumberFormatException ex) {
                invalidKey(tfKey, "Key must be an integer!");
            }
        });

        btClear.setOnAction(e -> {
            tfKey.clear();
            AVLTree.clear();
            view1.displayAVLTree();
            view1.setStatus("AVL Tree deleted");
        });

        btExit.setOnAction(e -> {
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("Goodbye!");
            exit.setContentText("Do you really wanna end the exposition? (All students currenty exposing will be graded 100)");
            Optional<ButtonType> result = exit.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.exit(0);
            }
        });

        
        Scene scene = new Scene(pane, 900, 900);
        primaryStage.setTitle("Tree Visualizer"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }

   
    private void invalidKey(TextField key, String alertHeader) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(alertHeader);
        alert.setContentText("Please enter an integer in the key box and try again");
        key.requestFocus();
        alert.showAndWait();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
}