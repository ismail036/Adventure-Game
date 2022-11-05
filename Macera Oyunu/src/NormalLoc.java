public class NormalLoc extends Location{


    protected NormalLoc(Player player , String name) {
        super(player , name);
    }

    @Override
    boolean onLocation() {
        return true;
    }
}
