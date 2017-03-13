/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.util;

import java.util.List;
import javafx.scene.input.MouseEvent;
import ua.cn.al.teach.figures.shapes.Circle;
import ua.cn.al.teach.figures.shapes.Composite;
import ua.cn.al.teach.figures.shapes.Point;
import ua.cn.al.teach.figures.shapes.Shape;

/**
 *
 * @author artem
 */
public enum ShapeType {
    Line{
    @Override
    public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e){
     prev = null;
     if (TempPoinst_List.isEmpty())TempPoinst_List.add(new Point((int) e.getX(), (int) e.getY()));
     else
     TempPoinst_List.clear();
    }
    },
    PolyLine {
        @Override
        public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e) {
            TempPoinst_List.add(new Point((int) e.getX(), (int) e.getY()));
        }
    },
    Polygon{
        @Override
        public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e) {
            TempPoinst_List.add(new Point((int) e.getX(), (int) e.getY()));
        }
    },
    Triangle{
        @Override
        public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e)
        {
             if (TempPoinst_List.size() < 2)
             TempPoinst_List.add(new Point((int) e.getX(), (int) e.getY()));
             else
                 TempPoinst_List.clear();                 
        }
    },
    Rectangle{
        @Override
        public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e) {
             TempPoinst_List.clear();
        }
    },
    Bow{
        @Override
        public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e) {
           TempPoinst_List.clear();
        }
    },
    Circle{
        @Override
        public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e) {
         prev = null;
         TempPoinst_List.clear();
        }
    },
    Bezier{
        @Override
        public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e) {
        if(TempPoinst_List.isEmpty())TempCircle.deleteAll();
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) e.getX(), (int) e.getY())));
        TempCircle.SetColorForAll(javafx.scene.paint.Color.BLUE);
        if (TempPoinst_List.size() < 2) 
        TempPoinst_List.add(new Point((int) e.getX(), (int) e.getY()));
        else 
        TempPoinst_List.clear();
        }
    },
    CubicBezier{
        @Override
        public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e) {
        if(TempPoinst_List.isEmpty())TempCircle.deleteAll();
        TempCircle.add(TempCircle.getSize(), new Circle(new Point((int) e.getX(), (int) e.getY())));
        TempCircle.SetColorForAll(javafx.scene.paint.Color.BLUE);
        if (TempPoinst_List.size() < 3) 
        TempPoinst_List.add(new Point((int) e.getX(), (int) e.getY()));
        else 
        TempPoinst_List.clear();
        }
    },
    Composite{
        @Override
        public void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    public abstract void Click(Shape prev, List<Point> TempPoinst_List, Composite TempCircle, MouseEvent e);
}
