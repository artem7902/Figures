/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.figures.shapes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ua.cn.al.teach.figures.engine.GraphicsEngine;

/**
 *
 * @author artem
 */

@XmlRootElement(name="sheet", namespace="http://www.figures.org/sheet")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso( {Line.class, PolyLine.class, Polygon.class, Bow.class, Circle.class, Rectangle.class, Triangle.class, Bezier.class, CubicBezier.class} )
public class Sheet extends Composite{
    public Shape SelectShape(Point temp, Composite TempCircle){
     int max=-1;
     Shape sel = null;
    for(Shape s: shapes){
       if(s.z_index>max && s.isSelected(temp) ){
           max=s.z_index;
           sel=s;
       }
       }
    if(sel!=null){sel.AddTempCircle(TempCircle);
    System.out.println(sel.z_index);}
    return sel;
    }
    @Override
    public boolean isSelected(Point temp){
     return false;
    }
        public Shape add(Shape s){
        if(s.z_index!=-2)s.z_index=++Max_z_index;
        shapes.add(s);
        return s;
    }
        public Shape add(int pos, Shape s){
        if(s.z_index!=-2)s.z_index=++Max_z_index;
        shapes.add(pos,s);
        return s;
    }
    public void GroupSelected(Point temp, Composite group, Composite TempCircle){
    for(Shape p : shapes){
    if(!group.constaint(p) && p.isSelected(temp)){
        if(!p.equals(TempCircle)){
            p.AddTempCircle(TempCircle);
            group.add(p);
                }
    }
    }
    }
        public void AddGroup(Composite comp){
        for(Shape s : comp.getList())
        this.delete(s);
        this.add(comp);
        }
        public void UnGroup(Composite comp){
        this.delete(comp);
        for(Shape s : comp.getList())
        this.add(s);
        }
        public void draw(GraphicsEngine ge) {
        ge.Clear();
        super.draw(ge);
       }
}
