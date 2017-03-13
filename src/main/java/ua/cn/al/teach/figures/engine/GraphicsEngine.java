/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.figures.engine;

import java.util.List;
import ua.cn.al.teach.figures.shapes.Point;
import ua.cn.al.teach.util.RGBcolor;

/**
 *
 * @author artem
 */
public interface GraphicsEngine {
    public void strokeLine(double x1, double y1, double x2, double y2);
    public void setColor(RGBcolor c);
    public void setFillColor(RGBcolor c);
    public void setLineWidth(int w);
    public void strokeCurve(List<Point> points);
    public void Oval(Point center, int R);
    public void Bow(Point tmp, int width, int height);
    public void Clear();
    public void Polygon(List<Point> points);
    public void PolyLine(List<Point> points);
    public void Rectangle(Point tmp, int width, int height);
    public void SetGlobalOpacity(double alpha);
}
