import java.util.Random;

public class Mine extends BattleLoc{
    public Mine(Player player){
        super(player,"Maden",new Snake(),award(),5);
    }

    public static String award(){
        Random random = new Random();
        int rand = random.nextInt(100) + 1;
        if (rand<=15){
            int rand2 = random.nextInt(100) + 1;
            if (rand2<=20){
                return Weapon.weapons()[2].getName();
            }else if (rand2>20 && rand2<=50){
                return Weapon.weapons()[1].getName();
            } else{
                return Weapon.weapons()[0].getName();
            }
        }else if (rand>15&&rand<=30){
            int rand2 = random.nextInt(100) + 1;
            if (rand2<=20){
                return Armor.armors()[2].getName();
            }else if (rand2>20 && rand2<=50){
                return Armor.armors()[1].getName();
            } else{
                return Armor.armors()[0].getName();
            }
        }else if(rand>30&&55<=rand){
            int rand2 = random.nextInt(100) + 1;
            if (rand2<=20){
                return "10";
            }else if (rand2>20 && rand2<=50){
                return "5";
            } else{
                return "1";
            }
        }else {
            return "Ödül kazanamadınız...";
        }

    }
}
