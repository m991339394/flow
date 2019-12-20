package io.renren;

import java.awt.*;

/**
 * @ClassName MyCircke
 * @Description TODO
 * @Author jgl
 * @Date 2019/12/19 21:27
 * @Version 1.0
 */
public class MyCircle {
    int x;
    int y;
    int radius;
    Color color;

    public MyCircle(int xCenter, int yCenter, int size, Color circleColor)
    {
        x = xCenter;
        y = yCenter;
        radius = size;
        color = circleColor;
    }
    public void drawUnfilled(Graphics gc)
    {
        gc.setColor(color);
        gc.drawOval(x-radius, y-radius, radius*2, radius*2);
    }

    public static void main(String[] args) {
        MyCircle myCircle=new MyCircle(1,1,5 ,new Color(255,255,255));

    }
}
