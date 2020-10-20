import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 19.10.2017
  * @author 
  */

public class Menue extends JFrame {
  // Anfang Attribute
  private JLabel lMINESWEAPER = new JLabel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  
  private JNumberField jNumberField1 = new JNumberField();
  private JLabel lLange = new JLabel();
  private JLabel lMinenanzahl = new JLabel();
  private JButton bBenutzerdeffiniert = new JButton();
  private JNumberField jNumberField2 = new JNumberField();
  private JNumberField jNumberField3 = new JNumberField();
  private JLabel lBreite = new JLabel();
  // Ende Attribute
  
  public Menue() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300; 
    int frameHeight = 350;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Menue");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    lMINESWEAPER.setBounds(24, 16, 251, 57);
    lMINESWEAPER.setText("MINESWEEPER");
    lMINESWEAPER.setFont(new Font("Dialog", Font.BOLD, 24));
    lMINESWEAPER.setHorizontalTextPosition(SwingConstants.CENTER);
    lMINESWEAPER.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lMINESWEAPER);
    jButton1.setBounds(16, 88, 257, 33);
    jButton1.setText("einfach (9*9)");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        new Spiel(10,9,9);
    setVisible(false);
      }
    });
    cp.add(jButton1);
    jButton2.setBounds(16, 128, 257, 33);
    jButton2.setText("mittel(16*16)");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        new Spiel(40,16,16);
    setVisible(false);
      }
    });
    cp.add(jButton2);
    jButton3.setBounds(16, 168, 257, 33);
    jButton3.setText("schwer(30*16)");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
         new Spiel(99,16,30);
    setVisible(false);
      }
    });
    cp.add(jButton3);
    jNumberField1.setBounds(8, 240, 41, 33);
    jNumberField1.setText("");
    cp.add(jNumberField1);
    lLange.setBounds(0, 216, 42, 19);
    lLange.setText("Länge");
    cp.add(lLange);
    lMinenanzahl.setBounds(104, 216, 75, 19);
    lMinenanzahl.setText("Minenanzahl");
    cp.add(lMinenanzahl);
    bBenutzerdeffiniert.setBounds(160, 240, 113, 33);
    bBenutzerdeffiniert.setText("Benutzerdeffiniert");
    bBenutzerdeffiniert.setMargin(new Insets(2, 2, 2, 2));
    bBenutzerdeffiniert.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
    int Laenge,Breite, Mine;
    if(jNumberField1.getInt()>0&&jNumberField1.getInt()<30)
          Laenge=jNumberField1.getInt();
    else Laenge=30;
    if(jNumberField3.getInt()>0&&jNumberField3.getInt()<24)
          Breite=jNumberField3.getInt();
    else Breite=24;
   if(jNumberField2.getInt()>0&&jNumberField2.getInt()<Laenge*Breite*0.93)
    Mine=jNumberField2.getInt ();
    else Mine=(int)(Laenge*Breite*0.93) ;
    new Spiel(Mine,Breite,Laenge);
     setVisible(false);
    }});
    cp.add(bBenutzerdeffiniert);
    jNumberField2.setBounds(112, 240, 41, 33);
    jNumberField2.setText("");
    cp.add(jNumberField2);
    jNumberField3.setBounds(56, 240, 41, 33);
    jNumberField3.setText("");
    cp.add(jNumberField3);
    lBreite.setBounds(56, 216, 43, 19);
    lBreite.setText("Breite");
    cp.add(lBreite);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Menue
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Menue();
  } // end of main
  
  public void jButton1_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen

  } // end of jButton1_ActionPerformed

  public void jButton2_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen

  } // end of jButton2_ActionPerformed

  public void jButton3_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen

  } // end of jButton3_ActionPerformed

  public void bBenutzerdeffiniert_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen

  } // end of bBenutzerdeffiniert_ActionPerformed

  // Ende Methoden
} // end of class Menue

