/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.cn.al.teach.util;

/**
 *
 * @author artem
 */
public class RGBcolor {
    public final int R;
    public final int G;
    public final int B;
    public final int opacity;

    public RGBcolor(int R, int G, int B, int opacity) {
        this.R = R;
        this.G = G;
        this.B = B;
        this.opacity = opacity;
    }
    public RGBcolor(double R, double G, double B, double opacity) {
        this.R = (int)(R*255);
        this.G = (int)(G*255);
        this.B = (int)(B*255);
        this.opacity = (int)(opacity*255);
    }
}
