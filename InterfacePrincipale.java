import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.text.Utilities;
import java.util.HashSet; // Import the HashSet class

public class InterfacePrincipale extends JFrame implements ActionListener {
    private JFileChooser fc;
    private ArrayList<String> texte;
    public JTextArea ta;
    private HashSet<String> motsSurlignés;

    protected static final Dictionnaire dico = new Dictionnaire();
    private JScrollPane scrollPane;
    private final JButton chooser, menu, verifier;
    private final JMenuItem ecrire, afficher;
    private final JPopupMenu dropDownButton;

    public InterfacePrincipale() {
        JPanel haut = new JPanel();
        JPanel bas = new JPanel();
        this.add(haut, "North");
        this.add(bas, "Center");

        this.ta = new JTextArea("", 100, 20);
        ta.setLineWrap( true );
        ta.setSize( ta.getPreferredSize() );
        ta.addMouseListener( new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if ( SwingUtilities.isLeftMouseButton(e) )
                {
                    // Events liés à la zone de texte TextArea
                    try
                    {
                        int offset = ta.viewToModel2D(e.getPoint());
                        int start = Utilities.getWordStart(ta, offset);
                        int end = Utilities.getWordEnd(ta, offset);
                        String word = ta.getDocument().getText(start, end-start);
                        if (!dico.bonMot(word.toLowerCase()) && word.matches("[a-zA-z]+") && motsSurlignés.contains(word)){
                            CorrectionWindow fen = new CorrectionWindow(word, start, end, ta);
                        }
                    }
                    catch (Exception e2) {}
                }
            }
        });

        bas.add(this.ta);

        scrollPane = new JScrollPane(ta);
        getContentPane().add( scrollPane );

        // Boutons du menu principal
        this.menu = new JButton("Fichier");
        this.dropDownButton = new JPopupMenu("Fichier");

        this.ecrire = new JMenuItem("Sauvegarder");
        this.dropDownButton.add(ecrire);

        this.afficher = new JMenuItem("Afficher");
        this.dropDownButton.add(afficher);
        haut.add(menu);

        this.verifier = new JButton("Vérifier");
        haut.add(verifier);
        this.chooser = new JButton("Dictionnaire");
        haut.add(chooser);

        this.fc = new JFileChooser();
        this.texte = new ArrayList();

        this.menu.addActionListener(this);
        this.chooser.addActionListener(this);
        this.afficher.addActionListener(this);
        this.verifier.addActionListener(this);
        this.ecrire.addActionListener(this);

        this.chooser.setToolTipText("Sélectionner le dictionnaire de mot.");
        this.afficher.setToolTipText("Sélectionner le texte à corriger.");
        this.verifier.setToolTipText("Vérifier pour des erreurs.");
        this.ecrire.setToolTipText("Sauvegarder les corrections.");
    }
    // Dérouler le menu
    public void deroulerMenu() {
        dropDownButton.show(menu, 0, menu.getHeight());
    }
    // Charger le dictionnaire
    public void chargerFichier() {
        dico.readDico();
    }
    // Mettre le contenu du textArea dans un fichier
    public void ecrireFichier() {
        int val = this.fc.showOpenDialog(this);

        try {
            if (val == 0) {
                BufferedWriter r = new BufferedWriter(new FileWriter(this.fc.getSelectedFile()));
                if (ta.getText() != null) r.write(ta.getText());
                r.close();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }


    public void afficher() {
        int val = this.fc.showOpenDialog(this);

        try {
            if (val == 0) {
                BufferedReader r = new BufferedReader(new FileReader(this.fc.getSelectedFile()));
                String line = null;

                while((line = r.readLine()) != null) {
                    this.texte.add(line);
                }

                r.close();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        if (this.texte != null) {
            Iterator var1 = this.texte.iterator();

            while(var1.hasNext()) {
                String s = (String)var1.next();
                this.ta.append(s + "\n");
            }
        }

    }
    // On va chercher la classe MistakeHighlight pour surligner les mots hors-dictionnaire
    public void verifier(){

        MistakeHighlight h = new MistakeHighlight(ta);
        h.removeHighlights(ta);
        motsSurlignés = new HashSet<String>();
        String texte = ta.getText();
        texte = texte.replace("\n", " ");
        String[] cor = texte.split(" ");
        for (int i = 0; i < cor.length; i++) {
            String t1 = cor[i];
            // On vérifie que le mot t1 n'est pas dans le dictionnaire et qu'il est seulement composé de lettres
            if (!dico.bonMot(t1.toLowerCase()) && t1.matches("[a-zA-Z]+")) {
                h.highlight(t1);
                motsSurlignés.add(t1);
            }
        }
    }
    // actionPerformed liés aux boutons liés au fichier
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.menu){
            this.deroulerMenu();
        } else if (e.getSource() == this.chooser) {
            this.chargerFichier();
        } else if (e.getSource() == this.afficher) {
            this.afficher();
        } else if (e.getSource() == this.verifier) {
            this.verifier();
        } else if (e.getSource() == this.ecrire) {
            this.ecrireFichier();
        }
    }
}
