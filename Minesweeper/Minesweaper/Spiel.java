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

public class Spiel extends JFrame {
  // Anfang Attribute
  private final int Minenanzahl;
  private long Time;
  private final int Laenge;
   private final int Breite;
  private int Minenmakiert;
  private int [] Null;
  private int NullCounter;
  private int[]Minen;
  private JButton [] jButtons;
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  // Ende Attribute
  
  public Spiel(int Minenanzahl, int Laenge,int Breite) { 
   super(); 
  this.Minenanzahl=Minenanzahl; 
    this.Laenge=Laenge;
    this.Breite=Breite;
    Time=java.lang.System.currentTimeMillis();
    Null=new int[Laenge*Breite-Minenanzahl];
    Minen=new int[Minenanzahl];
     jButtons=new JButton[Laenge*Breite];
    // Frame-Initialisierung
    
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth=300,frameHeight=350;
    if (!(Laenge*17+64<300)) {
    frameWidth = Breite*17+64+((Breite*17+64/3)-10);
    frameHeight = Laenge*17+64+41;}
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);      
    setTitle("Spiel");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    NullCounter=0;
    Minenmakiert=Minenanzahl;
    // Anfang Komponenten
    for(int f=0;f<jButtons.length;f++){
      jButtons[f]=new JButton();
      jButtons[f].setMargin(new Insets(2, 2, 2, 2));
    }
    int jButtoncounter=0;
    for(int f=1;f<=Laenge;f++){
      for(int g=1;g<=Breite;g++){
        jButtons[jButtoncounter++].setBounds(16*g, 16*f, 17, 17);
      }              
    }
    for(int f=0;f<jButtons.length;f++){
        jButtons[f].addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent event){
      if (SwingUtilities.isRightMouseButton(event)) {
      for(int f=0;f<Laenge*Breite;f++){
      if (event.getSource()==jButtons[f]) {
      if(jButtons[f].isEnabled()){
      if (jButtons[f].getText().equals("")) {
      jButtons[f].setText("!");
      jButtons[f].setEnabled(false);
      jButtons[f].setBackground(Color.ORANGE);
      jLabel2.setText(""+(--Minenmakiert));} 
      if(Minenmakiert==0){
      int lsg=0;
      for(int fh=0;fh<Laenge*Breite;fh++){
      if(jButtons[fh].getText().equals("!")){
      for(int g=0;g<Minenanzahl;g++){
        if(fh==Minen[g])lsg++;}
        }
        }
      if(lsg==Minenanzahl)
      Gewonnen();
        }}
      else if(jButtons[f].getText().equals("!")){
       jLabel2.setText(""+(++Minenmakiert));
          jButtons[f].setText("");
      jButtons[f].setEnabled(true);
      } // end of if-else
      }}}}}); 
      jButtons[f].addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        for(int f=0;f<Laenge*Breite;f++){
                if (evt.getSource()==jButtons[f]) {
      boolean aa=true;
                  for(int g=0;g<Minenanzahl;g++){
                          if(f==Minen[g]){
                            GameOver();
      aa=false;
                            }
                    }
      if(aa)
                    NoMine(f);
                    break; 
                } 
          }
      }});
    cp.add(jButtons[f]);
    }
    for(int f=0;f<Minenanzahl;f++){
       Minen[f]=(int)(Math.random()*(Laenge*Breite));
      for(int g=0;g<f;g++){
        if(Minen[f]==Minen[g]) { 
          f--;      
        }
      }
    } 
    jLabel1.setBounds(56, frameHeight-70, 227, 41);
    jLabel1.setText("");
    cp.add(jLabel1);
    jLabel2.setBounds(8, frameHeight-70, 43, 41);
    jLabel2.setText(""+Minenanzahl);
    cp.add(jLabel2);
    jButton1.setBounds(frameWidth-(frameWidth/3+10), 25, frameWidth/3-10, 33);
    jButton1.setText("Neustarten");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    cp.add(jButton1);
    jButton2.setBounds(frameWidth-(frameWidth/3+10), 60, frameWidth/3-10, 33);
    jButton2.setText("Neues Spiel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
             
         Spiel einfach=new Spiel(Minenanzahl,Laenge,Breite);
    setVisible(false);
      }
    });
    cp.add(jButton2);
    jButton3.setBounds(frameWidth-(frameWidth/3+10), 95, frameWidth/3-10, 33);
    jButton3.setText("Hauptmenü");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
         new Menue();
    setVisible(false);
      }
    });
    cp.add(jButton3);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Spiel
  
  // Anfang Methoden
  
  public static void main(String[] args) {
  new Menue();
  } // end of main
    public void NoMine(int index) {
     jButtons[index].setText(String.valueOf(Aufdecken(index)));
    boolean aa=true;
    while (aa) { 
         aa=false;
    for(int f=0;f<Laenge*Breite;f++){
            if(jButtons[f].getText().equals("0")){
          boolean ab=true;
          for(int g=0;g<NullCounter&&ab;g++){
          if(f==Null[g]) ab=false;
            }
            if(ab){
            Null[NullCounter++]=f;
               jButtons[f].setText(String.valueOf(Aufdecken(f)));
               if(f>(Breite-1)){//oben
              if (!isBlocked(f-Breite)) 
                             jButtons[f-Breite].setText(String.valueOf(Aufdecken(f-Breite)));
                              if(f%Breite!=(Breite-1)&&!isBlocked(f-(Breite-1)))jButtons[f-(Breite-1)].setText(String.valueOf(Aufdecken(f-(Breite-1)))); 
                              if(f%Breite!=0&&!isBlocked(f-(Breite+1)))jButtons[f-(Breite+1)].setText(String.valueOf(Aufdecken(f-(Breite+1)))); 
               }
               if(f<(Laenge*Breite)-Breite){  //unten
              if(!isBlocked(f+Breite))
                           jButtons[f+Breite].setText(String.valueOf(Aufdecken(f+Breite)));
                           if(f%Breite!=0&&!isBlocked(f+(Breite-1)))jButtons[f+(Breite-1)].setText(String.valueOf(Aufdecken(f+(Breite-1))));
                           if(f%Breite!=(Breite-1)&&!isBlocked(f+(Breite+1)))jButtons[f+(Breite+1)].setText(String.valueOf(Aufdecken(f+(Breite+1))));
                           }
            if(f%Breite!=0&&!isBlocked(f-1)) jButtons[f-1].setText(String.valueOf(Aufdecken(f-1)));             //links
            if(f%Breite!=(Breite-1)&&!isBlocked(f+1)) jButtons[f+1].setText(String.valueOf(Aufdecken(f+1)));         
                aa=true;  }  
              }
      }
       } // end of while    
       for(int f=0;f<Laenge*Breite;f++){
       if(jButtons[f].getText().equals("1"))jButtons[f].setForeground(Color.BLUE);
      if(jButtons[f].getText().equals("2"))jButtons[f].setForeground(Color.GREEN);
      if(jButtons[f].getText().equals("3"))jButtons[f].setForeground(Color.RED);
      if(jButtons[f].getText().equals("4"))jButtons[f].setForeground(Color.MAGENTA);
      if(jButtons[f].getText().equals("5"))jButtons[f].setForeground(Color.GRAY);
      if(jButtons[f].getText().equals("6"))jButtons[f].setForeground(Color.CYAN);
      if(jButtons[f].getText().equals("7"))jButtons[f].setForeground(Color.PINK);
      if(jButtons[f].getText().equals("8"))jButtons[f].setForeground(Color.BLACK);
         if(jButtons[f].getText().equals("0")){
           jButtons[f].setText(""); jButtons[f].setEnabled(false);}} 
      Checken();
    }
    private int Aufdecken(int index){
      int u=0;
    for(int f=0;f<Minenanzahl;f++){
      if(index>(Breite-1)){//oben
           if(index-Breite==Minen[f])u++;
           else if(index-(Breite-1)==Minen[f]&&index%Breite!=(Breite-1))u++; 
           else if(index-(Breite+1)==Minen[f]&&index%Breite!=0)u++; 
        }
      if(index<(Laenge*Breite)-Breite){  //unten
        if(index+Breite==Minen[f])u++;
        else if(index+(Breite-1)==Minen[f]&&index%Breite!=0)u++;
        else if(index+(Breite+1)==Minen[f]&&index%Breite!=(Breite-1))u++;
        }
      if(index-1==Minen[f]&&index%Breite!=0) u++;             //links
      if(index+1==Minen[f]&&index%Breite!=(Breite-1)) u++; }           //rechts
    return u;
    }
      public boolean isBlocked(int index){  return jButtons[index].getText().equals("!");}
      public void Checken(){      
      int i=0,j=0;
    for(int f=0;f<Breite*Laenge;f++){
    if (jButtons[f].isEnabled()&&jButtons[f].getText().equals("")) {
        j++;
        for(int g=0;g<Minenanzahl;g++)
        if(f==Minen[g]) i++;
    }    
        }if(j==i)Gewonnen();}                   // System.err.println(i+(-(Minenmakiert-10))+""); 
      public void Gewonnen(){
             sperren();
    jLabel1.setText("Gewonnen, Zeit:"+((java.lang.System.currentTimeMillis()-Time)/1000)+"sek");
        }
      public void GameOver(){
             sperren();
    jLabel1.setText("Verloren");
    for(int f=0;f<Minenanzahl;f++)
    jButtons[Minen[f]].setBackground(Color.RED);
        }
  public void sperren(){
    for(int f=0;f<Breite*Laenge;f++)
    jButtons[f].setEnabled(false);}      
  public void jButton1_ActionPerformed(ActionEvent evt) {
    for(int f=0;f<Breite*Laenge;f++){
      jButtons[f].setText("");
      jButtons[f].setEnabled(true);
      jButtons[f].setBackground(new JButton().getBackground());}
      jLabel1.setText("");
    Time=java.lang.System.currentTimeMillis();
      NullCounter=0;
    Minenmakiert=Minenanzahl;
    jLabel2.setText(""+Minenmakiert);

  } // end of jButton1_ActionPerformed

  
  // Ende Methoden
} // end of class Spiel

