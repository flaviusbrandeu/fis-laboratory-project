package org.fis.student.football.team.application.add.football.players.models;

import java.util.*;

public class FootballPlayer {
    private String name;
    private String post;
    private String agent;
    private long marketPrice;
    private long salary;
    private Hashtable<String,Float> skills;
    private ArrayList<String> lastThreeTeams;

    public FootballPlayer(String name, String post, String agent, long marketPrice, long salary, Hashtable<String, Float> skills) {
        this.name = name;
        this.post = post;
        this.agent = agent;
        this.marketPrice = marketPrice;
        this.salary = salary;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Hashtable<String, Float> getSkills() {
        return skills;
    }

    public void setSkills(Hashtable<String,Float> skills) {
        this.skills = skills;
    }

    public ArrayList<String> getLastThreeTeams() {
        return lastThreeTeams;
    }

    public void setLastThreeTeams(ArrayList<String> lastThreeTeams) {
        this.lastThreeTeams = lastThreeTeams;
    }

    @Override
    public String toString() {
        return "FootballPlayer{" +
                "name='" + name + '\'' +
                ", post='" + post + '\'' +
                ", agent='" + agent + '\'' +
                ", marketPrice=" + marketPrice +
                ", salary=" + salary +
                ", skills=" + skills +
                ", lastThreeTeams=" + lastThreeTeams +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballPlayer that = (FootballPlayer) o;
        return Float.compare(that.marketPrice, marketPrice) == 0 &&
                Float.compare(that.salary, salary) == 0 &&
                name.equals(that.name) &&
                post.equals(that.post) &&
                Objects.equals(agent, that.agent) &&
                Objects.equals(skills, that.skills) &&
                Objects.equals(lastThreeTeams, that.lastThreeTeams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, post, agent, marketPrice, salary, skills, lastThreeTeams);
    }
}
