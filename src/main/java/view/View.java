package view;

import sort.Model;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {
    private Model model;
    public static final int width = 1500;
    public static final int height = 1050;
    public View(Model model){
        this.model = model;
        getContentPane().setBackground(Color.GRAY);//背景颜色
        setBounds(200,20,width,height);//窗口大小
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("排序算法可视化");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        model.draw(g);

    }

    public static void main(String[] args) {

        Model model = new Model();
        View view = new View(model);
        model.addObserver(view);//绑定
        new Thread(model).start();
    }

    public void update(Observable o, Object arg) {
        repaint();
    }
}
