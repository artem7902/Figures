/*
 * 
 * 
 */
package ua.cn.al.teach.figures.shapes;

import ua.cn.al.teach.util.ShapeType;
import ua.cn.al.teach.util.TypeChecker;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import ua.cn.al.teach.figures.engine.GraphicsEngine;
import ua.cn.al.teach.util.ShapeFactory;

/**
 *
 * @author al
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Line extends Shape implements Serializable{
            static
	{
            ShapeFactory.getInstance().registerProduct("1", new Line());
	}
    @XmlElement( required = true )
    private Point A;
    @XmlElement( required = true )
    private Point B;
    private int a;
    private int b;
    private boolean vertical;
    private boolean first_exact;
    private final int FIX_BASE=1000;
    public Line() {
       
    }
    @Override
    public void draw(GraphicsEngine ge) {
        ge.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
             
    }

    public Line(Point A, Point B) {
        if(A.getX()<B.getX()){
        this.A = A;
        this.B = B;
        first_exact=true;
        }
        else{
        this.A=B;
        this.B=A;
        first_exact=false;
        }
        if(A.getX()==B.getX()){
        vertical=true;
        a=0;
        b=a;
        }
        else{
        vertical=false;
        a=(FIX_BASE*(B.getY()-A.getY()))/(B.getX()-A.getX());
        b=A.getY()-(a*A.getX())/FIX_BASE;
        }
    }
    public Point getA(){
    return A;
    }
    public Point getB(){
    return B;
    }
     public int geta(){
    return a;
    }
    public int getb(){
    return b;
    }
    public int getFIX_BASE(){
    return FIX_BASE;
    }
    public boolean getvertical(){
    return vertical;
    }
    public boolean getf_extract(){
    return first_exact;
    }
    public Point setA(Point A){
    return this.A=A;
    }
    public Point setB(Point B){
    return this.B=B;
    }
    @Override
    public boolean isSelected(Point temp){
    double rastoy=Math.sqrt(Math.pow(A.getX()-B.getX(), 2)+Math.pow(A.getY()-B.getY(), 2));
    double At=Math.sqrt(Math.pow(A.getX()-temp.getX(), 2)+Math.pow(A.getY()-temp.getY(), 2));
    double Bt=Math.sqrt(Math.pow(B.getX()-temp.getX(), 2)+Math.pow(B.getY()-temp.getY(), 2));
    return Math.abs(rastoy-(At+Bt))<0.1+Math.sqrt(lineWidth);
    }
    @Override
    public void moveTo(Point p) {
        A.setX(A.getX()+p.getX());
        A.setY(A.getY()+p.getY());
        B.setX(B.getX()+p.getX());
        B.setY(B.getY()+p.getY());
    }
    @Override
    public Shape createShape(List<Point> point, double FirstParam, double SecondParam) {
        TypeChecker.setType(ShapeType.Line);
        if(point.size()==2) return new Line(point.get(0), point.get(1));
        else return null;
    }

    @Override
    public void AddTempCircle(Composite TempCircle) {
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) A.getX(), (int) A.getY()), 1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) B.getX(), (int) B.getY()), 1+this.lineWidth));
        TempCircle.SetFillForAll(javafx.scene.paint.Color.BLUE);
    }
}
