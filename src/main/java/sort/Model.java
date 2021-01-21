package sort;

import obj.Element;
import view.View;
import java.awt.*;
import java.util.Observable;
import java.util.Random;

public class Model extends Observable implements Runnable{
    private static Random random =new Random();
    private Element[] elements;
    private static int size = View.width/3;
    private String info="";

    public Model(){
        elements = new Element[size];
        for (int i = 0; i < size; i++) {
            elements[i] = new Element(i);
        }
        disorganize();
    }
    private void disorganize(){
        for (int i = 0; i < elements.length; i++) {
            Utils.swap(elements,i,random.nextInt(size));
        }
    }

    /**
     * 画图
     * @param g
     */
    public void draw(Graphics g){
        for (int i = 0; i < size; i++) {
            elements[i].drawSelf(g);
        }
        Color color = g.getColor();
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体",Font.BOLD,30));
        g.drawString(info,50,80);
        g.setColor(color);
    }

    public void run() {
        while (true){
            disorganize();
            this.info = "归并排序log(N)";
            Sort.mergeSort(elements,0,size-1,this::show);

            disorganize();
            this.info = "堆排序log(N)";
            Sort.heapSort(elements,this::show);

            this.info = "快速排序log(N)";
            disorganize();
            Sort.quicksort(elements,0,size-1,this::show);

            this.info = "希尔排序log(N)";
            disorganize();
            Sort.shellSort(elements,this::show);

            this.info = "选择排序N^2";
            disorganize();
            Sort.selctionSort(elements,this::show);

            this.info = "冒泡排序N^2";
            disorganize();
            Sort.bubbleSort(elements,this::show);
        }
    }

     void show(int a,int b){
        elements[a].setOperation(true);
        elements[b].setOperation(true);
        setChanged();
        notifyObservers();
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elements[a].setOperation(false);
        elements[b].setOperation(false);
    }
}
