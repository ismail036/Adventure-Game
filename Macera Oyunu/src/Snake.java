import java.util.Random;

public class Snake extends Obstacle{
    public Snake(){
        super(4,"Yılan",hasar(),12,0);
    }

    public static int hasar(){
        Random r = new Random();
        return r.nextInt(4)+3;
    }
}
