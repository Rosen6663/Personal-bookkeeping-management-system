package LOGIN;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    int Jwidth=1200;
    int Jhight=800;
    NewButton Register;
    NewButton Login;
    MyRoundJpassworldtextField textField2;
    MyRoundJtextField textField1;
    Statement stat;
    public Login(){
        initmysql();
        //初始化界面
        initJFrame();
        //登录框
        initLogin();
        this.setVisible(true);
        int i=0;

        initbackgroud();

    }

    private void initmysql() {
        try {
            Connection conn= DriverManager.getConnection("jdbc:mysql:///project","root","123456");
            stat=conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initLogin() {
        initlogin();
        initusename();
        initpassword();

        initButton();
         textField1= new MyRoundJtextField();
         textField1.setBounds(260,320,250,20);
         textField2= new MyRoundJpassworldtextField();
        textField2.setBounds(260,420,250,20);
        //textField1.setForeground(new Color(0xCBB214, false));
       // textField1.setBackground(new Color(25,20,100, 0));
        this.getContentPane().add(textField1);
        this.getContentPane().add(textField2);

        initbeijing();
    }

    private void initButton() {
        Login =new NewButton("LOGIN",200,200);
        Login.setFont(new Font("LOGIN",1,20));
        Login.setBounds(250,500,200,100);
        Login.addActionListener(this);
        Register =new NewButton("REGISTER",200,200,new Color(29, 90, 133, 200));

        Register.setFont(new Font("REGISTER",1,20));
        Register.setBounds(750,330,200,100);
        Register.addActionListener(this);
        this.getContentPane().add(Login);
        this.getContentPane().add(Register);
    }

    private void initbeijing() {
        JPanel login=new JPanel();
        login.setBounds(100,100,500,600);
        login.setBackground (new Color(25,20,100,123));
        JPanel login1=new JPanel();
        login1.setBounds(600,100,500,600);
        login1.setBackground (new Color(29, 133, 74, 123));
        this.add(login1);
        this.add(login);
    }


    private void initusename() {
        //设置文字的形式与大小
        Font usernameFont =new Font(null,1,20);
        //设置账户文字
        JLabel usernameText=new JLabel("userid:");
        //设置文字位置和大小
        usernameText.setBounds(150,300,110,50);
        //设置文字颜色
        usernameText.setForeground(Color.WHITE);
        //设置文字字体
        usernameText.setFont(usernameFont);
        //将文字添加到界面上
        this.getContentPane().add(usernameText);
    }
    private void initpassword() {
        //设置文字的形式与大小
        Font usernameFont =new Font(null,1,20);
        //设置账户文字
        JLabel usernameText=new JLabel("password:");
        //设置文字位置和大小
        usernameText.setBounds(150,400,120,50);
        //设置文字颜色
        usernameText.setForeground(Color.WHITE);
        //设置文字字体
        usernameText.setFont(usernameFont);
        //将文字添加到界面上
        this.getContentPane().add(usernameText);

    }


    private void initlogin() {
        //设置文字的形式与大小
        Font usernameFont =new Font(null,1,50);
        //设置账户文字
        JLabel usernameText=new JLabel("LOGIN");
        //设置文字位置和大小
        usernameText.setBounds(270,180,500,100);
        //设置文字颜色
        usernameText.setForeground(Color.WHITE);
        //设置文字字体
        usernameText.setFont(usernameFont);
        //将文字添加到界面上
        this.getContentPane().add(usernameText);
    }

    private void initbackgroud() {
        //创建图片像
        ImageIcon ico=new ImageIcon("..\\Project_2023.6\\素材\\2.png");
        //将图片与按钮大小相适应
        Image temp=ico.getImage().getScaledInstance(Jwidth,Jhight,ico.getImage().SCALE_DEFAULT);
        ico=new ImageIcon(temp);
        JLabel backgrouds=new JLabel(ico);
        backgrouds.setBounds(0,0,Jwidth,Jhight);
        this.getContentPane().add(backgrouds);
    }

    private void initJFrame() {
        this.setSize(Jwidth,Jhight);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o= e.getSource();
        if(o== Register){
            this.setVisible(false);
            new Register();
        }else if(o==Login){
           String id= textField1.getText();

           String password= textField2.getText();
            int tt=0;
            if(id.equals("")||password.equals("")){
                if(id.equals("")){
                    JOptionPane.showMessageDialog(this,"账户不能为空");
                }else if(password.equals("")){
                    JOptionPane.showMessageDialog(this,"密码不能为空");
                }
            }
           else {
            try {
                ResultSet resultSet = stat.executeQuery("select *from user");
                    while (resultSet.next()) {
                        String temp = resultSet.getString(1);
                        if (temp.equals(id)) {
                            temp = resultSet.getString(2);
                            if (temp.equals(password)) {
                                tt = -1;
                                temp = resultSet.getString(3);
                                JOptionPane.showMessageDialog(this, "欢迎用户" + temp + "使用个人记账系统");
                                new Manager.Manager(id);
                                this.setVisible(false);
                            } else {
                                tt = -1;
                                JOptionPane.showMessageDialog(this, "账户或密码错误!");
                                break;
                            }
                        }
                    }
                    if (tt == 0) {
                        JOptionPane.showMessageDialog(this, "账户或密码错误!");
                    }
                } catch(SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
