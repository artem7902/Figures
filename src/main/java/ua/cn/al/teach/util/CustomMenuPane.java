/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.util;

import javafx.scene.control.ContextMenu;
import javafx.scene.layout.Pane;

/**
 *
 * @author artem
 */
public class CustomMenuPane extends Pane {
    private ContextMenu contextMenu;
    public void setContextMenu(ContextMenu contextMenu){
    this.contextMenu=contextMenu;
    }
    public ContextMenu getContextMenu(){
    return contextMenu;
    }
}
