import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Macera oyununa hosgeldiniz !");
        System.out.println("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " bu karanlık ve sisli adaya hosgeldiniz !");
        System.out.println("Lütfen oyuna baslamak için bir karakter seçiniz");
        player.selectChar();

        while (true){
            Location location = null;
            player.printInfo();
            System.out.println();
            System.out.println("##########Bölgeler##########");
            System.out.println();
            System.out.println("1 - Güvenli Ev --> Burası sizin için güvenli bir ev , düşman yoktur !");
            System.out.println("2 - Eşya Dükkanı --> Silah veya zırh satı  alabilirsiniz !");
            System.out.println("3 - Mağara --> Ödül <Yemek> , dikkatli ol zombi çıkabilir !");
            System.out.println("4 - Orman --> Ödül <Odun> , dikkatli ol vampir çıkabilir !");
            System.out.println("5 - Nehir --> Ödül <Su> , dikkarli ol karsına ayı çıkabilir !");
            System.out.println("6 - Maden --> Ödil < Para, Silah veya Zırh > , dikkatli ol karsına yılan çıkabilir !");
            System.out.println("0 - Çıkıs Yap --> Oyunu sonlandır");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (player.getInventory().isFood() == true){
                        System.out.println("Bu bölgedeki tüm düsmanları yendiniz. Lütfen baska bir bölge seçiniz.");
                        break;
                    }else if(player.getInventory().isFood() == false){
                        location = new Cave(player);
                        break;
                    }
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz !");
                    break;
            }

            if (selectLoc==0){
                break;
            }




            try {
                if (location.getName() == "Güvenli Ev" && player.getInventory().isFood() == true && player.getInventory().isFirewood() == true&& player.getInventory().isWater() == true ){
                    System.out.println("YOU WİN!");
                    break;
                }
                if (!location.onLocation()){
                    System.out.println("GAME OVER!");
                    break;
                }
            }catch (Exception e){

            }
        }
    }
}
