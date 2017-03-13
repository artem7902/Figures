/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.figures.engine;

import java.util.HashMap;
import ua.cn.al.teach.figures.shapes.Shape;

/**
 *
 * @author artem
 */
public class Graphics {
     volatile static Graphics instance = null;
    HashMap<String, GraphicsEngine> configuredEngines = new HashMap<>();
    GraphicsEngine currentGE;
    final int threadNumber = 4;

    private Graphics() {
    }

    public static Graphics getInstance() {
        if (instance == null) {
            instance = new Graphics();
        }
        return instance;
    }

    public void addEngine(GraphicsEngine ge, String name) {
        currentGE = ge;
        configuredEngines.put(name, ge);
    }

    public GraphicsEngine getCurrentGE() {
        return currentGE;
    }

    public void show(Shape s){
            s.show(currentGE);
    }

    /*public void showAsync(final Shape s) {
        ExecutorService executor = Executors.newFixedThreadPool(threadNumber);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                synchronized(s){  
                    show(s);
                }
            }
        }
        );
       // executor.shutdown();
    }*/
}
