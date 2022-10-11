import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake{

    private java.util.List<Point> body = new ArrayList<>();
    private Point head;
    private Point tail;
    private int size;

    public Snake(int size) {

        this.size = size;
    }


    public List<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return head;
    }

    public int getSnakeSize() {
        return size;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public void setTail(Point tail) {
        this.tail = tail;
    }

}







