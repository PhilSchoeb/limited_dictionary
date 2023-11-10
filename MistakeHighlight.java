import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

class MistakeHighlight extends JFrame
{
    JTextArea textComp;

    MistakeHighlight(JTextArea ta)
    {
        textComp = ta;

    }

    // Surligner les mots hors-dictionnaire dans la zone texte
    public void highlight(String pattern) {

        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            // Obtenir les mots erronés
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // "peinturer" le surlignement sur les mots correspondant au pattern: mots erronés
                hilite.addHighlight(pos, pos+pattern.length(), myHighlightPainter);
                pos += pattern.length();
            }
        } catch (BadLocationException e) {
        }
    }
    // Enlever le surligneur dans tout le TextArea
    public void removeHighlights(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();

        for (int i=0; i<hilites.length; i++) {
            if (hilites[i].getPainter() instanceof MyHighlightPainter) {
                hilite.removeHighlight(hilites[i]);
            }
        }
    }
    // Instance de surlignement
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.red);

    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
        public MyHighlightPainter(Color color) {
            super(color);
        }
    }
}

