import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet; // Import the HashSet class

// Classe qui contient toutes les méthodes liées au Dictionnaire : lecture, comparaison, obtention des 5 mots

public class Dictionnaire extends Component {
    protected String textDico;
    protected static HashSet<String> motsDico;
    protected JFileChooser file;


    public Dictionnaire(){
        textDico = new String();
        motsDico = new HashSet<String>();
        file = new JFileChooser();
    }
// Met le dictionnaire au bon format
    public void readDico(){
        int val = this.file.showOpenDialog(this);

        try {
            if (val == 0) {
                BufferedReader r = new BufferedReader(new FileReader(this.file.getSelectedFile()));
                String line = null;

                while((line = r.readLine()) != null) {
                    this.motsDico.add(line);
                }

                r.close();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
    public Boolean bonMot(String s){
        return motsDico.contains(s);
    }
// Comparateur
    public static int distance(String s1, String s2){
        int edits[][]=new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++)
            edits[i][0]=i;
        for(int j=1;j<=s2.length();j++)
            edits[0][j]=j;
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                int u=(s1.charAt(i-1)==s2.charAt(j-1)?0:1);
                edits[i][j]=Math.min(
                        edits[i-1][j]+1,
                        Math.min(
                                edits[i][j-1]+1,
                                edits[i-1][j-1]+u
                        )
                );
            }
        }
        return edits[s1.length()][s2.length()];
    }

    // Méthode pour trouver les 5 mots les plus proches
    public String[] motsProches(String s) {
        String[] tabMotsProches = new String[5];
        // Transformer le HashSet en Array
        String[] motsDicoArray = motsDico.toArray(new String[motsDico.size()]);
        int longueur = motsDicoArray.length;
        // Gérer les cas avec un dictionnaire de 5 mots ou moins
        if (longueur <= 5) {
            for (int i = 0; i < longueur; i++) {
                tabMotsProches[i] = motsDicoArray[i];
            }
        }
        else {
            // Ajout des 5 premiers mots
            tabMotsProches[0] = motsDicoArray[0];
            tabMotsProches[1] = motsDicoArray[1];
            tabMotsProches[2] = motsDicoArray[2];
            tabMotsProches[3] = motsDicoArray[3];
            tabMotsProches[4] = motsDicoArray[4];
            for (int i = 5; i < motsDicoArray.length; i++) {
                String motTemp = motsDicoArray[i];
                for (int j = 0; j < 5; j++) {
                    boolean b = distance(s, motTemp) < distance(s, tabMotsProches[j]);
                    if (b) {
                        String temp = tabMotsProches[j];
                        tabMotsProches[j] = motTemp;
                        motTemp = temp;
                    }
                }
            }
        }
        return tabMotsProches;
    }
}

