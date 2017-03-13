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
import ua.cn.al.teach.figures.engine.GraphicsEngine;
import ua.cn.al.teach.util.ShapeFactory;

/**
 *
 * @author al
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Bow extends Shape implements Serializable {
        static
	{
	ShapeFactory.getInstance().registerProduct("4", new Bow());
	}
    private int height;
    private int width;
    private double start_angle;
    private double arcExtent;
    public Bow(){
    x=0;
    y=0;
    height=0;
    width=0;
    start_angle=0;
    arcExtent=0;
    }
    public Bow(Point center){
    x=center.x;
    y=center.y;
    height=50;
    width=50;
    start_angle=0;
    arcExtent=360;
    }
    public Bow(Point center, int h){
    x=center.x;
    y=center.y;
    height=h;
    width=0;
    start_angle=0;
    arcExtent=0;
    }
    public Bow(Point center, int h, int w){
    x=center.x;
    y=center.y;
    height=h;
    width=w;
    start_angle=0;
    arcExtent=-180;
    }
    public Bow(Point center, int h, int w, double s_a){
    x=center.x;
    y=center.y;
    height=h;
    width=w;
    start_angle=s_a;
    arcExtent=0;
    }
    public Bow(Point center, int h, int w, double s_a, double ar_ex) {
        x=center.x;
        y=center.y;
        this.height=h;
        this.width=w;
        this.start_angle=s_a;
        this.arcExtent=ar_ex;
    }
    @Override
    public void draw(GraphicsEngine ge) {
      ge.Bow(new Point(x, y), width, height);
    }
    public boolean isSelected(Point temp){
     if(temp.getX()<x || temp.getX()>x+width)return false;
     if(temp.getY()<y+height/2 || temp.getY()>y+height) return false;
     return true;
    }

    @Override
    public void moveTo(Point p) {
        x+=p.getX();
        y+=p.getY();
    }

    @Override
    public Shape createShape(List<Point> point, double FirstParam, double SecondParam) {
        TypeChecker.setType(ShapeType.Bow);
      int w=(int)FirstParam;
       int h=(int)SecondParam;
        if(!point.isEmpty()) return new Bow(new Point(point.get(0).getX()-w/2, point.get(0).getY()-h), h, w);
        return null;
    }

    @Override
    public void AddTempCircle(Composite TempCircle) {
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x+width/2, (int) y+height), 1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x+width, (int) y+height/2), 1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x, (int) y+height/2), 1+this.lineWidth));
        TempCircle.SetFillForAll(javafx.scene.paint.Color.BLUE);
    }
}
