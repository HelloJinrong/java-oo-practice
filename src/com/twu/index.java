package com.twu;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class index {
    private static User us;
    private static String termianlInput;
    private static HotSearch hotSearch;
    private static Admin adm;

    public static void fistIndex(){
        Scanner input = new Scanner(System.in);
        hotSearch =new HotSearch();
        while(true) {
            firstMenu();
            termianlInput = input.next();
            switch (termianlInput) {
                case "1":user();break;
                case "2":admin(); break;
                case "3": return;
            }
        }
    }

    public static void user(){
        System.out.println("请输入您的昵称：");
        String username =new Scanner(System.in).nextLine();
        us = new User(username);

        while (true){
            userMenu();
            termianlInput=new Scanner(System.in).nextLine();
            switch (termianlInput){
                case "1":
                    hotSearch.checkHot();break;
                case "2":
                    System.out.println("请输入要投票的热搜名字！");
                    String name =new Scanner(System.in).nextLine();
                    System.out.println("请输入要投票的票数（还有"+us.getVoteNum()+"票可投！");
                    int vo= Integer.parseInt(new Scanner(System.in).next());
                    hotSearch.vote(us,vo,name);
                    break;
                case "3":
                    System.out.println("请输入要购买的热搜名字！");
                    String str =new Scanner(System.in).nextLine();
                    System.out.println("请输入要花费的金额！");
                    int money = Integer.parseInt(new Scanner(System.in).nextLine());
                    System.out.println("请输入要购买的排名！");
                    int rank = Integer.parseInt(new Scanner(System.in).nextLine());
                    hotSearch.buy(str,rank,money);
                    break;
                case "4":
                    hotSearch.addHot();break;
                case "5": return;
            }

        }
    }

    public static void admin(){
        System.out.println("请输入您的账号：");
        String username =new Scanner(System.in).nextLine();
        System.out.println("请输入您的密码：");
        String word =new Scanner(System.in).nextLine();
        adm=new Admin();
        if(adm.login(username,word)){
            System.out.println("登陆成功！");
            while(true){
                adaMenu();
                termianlInput=new Scanner(System.in).nextLine();
                switch (termianlInput){
                    case "1":
                        hotSearch.checkHot();break;
                    case "2":
                        hotSearch.addHot();break;
                    case "3":
                        hotSearch.addSupperHot();break;
                    case "4": return;
                }

            }
        }else
            System.out.println("登陆失败！");

    }

    public  static void userMenu(){
        List<String> index= Arrays.asList("你好,"+us.getName()+"。你可以进行以下操作：","~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
                "1.查看热搜排行榜","2.给热搜事件投票","3.购买热搜","4.添加热搜","5.退出");
        index.stream().forEach(System.out::println);
    }
    public  static void adaMenu(){
        List<String> index= Arrays.asList("你好,管理员！你可以进行以下操作：","~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
                "1.查看热搜排行榜","2.添加热搜","3.添加超级热搜","4.退出");
        index.stream().forEach(System.out::println);
    }


    public static void firstMenu(){
        List<String> index1= Arrays.asList("欢迎来到热搜世界!","您是谁呀？",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
                "尊贵的用户（请输入1）","聪明的管理员（请输入2）","什么也不想干（请输入3)");
        index1.stream().forEach(System.out::println);
    }


}


