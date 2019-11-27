
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;


public class AVLView extends Pane {
   
    private Tree tree = new Tree<>();
    private double radius = 30; 
    private double vGap = 70;
    AVLView(Tree<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayAVLTree() {
        this.getChildren().clear(); 
        if (tree.getRoot() != null) {
           
            displayAVLTree(tree.getRoot(), getWidth() / 2, vGap, getWidth()/Scaler(tree.getRoot().getHeight()));
        }
    }

   
    private void displayAVLTree(Node<Integer> root, double x, double y, double hGap) {
       
        if (root.getRightChild() != null) {
           
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
          
            displayAVLTree(root.getRightChild(), x - hGap, y + vGap, hGap / 2);
        }

        if (root.getLeftChild() != null) {
           
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
         
            displayAVLTree(root.getLeftChild(), x + hGap, y + vGap, hGap / 2);
        }

      
        Circle circle = new Circle(x, y, radius);


        circle.setFill(Color.hsb(tree.getRoot().getHeight()*10,1.0,1.0));
        Text t = new Text(x - 7, y + 5, root.getValue() + "");
        t.setFill(Color.WHITE);
        t.setFont(Font.font ("OpenSans",Scaler(root.getValue())*6));
        getChildren().addAll(circle, t);
        
    }

    public int Scaler (int number){
        int n = String.valueOf(number).length();
        
        if (n==5){
            return 1;
        }
        else if (n==4){
            return 2;
        }
        else if (n==3){
            return 3;
        }
        else if (n==2){
            return 4;
        }
        else if (n==1){    
        return 5;
        }

        return n;
    }
}