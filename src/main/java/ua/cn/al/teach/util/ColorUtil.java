/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.util;

import javafx.scene.paint.Color;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author artem
 */
public class ColorUtil extends XmlAdapter<String, Color>{

    @Override
    public Color unmarshal(String v) throws Exception {
       return Color.valueOf(v);
    }

    @Override
    public String marshal(Color v) throws Exception {
        return v.toString();
    }
}
