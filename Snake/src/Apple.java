public class Apple {

    private int x;
    private int y;
    private final int  size;

    public Apple(int size) {
        this.size = size;


    }

    ///SETTER
    public void appleSetX(int x) {
        this.x = x;
    }

    public void appleSetY(int y) {
        this.y = y;
    }


    ///Getter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAppleSize() {
        return size;
    }


}
