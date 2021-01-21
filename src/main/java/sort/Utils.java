package sort;

import obj.Element;

public class Utils {
    public static void swap(Element[] elements,int x ,int y){
        if (x!=y){
            int a = elements[x].score;
            elements[x].score=elements[y].score;
            elements[y].score = a;
        }
    }
}
