import java.util.Arrays;

public class MyArray {
    int[] elementsArray;
    int length;//数组下标

    public static void main(String[] args) {
        MyArray myArray = new MyArray(3);
        myArray.add(2);
        myArray.add(2);
        myArray.add(2);
        System.out.println(myArray);
        myArray.add(3);
    }

    public MyArray(int size) {
        if (size > 0) {
            elementsArray = new int[size];
        }
        ;
    }

    /**
     * 添加元素
     */
    public void add(int element) {
        if (length < elementsArray.length) {
            elementsArray[length] = element;

        } else {
            System.out.println("this Array is full!"+"the elements "+element+" not into this Array.");
        }
        length++;
    }

    @Override
    public String toString() {
        return "MyArray{" +
                "elementsArray=" + Arrays.toString(elementsArray) +
                ", length=" + length +
                '}';
    }
}
