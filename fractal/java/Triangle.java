/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.java;

/**
 *
 * @author yigit
 */import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
public class Triangle extends Polygon{ // equiletarel triangle
    private Point2D p1;
    private Point2D p2;
    private Point2D p3;
    
    public Triangle(Point2D p1, Point2D p2, Point2D p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
        
    }
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3){
        this.p1 = new Point2D(x1, y1);
        this.p2 = new Point2D(x2, y2);
        this.p3 = new Point2D(x3, y3);
        this.getPoints().addAll(x1,y1,x2,y2,x3,y3);
    }
    public Point2D getP1(){
        return this.p1;
    }
    public Point2D getP2(){
        return this.p1;
    }
    public Point2D getP3(){
        return this.p1;
    }
    public double getSide(){
        return this.p1.distance(p2);
    }
    public void setP1(double x, double y){//new X Y
        this.getPoints().remove(0);
        this.getPoints().remove(0);
        this.p1 = new Point2D(x,y);
        this.getPoints().add(0,p1.getX());
        this.getPoints().add(1,p1.getY());         
    }
    public void setP2(double x, double y){//new X Y
        this.getPoints().remove(2);
        this.getPoints().remove(2);
        this.p2 = new Point2D(x,y);
        this.getPoints().add(2,p2.getX());
        this.getPoints().add(3,p2.getY());
    }
    public void setP3(double x, double y){//new X Y
        this.getPoints().remove(4);
        this.getPoints().remove(4);
        this.p3 = new Point2D(x,y);
        this.getPoints().add(4,p3.getX());
        this.getPoints().add(5, p3.getY());
    }
    public void moveX(double x){
        setP1(this.p1.getX() + x, this.p1.getY());
        setP2(this.p2.getX() + x, this.p2.getY());
        setP3(this.p3.getX() + x, this.p3.getY());
    }
    public void moveY(double y){
        setP1(this.p1.getX(), this.p1.getY() + y);
        setP2(this.p2.getX(), this.p2.getY() + y);
        setP3(this.p3.getX(), this.p3.getY() + y);
    }
    public void moveP1toY(Point2D p){
        //move this triangle's point to the given point's y 
        //MOVES THE POINT UP
        double height = this.getSide() * Math.sqrt(3) / 2;
        setP1(this.p1.getX(), p.getY());
        setP2(this.p2.getX(), p.getY() - height / 3);
        setP3(this.p3.getX(), p.getY() - height / 3);
    }
    @Override
    public String toString(){
        return "P1: " + this.p1 + "\nP2: " + this.p2 + "\nP3: " + this.p3;
        
    }
}
