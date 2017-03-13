/*
 * 
 * 
 */
package ua.cn.al.teach.figures.shapes;
import ua.cn.al.teach.util.RGBcolor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ua.cn.al.teach.figures.engine.GraphicsEngine;
import javafx.scene.paint.Color;
/**
 *
 * @author al
 */
public class Composite extends Shape implements Serializable{
    @XmlElementWrapper(name = "shapelist")
    @XmlElement(name = "shape")
    protected List<Shape> shapes;
    @XmlAttribute(name = "Max_z_index")
    protected int Max_z_index=0;

    public Composite() {
        shapes=Collections.synchronizedList(new ArrayList<Shape>());
    }
    public List<Shape> getList(){
    return shapes;
    }
    @Override
    public void draw(GraphicsEngine ge) {
       for(Shape s: shapes){
           ge.setColor(new RGBcolor(s.getColor().getRed(),s.getColor().getGreen(), s.getColor().getBlue(), s.getColor().getOpacity()));
           ge.setFillColor(new RGBcolor(s.getFill().getRed(), s.getFill().getGreen(),s.getFill().getBlue(), s.getColor().getOpacity()));
           ge.setLineWidth(s.getlineWidth());
           s.draw(ge);
       }
    }
    public Shape add(Shape s){
        if(s.z_index==-1)s.z_index=++Max_z_index;
        shapes.add(s);
        return s;
    }
     public Shape get(int num){
        return shapes.get(num);
    }
     public int GetMaxZIndex(){
     return this.Max_z_index;
     }
     public boolean constaint(Shape s){
        return shapes.contains(s);
    }
    public Shape add(int pos, Shape s){
        if(s.z_index==-1)s.z_index=++Max_z_index;
        shapes.add(pos,s);
        return s;
    }
    public void delete(Shape s){
        if(!shapes.contains(s)) return;
    if(s.z_index!=this.Max_z_index){
    for(Shape tmp : shapes){
        if(tmp.getZIndex()>s.getZIndex())tmp.z_index--;
    }
    }
    shapes.remove(s);
    this.Max_z_index--;
    }
    public void deleteAll(){
    Max_z_index=0;
    shapes.removeAll(shapes);
    }
    @Override
     public boolean isSelected(Point temp){
    for(Shape s: shapes) if(s.isSelected(temp))return true;
        return false;
    }
     public int getSize(){
     return shapes.size();
     }
     public boolean isEmpty(){
     return shapes.isEmpty();
     } 
     public void SetColorForAll(Color col){
           for(Shape s: shapes) s.setColor(col);
     }
     public void SetFillForAll(Color col){
           for(Shape s: shapes) s.setFill(col);
     }

    @Override
    public void moveTo(Point p) {
        for(Shape s: shapes) s.moveTo(p);
    }
    
    @Override
    public Shape createShape(List<Point> point, double FirstParam, double SecondParam) {
        return null;
    }
    
    @Override
    public void AddTempCircle(Composite TempCircle) {
    for(Shape s: this.shapes) s.AddTempCircle(TempCircle);
    }
    
    public void ChangeZIndex(Shape shap, int ZIndex){
    if(shap.z_index<ZIndex){
        for(Shape tmp: shapes)
        if(tmp.z_index<=ZIndex && tmp.z_index>shap.z_index)tmp.z_index--;
    }
    else
    {
    for(Shape tmp: shapes)
        if(tmp.z_index>=ZIndex && tmp.z_index<shap.z_index)tmp.z_index++;
    }
    shap.z_index=ZIndex;
    if(ZIndex>=this.Max_z_index)this.Max_z_index=ZIndex+1;
    }
}
