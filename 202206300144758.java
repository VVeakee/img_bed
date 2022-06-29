
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class sybg {
    public static void main(String[] args) {
        new Win_LOGIN();
    }
}
class Win_MAIN extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    JMenuBar menubar;
    JMenu menucx,menuwh,menubz,menuhztj;
    JMenuItem mitemcxaxhxm,mitemcxbjll,mitemcxsxcx,mitemtj,mitemxg,mitemsc,mitemgy,mitemcxgrb,mitemtc,mitembmcjhz;
    JToolBar toolbar;
    JButton tbgrb,tbllcx,tbsxcx,tbxhxmcx,tbtj,tbxg,tbsc;
    Win_MAIN(){
        setTitle("陈茂源的基于JAVA的高考录取管理系统");
        mitemcxaxhxm=new JMenuItem("按考号姓名查询");
        mitemcxbjll=new JMenuItem("按大学录取查询");
        mitemcxsxcx=new JMenuItem("自定义条件筛选查询");
        mitemcxgrb=new JMenuItem("光荣榜");
        mitembmcjhz=new JMenuItem("报名及成绩统计");
        mitemtc=new JMenuItem("退出");
        mitemtj=new JMenuItem("添加记录");
        mitemxg=new JMenuItem("修改记录");
        mitemsc=new JMenuItem("删除记录");
        mitemgy=new JMenuItem("关于...");
        menucx=new JMenu("录取");
        menuhztj=new JMenu("汇总统计");
        menuwh=new JMenu("成绩维护");
        menubz=new JMenu("帮助");
        menubar=new JMenuBar();

        menucx.add(mitemcxaxhxm);
        menucx.add(mitemcxbjll);
        menucx.add(mitemcxsxcx);
        menucx.addSeparator();
        menucx.add(mitemcxgrb);
        menucx.addSeparator();
        menucx.add(mitemtc);
        menuhztj.add(mitembmcjhz);
        menuwh.add(mitemtj);
        menuwh.add(mitemxg);
        menuwh.add(mitemsc);
        menubz.add(mitemgy);
        menubar.add(menucx);
        menubar.add(menuhztj);
        menubar.add(menuwh);
        menubar.add(menubz);
        mitemcxaxhxm.addActionListener(this);
        mitemcxbjll.addActionListener(this);
        mitemcxsxcx.addActionListener(this);
        mitemcxgrb.addActionListener(this);
        mitemtj.addActionListener(this);
        mitemxg.addActionListener(this);
        mitemsc.addActionListener(this);
        mitemtc.addActionListener(this);
        mitemgy.addActionListener(this);
        mitembmcjhz.addActionListener(this);
        setJMenuBar(menubar);

        toolbar=new JToolBar("浮动工具栏");

        tbgrb=new JButton(new ImageIcon("tb/32x32/blue32_030.gif"));
        tbllcx=new JButton(new ImageIcon("tb/32x32/blue32_068.gif"));
        tbsxcx=new JButton(new ImageIcon("tb/32x32/blue32_048.gif"));
        tbxhxmcx=new JButton(new ImageIcon("tb/32x32/blue32_029.gif"));
        tbtj=new JButton(new ImageIcon("tb/32x32/blue32_034.gif"));
        tbxg=new JButton(new ImageIcon("tb/32x32/blue32_033.gif"));
        tbsc=new JButton(new ImageIcon("tb/32x32/blue32_012.gif"));

        toolbar.add(tbgrb);
        toolbar.add(tbllcx);
        toolbar.add(tbsxcx);
        toolbar.add(tbxhxmcx);
        toolbar.add(tbtj);
        toolbar.add(tbxg);
        toolbar.add(tbsc);

        add(toolbar,BorderLayout.NORTH);
        tbgrb.addActionListener(this);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mitemcxbjll)
            new Win_LLCX();
        else if(e.getSource()==mitemcxsxcx)
            new Win_SXLL();
        else if(e.getSource()==mitemcxgrb||e.getSource()==tbgrb)
            new Win_GRB();
        else if(e.getSource()==mitemcxaxhxm)
            new Win_AXHXMCX();
        else if(e.getSource()==mitembmcjhz)
            new Win_BMTJ();
        else if(e.getSource()==mitemtj)
            new Win_TJ();
        else if(e.getSource()==mitemxg)
            new Win_XG();
        else if(e.getSource()==mitemsc)
            new Win_SC();
        else if(e.getSource()==mitemgy)
            JOptionPane.showMessageDialog(null, "作者：陈茂源\n版本：1.0\n录取时间：2022年6月27日");
        else if(e.getSource()==mitemtc)
            System.exit(0);
    }
}

