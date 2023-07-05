package LOGIN;
import javax.swing.*;
import java.awt.*;

public class NewButton extends JButton
{
    private String s;
    private int sizeX;
    private int sizeY;
    private Color color=new Color(30, 236, 130, 126);
    public NewButton(String s ,int sizeX,int sizeY)    //传递图片引用
    {
        super(s);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        setContentAreaFilled(false);
    }

    public NewButton(String s ,int sizeX,int sizeY,Color color)
    {
        super(s);
        this.color=color;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g)
    {
        g.setColor(this.color);
        g.fillRoundRect(0,0,getSize().width-1,getSize().height-1,15,15);        //绘制一个圆角矩形getSize()为获取组件的大小
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g)
    {
        g.drawRoundRect(0,0,getSize().width-1,getSize().height-1,15,15);
    }
}