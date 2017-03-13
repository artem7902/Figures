/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.figures.shapes;

import ua.cn.al.teach.util.ShapeType;
import ua.cn.al.teach.util.TypeChecker;
import java.util.List;
import ua.cn.al.teach.util.ShapeFactory;

/**
 *
 * @author artem
 */
public class CubicBezier extends Bezier{
    static{
            ShapeFactory.getInstance().registerProduct("9", new CubicBezier());
    }
    @Override
    public Shape createShape(List<Point> point, double FirstParam, double SecondParam) {
        TypeChecker.setType(ShapeType.CubicBezier);
        if(point.size()==4) return new Bezier(point.get(0), point.get(1), point.get(2), point.get(3));
        return null;
    }
}