class Win_BMTJ extends JFrame{
    private static final long serialVersionUID = 1L;
    int w,h;
    JPanel pNorth;
    JScrollPane pCenter;
    JLabel Label_grb;
    MyTable table;
    Object a[][];
    Object name[]={"序号","大学","录取人数","平均分"};
    Connection con;
    Statement sql;
    ResultSet rs;
    Win_BMTJ(){
        w=Toolkit.getDefaultToolkit().getScreenSize().width;
        h=Toolkit.getDefaultToolkit().getScreenSize().height;
        int i;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456");
            sql=con.createStatement();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        try{
            rs=sql.executeQuery("SELECT table.yxmc, Count(table.yxmc) AS rs, Avg(table.tzcj) AS tzcj FROM `table` GROUP BY table.yxmc ORDER BY table.yxmc;");
            a=new Object[12][4];
            i=0;
            while(rs.next()){
                a[i][0]=i+1;
                a[i][1]=rs.getString("yxmc");
                a[i][2]=rs.getString("rs");
                a[i][3]=rs.getString("tzcj");
                i++;
                if(i>=12) break;
            }
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
        try{
            con.close();
        }
        catch(SQLException e1){
            System.out.println(e1);
        }

        table=new MyTable(a,name);
        table.setRowHeight(20);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        pCenter=new JScrollPane(table);

        getContentPane().add(pCenter,BorderLayout.CENTER);
        setTitle("陈茂源的录取统计");

        Font font_label=new Font("隶书",Font.BOLD,18);
        pNorth=new JPanel();
        Label_grb=new JLabel("录取统计");
        Label_grb.setForeground(Color.red);
        Label_grb.setFont(font_label);
        pNorth.add(Label_grb);
        getContentPane().add(pNorth,BorderLayout.NORTH);

        setBounds((w-640)/2,(h-350)/2,640,350);
        setIconImage(Toolkit.getDefaultToolkit().getImage("tb/32x32/blue32_012.gif"));
        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
class Win_GRB extends JFrame implements ItemListener,WindowListener{
    private static final long serialVersionUID = 1L;
    int w,h;
    JPanel pSouth,pNorth;
    JScrollPane pCenter;
    JLabel Label_grb;
    JComboBox yx,jb;
    MyTable table;
    Object a[][];
    Object name[]={"序号","姓名","考号","大学","专业","录取时间","总分"};
    Connection con;
    Statement sql;
    ResultSet rs;
    Win_GRB(){
        w=Toolkit.getDefaultToolkit().getScreenSize().width;
        h=Toolkit.getDefaultToolkit().getScreenSize().height;
        qdzc();
        sx("会计B181","录取时间");
    }
    protected void sx(String str_yx,String str_jb)
    {
        getContentPane().removeAll();
        int i;
        try{
            rs=sql.executeQuery("SELECT * FROM `table` order by tzcj desc  LIMIT 1000");
            a=new Object[100][7];
            i=0;
            while(rs.next()){
                a[i][0]=i+1;
                a[i][1]=rs.getString("xm");
                a[i][2]=rs.getString("kh");
                a[i][3]=rs.getString("yxmc");
                a[i][4]=rs.getString("zymc");
                a[i][5]=rs.getString("lqsj");
                a[i][6]=rs.getString("tzcj");
                i++;
                if(i>=100) break;
            }
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
        table=new MyTable(a,name);
        table.setRowHeight(20);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        pCenter=new JScrollPane(table);

        getContentPane().add(pCenter,BorderLayout.CENTER);
        setTitle("陈茂源的光荣榜");

        Font font_label=new Font("隶书",Font.BOLD,18);
        pNorth=new JPanel();
        Label_grb=new JLabel("光荣榜");
        Label_grb.setForeground(Color.red);
        Label_grb.setFont(font_label);
        pNorth.add(Label_grb);
        getContentPane().add(pNorth,BorderLayout.NORTH);

        pSouth=new JPanel();
        yxbd(str_yx);

        jb=new JComboBox();
        jb.addItem("2018年12月28日");
        jb.addItem("2018年12月29日");

        if(str_jb.equals("2018年12月28日"))
            jb.setSelectedIndex(0);
        else
            jb.setSelectedIndex(1);

        yx.addItemListener(this);
        jb.addItemListener(this);
        pSouth.add(yx);
        pSouth.add(jb);
        getContentPane().add(pSouth,BorderLayout.SOUTH);
        setBounds(20,110,w-40,h-154);
        setIconImage(Toolkit.getDefaultToolkit().getImage("tb/32x32/blue32_012.gif"));
        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    protected void yxbd(String str_yx)
    {
        int i;
        yx=new JComboBox();
        try{
            rs=sql.executeQuery("SELECT table.yxmc FROM `table` GROUP BY table.yxmc ORDER BY table.yxmc;");
            while(rs.next()){
                yx.addItem(rs.getString("yxmc"));
            }
        }
        catch(SQLException e1){
            System.out.println(e1);
        }

        for(i=0;i<yx.getItemCount();i++)
        {

                yx.setSelectedIndex(i);

        }
    }
    protected void qdzc()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456");
            sql=con.createStatement();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public void itemStateChanged(ItemEvent arg0) {
        sx(yx.getSelectedItem().toString(),jb.getSelectedItem().toString());
    }
    public void windowOpened(WindowEvent arg0) {
    }
    public void windowClosing(WindowEvent arg0) {
        try{
            con.close();
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
    }
    public void windowClosed(WindowEvent arg0) {
    }
    public void windowIconified(WindowEvent arg0) {
    }
    public void windowDeiconified(WindowEvent arg0) {
    }
    public void windowActivated(WindowEvent arg0) {
    }
    public void windowDeactivated(WindowEvent arg0) {
    }
}
class Win_SXLL extends JFrame implements ActionListener,WindowListener{
    private static final long serialVersionUID = 1L;
    int w,h;
    JPanel pSouth,pNorth;
    JScrollPane pCenter;
    JLabel Label_grb,Label_sxtj,Label_px;
    JTextField Text_sxtj;
    JComboBox tj,tjgz,pxyj,px;
    JButton Button_sx;
    MyTable table;
    Object a[][];
    Object name[]={"姓名","考号","大学","专业","录取时间","总分"};
    ZD ar_tj[]={new ZD("不限","bx"),new ZD("大学","yxmc"),new ZD("考号","kh"),new ZD("姓名","xm"),new ZD("总分","tzcj"),new ZD("专业","zymc"),new ZD("录取时间","lqsj")};
    ZD ar_px[]={new ZD("不限","bx"),new ZD("大学","yxmc"),new ZD("考号","kh"),new ZD("姓名","xm"),new ZD("总分","tzcj"),new ZD("专业","zymc"),new ZD("录取时间","lqsj")};
    Connection con;
    Statement sql;
    ResultSet rs;
    Win_SXLL(){
        w=Toolkit.getDefaultToolkit().getScreenSize().width;
        h=Toolkit.getDefaultToolkit().getScreenSize().height;
        qdzc();
        sx("不限","00","00","不限","00");
    }
    protected void sx(String str_sxyj,String str_tjgz,String str_sxtj,String str_pxyj,String str_px)
    {
        getContentPane().removeAll();
        int i;
        String sql_tj="",sql_px="";
        try{
            if(str_sxyj.equals("不限")||str_sxtj.trim().length()==0)
                sql_tj="1=1";
            else
            {
                for(i=1;i<=5;i++)
                    if(str_sxyj.equals(ar_tj[i].zdhz))
                    {
                        sql_tj=ar_tj[i].zm;
                        break;
                    }
                if(str_tjgz.equals("等于"))
                    sql_tj=sql_tj+"='"+str_sxtj+"'";
                else
                    sql_tj=sql_tj+" like '%"+str_sxtj+"%'";
            }

            if(str_pxyj.equals("不限"))
                sql_px="order by tzcj asc";
            else
            {
                for(i=1;i<=3;i++)
                    if(str_pxyj.equals(ar_px[i].zdhz))
                    {
                        sql_px=ar_px[i].zm;
                        break;
                    }
                sql_px="order by "+sql_px;

                if(str_px.equals("升序"))
                    sql_px=sql_px+" asc";
                else
                    sql_px=sql_px+" desc";;
            }
            rs=sql.executeQuery("SELECT * FROM `table` where ("+sql_tj+") "+sql_px);

            rs.last();
            a=new Object[rs.getRow()][7];
            i=0;
            rs.beforeFirst();
            while(rs.next()){
                a[i][0]=rs.getString("xm");
                a[i][1]=rs.getString("kh");
                a[i][2]=rs.getString("yxmc");
                a[i][3]=rs.getString("zymc");
                a[i][4]=rs.getString("lqsj");
                a[i][5]=rs.getInt("tzcj")+"";
                i++;
            }
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
        table=new MyTable(a,name);
        table.setRowHeight(20);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        pCenter=new JScrollPane(table);

        getContentPane().add(pCenter,BorderLayout.CENTER);
        setTitle("陈茂源的自定义条件筛选录取查询");

        Font font_label=new Font("隶书",Font.BOLD,18);
        pNorth=new JPanel();
        Label_grb=new JLabel("自定义条件筛选录取查询");
        Label_grb.setForeground(Color.red);
        Label_grb.setFont(font_label);
        pNorth.add(Label_grb);
        getContentPane().add(pNorth,BorderLayout.NORTH);

        pSouth=new JPanel();

        tj=new JComboBox();
        for(i=0;i<=6;i++)
            tj.addItem(ar_tj[i].zdhz);
        for(i=0;i<=6;i++)
            if(str_sxyj.equals(ar_tj[i].zdhz))
            {
                tj.setSelectedIndex(i);
                break;
            }

        tjgz=new JComboBox();
        tjgz.addItem("等于");
        tjgz.addItem("类似于");
        if(str_tjgz.equals("等于"))
            tjgz.setSelectedIndex(0);
        else
            tjgz.setSelectedIndex(1);

        Text_sxtj=new JTextField(12);
        if(!str_sxtj.equals("00"))
            Text_sxtj.setText(str_sxtj);

        Label_sxtj=new JLabel("条件:");
        Label_px=new JLabel("　　排序:");

        pxyj=new JComboBox();
        for(i=0;i<=6;i++)
            pxyj.addItem(ar_px[i].zdhz);
        for(i=0;i<=6;i++)
            if(str_pxyj.equals(ar_px[i].zdhz))
            {
                pxyj.setSelectedIndex(i);
                break;
            }

        px=new JComboBox();
        px.addItem("升序");
        px.addItem("降序");
        if(str_px.equals("升序"))
            px.setSelectedIndex(0);
        else
            px.setSelectedIndex(1);

        JButton Button_sx=new JButton("刷新");
        Button_sx.addActionListener(this);

        pSouth.add(Label_sxtj);
        pSouth.add(tj);
        pSouth.add(tjgz);
        pSouth.add(Text_sxtj);
        pSouth.add(Label_px);
        pSouth.add(pxyj);
        pSouth.add(px);
        pSouth.add(Button_sx);
        getContentPane().add(pSouth,BorderLayout.SOUTH);
        setBounds(20,110,w-40,h-151);
        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    protected void qdzc()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456");
            sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public void windowOpened(WindowEvent arg0) {
    }
    public void windowClosing(WindowEvent arg0) {
        try{
            con.close();
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
    }
    public void windowClosed(WindowEvent arg0) {
    }
    public void windowIconified(WindowEvent arg0) {
    }
    public void windowDeiconified(WindowEvent arg0) {
    }
    public void windowActivated(WindowEvent arg0) {
    }
    public void windowDeactivated(WindowEvent arg0) {
    }
    public void actionPerformed(ActionEvent arg0) {
        sx(tj.getSelectedItem().toString(),tjgz.getSelectedItem().toString(),Text_sxtj.getText(),pxyj.getSelectedItem().toString(),px.getSelectedItem().toString());
    }
}
class Win_LLCX extends JFrame implements TreeSelectionListener,WindowListener{
    private static final long serialVersionUID = 1L;
    int w,h;
    JPanel pNorth;
    JSplitPane jspanel;
    JScrollPane jscrollpanestree;
    JLabel Label_grb;
    JTree Tree_yxbj;
    MyTable table;
    Object a[][];
    Object name[]={"姓名","考号","大学","专业","录取时间","总分"};
    Connection con;
    Statement sql;
    ResultSet rs,rs1;
    Win_LLCX(){
        w=Toolkit.getDefaultToolkit().getScreenSize().width;
        h=Toolkit.getDefaultToolkit().getScreenSize().height;
        qdzc();
        sx("保定学院",0);
    }
    protected void sx(String str_sel,int seljb)
    {
        getContentPane().removeAll();
        int i;

        try{
            rs=sql.executeQuery("SELECT * FROM `table` where yxmc='"+str_sel+"'");
            rs.last();
            a=new Object[rs.getRow()][7];
            rs.beforeFirst();
            i=0;
            while(rs.next()){
                a[i][0]=rs.getString("xm");
                a[i][1]=rs.getString("kh");
                a[i][2]=rs.getString("yxmc");
                a[i][3]=rs.getString("zymc");
                a[i][4]=rs.getString("lqsj");
                a[i][5]=rs.getString("tzcj");
                i++;
            }
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
        table=new MyTable(a,name);
        table.setRowHeight(20);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        setTitle("陈茂源的按大学录取查询");

        Font font_label=new Font("隶书",Font.BOLD,18);
        pNorth=new JPanel();
        Label_grb=new JLabel("按大学录取查询");
        Label_grb.setForeground(Color.red);
        Label_grb.setFont(font_label);
        pNorth.add(Label_grb);
        getContentPane().add(pNorth,BorderLayout.NORTH);

        treeyxbjbd(str_sel,seljb);
        JSplitPane jspanel=new JSplitPane();

        jscrollpanestree =new JScrollPane(Tree_yxbj);
        jspanel.setLeftComponent(jscrollpanestree);
        jspanel.setRightComponent(new JScrollPane(table));
        jspanel.setDividerLocation(170);
        getContentPane().add(jspanel,BorderLayout.CENTER);
        setBounds(20,110,w-40,h-151);
        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    protected void treeyxbjbd(String str_sel,int seljb)
    {
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("录取学校");

        DefaultMutableTreeNode nodeyx=null,nodebj=null,chosen=null;

        try{
            StringBuffer str_yxmcy=new StringBuffer(""),str_bjmcy=new StringBuffer(""),str_yxmcx=new StringBuffer(""),str_bjmcx=new StringBuffer("");
            rs1=sql.executeQuery("SELECT distinct yxmc FROM `table`");
            rs1.beforeFirst();
            while(rs1.next()){
                str_yxmcx=new StringBuffer(rs1.getString("yxmc"));

                if(!str_yxmcx.toString().equals(str_yxmcy.toString()))
                {
                    nodeyx=new DefaultMutableTreeNode(str_yxmcx);
                    root.add(nodeyx);

                    chosen=nodeyx;

                }

            }
            if(seljb==0)
                chosen=root;
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Tree_yxbj=new JTree(root);
        TreePath visiblePath = new TreePath(((DefaultTreeModel)Tree_yxbj.getModel()).getPathToRoot(chosen));
        Tree_yxbj.setSelectionPath(visiblePath);
        Tree_yxbj.scrollPathToVisible(visiblePath);
        Tree_yxbj.addTreeSelectionListener(this);
    }
    protected void qdzc()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456");
            sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public void windowOpened(WindowEvent arg0) {
    }
    public void windowClosing(WindowEvent arg0) {
        try{
            con.close();
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
    }
    public void windowClosed(WindowEvent arg0) {
    }
    public void windowIconified(WindowEvent arg0) {
    }
    public void windowDeiconified(WindowEvent arg0) {
    }
    public void windowActivated(WindowEvent arg0) {
    }
    public void windowDeactivated(WindowEvent arg0) {
    }
    public void valueChanged(TreeSelectionEvent arg0) {
        DefaultMutableTreeNode node=(DefaultMutableTreeNode)Tree_yxbj.getLastSelectedPathComponent();
        if (node.isLeaf()) {
            System.out.println("你选择了：" + node.getUserObject().toString());
            sx(node.getUserObject().toString(),0);
        }

    }
}



class Win_AXHXMCX extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    Box box1,box2,basebox;
    JLabel Label_bt,Label_kh,Label_xm;
    JTextField Text_kh,Text_xm;
    JButton Button_cx;
    Connection con;
    Statement sql;
    ResultSet rs;
    Win_AXHXMCX(){
        setTitle("陈茂源的按考号姓名查询");
        Font font_label=new Font("隶书",Font.BOLD,18);

        Label_bt=new JLabel("按考号姓名查询");
        Label_bt.setForeground(Color.red);
        Label_bt.setFont(font_label);

        Text_kh=new JTextField(16);
        Text_xm=new JTextField(16);
        Label_kh=new JLabel("考号:");
        Label_xm=new JLabel("姓名:");

        Button_cx=new JButton("查询");
        Button_cx.addActionListener(this);

        box1=Box.createHorizontalBox();
        box1.add(Label_kh);
        box1.add(Box.createHorizontalStrut(10));
        box1.add(Text_kh);

        box2=Box.createHorizontalBox();
        box2.add(Label_xm);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(Text_xm);

        basebox=Box.createVerticalBox();
        basebox.add(Box.createVerticalStrut(10));
        basebox.add(Label_bt);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box1);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box2);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(Button_cx);
        add(basebox);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        int w=Toolkit.getDefaultToolkit().getScreenSize().width;
        int h=Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((w-470)/2,(h-230)/2-90,470,230);
        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    protected void cjcx(String str_kh,String str_xm)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456");
            sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        try{
            rs=sql.executeQuery("SELECT * FROM `table` where (kh='"+str_kh+"' and xm='"+str_xm+"')");
            if(rs.next())
            {

                String str_cxxm=rs.getString("xm");
                String str_yxmc=rs.getString("yxmc");
                String str_zymc =rs.getString("zymc");
                String str_tzcj=rs.getInt("tzcj")+"";
                JOptionPane.showMessageDialog(null,"姓名:"+str_cxxm+"总分:"+str_tzcj+"大学:"+str_yxmc+"专业:"+str_zymc,"录取结果",JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null,"考号或姓名输入错误!","录取",JOptionPane.WARNING_MESSAGE);
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
        try
        {
            con.close();
        }
        catch ( SQLException sqlex )
        {
            System.err.println( "Unable to disconnect" );
            sqlex.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent arg0) {
        if(Text_kh.getText().trim().length()==0)
            JOptionPane.showMessageDialog(null,"考号不能为空!","录取",JOptionPane.WARNING_MESSAGE);
        else if(Text_xm.getText().trim().length()==0)
            JOptionPane.showMessageDialog(null,"姓名不能为空!","录取",JOptionPane.WARNING_MESSAGE);
        else
            cjcx(Text_kh.getText(),Text_xm.getText());
    }
}
class Win_TJ extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    Box box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,basebox;
    JLabel Label_bt,Label_xm, Label_kh, Label_yxdh, Label_zydh, Label_lqsj, Label_yxmc, Label_zymc, Label_klmc, Label_pcmc, Label_tzcj;
    JTextField Text_xm,Text_kh, Text_yxdh, Text_zydh, Text_lqsj, Text_yxmc, Text_zymc, Text_klmc, Text_pcmc, Text_tzcj;
    JButton Button_tj;
    Connection con;
    Statement sql;
    ResultSet rs;
    Win_TJ(){
        setTitle("陈茂源的添加记录");
        Font font_label=new Font("隶书",Font.BOLD,18);

        Label_bt=new JLabel("添加记录");
        Label_bt.setForeground(Color.red);
        Label_bt.setFont(font_label);

        Label_xm=new JLabel("　　姓名:");
        Label_kh=new JLabel("　　考号:");
        Label_yxdh=new JLabel("　　院校代号:");
        Label_zydh=new JLabel("　　专业代号:");
        Label_lqsj=new JLabel("   录取时间:");
        Label_yxmc=new JLabel("　　大学:");
        Label_zymc=new JLabel("　　专业:");
        Label_klmc=new JLabel("　　科类代码:");
        Label_pcmc=new JLabel("　　批次:");
        Label_tzcj=new JLabel(" 总分:");

        Text_xm=new JTextField(16);
        Text_kh=new JTextField(16);
        Text_yxdh=new JTextField(16);
        Text_zydh=new JTextField(16);
        Text_lqsj=new JTextField(16);
        Text_yxmc=new JTextField(16);
        Text_zymc=new JTextField(16);
        Text_klmc=new JTextField(16);
        Text_pcmc=new JTextField(16);
        Text_tzcj=new JTextField(16);




        Button_tj=new JButton("添加");
        Button_tj.addActionListener(this);

        box1=Box.createHorizontalBox();
        box1.add(Label_xm);
        box1.add(Box.createHorizontalStrut(10));
        box1.add(Text_xm);

        box2=Box.createHorizontalBox();
        box2.add(Label_kh);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(Text_kh);

        box3=Box.createHorizontalBox();
        box3.add(Label_yxdh);
        box3.add(Box.createHorizontalStrut(10));
        box3.add(Text_yxdh);

        box4=Box.createHorizontalBox();
        box4.add(Label_zydh);
        box4.add(Box.createHorizontalStrut(10));
        box4.add(Text_zydh);

        box5=Box.createHorizontalBox();
        box5.add(Label_lqsj);
        box5.add(Box.createHorizontalStrut(10));
        box5.add(Text_lqsj);

        box6=Box.createHorizontalBox();
        box6.add(Label_yxmc);
        box6.add(Box.createHorizontalStrut(10));
        box6.add(Text_yxmc);

        box7=Box.createHorizontalBox();
        box7.add(Label_zymc);
        box7.add(Box.createHorizontalStrut(10));
        box7.add(Text_zymc);


        box8=Box.createHorizontalBox();
        box8.add(Label_klmc);
        box8.add(Box.createHorizontalStrut(10));
        box8.add(Text_klmc);

        box9=Box.createHorizontalBox();
        box9.add(Label_pcmc);
        box9.add(Box.createHorizontalStrut(10));
        box9.add(Text_pcmc);

        box10=Box.createHorizontalBox();
        box10.add(Label_tzcj);
        box10.add(Box.createHorizontalStrut(10));
        box10.add(Text_tzcj);

        basebox=Box.createVerticalBox();
        basebox.add(Box.createVerticalStrut(10));
        basebox.add(Label_bt);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box1);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box2);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box3);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box4);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box5);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box6);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box7);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box8);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box9);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box10);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(Button_tj);
        add(basebox);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        int w=Toolkit.getDefaultToolkit().getScreenSize().width;
        int h=Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((w-470)/2,(h-480)/2-90,470,480);
        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    protected void tjjl(String str_xm,String str_kh,String str_yxdh,String str_zydh,String str_lqsj,String str_yxmc,String str_zymc,String str_klmc,String str_pcmc,String str_tzcj)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456");
            //sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        try{
            System.out.println("insert into `table` (xm,kh,yxdh,zydh,lqsj,yxmc,zymc,klmc,pcmc,tzcj) values('"+str_xm+"','"+str_kh+"','"+str_yxdh+"','"+str_zydh+"','"+str_lqsj+"','"+str_yxmc+"','"+str_zymc+"','"+str_klmc+"','"+str_pcmc+"','"+str_tzcj+"')");
            int ok=sql.executeUpdate("insert into `table` (xm,kh,yxdh,zydh,lqsj,yxmc,zymc,klmc,pcmc,tzcj) values('"+str_xm+"','"+str_kh+"','"+str_yxdh+"','"+str_zydh+"','"+str_lqsj+"','"+str_yxmc+"','"+str_zymc+"','"+str_klmc+"','"+str_pcmc+"','"+str_tzcj+"')");
            //if(ok==0)
            //{
            //	JOptionPane.showMessageDialog(null,"添加记录失败!","添加记录",JOptionPane.WARNING_MESSAGE);
            //}
            //else
            {
                JOptionPane.showMessageDialog(null,"添加记录成功!","添加记录",JOptionPane.WARNING_MESSAGE);
                Text_xm.setText("");
                Text_kh.setText("");
                Text_yxdh.setText("");
                Text_zydh.setText("");
                Text_lqsj.setText("");
                Text_yxmc.setText("");
                Text_zymc.setText("");
                Text_klmc.setText("");
                Text_pcmc.setText("");
                Text_tzcj.setText("");

            }
        }
        catch(SQLException e1){
            JOptionPane.showMessageDialog(null,"添加记录失败!","添加记录",JOptionPane.WARNING_MESSAGE);
            //System.out.println(e1);
        }
        try
        {
            con.close();
        }
        catch ( SQLException sqlex )
        {
            System.err.println( "Unable to disconnect" );
            sqlex.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent arg0) {
        if(Text_kh.getText().trim().length()==0)
            JOptionPane.showMessageDialog(null,"考号不能为空!","添加记录",JOptionPane.WARNING_MESSAGE);
        else if(Text_xm.getText().trim().length()==0)
            JOptionPane.showMessageDialog(null,"姓名不能为空!","添加记录",JOptionPane.WARNING_MESSAGE);
        else
            tjjl(Text_xm.getText(),Text_kh.getText(), Text_yxdh.getText(), Text_zydh.getText(), Text_lqsj.getText(), Text_yxmc.getText(), Text_zymc.getText(), Text_klmc.getText(), Text_pcmc.getText(), Text_tzcj.getText());
    }
}
class Win_XG extends JFrame implements ActionListener,ItemListener,WindowListener{
    private static final long serialVersionUID = 1L;
    Box box0,box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,basebox;
    JLabel Label_search,Label_bt,Label_xm, Label_kh, Label_yxdh, Label_zydh, Label_lqsj, Label_yxmc, Label_zymc, Label_klmc, Label_pcmc, Label_tzcj;
    JTextField Text_search,Text_xm,Text_kh, Text_yxdh, Text_zydh, Text_lqsj, Text_yxmc, Text_zymc, Text_klmc, Text_pcmc, Text_tzcj;
    JComboBox list_xmxh;
    JButton Button_tj;
    Connection con;
    Statement sql;
    ResultSet rs;
    Win_XG(){
        qdzc();
        sx("0");
    }
    protected void sx(String str_xmxh)
    {
        getContentPane().removeAll();
        setTitle("陈茂源的修改记录");
        Font font_label=new Font("隶书",Font.BOLD,18);

        Label_bt=new JLabel("修改记录");
        Label_bt.setForeground(Color.red);
        Label_bt.setFont(font_label);

        Label_search=new JLabel("　　考号:");
        JButton button = new JButton("查找");

        button.addActionListener(this);

        Label_xm=new JLabel("　　姓名:");
        Label_kh=new JLabel("　　考号:");
        Label_yxdh=new JLabel("　　院校代号:");
        Label_zydh=new JLabel("　　专业代号:");
        Label_lqsj=new JLabel("   录取时间:");
        Label_yxmc=new JLabel("　　大学:");
        Label_zymc=new JLabel("　　专业:");
        Label_klmc=new JLabel("　　科类代码:");
        Label_pcmc=new JLabel("　　批次:");
        Label_tzcj=new JLabel(" 总分:");

        Text_search =new JTextField(16);
        Text_xm=new JTextField(16);
        Text_kh=new JTextField(16);
        Text_yxdh=new JTextField(16);
        Text_zydh=new JTextField(16);
        Text_lqsj=new JTextField(16);
        Text_yxmc=new JTextField(16);
        Text_zymc=new JTextField(16);
        Text_klmc=new JTextField(16);
        Text_pcmc=new JTextField(16);
        Text_tzcj=new JTextField(16);

        Button_tj=new JButton("保存修改");
        Button_tj.addActionListener(this);

        box0=Box.createHorizontalBox();
        box0.add(Label_search);
        box0.add(Box.createHorizontalStrut(10));
        box0.add(Text_search);
        box0.add(button);

        box1=Box.createHorizontalBox();
        box1.add(Label_xm);
        box1.add(Box.createHorizontalStrut(10));
        box1.add(Text_xm);

        box2=Box.createHorizontalBox();
        box2.add(Label_kh);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(Text_kh);

        box3=Box.createHorizontalBox();
        box3.add(Label_yxdh);
        box3.add(Box.createHorizontalStrut(10));
        box3.add(Text_yxdh);

        box4=Box.createHorizontalBox();
        box4.add(Label_zydh);
        box4.add(Box.createHorizontalStrut(10));
        box4.add(Text_zydh);

        box5=Box.createHorizontalBox();
        box5.add(Label_lqsj);
        box5.add(Box.createHorizontalStrut(10));
        box5.add(Text_lqsj);

        box6=Box.createHorizontalBox();
        box6.add(Label_yxmc);
        box6.add(Box.createHorizontalStrut(10));
        box6.add(Text_yxmc);

        box7=Box.createHorizontalBox();
        box7.add(Label_zymc);
        box7.add(Box.createHorizontalStrut(10));
        box7.add(Text_zymc);


        box8=Box.createHorizontalBox();
        box8.add(Label_klmc);
        box8.add(Box.createHorizontalStrut(10));
        box8.add(Text_klmc);

        box9=Box.createHorizontalBox();
        box9.add(Label_pcmc);
        box9.add(Box.createHorizontalStrut(10));
        box9.add(Text_pcmc);

        box10=Box.createHorizontalBox();
        box10.add(Label_tzcj);
        box10.add(Box.createHorizontalStrut(10));
        box10.add(Text_tzcj);



        basebox=Box.createVerticalBox();

//        basebox.add(Box.createVerticalStrut(10));
//        basebox.add(Label_bt);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box0);


        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box1);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box2);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box3);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box4);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box5);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box6);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box7);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box8);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box9);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box10);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(Button_tj);

        add(basebox);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        int w=Toolkit.getDefaultToolkit().getScreenSize().width;
        int h=Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((w-470)/2,(h-480)/2-90,470,530);


        Text_xm.setText("");
        Text_kh.setText("");
        Text_yxdh.setText("");
        Text_zydh.setText("");
        Text_lqsj.setText("");
        Text_yxmc.setText("");
        Text_zymc.setText("");
        Text_klmc.setText("");
        Text_pcmc.setText("");
        Text_tzcj.setText("");

        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    protected void xgjl()
    {
        String str_xm = Text_xm.getText();
        String str_kh = Text_kh.getText();
        String str_yxdh = Text_yxdh.getText();
        String str_zydh = Text_zydh.getText();
        String str_lqsj = Text_lqsj.getText();
        String str_yxmc = Text_yxmc.getText();
        String str_zymc = Text_zymc.getText();
        String str_klmc = Text_klmc.getText();
        String str_pcmc = Text_pcmc.getText();
        String str_tzcj = Text_tzcj.getText();

        try{
            System.out.println("update `table` set xm='"+str_xm+"',kh='"+str_kh+"',yxdh='"+str_yxdh+"',zydh='"+str_zydh+"',lqsj='"+str_lqsj+"',yxmc='"+str_yxmc+"',zymc='"+str_zymc+"',klmc='"+str_klmc+"',pcmc='"+str_pcmc+"',tzcj='"+str_tzcj+"' where kh='"+Text_search.getText()+"' limit 1");
            int ok=sql.executeUpdate("update `table` set xm='"+str_xm+"',kh='"+str_kh+"',yxdh='"+str_yxdh+"',zydh='"+str_zydh+"',lqsj='"+str_lqsj+"',yxmc='"+str_yxmc+"',zymc='"+str_zymc+"',klmc='"+str_klmc+"',pcmc='"+str_pcmc+"',tzcj='"+str_tzcj+"' where kh='"+Text_search.getText()+"' limit 1");
            if(ok==0)
            {
                JOptionPane.showMessageDialog(null,"修改记录失败!","修改记录",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"修改记录成功!","修改记录",JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(SQLException e1){
            JOptionPane.showMessageDialog(null,"修改记录失败!","修改记录",JOptionPane.WARNING_MESSAGE);
            //System.out.println(e1);
        }
    }

    protected void qdzc()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456");
            sql=con.createStatement();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public void actionPerformed(ActionEvent arg0) {
        if (Text_kh.getText().equals("")||!Text_kh.getText().equals(Text_search.getText())){
            String kh_search = Text_search.getText();
            try{
                //System.out.println("SELECT kh FROM `table` where kh="+kh_search+" LIMIT 1;");
                rs=sql.executeQuery("SELECT * FROM `table` where kh='"+kh_search+"' LIMIT 1;");
                rs.next();
                Text_xm.setText(rs.getString("xm"));
                Text_kh.setText(rs.getString("kh"));
                Text_yxdh.setText(rs.getString("yxdh"));
                Text_zydh.setText(rs.getString("zydh"));
                Text_lqsj.setText(rs.getString("lqsj"));
                Text_yxmc.setText(rs.getString("yxmc"));
                Text_zymc.setText(rs.getString("zymc"));
                Text_klmc.setText(rs.getString("klmc"));
                Text_pcmc.setText(rs.getString("pcmc"));
                Text_tzcj.setText(rs.getString("tzcj"));
            }
            catch(SQLException e1){
                System.out.println(e1);
                JOptionPane.showMessageDialog(null,"未找到该考号!","提示",JOptionPane.WARNING_MESSAGE);
            }
        }else {
            xgjl();
        }
    }

    public void itemStateChanged(ItemEvent arg0) {
        sx(list_xmxh.getSelectedItem().toString());
    }
    public void windowOpened(WindowEvent arg0) {
    }
    public void windowClosing(WindowEvent arg0) {
        try{
            con.close();
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
    }
    public void windowClosed(WindowEvent arg0) {
    }
    public void windowIconified(WindowEvent arg0) {
    }
    public void windowDeiconified(WindowEvent arg0) {
    }
    public void windowActivated(WindowEvent arg0) {
    }
    public void windowDeactivated(WindowEvent arg0) {
    }
}
class Win_SC extends JFrame implements ActionListener,ItemListener,WindowListener{
    private static final long serialVersionUID = 1L;
    Box box0,box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,basebox;
    JLabel Label_search,Label_bt,Label_xm, Label_kh, Label_yxdh, Label_zydh, Label_lqsj, Label_yxmc, Label_zymc, Label_klmc, Label_pcmc, Label_tzcj;
    JTextField Text_search,Text_xm,Text_kh, Text_yxdh, Text_zydh, Text_lqsj, Text_yxmc, Text_zymc, Text_klmc, Text_pcmc, Text_tzcj;
    JComboBox list_xmxh;
    JButton Button_tj;
    Connection con;
    Statement sql;
    ResultSet rs;
    Win_SC(){
        qdzc();
        sx("0");
    }
    protected void sx(String str_xmxh)
    {
        getContentPane().removeAll();
        setTitle("陈茂源的修改记录");
        Font font_label=new Font("隶书",Font.BOLD,18);

        Label_bt=new JLabel("删除记录");
        Label_bt.setForeground(Color.red);
        Label_bt.setFont(font_label);

        Label_search=new JLabel("　　考号:");
        JButton button = new JButton("查找");

        button.addActionListener(this);

        Label_xm=new JLabel("　　姓名:");
        Label_kh=new JLabel("　　考号:");
        Label_yxdh=new JLabel("　　院校代号:");
        Label_zydh=new JLabel("　　专业代号:");
        Label_lqsj=new JLabel("   录取时间:");
        Label_yxmc=new JLabel("　　大学:");
        Label_zymc=new JLabel("　　专业:");
        Label_klmc=new JLabel("　　科类代码:");
        Label_pcmc=new JLabel("　　批次:");
        Label_tzcj=new JLabel(" 总分:");

        Text_search =new JTextField(16);
        Text_xm=new JTextField(16);
        Text_kh=new JTextField(16);
        Text_yxdh=new JTextField(16);
        Text_zydh=new JTextField(16);
        Text_lqsj=new JTextField(16);
        Text_yxmc=new JTextField(16);
        Text_zymc=new JTextField(16);
        Text_klmc=new JTextField(16);
        Text_pcmc=new JTextField(16);
        Text_tzcj=new JTextField(16);

        Button_tj=new JButton("确认删除");
        Button_tj.addActionListener(this);

        box0=Box.createHorizontalBox();
        box0.add(Label_search);
        box0.add(Box.createHorizontalStrut(10));
        box0.add(Text_search);
        box0.add(button);

        box1=Box.createHorizontalBox();
        box1.add(Label_xm);
        box1.add(Box.createHorizontalStrut(10));
        box1.add(Text_xm);

        box2=Box.createHorizontalBox();
        box2.add(Label_kh);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(Text_kh);

        box3=Box.createHorizontalBox();
        box3.add(Label_yxdh);
        box3.add(Box.createHorizontalStrut(10));
        box3.add(Text_yxdh);

        box4=Box.createHorizontalBox();
        box4.add(Label_zydh);
        box4.add(Box.createHorizontalStrut(10));
        box4.add(Text_zydh);

        box5=Box.createHorizontalBox();
        box5.add(Label_lqsj);
        box5.add(Box.createHorizontalStrut(10));
        box5.add(Text_lqsj);

        box6=Box.createHorizontalBox();
        box6.add(Label_yxmc);
        box6.add(Box.createHorizontalStrut(10));
        box6.add(Text_yxmc);

        box7=Box.createHorizontalBox();
        box7.add(Label_zymc);
        box7.add(Box.createHorizontalStrut(10));
        box7.add(Text_zymc);


        box8=Box.createHorizontalBox();
        box8.add(Label_klmc);
        box8.add(Box.createHorizontalStrut(10));
        box8.add(Text_klmc);

        box9=Box.createHorizontalBox();
        box9.add(Label_pcmc);
        box9.add(Box.createHorizontalStrut(10));
        box9.add(Text_pcmc);

        box10=Box.createHorizontalBox();
        box10.add(Label_tzcj);
        box10.add(Box.createHorizontalStrut(10));
        box10.add(Text_tzcj);



        basebox=Box.createVerticalBox();

//        basebox.add(Box.createVerticalStrut(10));
//        basebox.add(Label_bt);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box0);


        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box1);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box2);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box3);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box4);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box5);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box6);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box7);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box8);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box9);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box10);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(Button_tj);

        add(basebox);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        int w=Toolkit.getDefaultToolkit().getScreenSize().width;
        int h=Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((w-470)/2,(h-480)/2-90,470,530);


        Text_xm.setText("");
        Text_kh.setText("");
        Text_yxdh.setText("");
        Text_zydh.setText("");
        Text_lqsj.setText("");
        Text_yxmc.setText("");
        Text_zymc.setText("");
        Text_klmc.setText("");
        Text_pcmc.setText("");
        Text_tzcj.setText("");

        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    protected void scjl()
    {

        try{

            System.out.println("delete from `table`" + " where kh='"+Text_search.getText()+"' limit 1");
            int ok=sql.executeUpdate("delete from `table`" + " where kh='"+Text_search.getText()+"' limit 1");
            if(ok==0)
            {
                JOptionPane.showMessageDialog(null,"删除记录失败!","删除记录",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"删除记录成功!","删除记录",JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(SQLException e1){
            JOptionPane.showMessageDialog(null,"删除记录失败!","删除记录",JOptionPane.WARNING_MESSAGE);
            //System.out.println(e1);
        }
    }

    protected void qdzc()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456");
            sql=con.createStatement();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public void actionPerformed(ActionEvent arg0) {
        if (Text_kh.getText().equals("")||!Text_kh.getText().equals(Text_search.getText())){
            String kh_search = Text_search.getText();
            try{
                //System.out.println("SELECT kh FROM `table` where kh="+kh_search+" LIMIT 1;");
                rs=sql.executeQuery("SELECT * FROM `table` where kh='"+kh_search+"' LIMIT 1;");
                rs.next();
                Text_xm.setText(rs.getString("xm"));
                Text_kh.setText(rs.getString("kh"));
                Text_yxdh.setText(rs.getString("yxdh"));
                Text_zydh.setText(rs.getString("zydh"));
                Text_lqsj.setText(rs.getString("lqsj"));
                Text_yxmc.setText(rs.getString("yxmc"));
                Text_zymc.setText(rs.getString("zymc"));
                Text_klmc.setText(rs.getString("klmc"));
                Text_pcmc.setText(rs.getString("pcmc"));
                Text_tzcj.setText(rs.getString("tzcj"));
            }
            catch(SQLException e1){
                System.out.println(e1);
                JOptionPane.showMessageDialog(null,"未找到该考号!","提示",JOptionPane.WARNING_MESSAGE);
            }
        }else {
            scjl();
        }
    }
    public void itemStateChanged(ItemEvent arg0) {
        sx(list_xmxh.getSelectedItem().toString());
    }
    public void windowOpened(WindowEvent arg0) {
    }
    public void windowClosing(WindowEvent arg0) {
        try{
            con.close();
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
    }
    public void windowClosed(WindowEvent arg0) {
    }
    public void windowIconified(WindowEvent arg0) {
    }
    public void windowDeiconified(WindowEvent arg0) {
    }
    public void windowActivated(WindowEvent arg0) {
    }
    public void windowDeactivated(WindowEvent arg0) {
    }
}
class Win_LOGIN extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    Box box1,box2,basebox;
    JLabel Label_bt,Label_yhm,Label_mm;
    JTextField Text_yhm;
    JPasswordField Text_mm;
    JButton Button_dl;
    Connection con;
    Statement sql;
    ResultSet rs;
    Win_LOGIN(){
        setTitle("陈茂源的用户登录");
        Font font_label=new Font("隶书",Font.BOLD,18);

        Label_bt=new JLabel("基于Java的高考录取管理系统");
        Label_bt.setForeground(Color.red);
        Label_bt.setFont(font_label);

        Text_yhm=new JTextField();
        Text_mm=new JPasswordField();
        Label_yhm=new JLabel("用户名:");
        Label_mm=new JLabel("　密码:");

        Button_dl=new JButton("登录");
        Button_dl.addActionListener(this);

        box1=Box.createHorizontalBox();
        box1.add(Label_yhm);
        box1.add(Box.createHorizontalStrut(10));
        box1.add(Text_yhm);

        box2=Box.createHorizontalBox();
        box2.add(Label_mm);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(Text_mm);

        basebox=Box.createVerticalBox();
        basebox.add(Box.createVerticalStrut(10));
        basebox.add(Label_bt);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box1);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box2);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(Button_dl);
        add(basebox);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        int w=Toolkit.getDefaultToolkit().getScreenSize().width;
        int h=Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((w-470)/2,(h-230)/2-90,470,230);
        validate();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    protected void dlpd(String str_yhm,String str_mm)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(""+e);
        }
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmy?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8","root","123456");
            sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        try{
            rs=sql.executeQuery("SELECT * FROM yhb where (yhm='"+str_yhm+"' and mm='"+str_mm+"')");
            if(rs.next())
            {
                dispose();
                new Win_MAIN();
            }
            else
                JOptionPane.showMessageDialog(null,"用户名或密码不对!","用户登录",JOptionPane.WARNING_MESSAGE);
        }
        catch(SQLException e1){
            System.out.println(e1);
        }
        try
        {
            con.close();
        }
        catch ( SQLException sqlex )
        {
            System.err.println( "Unable to disconnect" );
            sqlex.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent arg0) {
        char c[]=Text_mm.getPassword();
        String str_mm=new String(c);
        if(Text_yhm.getText().trim().length()==0)
            JOptionPane.showMessageDialog(null,"用户名不能为空!","用户登录",JOptionPane.WARNING_MESSAGE);
        else if(str_mm.trim().length()==0)
            JOptionPane.showMessageDialog(null,"密码不能为空!","用户登录",JOptionPane.WARNING_MESSAGE);
        else
            dlpd(Text_yhm.getText(),str_mm);
    }
}
class MyTable extends JTable{
    private static final long serialVersionUID = 1L;
    MyTable(Object[][] a,Object[] lm)
    {
        super(a,lm);
    }
    public boolean isCellEditable(int row,int column)
    {
        return false;
    }
}
class ZD{
    String zdhz;
    String zm;
    ZD(String zdhz,String zm)
    {
        this.zdhz=zdhz;
        this.zm=zm;
    }
}