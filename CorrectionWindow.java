import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// Classe qui s'occupe de la correction des mots erronés par l'utilisateur (interface)
public class CorrectionWindow{
    protected JDialog dialog;
    protected String selectedWord;
    // Propose les 5 mots les plus proches du mot cliqué
    public CorrectionWindow(String s, int begWord, int endWord,JTextArea textArea){
        dialog = new JDialog();
        dialog.setUndecorated(true);
        
        // Création d'une liste proposant les 5 mots les plus proches
        String[] motsProches = InterfacePrincipale.dico.motsProches(s);
        String[] motsProposes = {motsProches[0],motsProches[1],motsProches[2],motsProches[3],motsProches[4]};
        JList<String> myList = new JList<String>(motsProposes);
        
        // Ajustements cosmétiques de la liste
        dialog.add(myList);
        myList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        myList.setBackground(new Color(255,205,212));
        dialog.pack();
        
        // Instaurer des conditions de fermeture de l'objet JDialog et la modification du texte
        myList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedWord = myList.getSelectedValue();
                textArea.replaceRange(selectedWord,begWord,endWord);
                dialog.dispose();
            }
            public void mouseExited(MouseEvent e) {dialog.dispose();}
        });
        
        // Initier la localisation du JDialog à l'endroit où le curseur se trouve
        dialog.setLocation(MouseInfo.getPointerInfo().getLocation());
        dialog.setVisible(true);
    }
}
