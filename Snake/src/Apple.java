

public class Apple {

    private int x;
    private int y;
    private int size;
    private boolean isEaten = false;

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

    public void appleSetSize(int x) {
        this.size = size;
    }

    public void appleSetStatus(boolean status) {
        this.isEaten = status;
    }


    ///Getter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public int getAppleSize() {
        return size;
    }


}
