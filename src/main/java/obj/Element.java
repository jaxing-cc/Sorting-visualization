package obj;

import view.View;

import java.awt.*;

public class Element {
    public int score;
    private int index;
    private boolean operation;
    public Element(int index){
//        score = (int) (index*0.666);
        score = index*2;
        this.index = index;
        operation = false;
    }
    public void drawSelf(Graphics g){
        Color color = g.getColor();
        if (operation){
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.orange);
        }
        g.fillRect(index*3+10, View.height-score,3,score);
        g.setColor(color);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isOperation() {
        return operation;
    }

    public void setOperation(boolean operation) {
        this.operation = operation;
    }
}
