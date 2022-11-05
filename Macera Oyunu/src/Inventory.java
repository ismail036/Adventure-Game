public class Inventory  {
    private Weapon weapon;
    private Armor armor;

    private boolean food,water,firewood;
    public Inventory() {
        this.weapon = new Weapon("Yumruk",-1,0,0);
        this.armor = new Armor(-1,"Paçavra",0,0);
        this.firewood = false;
        this.food = false;
        this.water = false;
    }


    public void  odulSorgula(){
        System.out.println("Envanterinizde bulunan ödüller : ");
        if (this.firewood){
            System.out.println("Odun");
        }
        if (this.water){
            System.out.println("Su");
        }        if (this.food){
            System.out.println("Yiyecek");
        }

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }


    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }
}

