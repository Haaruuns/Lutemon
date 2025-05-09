package com.example.lutemongame;

import android.widget.ImageView;

import java.io.Serializable;

public class Lutemon implements Serializable{

    protected String name;
    protected String color;
    protected int attack;
    protected int defense;
    protected int experience = 0;
    protected int health;
    protected int maxHealth;
    protected int id;
    private static int idCounter = 0;

    protected int wins;
    protected int losses;
    protected int image;

    public Lutemon(String name, String color, int attack, int defense, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.id = idCounter++;
        this.experience = 0;
        this.wins = 0;
        this.losses = 0;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getExperience() {
        return experience;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    public int attack(Lutemon defender) {
        if (Math.random() < 0.1) { // 10% mahis, et hyökkäys väistetään
            return 0;
        }
        int bonus = (int) (Math.random() * 3);
        int damage = this.attack + this.experience + bonus - defender.defense;
        defender.receiveDamage(damage);
        return damage;

    }
    public void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }
    public boolean isAlive() {
        return this.health > 0;
    }
    public void levelUp() {
        this.wins += 1;
        this.experience += 1;
    }
    public void addLoss() {
        this.losses += 1;
    }
    public void resetHealth() {
        this.health = this.maxHealth;
    }

    public void increaseExperience() {
        this.experience += 1;
    }
}
