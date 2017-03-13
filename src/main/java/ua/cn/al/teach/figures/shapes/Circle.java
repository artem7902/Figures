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
public class Circle extends Shape implements Serializable {
        static
	{
	ShapeFactory.getInstance().registerProduct("5", new Circle());
	}
    private int R;
     public Circle() {
        x=0;
        y=0;
        this.R=0;
    }
     public Circle(Point center) {
        x=center.x;
        y=center.y;
        R=2;
    }
  public Circle(int R) {
        x=R*2;
        y=R*2;
        this.R=R;
    }
    public Circle(Point center, int R) {
        x=center.x;
        y=center.y;
        this.R=R;
    }

    @Override
    public void draw(GraphicsEngine ge) {
       ge.Oval(new Point(x,y), R);
    }
    @Override
    public boolean isSelected(Point temp){
        return Math.sqrt(Math.pow(this.x-temp.getX(), 2)+Math.pow(this.y-temp.getY(), 2))<=this.R;
    }

    @Override
    public void moveTo(Point p) {
        x+=p.getX();
        y+=p.getY();
    }
    @Override
    public Shape createShape(List<Point> point, double FirstParam, double SecondParam) {
        TypeChecker.setType(ShapeType.Circle);
        if(!point.isEmpty()) return new Circle(point.get(0), (int)FirstParam);
        return null;
    }

    @Override
    public void AddTempCircle(Composite TempCircle) {
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x+R, (int) y+R),1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x-R, (int) y-R), 1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x+R, (int) y-R), 1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x-R, (int) y+R), 1+this.lineWidth));
        TempCircle.SetFillForAll(javafx.scene.paint.Color.BLUE);
    }
}
