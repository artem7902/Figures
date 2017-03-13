/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.figures.shapes;

import ua.cn.al.teach.util.ShapeType;
import ua.cn.al.teach.util.TypeChecker;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ua.cn.al.teach.figures.engine.GraphicsEngine;
import ua.cn.al.teach.util.ShapeFactory;

/**
 *
 * @author artem
 */
@XmlType(name="PolyLine")
@XmlAccessorType(XmlAccessType.NONE)
public class PolyLine extends Shape implements Serializable {
        static
	{
	ShapeFactory.getInstance().registerProduct("2", new PolyLine());
	}
    @XmlElementWrapper(name = "plist", required = true )
    @XmlElement(name = "point")
    protected List <Point> plist=plist=new LinkedList<>();;
    public PolyLine(){
    }
    public PolyLine(Point... Points){
        plist.addAll(Arrays.asList(Points));
    }
    public void addPoints(Point... Points){
    plist.addAll(Arrays.asList(Points));
    }
    @Override
    public void draw(GraphicsEngine ge) {
        ge.PolyLine(plist);
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
        TypeChecker.setType(ShapeType.PolyLine);
        if(point.size()>1) return new PolyLine(point.toArray(new Point[point.size()]));
        else return null;
    }

    @Override
    public boolean isSelected(Point temp) {
        Point prev=null;
        for(Point p : plist){
        if(prev!=null)
        if(new Line(new Point(prev.getX(),prev.getY()),new Point(p.getX(),p.getY())).isSelected(temp)) return true;
        prev=p;
        }
        return false;
    }

    @Override
    public void AddTempCircle(Composite TempCircle) {
        for(Point p : plist)
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) p.getX(), (int) p.getY()), 1+this.lineWidth));
        TempCircle.SetFillForAll(javafx.scene.paint.Color.BLUE);
    }
    public void removePoint(int position){
    plist.remove(position);
    }
}
