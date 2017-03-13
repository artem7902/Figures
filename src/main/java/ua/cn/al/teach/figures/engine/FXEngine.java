/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.figures.engine;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import ua.cn.al.teach.figures.shapes.Point;
import ua.cn.al.teach.util.RGBcolor;

/**
 *
 * @author artem
 */
public class FXEngine implements GraphicsEngine{
     GraphicsContext gc;

    public void setGC(GraphicsContext gc) {
        this.gc = gc;
    }
     public GraphicsContext getGC() {
       return gc;
    }

    @Override
    public void strokeLine(double x1, double y1, double x2, double y2) {
        gc.strokeLine(x1, y1, x2, y2);
    }

    @Override
    public void setColor(RGBcolor rgb) {
        gc.setStroke(Color.rgb(rgb.R, rgb.G, rgb.B, rgb.opacity/255));
    }

    @Override
    public void setLineWidth(int w) {
        gc.setLineWidth(w);
    }

    @Override
    public void setFillColor(RGBcolor rgb) {
        gc.setFill(Color.rgb(rgb.R, rgb.G, rgb.B, rgb.opacity/255));        
    }
     @Override
    public void strokeCurve(List<Point> point){
        gc.beginPath();
        gc.moveTo(point.get(0).getX(), point.get(0).getY());
        if(point.size()==3)gc.quadraticCurveTo(point.get(1).getX(), point.get(1).getY(), point.get(2).getX(), point.get(2).getY());
        else gc.bezierCurveTo(point.get(1).getX(), point.get(1).getY(), point.get(2).getX(), point.get(2).getY(), point.get(3).getX(), point.get(3).getY());
        gc.stroke();
        }

    @Override
    public void Oval(Point center, int R) {
        if(((javafx.scene.paint.Color)gc.getFill()).equals(Color.WHITE))gc.strokeOval(center.getX()-R, center.getY()-R, R*2, R*2);
        else
        gc.fillOval(center.getX()-R, center.getY()-R, R*2, R*2);
    }

    @Override
    public void Bow(Point tmp, int width, int height) {
       if(((javafx.scene.paint.Color)gc.getFill()).equals(Color.WHITE))gc.strokeArc(tmp.getX(), tmp.getY(), width, height, 0, -180, ArcType.OPEN);
        else
        gc.fillArc(tmp.getX(), tmp.getY(), width, height, 0, -180, ArcType.OPEN);
    }

    @Override
    public void Clear() {
         gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    @Override
    public void Polygon(List<Point> points) {
       double[] px=new double[points.size()+1];
       double[] py=new double[points.size()+1];
       int num=0;
       for(Point p : points)
       {
       px[num]=p.getX();
       py[num]=p.getY();
       num++;
       }
        if(!((javafx.scene.paint.Color)gc.getFill()).equals(Color.WHITE) && points.size()>2)
            gc.fillPolygon(px, py, points.size());
        else
            gc.strokePolygon(px, py, points.size());
    }
     @Override
     public void PolyLine(List<Point> points) {
         double[] px=new double[points.size()];
       double[] py=new double[points.size()];
       int num=0;
       for(Point p : points)
       {
       px[num]=p.getX();
       py[num]=p.getY();
       num++;
       }
           gc.strokePolyline(px, py, points.size()); 
    }
      @Override
     public void Rectangle(Point tmp, int width, int height) {
       if(((javafx.scene.paint.Color)gc.getFill()).equals(Color.WHITE)) gc.strokeRect(tmp.getX(), tmp.getY(), width, height);
        else
        gc.fillRect(tmp.getX(), tmp.getY(), width, height);
    }
      public void SetGlobalOpacity(double alpha){
      gc.setGlobalAlpha(alpha);
      }
}
