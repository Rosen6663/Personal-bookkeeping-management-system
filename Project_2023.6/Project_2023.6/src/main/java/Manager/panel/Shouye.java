package Manager.panel;



import LOGIN.NewButton;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;

public class Shouye extends JPanel implements ActionListener {
    Statement stat;

    JPanel p1;

    double totalmoney;
    double qianzhaimoney;
    NewButton tianjia,shanchu,xiugai,chaxun,daoru,daochu;
    String id;

    JLabel zongzichan, jingmoney, qianzhai;
    JFrame tianjiapage;
    JComboBox<String> shouruzhichu;
    JButton querenxuanze;
    JComboBox<String> 账户选择;
    JButton enterjiajia;
    JComboBox<String> kemubox;
    JTextField liushuifield,beizufield;
    JFrame jiapage2,shanchupage2;
    JFrame shanchupage1,xiugaipage1;
    JComboBox<String> shanchubox1,xiugaibox1;
    JButton deletebutton1;
     JButton shanchubutton;
JComboBox<String> xuanzebox;
     JButton xiugaibutton1;
    JFrame xiugaipage2;
 JComboBox<String> xiugaixuanzebox;
    JButton xiugaibutton;
    JTextField xiugaimoney;
    JFrame chaxunpage1;
    JComboBox<String> chaxunbox1;
     JButton chaxunbutton1;
    JFrame chaxunpage2;
     JTextField daorulujingfield;
    JFrame  daorumianban;
    JButton daoruenter;

    public Shouye(String id) {
        this.setLayout(null);
        this.id = id;
        initmysql();
        initding();

        tianjia=new NewButton("添加一条新记账",100,100,  new Color(75, 231, 201, 255));
        tianjia.setFont(new Font("123",1,30));
        tianjia.setForeground(Color.WHITE);
        tianjia.setBounds(100,150,400,150);
        tianjia.addActionListener(this);
        this.add(tianjia);

        shanchu=new NewButton("删除一条记账",100,100,  new Color(90, 192, 229, 255));
        shanchu.setFont(new Font("123",1,30));
        shanchu.setForeground(Color.WHITE);
        shanchu.setBounds(502,150,400,150);
        shanchu.addActionListener(this);
        this.add(shanchu);

        xiugai=new NewButton("修改一条记账",100,100,  new Color(15, 65, 173, 255));
        xiugai.setFont(new Font("123",1,30));
        xiugai.setForeground(Color.WHITE);
        xiugai.setBounds(100,302,400,150);
        xiugai.addActionListener(this);
        this.add(xiugai);

        chaxun=new NewButton("查询记账记录",100,100,  new Color(12, 9, 91, 255));
        chaxun.setFont(new Font("123",1,30));
        chaxun.setForeground(Color.WHITE);
        chaxun.setBounds(502,302,400,150);
        chaxun.addActionListener(this);
        this.add(chaxun);
        daochu=new NewButton("导出",100,100,  new Color(28, 137, 215, 255));
        daochu.setFont(new Font("123",1,30));
        daochu.setForeground(Color.WHITE);
        daochu.setBounds(100,454,400,150);
        daochu.addActionListener(this);
        this.add(daochu);
        daoru=new NewButton("导入",100,100,  new Color(37, 63, 201, 255));
        daoru.setFont(new Font("123",1,30));
        daoru.setForeground(Color.WHITE);
        daoru.setBounds(502,454,400,150);
        daoru.addActionListener(this);
        this.add(daoru);




        this.setLayout(null);
        this.add(p1);
        this.setVisible(true);
    }


