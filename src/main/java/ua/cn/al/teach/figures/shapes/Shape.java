/*
 * 
 * 
 */
package ua.cn.al.teach.figures.shapes;

import java.io.Serializable;
import java.util.List;
import javafx.scene.paint.Color;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ua.cn.al.teach.figures.engine.GraphicsEngine;
import ua.cn.al.teach.util.ColorUtil;

/**
 *
 * @author al
 */
@XmlType(name="shape", namespace = "http://www.figures.org/shape")
public abstract class Shape extends Point implements Serializable{
    protected Color color = Color.BLACK;
    protected Color fill = Color.WHITE;
    protected int lineWidth = 1;
    protected int z_index=-1;
    protected int drawn = 0; // 0- dont draw, 1 - draw, 2 - was changed

    public abstract void draw(GraphicsEngine ge);
    public abstract Shape createShape(List<Point> point, double FirstParam, double SecondParam);

    public void setColor(Color c) {
    this.color=c;
    }

    public void setFill(Color c) {
        fill = c;
    }
    
    public void setlineWidth(int lw) {
        this.lineWidth = lw;
    }
    public void setZIndex(int zind) {
        this.z_index = zind;
    }
        @XmlJavaTypeAdapter(ColorUtil.class)
    public Color getColor() {
    return this.color;
    }
    @XmlJavaTypeAdapter(ColorUtil.class)
    public Color getFill() {
        return this.fill;
    }
    @XmlAttribute(name="line_width")
    public int getlineWidth() {
        return this.lineWidth;
    }
    @XmlAttribute(name="z_index")
    public int getZIndex() {
        return this.z_index;
    }
    public void show(GraphicsEngine ge){
        draw(ge);
    }
    public abstract boolean isSelected(Point temp);
    public abstract void moveTo(Point p);
    public abstract void AddTempCircle(Composite TempCircle);
}
