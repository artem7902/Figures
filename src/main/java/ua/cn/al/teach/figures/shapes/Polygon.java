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
import java.util.List;
import ua.cn.al.teach.figures.engine.GraphicsEngine;
import ua.cn.al.teach.util.ShapeFactory;

/**
 *
 * @author artem
 */
public class Polygon extends PolyLine implements Serializable {
        static
	{
	ShapeFactory.getInstance().registerProduct("3", new Polygon());
	}
    public Polygon(){
    }
    public Polygon(Point... Points){
        plist.addAll(Arrays.asList(Points));
    }
    public void addPoints(Point... Points){
    plist.addAll(Arrays.asList(Points));
    }
    @Override
    public void draw(GraphicsEngine ge) {   
        ge.Polygon(plist);
    }
    @Override
    public void moveTo(Point p) {
       for (Point point : plist) {
        point.setX(point.getX()+p.getX());
        point.setY(point.getY()+p.getY());
        }
    }
        @Override
    public boolean isSelected(Point temp){
    int count=0;
    Point prev=null;
    for(Point p : plist){
        if(prev!=null)
        if(isAbout(new Line(new Point(prev.getX(),prev.getY()),new Point(p.getX(),p.getY())), temp))count++;
        prev=p;
        }
    if(isAbout(new Line(new Point(plist.get(plist.size()-1).getX(),plist.get(plist.size()-1).getY()),new Point(plist.get(0).getX(),plist.get(0).getY())), temp))count++;
    return (count % 2)==1;
    }
         public boolean isBetween(Line line, Point temp){
         if(line.getf_extract()) {
                        if(temp.getX()<line.getA().getX())
                                return false;
                        return temp.getX()<line.getB().getX();
                } else {
                        if(temp.getX()<=line.getA().getX())
                                return false;
                        return temp.getX()<=line.getB().getX();
                }
     }
    public boolean isAbout(Line line, Point temp){
    if(line.getvertical())return false;
    if(!isBetween(line, temp)) return false;
    return (temp.getX()*line.geta())/line.getFIX_BASE()+line.getb()>temp.getY();
    }
    @Override
    public Shape createShape(List<Point> point, double FirstParam, double SecondParam) {
        TypeChecker.setType(ShapeType.Polygon);
        if(point.size()>1) return new Polygon(point.toArray(new Point[point.size()]));
        else return null;
    }
}
