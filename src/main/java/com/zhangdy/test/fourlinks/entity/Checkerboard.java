package com.zhangdy.test.fourlinks.entity;

import com.google.common.collect.Lists;
import com.zhangdy.util.IDS;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * 棋盘
 */
@Getter
@Setter
@Slf4j
public class Checkerboard {
    /** 行数 */
    private int      line;
    /** 列数 */
    private int      column;
    /** 棋盘创建者 */
    private Gamer    creator;
    /** 玩家 */
    private Gamer    gamer;
    /** 棋盘上所有的棋子 */
    private List<Piece>   pieceList;
    /** 没满的列 */
    private List<Integer> notFullColumnList;

    /** 默认行 6 行 */
    private static final int        DEFAULT_LINE            = 6;
    /** 默认宽 8 列  */
    private static final int        DEFAULT_COLUMN          = 8;
    private static final String     CREATOR_MESSAGE         = ">>>轮到我了，我把O棋子放在第{0}列...";
    private static final String     GAMER_MESSAGE           = ">>>轮到你了，你放X棋子，请选择列号：";
    private static final String     COLUMN_FULL             = ">>>该列已经满了";
    private static final String     REGULATIONS_NUMBER      = ">>>输入内容不符合要求~";
    private static final String     WINNER_MESSAGE          = ">>>{0},获得游戏胜利";

    private static final  Pattern pattern = java.util.regex.Pattern.compile("[1-8]");

    public void start(){
        robotRandomPiece();
        Scanner scan = new Scanner(System.in);
        while(true){
            boolean b = this.gamerAddPiece(scan);
            if (!b) {
                continue;
            }
            this.print();
            this.robotRandomPiece();
        }

    }

    private boolean gamerAddPiece(Scanner scan){
        System.out.print(GAMER_MESSAGE);
        String s = scan.nextLine();
        if (!pattern.matcher(s).matches()) {
            System.out.println(REGULATIONS_NUMBER);
            return false;
        }
        int column = Integer.parseInt(s);
        if (!notFullColumnList.contains(column)) {
            System.out.println(COLUMN_FULL);
            return false;
        }
        addPiece(gamer, column, getLine(column));
        return true;
    }


    private void robotRandomPiece(){
        int randomLine = getRandomColumn();
        System.out.println(MessageFormat.format(CREATOR_MESSAGE, randomLine));
        addPiece(creator, randomLine, getLine(randomLine));
        print();
    }

    private  int getLine(int column){
        if (pieceList.isEmpty()) {
            return 1;
        }
        List<Piece> collect = pieceList.stream().filter(p -> p.x == column).collect(Collectors.toList());
        if (collect == null  || collect.isEmpty()) {
            return 1;
        }
        return collect.size() + 1;
    }

    private int getRandomColumn(){
        int i = IDS.randomtInt(notFullColumnList.size());
        return notFullColumnList.get(i);
    }


    private Checkerboard(Gamer creator, Gamer gamer, int line, int column, List<Piece> pieceList,  List<Integer> notFullColumnList){
        this.line               = line;
        this.column             = column;
        this.creator            = creator;
        this.gamer              = gamer;
        this.pieceList          = pieceList;
        this.notFullColumnList  = notFullColumnList;
    }

    public static Checkerboard create(Gamer creator, Gamer gamer){
        List<Integer> notFullColumnList = Lists.newArrayList();
        for (int i = 1; i <= DEFAULT_COLUMN; i++) {
            notFullColumnList.add(i);
        }
        return new Checkerboard(creator, gamer, DEFAULT_LINE, DEFAULT_COLUMN,  Lists.newArrayList(), notFullColumnList);
    }

    public void addPiece(Gamer gamer, int x, int y){
        Piece piece = Piece.create(gamer, x, y);
        if (y == line) {
            notFullColumnList.remove(Integer.valueOf(x));
        }
        pieceList.add(piece);
        // 检查 notFullColumnList 是否为空  为空则平局
        if (notFullColumnList.isEmpty()) {
            System.out.println("平局");
            this.exit();
        }
        //   检查是否有人胜出 胜出退出游戏
        this.checkWinner();
    }

    private void checkWinner(){
        // 横向
        Comparator<Piece> xSort = Comparator.comparing(Piece::getX);
        for (int i = 1; i <= line; i++) {
            int y = i;
            List<Piece> list = pieceList.stream().filter(p -> p.y == y).sorted(xSort).collect(Collectors.toList());
            this.checkWinner(list);
        }
        // 竖向
        Comparator<Piece> ySort = Comparator.comparing(Piece::getY);
        for (int i = 1; i <= column; i++) {
            int x = i;
            List<Piece> list = pieceList.stream().filter(p -> p.x == x).sorted(ySort).collect(Collectors.toList());
            this.checkWinner(list);
        }
        // 左斜

        loop1 : for (int i = 1; i <= column; i++) {
            List<Piece> list = Lists.newArrayList();
            loop2 : for (int j = line; j >=1 ; j--) {
                if (j > line) {
                    break loop2;
                }
                int x = i;
                int y = j;
                list.addAll(pieceList.stream().filter(p -> p.x == x && p.y == y).collect(Collectors.toList()));
            }
            this.checkWinner(list);
        }


        // 右斜

    }


    private void checkWinner(List<Piece> list){
        if (list.isEmpty() || list.size() < 4) {
            return;
        }
        Gamer gamer = null;
        int count = 0;
        for (Piece piece : list) {
            if (piece.getGamer().equals(gamer)) {
                if (++count == 4) {
                    System.out.println(MessageFormat.format(WINNER_MESSAGE, gamer.getName()));
                    this.exit();
                }
                continue;
            }
            if (gamer == null || !piece.getGamer().equals(gamer)) {
                gamer = piece.getGamer();
                count = 1;
                continue;
            }
        }
    }

    private void exit(){
        print();
        System.exit(0);
    }

    public void print(){
        System.out.println(" 1 2 3 4 5 6 7 8");
        for (int i = line; i >= 1;i--) {
            for (int j = 1; j <= column; j++) {
                String s = "|{0}";
                Piece piece = this.getPiece(j, i);
                String symbol = " ";
                if (piece != null) {
                    symbol = piece.getGamer().getSymbol();
                }
                System.out.print(MessageFormat.format(s, symbol));
            }
            System.out.println("|");
        }
        System.out.println("-----------------");
    }



    public Piece getPiece(int x, int y){
        if (pieceList.isEmpty()) {
            return  null;
        }
        List<Piece> list = pieceList.stream().filter(p -> p.x == x && p.y == y).collect(Collectors.toList());
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 棋子
     */
    @Getter @Setter
    public static class Piece{
        /** 棋子归属用户 */
        private Gamer gamer;
        /** 棋子在棋盘上的坐标 */
        private int x;
        private int y;
        private Piece(Gamer gamer,  int x, int y){
            this.gamer = gamer;
            this.x = x;
            this.y = y;
        }
        public static Piece create(Gamer gamer, int x, int y){
            return new Piece(gamer, x, y);
        }
    }

}
