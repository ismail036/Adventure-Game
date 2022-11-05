import java.util.Random;

public class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli Ol! Burada " + obsNumber + " adet " + this.getObstacle().getName() + " yaşıyor !");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S")){
            if (combat(obsNumber)){
                System.out.println(this.getName() + " bulunan tüm düsmanları yendiniz ");
                if (this.getAward() == "food"){
                    this.getPlayer().getInventory().setFood(true);
                }else if (this.getAward() == "firewood"){
                    this.getPlayer().getInventory().setFirewood(true);
                }else if (this.getAward() == "water"){
                    this.getPlayer().getInventory().setWater(true);
                }else if(this.getAward() == "Tabanca"){
                    System.out.println("Rakipden tabanca düştü.");
                }else if(this.getAward() == "Kılıç"){
                    System.out.println("Rakipden kılıç düştü. ");
                }else if(this.getAward() == "Tüfek"){
                    System.out.println("Rakipden tüfek düştü. ");
                }else if(this.getAward() == "Hafif"){
                    System.out.println("Rakipden hafif zırh düştü. ");
                }else if(this.getAward() == "Orta"){
                    System.out.println("Rakipden orta zırh düştü. ");
                }else if(this.getAward() == "Ağır"){
                    System.out.println("Rakipden ağır zırh düştü. ");
                }else if(this.getAward() == "10"){
                    System.out.println("Rakipden 10 para düştü.");
                }else if(this.getAward() == "5"){
                    System.out.println("Rakipden 5 para düştü. ");
                }else if(this.getAward() == "1"){
                    System.out.println("Rakipden 1 para düştü. ");
                }

                System.out.println(this.getAward() + " envanterinize eklenmistir");

                this.getPlayer().getInventory().odulSorgula();

            }
        }
        if (this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz !");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber){
        for (int i=1;i<=obsNumber;i++){
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("<V>ur veya <K>aç");
                String selectCombat = input.nextLine().toUpperCase();
                if(selectCombat.equals("V")){
                    Random r = new Random();
                    int wStart =  r.nextInt(2)+1;
                    if (wStart == 1){
                        System.out.println("Siz vurdunuz  ! ");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0){
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage<0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    }
                    else if(wStart == 2){
                        if (this.getObstacle().getHealth() > 0){
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage<0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                        System.out.println("Siz vurdunuz  ! ");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();

                    }
                }else {
                    return false;
                }

            }

            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düsmanı yendiniz !");
                System.out.println(this.getObstacle().getAward() + " para kazandınız !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
            }
        }

        if (this.getPlayer().getHealth()>0){
            return true;
        }

        return false;
    }

    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı : " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats(){
        System.out.println("Oyunuc değerlei");
        System.out.println("-------------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());
    }

    public void obstacleStats(int i){
        System.out.println(i + ". " +this.getObstacle().getName() + " değerleri ");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());
    }
    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }
    public Obstacle getObstacle() {
        return obstacle;
    }
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
    public String getAward() {
        return award;
    }
    public void setAward(String award) {
        this.award = award;
    }
    public int getMaxObstacle() {
        return maxObstacle;
    }
    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
