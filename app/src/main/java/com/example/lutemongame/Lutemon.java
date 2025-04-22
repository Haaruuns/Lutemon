package com.example.lutemongame;

import android.widget.ImageView;

public class Lutemon {
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
}
