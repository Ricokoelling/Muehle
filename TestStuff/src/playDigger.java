import java.util.Scanner;

public class playDigger {
    private int kekw;
    private int hello;
    private boolean check;
    private String moin;
    Scanner sc = new Scanner(System.in);

    public playDigger() {
        this.kekw = 1;
        this.check = true;
        this.hello = 20;
        this.moin = "Wixxer";
    }

    public boolean getAll(){
        String moin = sc.next();
        if(moin.equals(".")){
            return true;
        }else return false;
    }

    public int getKekw() {
        return kekw;
    }

    public int getHello() {
        return hello;
    }

    public boolean isCheck() {
        return check;
    }
}
