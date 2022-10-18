/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.java;

/**
 *
 * @author yigit
 */
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
public class FractalJava extends Application {
    private ArrayList<Triangle> centerTriangles = new ArrayList<>();
    private ArrayList<Triangle> newCenterTriangles = new ArrayList<>();
    private Pane pane = new Pane();
    @Override
    public void start(Stage primaryStage){
        BorderPane borderPane = new BorderPane();
        TextField tf = new TextField();
        Label lbl = new Label("Enter an order");
        Button bt = new Button("OK");
        tf.setPrefColumnCount(3);
        tf.setAlignment(Pos.CENTER_RIGHT);
        HBox box = new HBox();
        box.getChildren().addAll(lbl,tf,bt);
        box.setSpacing(20);
        borderPane.setCenter(pane);
        borderPane.setBottom(box);
        Scene scene = new Scene(borderPane, 600, 600);
        primaryStage.setScene(scene);
        recursiveMethod(4);
        primaryStage.show();
        
        bt.setOnAction(e -> {
            
                int n = Integer.parseInt(tf.getText());
                pane.getChildren().clear();
                centerTriangles.clear();
                newCenterTriangles.clear();
                recursiveMethod(n);
        });
        
    }
    private void recursiveMethod(int n){ // n specifies the fractal order
        if(n == 0){
            //base case
            Triangle t = new Triangle(300,20, 60, 20 + 240 * Math.sqrt(3), 540,20 + 240 * Math.sqrt(3));
            t.setFill(Color.WHITE);
            t.setStroke(Color.BLACK);
            pane.getChildren().add(t);
           System.out.println("0 invoked");
        }
        else if(n == 1){
            //base case 2
            
            recursiveMethod(n - 1);
            Triangle t = new Triangle(300, 20 + 240 * Math.sqrt(3),180, + 20 + 120 * Math.sqrt(3),420, 20 + 120 * Math.sqrt(3));
            t.setStroke(Color.RED);
            //t.setFill(Color.RED);
            pane.getChildren().add(t);
            centerTriangles.add(t);
            System.out.println("1 invoked");
        }
        else{
            recursiveMethod(n - 1);
            for(int i = 0; i < centerTriangles.size(); i++){
                draw(centerTriangles.get(i));
                System.out.println("l0");
            }
            centerTriangles.clear();
            while(newCenterTriangles.size() != 0){
                centerTriangles.add(newCenterTriangles.remove(0));
                System.out.println("l1");
            }
        }
    }
    private void draw(Triangle centerTriangle){//update newCenterTriangles
        Point2D centerP1 = centerTriangle.getP1();
        Point2D centerP2 = centerTriangle.getP2();
        Point2D centerP3 = centerTriangle.getP3();

        //draw left triangle
        System.out.println("Center triangle is " + centerTriangle);
        final double d = centerTriangle.getSide();
        Point2D leftMidPoint = centerTriangle.getP1().midpoint(centerTriangle.getP2()); // p3
        Point2D P1 = new Point2D(leftMidPoint.getX() - d / 4, leftMidPoint.getY() + d * Math.sqrt(3) / 4); // hata olabilir
        Point2D P2 = new Point2D(leftMidPoint.getX() - d / 2, leftMidPoint.getY());
        Triangle tLeft = new Triangle(P1,P2,leftMidPoint);
        tLeft.setFill(Color.GREEN);
        //draw right triangle
        Point2D rightMidPoint = centerTriangle.getP1().midpoint(centerTriangle.getP2()); //p2
        Point2D P3 = new Point2D(rightMidPoint.getX() + d / 2, rightMidPoint.getY());
        P1 = new Point2D(leftMidPoint.getX() + d / 4, P1.getY());
        Triangle tRight = new Triangle(P1,rightMidPoint,P3);
        tRight.setFill(Color.ORANGE);
        //draw top triangle
        Point2D topMidPoint = centerTriangle.getP3().midpoint(centerTriangle.getP2()); //p1
        P2 = new Point2D(topMidPoint.getX() - d / 4, topMidPoint.getY() - d * Math.sqrt(3) / 4); // hata olabilir
        P3 = new Point2D(topMidPoint.getX() + d / 4, P2.getY()); // d / 4 ü 2 yap 82 içinde aynısı
        Triangle tTop = new Triangle(topMidPoint, P2, P3);
        tTop.setFill(Color.BISQUE);
        System.out.println("Before adjustments: " + tLeft.toString() + "\n" + tRight.toString() + "\n" + tTop.toString());
        //Adjustments
        tLeft.moveX(-d/4);
        tLeft.moveY(-Math.sqrt(3) * d / 4);
        tRight.moveX(d/4);
        tRight.moveY(-Math.sqrt(3) * d / 4);
        tTop.moveY(-Math.sqrt(3) * d / 2);
        System.out.println("After adjustments: " + tLeft.toString() + "\n" + tRight.toString() + "\n" + tTop.toString());
        //Second adjustments
        
        
        pane.getChildren().addAll(tLeft, tRight, tTop);
        newCenterTriangles.add(tTop);
        newCenterTriangles.add(tLeft);
        newCenterTriangles.add(tRight);
        
       
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
/*
private void draw(Triangle centerTriangle){//update newCenterTriangles
        //draw left triangle
        System.out.println("Center triangle is " + centerTriangle);
        final double d = centerTriangle.getSide();
        Point2D leftMidPoint = centerTriangle.getP1().midpoint(centerTriangle.getP2()); // p3
        Point2D P1 = new Point2D(leftMidPoint.getX() - d / 4, leftMidPoint.getY() + d * Math.sqrt(3) / 4); // hata olabilir
        Point2D P2 = new Point2D(leftMidPoint.getX() - d / 2, leftMidPoint.getY());
        Triangle tLeft = new Triangle(P1,P2,leftMidPoint);
        tLeft.setFill(Color.GREEN);
        //draw right triangle
        Point2D rightMidPoint = centerTriangle.getP1().midpoint(centerTriangle.getP2()); //p2
        Point2D P3 = new Point2D(rightMidPoint.getX() + d / 2, rightMidPoint.getY());
        P1 = new Point2D(leftMidPoint.getX() + d / 4, P1.getY());
        Triangle tRight = new Triangle(P1,rightMidPoint,P3);
        tRight.setFill(Color.ORANGE);
        //draw top triangle
        Point2D topMidPoint = centerTriangle.getP3().midpoint(centerTriangle.getP2()); //p1
        P2 = new Point2D(topMidPoint.getX() - d / 4, topMidPoint.getY() - d * Math.sqrt(3) / 4); // hata olabilir
        P3 = new Point2D(topMidPoint.getX() + d / 4, P2.getY()); // d / 4 ü 2 yap 82 içinde aynısı
        Triangle tTop = new Triangle(topMidPoint, P2, P3);
        tTop.setFill(Color.BISQUE);
        pane.getChildren().addAll(tLeft, tRight, tTop);
        newCenterTriangles.add(tTop);
        newCenterTriangles.add(tLeft);
        newCenterTriangles.add(tRight);
        
        
    }
*/
