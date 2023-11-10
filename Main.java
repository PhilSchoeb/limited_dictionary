import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        InterfacePrincipale rf = new InterfacePrincipale();
        rf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rf.setSize(600, 400);
        rf.setLocationRelativeTo(null);
        rf.setVisible(true);
    }
}
