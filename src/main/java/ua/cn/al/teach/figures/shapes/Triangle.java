/*
 * 
 * 
 */
package ua.cn.al.teach.figures.shapes;

import ua.cn.al.teach.util.ShapeType;
import java.io.Serializable;
import java.util.List;
import ua.cn.al.teach.util.ShapeFactory;
import ua.cn.al.teach.util.TypeChecker;

/**
 *
 * @author al
 */
public class Triangle extends Polygon implements Serializable{

     static
	{
	ShapeFactory.getInstance().registerProduct("6", new Triangle());
	}
     @Override
     public Shape createShape(List<Point> point, double FirstParam, double SecondParam) {
        TypeChecker.setType(ShapeType.Triangle);
        if(point.size()>1) return new Polygon(point.toArray(new Point[point.size()]));
        else return null;
    }
}
 