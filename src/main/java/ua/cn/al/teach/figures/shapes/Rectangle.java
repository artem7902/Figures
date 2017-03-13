/*
 * 
 * 
 */
package ua.cn.al.teach.figures.shapes;

import ua.cn.al.teach.util.ShapeType;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import ua.cn.al.teach.figures.engine.GraphicsEngine;
import ua.cn.al.teach.util.ShapeFactory;
import ua.cn.al.teach.util.TypeChecker;

/**
 *
 * @author al
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Rectangle extends Shape implements Serializable {
     static
	{
	ShapeFactory.getInstance().registerProduct("7", new Rectangle());
	}
    private int height;
    private int width;
     public Rectangle(){}
    public Rectangle(Point center){
    x=center.x;
    y=center.y;
    height=50;
    width=50;
    }
    public Rectangle(Point center, int h, int w){
    x=center.x;
    y=center.y;
    height=h;
    width=w;
    }
     @Override
    public Shape createShape(List<Point> point, double FirstParam, double SecondParam) {
        TypeChecker.setType(ShapeType.Rectangle);
        if(!point.isEmpty()){
            int w=(int)FirstParam;
            int h=(int)SecondParam;
            return new Rectangle(new Point(point.get(0).getX()-w/2, point.get(0).getY()-h/2), h, w);
        }
        return null;
    }

    @Override
    public void draw(GraphicsEngine ge) {
        ge.Rectangle(new Point(x, y), width, height);
    }
        @Override
    public boolean isSelected(Point temp) {
        if(y>temp.getY() || y+height<temp.getY())return false;
        if(x>temp.getX() || x+width<temp.getX())return false;
        return true;
    }

    @Override
    public void moveTo(Point p) {
       x+=p.getX();
       y+=p.getY();
    }

    @Override
    public void AddTempCircle(Composite TempCircle) {
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x, (int) y), 1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x, (int) y+height), 1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x+width, (int) y), 1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) x+width, (int) y+height),1+this.lineWidth));
        TempCircle.SetFillForAll(javafx.scene.paint.Color.BLUE);
    }
}