    private void initding() {

        //设置总金额
        String sql1="select sum(money+money*(lilv/100)*datediff(now(), lilvstart)) from account where accounttype!=6 and uuserid='"+this.id+"';";
        String sql2="select sum(money+money*(lilv/100)*datediff(now(), lilvstart)) from account where accounttype=6 and uuserid='"+this.id+"';";
        try {
            ResultSet r1= stat.executeQuery(sql1);


            while(r1.next()){

                totalmoney= r1.getDouble(1);
            }
            r1.close();
            ResultSet r2= stat.executeQuery(sql2);
            r2.next();
            qianzhaimoney= r2.getDouble(1);
            r2.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        p1=new JPanel(new BorderLayout());
        p1.setBounds(10,0,965,120);
        p1.setLayout(null);
        //贴上文字
        initflashmoney();
    }


    private void initflashmoney() {
        JLabel jingzichan = new JLabel("净资产(元)");
        jingzichan.setBounds(80, -10, 100, 100);
        jingzichan.setFont(new Font("1", 1, 20));
        jingzichan.setForeground(Color.GRAY);
        p1.add(jingzichan);

        //净资产金额
        jingmoney = new JLabel(String.valueOf(totalmoney - qianzhaimoney));
        jingmoney.setBounds(80, 30, 600, 100);
        jingmoney.setFont(new Font("1", 1, 30));
        jingmoney.setForeground(new Color(9, 147, 9));
        p1.add(jingmoney);
        //总资产
        zongzichan = new JLabel("总资产: " + String.valueOf(totalmoney));
        zongzichan.setBounds(700, -10, 200, 100);
        zongzichan.setFont(new Font("1", 1, 20));
        zongzichan.setForeground(Color.GRAY);
        p1.add(zongzichan);
        //欠债
        qianzhai = new JLabel("欠债:     " + String.valueOf(qianzhaimoney));
        qianzhai.setBounds(700, 30, 200, 100);
        qianzhai.setFont(new Font("1", 1, 20));
        qianzhai.setForeground(Color.GRAY);
        p1.add(qianzhai);

        ImageIcon beijing = new ImageIcon("..\\Project_2023.6\\素材\\kabaodingimage.jpg");
        JLabel jLabel = new JLabel(beijing);
        jLabel.setBounds(10, 10, 965, 120);
        p1.add(jLabel);


    }

    private void initmysql() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql:///project", "root", "123456");
            stat = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o=e.getSource();
        //添加一条新记账

        if(o==tianjia){
            tianjiapage=new JFrame();
            tianjiapage.setLayout(new FlowLayout());
            tianjiapage.setAlwaysOnTop(true);
            tianjiapage.setLocationRelativeTo(null);
            tianjiapage.setSize(300,150);
            tianjiapage.getContentPane().add(new JLabel("选择账户"));

            int p= 0;
            try {
                p = 0;
                ResultSet r=stat.executeQuery("select *from account where uuserid='"+id+"'");
                while (r.next()){
                    p++;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if(p!=0) {
                String[] s1 = new String[p];
                ResultSet r1;
                try {
                    r1 = stat.executeQuery("select *from account where uuserid='" + id + "'");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                int i = 0;
                try {
                    while (r1.next()) {
                        s1[i] = r1.getString(3);
                        i++;
                    }
                    r1.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                账户选择 = new JComboBox<>(s1);
                tianjiapage.getContentPane().add(账户选择);
                tianjiapage.getContentPane().add(new JLabel("选择流水方向"));
                String[] s2 = {"收入", "支出"};
                shouruzhichu = new JComboBox<>(s2);
                tianjiapage.getContentPane().add(shouruzhichu);
                querenxuanze = new JButton("确认选择");
                querenxuanze.addActionListener(this);
                querenxuanze.setPreferredSize(new Dimension(100, 50));
                tianjiapage.getContentPane().add(querenxuanze);
                tianjiapage.setVisible(true);
            }else{
                JFrame jFrame=new JFrame();
                jFrame.setSize(200,100);
                jFrame.setLocationRelativeTo(null);
                jFrame.setLayout(new FlowLayout());
                jFrame.setAlwaysOnTop(true);
                jFrame.getContentPane().add(new JLabel("没有账户可用来转账"));
                jFrame.setVisible(true);
            }
        }else if(o==querenxuanze){
            tianjiapage.setVisible(false);
             jiapage2=new JFrame();
            jiapage2.setSize(300,180);
            jiapage2.setLayout(new FlowLayout());
            jiapage2.setAlwaysOnTop(true);
            jiapage2.setLocationRelativeTo(null);
            jiapage2.getContentPane().add(new JLabel("选择想要添加的科目"));

            int p=0;
            try {
                if(shouruzhichu.getSelectedIndex()==0){
                    ResultSet r= stat.executeQuery("select *from shouruzikemu where uuserid='"+id+"'");
                    while (r.next()){
                        p++;
                    }
                }else {
                    ResultSet r= stat.executeQuery("select *from zhichuzikemu where uuserid='"+id+"'");
                    while (r.next()){
                        p++;
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String []s1=new String[p];
            //选择收入
            ResultSet r1;
            if(shouruzhichu.getSelectedIndex()==0){
                try {
                 r1 = stat.executeQuery("select *from shouruzikemu where uuserid='"+id+"'");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //选择支出
            }else {
                try {
                   r1= stat.executeQuery("select *from zhichuzikemu where uuserid='"+id+"'");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            int i=0;
            try {
                while (r1.next()){
                            s1[i]=r1.getString(2)+"-"+r1.getString(3);
                            i++;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            kemubox=new JComboBox<>(s1);
            jiapage2.getContentPane().add(kemubox);
            jiapage2.getContentPane().add(new JLabel("请输入流水的金额"));
             liushuifield=new JTextField();
            liushuifield.setPreferredSize(new Dimension(150,20));
            jiapage2.getContentPane().add(liushuifield);
            jiapage2.getContentPane().add(new JLabel("备注"));
             beizufield=new JTextField();
             beizufield.setPreferredSize(new Dimension(200,20));

            jiapage2.getContentPane().add(beizufield);
            enterjiajia=new JButton("确认添加");
            enterjiajia.addActionListener(this);
            jiapage2.getContentPane().add(enterjiajia);


            jiapage2.setVisible(true);
        }else if(o==enterjiajia){
            String sql1;
            String sql2;

            String acco= (String) 账户选择.getSelectedItem();
            int i=0;
              String s2= (String) kemubox.getSelectedItem();
            String []P=s2.split("-");
            String money=liushuifield.getText();
            String beizu=beizufield.getText();


if(money.equals("")){
    JOptionPane.showMessageDialog(jiapage2,"金额不能为空");
}else {
    String sql3;
    //收入
    if (shouruzhichu.getSelectedIndex() == 0) {
        if (acco.equals("债务")) {
            sql1 = "insert into injizhang(uuserid, accountname, bigkemu, zikemu, money, createdate, beizhu)\n" +
                    "values ('" + id + "','" + acco + "','" + P[0] + "','" + P[1] + "'," + money + ",now(),'" + beizu + "');";
            sql2 = "update account set money=money+money*(lilv/100)*datediff(now(),lilvstart)-" + money + " where accountname='" + acco + "' and uuserid='" + id + "'";
            sql3 = "update account set lilvstart=now() where accountname='" + acco + "' and uuserid='" + id + "'";
        } else {
            sql1 = "insert into injizhang(uuserid, accountname, bigkemu, zikemu, money, createdate, beizhu)\n" +
                    "values ('" + id + "','" + acco + "','" + P[0] + "','" + P[1] + "'," + money + ",now(),'" + beizu + "');";
            sql2 = "update account set money=money+money*(lilv/100)*datediff(now(),lilvstart)+" + money + " where accountname='" + acco + "' and uuserid='" + id + "'";
            sql3 = "update account set lilvstart=now() where accountname='" + acco + "' and uuserid='" + id + "'";
        }

    } else {
        if (acco.equals("债务")) {
            sql1 = "insert into outjizhang(uuserid, accountname, bigkemu, zikemu, money, createdate, beizhu)\n" +
                    "values ('" + id + "','" + acco + "','" + P[0] + "','" + P[1] + "'," + money + ",now(),'" + beizu + "');";
            sql2 = "update account set money=money+money*(lilv/100)*datediff(now(),lilvstart)+" + money + " where accountname='" + acco + "' and uuserid='" + id + "'";
            sql3 = "update account set lilvstart=now() where accountname='" + acco + "' and uuserid='" + id + "'";
        } else {
            sql1 = "insert into outjizhang(uuserid, accountname, bigkemu, zikemu, money, createdate, beizhu)\n" +
                    "values ('" + id + "','" + acco + "','" + P[0] + "','" + P[1] + "'," + money + ",now(),'" + beizu + "');";
            sql2 = "update account set money=money+money*(lilv/100)*datediff(now(),lilvstart)-" + money + " where accountname='" + acco + "' and uuserid='" + id + "'";
            sql3 = "update account set lilvstart=now() where accountname='" + acco + "' and uuserid='" + id + "'";
        }
    }
    try {
        stat.executeUpdate(sql1);
        stat.executeUpdate(sql2);
        stat.executeUpdate(sql3);


        JOptionPane.showMessageDialog(jiapage2, "添加成功");
        jiapage2.setVisible(false);
        this.remove(p1);
        p1.remove(qianzhai);
        p1.remove(zongzichan);
        p1.remove(jingmoney);
        initding();
        p1.repaint();
        this.add(p1);
        this.repaint();
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
}

        }else if(o==shanchu){
                 shanchupage1=new JFrame();
                 shanchupage1.setSize(300,120);
                 shanchupage1.setLayout(new FlowLayout());
                 shanchupage1.setLocationRelativeTo(null);
                 shanchupage1.setAlwaysOnTop(true);
                 shanchupage1.getContentPane().add(new JLabel("请选择你想要删除的账单信息类型"));

                 String []s={"收入","支出"};
                  shanchubox1=new JComboBox<>(s);
                 shanchupage1.getContentPane().add(shanchubox1);
                deletebutton1=new JButton("确认");
                deletebutton1.addActionListener(this);
                shanchupage1.getContentPane().add(deletebutton1);
                 shanchupage1.setVisible(true);
        }else if(o==deletebutton1){
            shanchupage1.setVisible(false);
            shanchupage2=new JFrame();
            shanchupage2.setSize(300,150);
            shanchupage2.setLayout(new FlowLayout());
            shanchupage2.setLocationRelativeTo(null);
            shanchupage2.setAlwaysOnTop(true);

            shanchupage2.getContentPane().add(new JLabel("请选择要删除的记账记录"));
            //收入
            ResultSet r1;
            int p=0;

            try {
                if (shanchubox1.getSelectedIndex() == 0) {
                    r1 = stat.executeQuery("select *from injizhang where uuserid='" + id + "'");
                    while (r1.next()) {
                        p++;
                    }

                    r1.close();


                        r1 = stat.executeQuery("select *from injizhang where uuserid='" + id + "'");

                    } else {
                        r1 = stat.executeQuery("select *from outjizhang where uuserid='" + id + "'");
                        while (r1.next()) {
                            p++;
                        }

                        r1.close();
                        r1 = stat.executeQuery("select *from outjizhang where uuserid='" + id + "'");
                    }
                } catch(SQLException ex){
                    throw new RuntimeException(ex);
                }
            if(p!=0){

                String[] s1 = new String[p];
                int i = 0;
                try {
                    while (r1.next()) {
                        s1[i] = r1.getString(2) + " " + r1.getString(3) + " " + r1.getString(4) + " " + r1.getString(5) + " " + r1.getString(6);
                        i++;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                xuanzebox = new JComboBox<>(s1);
                shanchupage2.getContentPane().add(xuanzebox);
                shanchubutton = new JButton("确认删除");
                shanchubutton.addActionListener(this);
                shanchupage2.getContentPane().add(shanchubutton);

                shanchupage2.setVisible(true);
            }else {
                JFrame jFrame=new JFrame();
                jFrame.setSize(200,100);
                jFrame.setLocationRelativeTo(null);
                jFrame.setLayout(new FlowLayout());
                jFrame.setAlwaysOnTop(true);
                 jFrame.getContentPane().add(new JLabel("没有记账记录"));
                 jFrame.setVisible(true);
            }
        }else if(o==shanchubutton){
            String temp= (String) xuanzebox.getSelectedItem();
            String []s1= temp.split(" ");

            try {
                if(shanchubox1.getSelectedIndex()==0){
                    stat.executeUpdate("delete from injizhang where uuserid='"+id+"' and createdate='"+s1[4]+" "+s1[5]+"'");
                    stat.executeUpdate("update account set money=money-"+s1[3]+" where uuserid='"+id+"' and accountname='"+s1[0]+"'");
                }else{
                    stat.executeUpdate("delete from outjizhang where uuserid='"+id+"' and createdate='"+s1[4]+" "+s1[5]+"'");
                    stat.executeUpdate("update account set money=money+"+s1[3]+" where uuserid='"+id+"' and accountname='"+s1[0]+"'");
                }
                JOptionPane.showMessageDialog(shanchupage2,"删除成功");
                shanchupage2.setVisible(false);
                this.remove(p1);
                p1.remove(qianzhai);
                p1.remove(zongzichan);
                p1.remove(jingmoney);
                initding();
                p1.repaint();
                this.add(p1);
                this.repaint();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }else if(o==xiugai){
            xiugaipage1=new JFrame();
            xiugaipage1.setSize(300,120);
            xiugaipage1.setLayout(new FlowLayout());
            xiugaipage1.setLocationRelativeTo(null);
            xiugaipage1.setAlwaysOnTop(true);
            xiugaipage1.getContentPane().add(new JLabel("请选择你想要修改的账单信息类型"));

            String []s={"收入","支出"};
            xiugaibox1=new JComboBox<>(s);
            xiugaipage1.getContentPane().add(xiugaibox1);
            xiugaibutton1=new JButton("确认");
            xiugaibutton1.addActionListener(this);
            xiugaipage1.getContentPane().add(xiugaibutton1);
            xiugaipage1.setVisible(true);
        }else if(o==xiugaibutton1){
            xiugaipage1.setVisible(false);
            xiugaipage2 = new JFrame();
            xiugaipage2.setSize(300,150);
            xiugaipage2.setLayout(new FlowLayout());
            xiugaipage2.setLocationRelativeTo(null);
            xiugaipage2.setAlwaysOnTop(true);

            xiugaipage1.add(new JLabel("请选择要修改的记账记录"));
            //收入
            ResultSet r1;
            int p=0;
            try {
                if(xiugaibox1.getSelectedIndex()==0){
                    r1= stat.executeQuery("select *from injizhang where uuserid='"+id+"'");
                    while (r1.next()){
                        p++;
                    }

                    r1.close();
                    r1= stat.executeQuery("select *from injizhang where uuserid='"+id+"'");
                }else {
                    r1= stat.executeQuery("select *from outjizhang where uuserid='"+id+"'");
                    while (r1.next()){
                        p++;
                    }

                    r1.close();
                    r1= stat.executeQuery("select *from outjizhang where uuserid='"+id+"'");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if(p!=0){
                String []s1=new String[p];
                int i=0;
                try {
                    while (r1.next()){
                        s1[i]=r1.getString(2)+" "+r1.getString(3)+" "+r1.getString(4)+" "+r1.getString(5)+" "+r1.getString(6);
                        i++;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                xiugaixuanzebox=new JComboBox<>(s1);
                xiugaipage2.add(xiugaixuanzebox);
                xiugaipage2.add(new JLabel("输入修改的金额"));
                xiugaimoney=new JTextField();
                xiugaimoney.setPreferredSize(new Dimension(150,30));
                xiugaipage2.add(xiugaimoney);

                xiugaibutton = new JButton("确认修改");
                xiugaibutton.addActionListener(this);
                xiugaipage2.add(xiugaibutton);

                xiugaipage2.setVisible(true);
            }else {
                JFrame jFrame=new JFrame();
                jFrame.setSize(200,100);
                jFrame.setLocationRelativeTo(null);
                jFrame.setLayout(new FlowLayout());
                jFrame.setAlwaysOnTop(true);
                jFrame.getContentPane().add(new JLabel("没有记账记录"));
                jFrame.setVisible(true);
            }

        }else if(o==xiugaibutton){
            String money=xiugaimoney.getText();
            if(money.equals("")){
                JFrame jFrame=new JFrame();
                jFrame.setSize(200,100);
                jFrame.setLocationRelativeTo(null);
                jFrame.setLayout(new FlowLayout());
                jFrame.setAlwaysOnTop(true);
                jFrame.getContentPane().add(new JLabel("金额不能为空"));
                jFrame.setVisible(true);
            }else {
                String temp = (String) xiugaixuanzebox.getSelectedItem();
                String[] s = temp.split(" ");
                try {
                    System.out.println(temp);
                    if (xiugaibox1.getSelectedIndex() == 0) {
                        stat.executeUpdate("update  injizhang set money=" + money + " where uuserid='" + id + "' and createdate='" + s[4] + " " + s[5] + "'");
                        stat.executeUpdate("update account set money=money-" + s[3] + "+" + money + " where uuserid='" + id + "' and accountname='" + s[0] + "'");
                    } else {
                        stat.executeUpdate("update  outjizhang set money=" + money + " where uuserid='" + id + "' and createdate='" + s[4] + " " + s[5] + "'");
                        stat.executeUpdate("update account set money=money+" + s[3] + "-" + money + " where uuserid='" + id + "' and accountname='" + s[0] + "'");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(xiugaipage2, "修改成功");
                xiugaipage2.setVisible(false);
                this.remove(p1);
                p1.remove(qianzhai);
                p1.remove(zongzichan);
                p1.remove(jingmoney);
                initding();
                p1.repaint();
                this.add(p1);
                this.repaint();
            }
        }else if(o==chaxun){
           chaxunpage1=new JFrame();
           chaxunpage1.setSize(300,120);
           chaxunpage1.setLayout(new FlowLayout());
           chaxunpage1.setLocationRelativeTo(null);
           chaxunpage1.setAlwaysOnTop(true);
           chaxunpage1.getContentPane().add(new JLabel("请选择你想要查询的账单信息类型"));

            String []s={"收入","支出"};
            chaxunbox1=new JComboBox<>(s);
            chaxunpage1.getContentPane().add(chaxunbox1);
            chaxunbutton1=new JButton("确认");
            chaxunbutton1.addActionListener(this);
            chaxunpage1.getContentPane().add(chaxunbutton1);
            chaxunpage1.setVisible(true);
        }else if(o==chaxunbutton1){
            chaxunpage1.setVisible(false);
            chaxunpage2 = new JFrame();
            chaxunpage2.setSize(900,400);

            chaxunpage2.setLocationRelativeTo(null);
            chaxunpage2.setAlwaysOnTop(true);

            String[] columnNames = { "账户名", "大类科目","小类科目","流水钱数","创建时间","备注" }; // 定义表格列名数组
            // 定义表格数据数组
            String[][] tableValues = new String[100][6];
            ResultSet r1=null;
            try {
                if(chaxunbox1.getSelectedIndex()==0){
                   r1=  stat.executeQuery("select *from injizhang where uuserid='"+id+"'");
                }else {
                     r1=  stat.executeQuery("select *from outjizhang where uuserid='"+id+"'");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                int i=0;
                while (r1.next()){
                    tableValues[i][0]= r1.getString(2);
                    tableValues[i][1]=r1.getString(3);
                    tableValues[i][2]=r1.getString(4);
                    tableValues[i][3]=r1.getString(5);
                    tableValues[i][4]=r1.getString(6);
                    tableValues[i][5]=r1.getString(7);
                    i++;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


            // 创建指定列名和数据的表格
            JTable table = new JTable(tableValues, columnNames);
            // 创建显示表格的滚动面板
            JScrollPane scrollPane = new JScrollPane(table);
            // 将滚动面板添加到边界布局的中间
            chaxunpage2.add(scrollPane, BorderLayout.CENTER);
            chaxunpage2.setVisible(true);
        }else if(o==daochu){
            //创建Excel对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("收入报表");
            //创建HSSFRow对象 （行）
            HSSFRow row = sheet.createRow(0);
            //创建HSSFCell对象  （单元格）
            HSSFCell cell1=row.createCell(0);
            HSSFCell cell2=row.createCell(1);
            HSSFCell cell3=row.createCell(2);
            HSSFCell cell4=row.createCell(3);
            HSSFCell cell5=row.createCell(4);
            HSSFCell cell6=row.createCell(5);
            HSSFCell cell7=row.createCell(6);
            HSSFCell cell8=row.createCell(7);
            //设置单元格的值
            cell1.setCellValue("用户id");
            cell2.setCellValue("账户名");
            cell3.setCellValue("大类科目");
            cell4.setCellValue("小类科目");
            cell5.setCellValue("金额");
            cell6.setCellValue("创建日期");
            cell7.setCellValue("备注");

            int p=1;
            try {
                ResultSet r1= stat.executeQuery("select *from injizhang where uuserid='"+id+"'");
                while (r1.next()){
                    HSSFRow row1 = sheet.createRow(p);
                    HSSFCell cell11=row1.createCell(0);
                    HSSFCell cell22=row1.createCell(1);
                    HSSFCell cell33=row1.createCell(2);
                    HSSFCell cell44=row1.createCell(3);
                    HSSFCell cell55=row1.createCell(4);
                    HSSFCell cell66=row1.createCell(5);
                    HSSFCell cell77=row1.createCell(6);

                    cell11.setCellValue(r1.getString(1));
                    cell22.setCellValue(r1.getString(2));
                    cell33.setCellValue(r1.getString(3));
                    cell44.setCellValue(r1.getString(4));
                    cell55.setCellValue(r1.getString(5));
                    cell66.setCellValue(r1.getString(6));
                    cell77.setCellValue(r1.getString(7));

                    p++;
                }
                HSSFSheet sheet1 = workbook.createSheet("支出报表");
                HSSFRow row2 = sheet1.createRow(0);
                //创建HSSFCell对象  （单元格）
                HSSFCell cell1o=row2.createCell(0);
                HSSFCell cell2o=row2.createCell(1);
                HSSFCell cell3o=row2.createCell(2);
                HSSFCell cell4o=row2.createCell(3);
                HSSFCell cell5o=row2.createCell(4);
                HSSFCell cell6o=row2.createCell(5);
                HSSFCell cell7o=row2.createCell(6);

                //设置单元格的值
                cell1o.setCellValue("用户id");
                cell2o.setCellValue("账户名");
                cell3o.setCellValue("大类科目");
                cell4o.setCellValue("小类科目");
                cell5o.setCellValue("金额");
                cell6o.setCellValue("创建日期");
                cell7o.setCellValue("备注");

                ResultSet r2= stat.executeQuery("select *from outjizhang where uuserid='"+id+"'");
                p=1;
                while (r2.next()){
                    HSSFRow row1 = sheet1.createRow(p);
                    HSSFCell cell11=row1.createCell(0);
                    HSSFCell cell22=row1.createCell(1);
                    HSSFCell cell33=row1.createCell(2);
                    HSSFCell cell44=row1.createCell(3);
                    HSSFCell cell55=row1.createCell(4);
                    HSSFCell cell66=row1.createCell(5);
                    HSSFCell cell77=row1.createCell(6);

                    cell11.setCellValue(r2.getString(1));
                    cell22.setCellValue(r2.getString(2));
                    cell33.setCellValue(r2.getString(3));
                    cell44.setCellValue(r2.getString(4));
                    cell55.setCellValue(r2.getString(5));
                    cell66.setCellValue(r2.getString(6));
                    cell77.setCellValue(r2.getString(7));

                    p++;
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            //输出Excel文件
            try {
                FileOutputStream output=new FileOutputStream("D:\\学习\\编程\\java\\Project_2023.6\\"+id+"用户的记账记录.xls");
                workbook.write(output);
                output.flush();
                JFrame jFrame=new JFrame();
                jFrame.setSize(200,100);
                jFrame.setLocationRelativeTo(null);
                jFrame.setLayout(new FlowLayout());
                jFrame.setAlwaysOnTop(true);
                jFrame.getContentPane().add(new JLabel("导出成功"));
                jFrame.setVisible(true);
            } catch (IOException ex) {
                JFrame jFrame=new JFrame();
                jFrame.setSize(200,100);
                jFrame.setLocationRelativeTo(null);
                jFrame.setLayout(new FlowLayout());
                jFrame.setAlwaysOnTop(true);
                jFrame.getContentPane().add(new JLabel("导出失败，该excel文件正在使用"));
                jFrame.setVisible(true);
            }
        }else if(o==daoru){
            daorumianban=new JFrame();
            daorumianban.setSize(300,150);
            daorumianban.setAlwaysOnTop(true);
            daorumianban.setLocationRelativeTo(null);
            daorumianban.setLayout(new FlowLayout());

            daorumianban.getContentPane().add(new JLabel("输入想要导入的excel文件的绝对路径"));
             daorulujingfield=new JTextField();
            daorulujingfield.setPreferredSize(new Dimension(100,50));
            daorumianban.getContentPane().add(daorulujingfield);
             daoruenter=new JButton("导入");
             daoruenter.addActionListener(this);
            daorumianban.getContentPane().add(daoruenter);


            daorumianban.setVisible(true);
        }else if(o==daoruenter){
            String path=daorulujingfield.getText();
            File file=new File(path);
            if(file.exists()){
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(path);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                HSSFWorkbook workbook = null;
                try {
                    workbook = new HSSFWorkbook(fis);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                HSSFSheet sheet = workbook.getSheetAt(0);
                //行是从0开始
                //列是从1开始
                 int totalrow=sheet.getLastRowNum();
               for(int p=1;p<=totalrow;p++){
                   HSSFRow row1=sheet.getRow(p);

                   HSSFCell cell1=row1.getCell(1);
                   HSSFCell cell2=row1.getCell(2);
                   HSSFCell cell3=row1.getCell(3);
                   HSSFCell cell4=row1.getCell(4);
                   HSSFCell cell5=row1.getCell(5);
                   HSSFCell cell6=row1.getCell(6);
                   try {
                       String sql="insert into injizhang(uuserid, accountname, bigkemu, zikemu, money, createdate, beizhu) value ('"+id+"','"+cell1.getStringCellValue()+"','"+cell2.getStringCellValue()+"','"+cell3.getStringCellValue()+"','"+cell4.getStringCellValue()+"','"+cell5.getStringCellValue()+"','"+cell6.getStringCellValue()+"')";
                       stat.executeUpdate(sql);
                   } catch (SQLException ex) {
                       JOptionPane.showMessageDialog(daorumianban,"第"+p+"行无法导入请检查账户和科目是否存在,或者已经存在此条记账记录了");
                   }
               }
                System.out.println(1);
                HSSFSheet sheet1 = workbook.getSheet("支出报表");
                //行是从0开始
                //列是从1开始
                int totalrow1=sheet1.getLastRowNum();
                for(int p=1;p<=totalrow1;p++){
                    HSSFRow row1p=sheet1.getRow(p);

                    HSSFCell cell1l=row1p.getCell(1);
                    HSSFCell cell2l=row1p.getCell(2);
                    HSSFCell cell3l=row1p.getCell(3);
                    HSSFCell cell4l=row1p.getCell(4);
                    HSSFCell cell5l=row1p.getCell(5);
                    HSSFCell cell6l=row1p.getCell(6);
                    System.out.println(cell1l.getStringCellValue());
                    try {
                        String sql="insert into outjizhang(uuserid, accountname, bigkemu, zikemu, money, createdate, beizhu) value ('"+id+"','"+cell1l.getStringCellValue()+"','"+cell2l.getStringCellValue()+"','"+cell3l.getStringCellValue()+"','"+cell4l.getStringCellValue()+"','"+cell5l.getStringCellValue()+"','"+cell6l.getStringCellValue()+"')";
                        stat.executeUpdate(sql);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(daorumianban,"第"+p+"行无法导入请检查账户和科目是否存在,或者已经存在此条记账记录了");
                    }
                }
                JOptionPane.showMessageDialog(daorumianban,"导入完毕");
                daorumianban.setVisible(false);
            }else {
                JOptionPane.showMessageDialog(daorumianban,"文件不存在");
            }
        }
    }
}