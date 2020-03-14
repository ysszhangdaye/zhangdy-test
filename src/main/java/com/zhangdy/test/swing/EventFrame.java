package com.zhangdy.test.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EventFrame extends JFrame implements ActionListener {
    private JPanel p = new JPanel();  //颜色按钮组
    private JPanel panel = new JPanel();  //操作按钮组
    private JButton jButton = null;  //按钮
    private JLabel jLabelB = new JLabel("黑色", JLabel.CENTER);// 黑色文本
    private JLabel jLabelY = new JLabel("黄色", JLabel.CENTER);//黄色文本
    private JTextField jTextFieldB = new JTextField();//黑色文本域
    private JTextField jTextFieldY = new JTextField();//黄色文本域
    private JButton resetBtn = new JButton("重新开始");   //使用匿名内部类
    private ArrayList<JButton> jButtonArrayList = new ArrayList<>();
    private JTextField jTextRes = new JTextField("");  // 输赢（如果所有的都变成黄色的话，即赢了）

    //由于继承了JFrame所以就是当前页面
    public EventFrame() {
        setLayout(new BorderLayout());    //首先清空布局
        p.setLayout(new GridLayout(5, 5));
        p.setSize(500, 300);
        for (int i = 1; i <= 25; i++) {
            jButton = new JButton();
//            jButton.setText();
            jButton.setSize(50, 20);
            jButton.setBackground(Color.black);
            //给按钮添加事件
            jButton.addActionListener(this);
            jButtonArrayList.add(jButton);
            p.add(jButton);
        }
        //文本域默认全黑，显示出现
        jTextFieldB.setText("15");
        jTextFieldY.setText("0");
        //操作按钮
        panel.setSize(500, 200);
        panel.setLayout(new GridLayout(3, 2));
        jLabelB.setFont(new Font("", Font.BOLD, 24));
        jTextFieldB.setFont(new Font("", Font.BOLD, 24));
        jLabelY.setFont(new Font("", Font.BOLD, 24));
        jTextFieldY.setFont(new Font("", Font.BOLD, 24));
        resetBtn.setFont(new Font("", Font.BOLD, 24));
        jTextRes.setFont(new Font("", Font.BOLD, 24));
        //重置按钮
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //把结果置空
                jTextRes.setText("");
                for (JButton button : jButtonArrayList) {
                    button.setBackground(Color.black);
                    updateNum();
                }
            }
        });
        panel.add(jLabelB);
        panel.add(jTextFieldB);
        panel.add(jLabelY);
        panel.add(jTextFieldY);
        panel.add(resetBtn);
        panel.add(jTextRes);
        panel.setBackground(Color.WHITE);
        getContentPane().add(p, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.SOUTH);
        p.setPreferredSize(new Dimension(0, 250));
        panel.setPreferredSize(new Dimension(400, 100));
        setBounds(200, 80, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        Color color = jButton.getBackground();   //获取当前颜色
        Color newColor = null;                  //周围应该改变的颜色
        //周围的都变色
        //上是当前位置减5； 下是加5，左是减一 ，右是加一
        // int index = Integer.parseInt(jButton.getText());
        if (color == Color.BLACK) {
            newColor = Color.yellow;
            jButton.setBackground(Color.yellow);
        } else if (color == Color.yellow) {
            newColor = Color.black;
            jButton.setBackground(Color.black);
        }
        int index = jButtonArrayList.indexOf(jButton);
        int left = index - 1;
        if ((left + 1) % 5 != 0) {    //左拐角部分不需要处理左边
            updateColor(left, newColor);
        }
        int right = index + 1;
        if (right % 5 != 0) {    //右拐角部分不需要处理右边
            updateColor(right, newColor);
        }
        int top = (index - 5);
        updateColor(top, newColor);
        int bottom = index + 5;
        updateColor(bottom, newColor);
        updateNum();
    }
    //变换颜色
    private void updateColor(int p, Color color) {
        //注意索引不能越界，而且拐角的位置需要注意（上面已经给了处理）
        if (p >= 0 && p < jButtonArrayList.size()) {
            jButtonArrayList.get(p).setBackground(color);
        }
    }
    //变动文本域的数量
    private void updateNum() {
        int blackNum = 0;
        int yellow = 0;
        for (int i = 0; i < jButtonArrayList.size(); i++) {
            Color color = jButtonArrayList.get(i).getBackground();
            if (color == Color.BLACK) {
                blackNum++;
            } else {
                yellow++;
                if (yellow == jButtonArrayList.size()) {
                    jTextRes.setText("恭喜你，你赢了");
                }
            }
        }
        jTextFieldB.setText(blackNum + "");
        jTextFieldY.setText(yellow + "");
    }
}
