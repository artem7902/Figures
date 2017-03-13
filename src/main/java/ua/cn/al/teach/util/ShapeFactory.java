/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ua.cn.al.teach.figures.shapes.Point;
import ua.cn.al.teach.figures.shapes.Shape;
/**
 *
 * @author artem
 */
public class ShapeFactory {
    private Map<String, Shape> m_RegisteredShapes;
    private static ShapeFactory instance = new ShapeFactory();

	private ShapeFactory()
	{
		m_RegisteredShapes=new HashMap<>();
	}

	public static ShapeFactory getInstance()
	{    
		return instance;
	}
    public void registerProduct(String shapeID, Shape s)    {
		m_RegisteredShapes.put(shapeID, s);
	}

	public Shape createShapebyID(String shapeID, List<Point> pList, double FirstParam, double SecondParam){
		return ((Shape)m_RegisteredShapes.get(shapeID)).createShape(pList, FirstParam, SecondParam);
	}
}
