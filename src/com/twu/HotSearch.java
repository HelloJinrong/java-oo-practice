package com.twu;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HotSearch{

    public boolean isBuy;//是否买过
    private String description;//热搜描述
    private int hotNumber;//热度
    private boolean isSupper;//是否为超级热搜
    private int rank;//排名
    private Map<Integer,Integer> buyHot=new HashMap<>();//热搜名次与价格

    public boolean isBuy() {
        return isBuy;
    }

    public void setRank(int rank) {
        this.rank = rank;
        this.isBuy=true;
    }

    public int getRank() {
        return rank;
    }

    private List<HotSearch> hotSearchList=new ArrayList<>();

    public boolean isSupper(String desc) {
        return isSupper;
    }

    public HotSearch() {
    }

    public String getDescription() {
        return description;
    }

    public int getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(int hotNumber) {
        this.hotNumber = hotNumber;
    }

    public HotSearch(boolean isBuy, String description, int hotNumber, boolean isSupper) {
        this.isBuy = isBuy;
        this.description = description;
        this.hotNumber = hotNumber;
        this.isSupper = isSupper;
    }



    //添加热搜
    public void addHot()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要添加的热搜事件：");
        String des = sc.nextLine();
        if((hotSearchList.stream().filter(e->e.getDescription().equals(des)).findFirst().isPresent())){
            System.out.println("已经有同名的啦！");
        }else{
            HotSearch newhot = new HotSearch(false,des,0,false);
            System.out.println("添加成功！");
            hotSearchList.add(newhot);
        }
        ranking();
    }

    //添加超级热搜
    public void addSupperHot()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要添加的超级热搜事件：");
        String des = sc.nextLine();
        if((hotSearchList.stream().filter(e->e.getDescription().equals(des)).findFirst().isPresent())){
            System.out.println("已经有同名的啦！");
        }else{
            HotSearch newhot = new HotSearch(false,des,0,true);
            System.out.println("添加成功！");
            hotSearchList.add(newhot);
        }
        ranking();
    }

    //显示热搜
    public void checkHot() {
        if(hotSearchList.size()==0){
            System.out.println("抱歉，热搜榜为空~");
        }else {
            int num = 1;
            for(HotSearch h :hotSearchList){
                System.out.println(num+" "+h.getDescription()+" "+h.getHotNumber());
                num++;
            }
        }
    }

    //投票
    public void vote(User us,int voteNum,String desc) {
        if(!isSu(desc)){
            hotSearchList.stream().filter(e -> e.getDescription().equals(desc)&&!e.isSupper).findFirst().get().setHotNumber(voteNum);
        }else
            hotSearchList.stream().filter(e -> e.getDescription().equals(desc)&&e.isSupper).findFirst().get().setHotNumber(voteNum*2);
        us.vote(voteNum);
        System.out.println("投票成功！还有"+us.getVoteNum()+"票");
        ranking();
    }

    //判断是否为super热搜
    public boolean isSu(String des)
    {
        if(hotSearchList.stream().filter(e->e.getDescription().equals(des)).findFirst().get().isSupper)
            return true;
        else
            return false;
    }

    //排名
    public void ranking(){
        hotSearchList.sort(Comparator.comparingInt(HotSearch::getHotNumber).reversed());
        List<HotSearch> temp = new ArrayList<>();
        IntStream.range(0,hotSearchList.size()).forEach(i -> temp.add(null));
        List<HotSearch> buylist = hotSearchList.stream().filter(HotSearch::isBuy).collect(Collectors.toList());
        List<HotSearch> notbuylist = hotSearchList.stream().filter(e -> !e.isBuy()).collect(Collectors.toList());
        buylist.forEach(e -> temp.set(e.getRank(),e));
        notbuylist.forEach(e -> temp.set(temp.indexOf(null),e));
        hotSearchList = new ArrayList<>();
        hotSearchList.addAll(temp);

    }



    //购买热搜
    public void buy(String name,int rank,int money){
        //如果已经被买过
        if(buyHot.containsKey(rank-1)){
            if(buyHot.get(rank-1)>money){
                System.out.println("购买失败！钱不够哦，最少都要大于"+buyHot.get(rank-1));
                return;
            }else{
                hotSearchList.remove(rank-1);
            }
        }else{ //没有被买过
            hotSearchList.stream().filter(e->e.getDescription().equals(name)).findFirst().get().setRank(rank-1);
        }
        buyHot.put(rank-1,money);
        ranking();
        System.out.println("购买成功！");
    }



}
