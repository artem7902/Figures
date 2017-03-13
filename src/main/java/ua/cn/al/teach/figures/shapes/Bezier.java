/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.figures.shapes;

import ua.cn.al.teach.util.ShapeType;
import ua.cn.al.teach.util.TypeChecker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import ua.cn.al.teach.figures.engine.GraphicsEngine;
import ua.cn.al.teach.util.ShapeFactory;

/**
 *
 * @author artem
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Bezier extends Shape implements Serializable {
        static
	{
	ShapeFactory.getInstance().registerProduct("8", new Bezier());
	}
     @XmlElementWrapper(name = "plist", required = true )
    @XmlElement(name = "point")
    private List <Point> plist = new ArrayList<>();
    public Bezier(){
    }
    public Bezier(Point a, Point b, Point c) {
        plist.addAll(Arrays.asList(a,b,c));
    }
    public Bezier(Point a, Point b, Point c, Point d) {
        plist.addAll(Arrays.asList(a,b,c,d));
    }

    @Override
    public void draw(GraphicsEngine ge) {
        ge.strokeCurve(plist);
    }

    @Override
    public boolean isSelected(Point temp) {
        if(plist.size()==3){
        for(double t=0;t<=1;t+=0.0005){
            int tempx=(int)(Math.pow(1-t,2)*(plist.get(0).getX()+x)+2*(1-t)*t*(plist.get(1).getX()+x)+t*t*(plist.get(2).getX()+x));
            int tempy=(int)(Math.pow(1-t,2)*(plist.get(0).getY()+y)+2*(1-t)*t*(plist.get(1).getY()+y)+t*t*(plist.get(2).getY()+y));
            if(Math.abs(temp.getX()-tempx)<=5+this.lineWidth/2 && Math.abs(temp.getY()-tempy)<=5+this.lineWidth/2 )return true;
        }
                return false;
        }
        else{
        for(double t=0;t<=1;t+=0.0005){
            int tempx=(int)(Math.pow(1-t,3)*(plist.get(0).getX()+x)+3*Math.pow(1-t,2)*t*(plist.get(1).getX()+x)+3*(1-t)*t*t*(plist.get(2).getX()+x)+t*t*t*(plist.get(3).getX()+x));
            int tempy=(int)(Math.pow(1-t,3)*(plist.get(0).getY()+y)+3*Math.pow(1-t,2)*t*(plist.get(1).getY()+y)+3*(1-t)*t*t*(plist.get(2).getY()+y)+t*t*t*(plist.get(3).getY()+y));
            if(Math.abs(temp.getX()-tempx)<=5+this.lineWidth/2 && Math.abs(temp.getY()-tempy)<=5+this.lineWidth/2 )return true;
        }
                return false;
        }
    }

    @Override
    public void moveTo(Point p) {
       for (Point point : plist) {
        point.setX(point.getX()+p.getX());
        point.setY(point.getY()+p.getY());
        }
    }
    @Override
    public Shape createShape(List<Point> point, double FirstParam, double SecondParam) {
        TypeChecker.setType(ShapeType.Bezier);
        if(point.size()==3) return new Bezier(point.get(0), point.get(1), point.get(2));
        if(point.size()==4) return new Bezier(point.get(0), point.get(1), point.get(2), point.get(3));
        return null;
    }

    @Override
    public void AddTempCircle(Composite TempCircle) {
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) plist.get(0).getX(), (int) plist.get(0).getY()), 1+this.lineWidth));
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) plist.get(plist.size()-1).getX(), (int)  plist.get(plist.size()-1).getY()), 1+this.lineWidth));
        TempCircle.SetFillForAll(javafx.scene.paint.Color.BLUE);
    }
}
