package com.twu;

public class User {
    private String name;
    private int voteNum=10;

    public User(String name, int voteNum) {
        this.name = name;
        this.voteNum = voteNum;
    }

    public User(String name) {
        this.name = name;
    }

    public int getVoteNum() {
        return voteNum;
    }

    public String getName() {
        return name;
    }

    public void  vote(int vote_num){
        this.voteNum-=vote_num;
    }
}
