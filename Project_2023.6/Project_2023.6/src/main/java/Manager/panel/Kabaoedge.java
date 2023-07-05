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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Kabaoedge extends JPanel implements ActionListener {
    Statement stat;
    JFrame xiugaijiemiankaung;
    JComboBox xiugaimingzi;
    JButton add,delete,gai;
    JPanel p1;

    double totalmoney;
    double qianzhaimoney;
    JButton jadd1;
    JButton jadd2;
    JButton jadd3;
    JButton jadd4;
    JButton jadd5;
    JButton jadd6;
    JButton jadd7;
    JButton jadd8;
    JFrame addpage1;
    String id;
    String []temp=new String[6];;
    JButton chuangjianok;
    JDialog dialog;
    JTextField nameField,lilv,yue;
    JLabel zongzichan,jingmoney,qianzhai;
    JButton deleteenter,gaienter;
    JTextField gaijinge;
    JFrame deletepage,gaipage;
    JButton querenxiugai;
    JComboBox deleteaccountfind,gaiaccountfind;
    NewButton xianjingButton,chongzhika,otherButton,weixingbutton,zhifubaoButton,zhaiwuButton,yinghangkaButton,gupiaoButton;
    JButton daochu;
     JButton daoru;
 JFrame daorumianban;
 JTextField daorulujingfield;
     JButton daoruenter;

    public Kabaoedge(String id){
        this.setLayout(null);
        this.id=id;
        initmysql();
        initding();
        //添加add按钮
        initbutton();

        this.setLayout(null);
        this.add(p1);
     this.setVisible(true);
    }

    private void initbutton() {
        add=new JButton();
        add.setBounds(465,350,100,100);
        add.setBorderPainted(false);
        add.setContentAreaFilled(false);
        add.addActionListener(this);
        ImageIcon ico = new ImageIcon("..\\Project_2023.6\\素材\\kabao添加.png");
        Image temp=ico.getImage().getScaledInstance(add.getWidth(),add.getHeight(),ico.getImage().SCALE_DEFAULT);
        ico=new ImageIcon(temp);
        add.setIcon(ico);
        this.add(add);

        delete=new JButton();
        delete.setBounds(245,350,100,100);
        delete.setBorderPainted(false);
        delete.setContentAreaFilled(false);
        delete.addActionListener(this);
        ImageIcon ico1 = new ImageIcon("..\\Project_2023.6\\素材\\删除.png");
        Image temp1=ico1.getImage().getScaledInstance(delete.getWidth(),delete.getHeight(),ico1.getImage().SCALE_DEFAULT);
        ico1=new ImageIcon(temp1);
        delete.setIcon(ico1);
        this.add(delete);

        gai=new JButton();
        gai.setBounds(685,350,100,100);
        gai.setBorderPainted(false);
        gai.setContentAreaFilled(false);
        gai.addActionListener(this);
        ImageIcon ico2 = new ImageIcon("..\\Project_2023.6\\素材\\修改.png");
        Image temp2=ico2.getImage().getScaledInstance( gai.getWidth(), gai.getHeight(),ico2.getImage().SCALE_DEFAULT);
        ico2=new ImageIcon(temp2);
        gai.setIcon(ico2);
        this.add(gai);

        daochu=new JButton();
        daochu.setBounds(875,350,100,100);
        daochu.setBorderPainted(false);
        daochu.setContentAreaFilled(false);
        daochu.addActionListener(this);
        ImageIcon ico3 = new ImageIcon("..\\Project_2023.6\\素材\\导出入.png");
        Image temp3=ico3.getImage().getScaledInstance(daochu.getWidth(),daochu.getHeight(),ico3.getImage().SCALE_DEFAULT);
        ico3=new ImageIcon(temp3);
        daochu.setIcon(ico3);
        this.add(daochu);

        daoru=new JButton();
        daoru.setBounds(25,350,100,100);
        daoru.setBorderPainted(false);
        daoru.setContentAreaFilled(false);
        daoru.addActionListener(this);
        ImageIcon ico4 = new ImageIcon("..\\Project_2023.6\\素材\\导出入.png");
        Image temp4=ico4.getImage().getScaledInstance(daoru.getWidth(),daoru.getHeight(),ico4.getImage().SCALE_DEFAULT);
        ico4=new ImageIcon(temp4);
        daoru.setIcon(ico4);
        this.add(daoru);


        xianjingButton=new NewButton("现金账户",100,100,  new Color(7, 23, 45, 239));
       xianjingButton.setFont(new Font("123",1,30));
       xianjingButton.setForeground(Color.WHITE);
       xianjingButton.setBounds(80,150,200,200);
       xianjingButton.addActionListener(this);

         zhifubaoButton=new NewButton("支付宝账户",100,100,  new Color(21, 59, 114, 239));
        zhifubaoButton.setFont(new Font("123",1,30));
        zhifubaoButton.setForeground(Color.WHITE);
        zhifubaoButton.setBounds(300,150,200,200);
        zhifubaoButton.addActionListener(this);


        weixingbutton=new NewButton("微信账户",100,100,  new Color(40, 63, 180, 239));
        weixingbutton.setFont(new Font("123",1,30));
        weixingbutton.setForeground(Color.WHITE);
        weixingbutton.setBounds(520,150,200,200);
        weixingbutton.addActionListener(this);

        yinghangkaButton=new NewButton("银行卡账户",100,100,  new Color(21, 189, 222, 110));
        yinghangkaButton.setFont(new Font("123",1,30));
        yinghangkaButton.setForeground(Color.WHITE);
        yinghangkaButton.setBounds(740,150,200,200);
        yinghangkaButton.addActionListener(this);

         gupiaoButton=new NewButton("股票账户",100,100,  new Color(31, 171, 101, 126));
        gupiaoButton.setFont(new Font("123",1,30));
        gupiaoButton.setForeground(Color.WHITE);
        gupiaoButton.setBounds(80,450,200,200);
        gupiaoButton.addActionListener(this);

         zhaiwuButton=new NewButton("债务",100,100,  new Color(25, 173, 133, 123));
        zhaiwuButton.setFont(new Font("123",1,30));
        zhaiwuButton.setForeground(Color.WHITE);
        zhaiwuButton.setBounds(300,450,200,200);
        zhaiwuButton.addActionListener(this);

        chongzhika=new NewButton("充值卡账户",100,100,  new Color(31, 171, 101, 126));
        chongzhika.setFont(new Font("123",1,30));
        chongzhika.setForeground(Color.WHITE);
        chongzhika.setBounds(520,450,200,200);
        chongzhika.addActionListener(this);


         otherButton=new NewButton("其他账户",100,100,  new Color(38, 173, 66, 255));
        otherButton.setFont(new Font("123",1,30));
        otherButton.setForeground(Color.WHITE);
        otherButton.setBounds(740,450,200,200);
        otherButton.addActionListener(this);


      this.add(xianjingButton);
      this.add(zhifubaoButton);
      this.add(weixingbutton);
      this.add(yinghangkaButton);
      this.add(zhaiwuButton);
      this.add(otherButton);
      this.add(chongzhika);
      this.add(gupiaoButton);


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
        JLabel jingzichan=new JLabel("净资产(元)");
        jingzichan.setBounds(80,-10,100,100);
        jingzichan.setFont(new Font("1",1,20));
        jingzichan.setForeground(Color.GRAY);
        p1.add(jingzichan);

        //净资产金额
        jingmoney=new JLabel(String.valueOf(totalmoney-qianzhaimoney));
        jingmoney.setBounds(80,30,600,100);
        jingmoney.setFont(new Font("1",1,30));
        jingmoney.setForeground(new Color(9, 147, 9));
        p1.add(jingmoney);
        //总资产
        zongzichan=new JLabel("总资产: "+String.valueOf(totalmoney));
        zongzichan.setBounds(700,-10,200,100);
        zongzichan.setFont(new Font("1",1,20));
        zongzichan.setForeground(Color.GRAY);
        p1.add(zongzichan);
        //欠债
        qianzhai=new JLabel("欠债:     "+String.valueOf(qianzhaimoney));
        qianzhai.setBounds(700,30,200,100);
        qianzhai.setFont(new Font("1",1,20));
        qianzhai.setForeground(Color.GRAY);
        p1.add(qianzhai);

        ImageIcon beijing=new ImageIcon("..\\Project_2023.6\\素材\\kabaodingimage.jpg");
        JLabel jLabel=new JLabel(beijing);
        jLabel.setBounds(10,10,965,120);
        p1.add(jLabel);
    }

    private void initmysql() {
        try {
            Connection conn= DriverManager.getConnection("jdbc:mysql:///project","root","123456");
            stat=conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o=e.getSource();
        //增加
        if(o==add){
            initjframe();
        }else if(o==jadd1){
            initDiolog(1);
        }else if(o==jadd2){
            initDiolog(2);
        }else if(o==jadd3){
            initDiolog(3);
        }else if(o==jadd4){
            initDiolog(4);
        }else if(o==jadd5){
            initDiolog(5);
        }else if(o==jadd6){
            initDiolog(6);
        }else if(o==jadd7){
            initDiolog(7);
        }else if(o==jadd8){
            initDiolog(8);
        }else if(o==chuangjianok){
            temp[0]=this.id;

            temp[2]= nameField.getText();
            temp[3]= yue.getText();
            temp[4]=lilv.getText();



            try {
               String sql="insert into account(uuserid, accounttype, accountname, money, lilv,createdate,lilvstart)values ('"+temp[0]+"',"+temp[1]+",'"+temp[2]+"',"+temp[3]+","+temp[4]+",'"+temp[5]+"','"+temp[5]+"');";
               stat.executeUpdate(sql);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,"账户不能重复");
            }
            dialog.setVisible(false);


            this.remove(p1);
            p1.remove(qianzhai);
            p1.remove(zongzichan);
            p1.remove(jingmoney);
            initding();
            p1.repaint();
            this.add(p1);
            this.repaint();
        //点击删除按钮
        }else if(o==delete){
             deletepage=new JFrame("删除账户");
            deletepage.setSize(300,200);
            deletepage.setAlwaysOnTop(true);
            deletepage.setLocationRelativeTo(null);

            deletepage.setLayout(new GridLayout(3,1));
            JLabel shanchumingzi=new JLabel("请选择要删除的账户类型:");

            String []a={"现金","支付宝","微信钱包","银行卡","股票","债务","充值卡","其他"};
            deleteaccountfind=new JComboBox(a);

            deleteenter=new JButton("确认");
            deleteenter.addActionListener(this);
            deletepage.add(shanchumingzi);
            deletepage.add(deleteaccountfind);
            deletepage.add(deleteenter);


            deletepage.setVisible(true);
            //点击确认删除的按钮
        }else if(o==deleteenter){
            String s1;
            int a=deleteaccountfind.getSelectedIndex();
            a=a+1;
            int geshu=0;
            try {
                ResultSet ss=stat.executeQuery("select *from account where accounttype="+a+" and uuserid='"+id+"'");
                while (ss.next()){
                    geshu++;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        if(geshu!=0) {
            ResultSet rss = null;
            try {
                rss = stat.executeQuery("select *from account where accounttype=" + a + " and uuserid='" + id + "'");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String[] deletekuang = new String[geshu];
            int i = 0;
            try {
                while (rss.next()) {
                    deletekuang[i] = rss.getString(3);
                    i++;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            deletepage.setVisible(false);
            JFrame jFrame = new JFrame();
            jFrame.setSize(300, 200);
            jFrame.setAlwaysOnTop(true);
            jFrame.setLayout(new GridLayout(3, 1));
            jFrame.setLocationRelativeTo(null);
            jFrame.add(new JLabel("请选择要删除的对象"));
            JComboBox j1 = new JComboBox(deletekuang);
            jFrame.add(j1);
            JButton j2 = new JButton("确认删除");
            jFrame.add(j2);
            jFrame.setVisible(true);
            j2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String SS = (String) j1.getSelectedItem();
                    try {
                        stat.executeUpdate("delete from account where uuserid='" + id + "'and accountname='" + SS + "'");
                        JOptionPane.showMessageDialog(jFrame, "成功删除");
                        Kabaoedge.this.remove(p1);
                        p1.remove(qianzhai);
                        p1.remove(zongzichan);
                        p1.remove(jingmoney);
                        initding();
                        p1.repaint();
                        Kabaoedge.this.add(p1);
                        Kabaoedge.this.repaint();
                        jFrame.setVisible(false);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(jFrame, "无法删除，该账户还存在流水记录绑定");
                    }
                }
            });
        }else {
            JFrame jFrame=new JFrame();
            jFrame.setSize(200,100);
            jFrame.setLocationRelativeTo(null);
            jFrame.setLayout(new FlowLayout());
            jFrame.setAlwaysOnTop(true);
            jFrame.getContentPane().add(new JLabel("此类不存在账户"));
            jFrame.setVisible(true);
        }


            //点击修改按钮
        }else if(o==gai){
            gaipage=new JFrame("修改账户");
            gaipage.setSize(300,200);
            gaipage.setAlwaysOnTop(true);
            gaipage.setLocationRelativeTo(null);

            gaipage.setLayout(new GridLayout(3,1));
            JLabel shanchumingzi=new JLabel("请选择要修改的账户类型:");

            String []a={"现金","支付宝","微信钱包","银行卡","股票","债务","充值卡","其他"};
            gaiaccountfind=new JComboBox(a);

            gaienter=new JButton("确认");
            gaienter.addActionListener(this);
            gaipage.add(shanchumingzi);
            gaipage.add(gaiaccountfind);
            gaipage.add(gaienter);


            gaipage.setVisible(true);
            //点击确认修改
        }else if(o==gaienter){
            int geshu = 0;

            int a= gaiaccountfind.getSelectedIndex();
            a=a+1;
            try {
                ResultSet rrr=stat.executeQuery("select *from account where accounttype="+a+" and uuserid='"+id+"'");
                while (rrr.next()){
                    geshu++;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
if(geshu!=0) {
    int x = -1;
    ResultSet rss = null;
    try {
        String sql = "select *from account where accounttype=" + a + " and uuserid='" + id + "'";
        rss = stat.executeQuery(sql);
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
    String[] deletekuang = new String[geshu];
    int i = 0;
    try {
        while (rss.next()) {
            deletekuang[i] = rss.getString(3);
            i++;
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }

    gaipage.setVisible(false);
    xiugaijiemiankaung = new JFrame();
    xiugaijiemiankaung.setSize(300, 200);
    xiugaijiemiankaung.setAlwaysOnTop(true);
    xiugaijiemiankaung.setLayout(new GridLayout(3, 1));
    xiugaijiemiankaung.setLocationRelativeTo(null);
    xiugaijiemiankaung.getContentPane().add(new JLabel("请选择要修改的对象"));
    xiugaimingzi = new JComboBox(deletekuang);
    xiugaijiemiankaung.getContentPane().add(xiugaimingzi);
    xiugaijiemiankaung.getContentPane().add(new JLabel("修改余额为"));
    gaijinge = new JTextField();
    xiugaijiemiankaung.add(gaijinge);
    querenxiugai = new JButton("确认修改");
    querenxiugai.addActionListener(this);
    xiugaijiemiankaung.getContentPane().add(querenxiugai);
    xiugaijiemiankaung.setVisible(true);
}else{
    JFrame jFrame=new JFrame();
    jFrame.setSize(200,100);
    jFrame.setLocationRelativeTo(null);
    jFrame.setLayout(new FlowLayout());
    jFrame.setAlwaysOnTop(true);
    jFrame.getContentPane().add(new JLabel("此类不存在账户"));
    jFrame.setVisible(true);
}

        }else if(o== querenxiugai){
            String m1= gaijinge.getText();
            String s1= (String) xiugaimingzi.getSelectedItem();
            try {
                stat.executeUpdate("update account set money="+m1+" where uuserid='"+id+"' and accountname='"+s1+"' ");
                stat.executeUpdate("update account set lilvstart=now() where uuserid='"+id+"' and accountname='"+s1+"' ");

                JOptionPane.showMessageDialog(xiugaijiemiankaung,"修改成功");
                xiugaijiemiankaung.setVisible(false);
                this.remove(p1);
                p1.remove(qianzhai);
                p1.remove(zongzichan);
                p1.remove(jingmoney);
                initding();
                p1.repaint();
                this.add(p1);
                this.repaint();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(xiugaijiemiankaung,"没有账户可供选择");
                xiugaijiemiankaung.setVisible(false);
            }


        } else if(o==xianjingButton||o==zhifubaoButton||o==weixingbutton||o==yinghangkaButton||o==gupiaoButton||o==zhaiwuButton||o==chongzhika||o==otherButton){
           int type=0;
            if(o==xianjingButton){
                type=1;
            }else if(o==zhifubaoButton) {
                type=2;
            }else if(o==weixingbutton){
                type=3;
            }else if (o==yinghangkaButton){
                type=4;
            }else if(o==gupiaoButton){
                type=5;
            }else if(o==zhaiwuButton){
                type=6;
            }else if(o==chongzhika){
                type=7;
            }else if(o==otherButton){
                type=8;
            }

            JDialog dialog1=new JDialog();
            dialog1.setModal(true);
            dialog1.setSize(800,200);
            dialog1.setLocationRelativeTo(null);
            dialog1.setAlwaysOnTop(true);
                                    //0         1      2       3         4        5
            String[] columnNames = { "账户名", "本金","利率","创建时间","利率计算开始时间","利率天数","余额" }; // 定义表格列名数组
            // 定义表格数据数组
            String[][] tableValues = new String[100][7];
            //查询


            String sql="select *,datediff(now(),lilvstart),money+money*(lilv/100)*datediff(now(), lilvstart) 余额 from account where accounttype="+type+" and uuserid='"+this.id+"'";
            ResultSet resultSet= null;
            try {
                resultSet = stat.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            int i=0;
            try {
                while (resultSet.next()){
                    tableValues[i][0]=resultSet.getString(3);
                    tableValues[i][1]= String.valueOf(resultSet.getDouble(4));
                    tableValues[i][2]= resultSet.getString(5);
                    tableValues[i][3]=resultSet.getString(6);
                    tableValues[i][4]= resultSet.getString(7);
                    tableValues[i][5]= String.valueOf(resultSet.getDouble(8));
                    tableValues[i][6]= String.valueOf(resultSet.getDouble(9));
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
            dialog1.getContentPane().add(scrollPane, BorderLayout.CENTER);
            dialog1.setVisible(true);
        }else if(o==daochu){
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("报表");
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
            //设置单元格的值
            cell1.setCellValue("用户id");
            cell2.setCellValue("账单类型");
            cell3.setCellValue("账单名");
            cell4.setCellValue("本金");
            cell5.setCellValue("利率");
            cell6.setCellValue("创建日期");
            cell7.setCellValue("利率计算开始日期");
            int p=1;
            try {
                ResultSet r1= stat.executeQuery("select *from account where uuserid='"+id+"'");
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


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            //输出Excel文件
            try {
                FileOutputStream output=new FileOutputStream("..\\Project_2023.6\\"+id+"用户的账户记录.xls");
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

                        String sql="insert into account(uuserid, accounttype, accountname, money, lilv, createdate,lilvstart) value ('"+id+"',"+cell1.getStringCellValue()+",'"+cell2.getStringCellValue()+"',"+cell3.getStringCellValue()+",'"+cell4.getStringCellValue()+"','"+cell5.getStringCellValue()+"','"+cell6.getStringCellValue()+"')";
                    try {
                        stat.executeUpdate(sql);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(daorumianban,"第"+p+"行无法导入，账户已存在");
                    }

                }

                JOptionPane.showMessageDialog(daorumianban,"导入完毕");
                daorumianban.setVisible(false);
                this.remove(p1);
                p1.remove(qianzhai);
                p1.remove(zongzichan);
                p1.remove(jingmoney);
                initding();
                p1.repaint();
                this.add(p1);
                this.repaint();
            }else {
                JOptionPane.showMessageDialog(daorumianban,"文件不存在");
            }
        }
    }

    private void initDiolog(int type) {
        addpage1.setVisible(false);
            dialog=new JDialog(addpage1,true);
      int i=4;
        dialog.setSize(600,200);
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        String name = "null";
        if(type==1){
            name="现金";
        }else  if(type==2){
            name="支付宝";
        }else  if(type==3){
            name="微信钱包";
        }else  if(type==4){
            name="储蓄卡";
        }else  if(type==5){
            name="股票";
        }else  if(type==6){
            name="债务";
            i=6;
        }else  if(type==7){
            name="充值卡";
        }else  if(type==8){
            name="其他";
        }
        dialog.setLayout(new GridLayout(i+1,2));
        dialog.add(new JLabel("名称:"));
         nameField=new JTextField(name);
        nameField.setSize(100,30);
        dialog.add(nameField);

        dialog.add(new JLabel("余额:"));
         yue=new JTextField("0");
        yue.setSize(100,30);
        dialog.add(yue);

        dialog.add(new JLabel("账户类型:"));
        dialog.add(new JLabel(name));
        dialog.add(new JLabel("利率(%):"));
         lilv = new JTextField("0");

if(type==1){
    dialog.add(new JLabel("0"));
}else {
    yue.setSize(100, 30);
    dialog.add(lilv);
}
        Date date=new Date();
        SimpleDateFormat dates=new SimpleDateFormat("yyyy-MM-dd");
        String  s2= dates.format(date);
if(type==6){
    dialog.add(new JLabel("注：借款日期默认为创建日期"));
    dialog.add(new JLabel(""+s2));
}

        temp[1]= String.valueOf(type);
        temp[5]=s2;
chuangjianok=new JButton("创建");
        chuangjianok.setSize(200,100);
        chuangjianok.addActionListener(this);
dialog.add(chuangjianok);

        dialog.setVisible(true);
    }

    private void initjframe() {
         addpage1=new JFrame();
         addpage1.setLocationRelativeTo(null);
        addpage1.setLayout(new BorderLayout());

        addpage1.setAlwaysOnTop(true);
        addpage1.setSize(600,400);
        JPanel addpage=new JPanel();
        addpage.setLayout(new GridLayout(4,4,0,0));
        addpage.setSize(600,300);
        JPanel page1=new JPanel();
        JLabel title=new JLabel("请选择想要增加的账户类型");
        title.setSize(100,100);
        title.setFont(new Font("12",1,30));
        page1.add(title);

        addpage1.getContentPane().add(page1,BorderLayout.NORTH);

         jadd1 = addpageofbutton(1);
         jadd2 = addpageofbutton(2);
         jadd3 = addpageofbutton(3);
         jadd4 = addpageofbutton(4);
         jadd5 = addpageofbutton(5);
         jadd6 = addpageofbutton(6);
         jadd7 = addpageofbutton(7);
         jadd8 = addpageofbutton(8);
        addpage.add(jadd1);
        addpage.add(jadd2);
        addpage.add(jadd3);
        addpage.add(jadd4);
        addpage.add(initjlabel("现金"));
        addpage.add(initjlabel("支付宝"));
        addpage.add(initjlabel("微信钱包"));
        addpage.add(initjlabel("储蓄卡"));
        addpage.add(jadd5);
        addpage.add(jadd6);
        addpage.add(jadd7);
        addpage.add(jadd8);
        addpage.add(initjlabel("股票"));
        addpage.add(initjlabel("债务"));
        addpage.add(initjlabel("充值卡"));
        addpage.add(initjlabel("其他"));

        addpage1.getContentPane().add(addpage,BorderLayout.CENTER);
        addpage1.setVisible(true);
    }

    private JLabel initjlabel(String S) {
        JLabel jLabel=new JLabel("          "+S);
        jLabel.setSize(50,20);
        jLabel.setFont(new Font("1",1,20));
        return jLabel;
    }

    private JButton addpageofbutton(int i) {
        JButton temp1=new JButton();
        temp1.setSize(50,50);
        temp1.setBorderPainted(false);
        temp1.setContentAreaFilled(false);
        temp1.addActionListener(this);
        ImageIcon ico = new ImageIcon("..\\Project_2023.6\\素材\\add"+i+".png");
        Image temp=ico.getImage().getScaledInstance(temp1.getWidth(),temp1.getHeight(),ico.getImage().SCALE_DEFAULT);
        ico=new ImageIcon(temp);
        temp1.setIcon(ico);
       // temp1.add(new JLabel(name));
        return temp1;
    }
}

