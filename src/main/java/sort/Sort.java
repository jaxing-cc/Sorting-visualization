package sort;

import obj.Element;

import java.util.Random;

public class Sort {
    /**
     * 冒泡排序
     * @param elements
     * @param handle
     */
    public static void bubbleSort(Element[] elements,Handle handle){
        for (int i = 0; i < elements.length-1; i++) {//冒泡排序
            boolean flag=false;
            for (int j = 0; j < elements.length-i-1; j++) {
                if (elements[j].score>elements[j+1].score){
                    Utils.swap(elements,j,j+1);
                    handle.handle(j,j+1);
                    flag=true;
                }
            }
            if (!flag){
                break;//如果以一次循环没有发生交换，则说明有序
            }
        }
    }

    /**
     * 归并排序
     * @param elements
     * @param l
     * @param r
     * @param handle
     */
    public static void mergeSort(Element[] elements,int l,int r,Handle handle){
        if (l==r){return;}
        int mid=(l+r)/2;
        mergeSort(elements,l,mid,handle);
        mergeSort(elements,mid+1,r,handle);
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        while(i <= mid && j <= r) {
            temp[k++] = elements[i].score < elements[j].score ? elements[i++].score : elements[j++].score;
        }
        while(i <= mid) {
            temp[k++] = elements[i++].score;
        }
        while(j <= r) {
            temp[k++] = elements[j++].score;
        }
        for (int m = 0; m < temp.length; m++) {
            elements[m+l].score=temp[m];
            handle.handle(0,m+l);
        }
    }

    /**
     * 堆排序
     * @param elements
     * @param handle
     */
    public static void heapSort(Element []elements,Handle handle){
        /**
         * parent=(i-1)/2
         * c1=2*i+2
         * c1=2*i+1
         */
        int n=elements.length;
        int lastNode=n-1;
        int parent=(lastNode-1)/2;
        for (int i = parent; i >= 0 ; i--) {//建堆
            heapify(elements,n,i,handle);
        }
        while(n>0){
            int bIndex = (n--)-1;
            Utils.swap(elements,0,bIndex);
            handle.handle(0,bIndex);
            heapify(elements,n,0,handle);
        }
    }
    private static void heapify(Element []tree,int n,int i,Handle handle){
        int child1=2*i+1;
        int child2=2*i+2;
        int max=i;
        if (child1<n){
            if (tree[max].score<tree[child1].score){
                max=child1;
            }
        }
        if (child2<n){
            if (tree[max].score<tree[child2].score){
                max=child2;
            }
        }
        if (max!=i){
            Utils.swap(tree,max,i);
            handle.handle(max,i);
            heapify(tree,n,max,handle);
        }
    }

    /**
     * 快速排序
     * @param elements
     * @param l
     * @param r
     * @param handle
     */
    public static void quicksort(Element []elements,int l,int r,Handle handle){//快速排序
        if (l < r){
            int left=l,right=r;
            int X=elements[left].score;
            while(left<right){
                while (left<right&&elements[right].score>X){right--;}
                if (left<right){
                    elements[left].score=elements[right].score;
                    handle.handle(left,right);
                    left++;
                }
                while (left<right&&elements[left].score<=X){left++;}
                if (left<right){
                    elements[right].score=elements[left].score;
                    handle.handle(left,right);
                    right--;
                }
            }
            elements[left].score=X;
            handle.handle(0,left);
            quicksort(elements,l,left-1,handle);
            quicksort(elements,left+1,r,handle);
        }
    }

    /**
     * 希尔排序
     * @param elements
     */
    public static void shellSort(Element []elements,Handle handle){
        int incre=elements.length;
        while(true){
            incre=incre/2;
            for (int i = 0; i < incre; i++) {
                for (int j = i; j < elements.length-incre; j+=incre) {
                    for (int k = j+incre; k > i; k-=incre) {
                        if (elements[k].score<elements[k-incre].score){
                            Utils.swap(elements,k,k-incre);
                            handle.handle(k,k-incre);
                        }else{
                            break;
                        }
                    }
                }
            }
            if (incre==1){
                break;
            }
        }
    }

    /**
     * 插入排序
     */
    public static void insertionSort(Element []elements,Handle handle){//插入排序
//        在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序
        for (int i = 0; i < elements.length-1; i++) {
            for (int j = i+1; j >0; j--) {
                if (elements[j].score<elements[j-1].score){
                   Utils.swap(elements,j,j-1);
                   handle.handle(j,j-1);
                }else{
                    break;
                }
            }
        }
    }

    /**
     * 选择排序
     * @param
     */
    public static void selctionSort(Element []elements,Handle handle){//选择排序  寻找第i个数后面的所有数中最小的数 跟第i个数做交换
        for (int i = 0; i < elements.length-1; i++) {
            int index=i;
            for (int j = i+1; j < elements.length; j++) {
                if (elements[j].score<elements[index].score){
                    index=j;
                }
            }
            if(index != i){
                Utils.swap(elements,index,i);
                handle.handle(index,i);
            }
        }
    }

    /***
     * 猴子排序
     * @param elements
     * @param handle
     */
    public static void BogoSort(Element []elements,Handle handle){
        Random random =new Random();
        while(true){
            boolean flag=true;
            for (int i = 0; i < elements.length; i++) {
                int y = random.nextInt(elements.length);
                Utils.swap(elements,i, y);
                handle.handle(i,y);
            }
            for (int x = 0; x < elements.length-1; x++) {
                if (elements[x].score>elements[x+1].score){
                    flag=false;//没有排序成功
                    break;
                }
            }
            if (flag){break;}
        }
    }
}
