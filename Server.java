
package chatapplication;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.border.EmptyBorder;
import java.net.*;
import java.io.*;


public class Server  implements ActionListener{
    
    JTextField text;
    JPanel a1;
    static Box vertical =Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout; 
    
    Server(){
        
        f.setLayout(null);
        
        JPanel p1 = new JPanel();    
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,957,70);
        p1.setLayout(null);
        f.add(p1);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back= new JLabel(i3);
        back.setBounds(20, 20, 30, 30);
        p1.add(back);
        
        
        back.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent ae){
         //setVisible(false);
           System.exit(0);
        }});
        
        
        
        ImageIcon i4 =new ImageIcon(ClassLoader.getSystemResource("icons/photo.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile= new JLabel(i6);
        profile.setBounds(100, 10, 50, 50);
        p1.add(profile);
        
        
        ImageIcon i7 =new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video= new JLabel(i9);
        video.setBounds(700, 18, 35, 40);
        p1.add(video);
        
        
        ImageIcon i10 =new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(40, 35, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone= new JLabel(i12);
        phone.setBounds(800, 20, 40, 35);
        p1.add(phone);
        
        
        ImageIcon i13 =new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 30, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert= new JLabel(i15);
        morevert.setBounds(900, 21, 10, 30);
        p1.add(morevert); 
        
        
        JLabel name=new JLabel("Sumit Pal");
        name.setBounds(180,15,100,18);
        p1.add(name);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        
        
        JLabel status=new JLabel("Active Now");
        status.setBounds(180,35,100,18);
        p1.add(status);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,13));
        
        
        
        a1=new JPanel();
        a1.setBounds(5,75,950,880);
        f.add(a1);
        
        
        text = new JTextField();
        text.setBounds(5,965,820,40);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(text);
        
        
        JButton send=new JButton("Send");
        send.setBounds(840,965,123,40);
        send.setBackground(new Color (7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(send);
        
        
        
        
        
        f.setSize(957,1030);//size according to frame
//        f.setLocation(300,70); ; // size accordinhg to dekstop
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
         
        f.setVisible(true);
    }
    
     public void actionPerformed (ActionEvent ae){
         try{
        String out=text.getText();
        JPanel p2= formatLabel(out);
        a1.setLayout(new BorderLayout());
       
       JPanel right=new JPanel(new BorderLayout());
       right.add(p2,BorderLayout.LINE_END);
       vertical.add(right);
       vertical.add(Box.createVerticalStrut(15));
       a1.add(vertical, BorderLayout.PAGE_START);
       
       dout.writeUTF(out);
       text.setText(" ");
       
       f.repaint();
       f.invalidate();
       f.validate(); 
         }catch(Exception e){
             e.printStackTrace();
         }
     }
     
    

    public static JPanel formatLabel(String out) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    JPanel panel=new JPanel();
    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
    
    JLabel output=new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
    output.setFont(new Font("Tahoma", Font.PLAIN, 16));
    output.setBackground(new Color(37, 211, 102));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15, 15, 15, 50));
    
    panel.add(output);
//    
//    Calender cal=Calender.getInstance();
//    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//    
//    JLabel time = new JLabel();
//    time.setText(sdf.format(cal.getTime()));
//    
//    panel.add(time);
   
    return panel;
    }
    
    public static void main(String[]args){
        new Server();
        
         
        try {
            ServerSocket skt = new ServerSocket(6001);
            while(true) {
                Socket s = skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                 dout = new DataOutputStream(s.getOutputStream());
                
                while(true) {
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
