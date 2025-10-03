package TGame;

import java.util.Random;

public abstract class Characters {
    protected String name;
    protected int health;
    protected int shield;
    

    protected int shieldTurns = 0;
    protected int cooldownSkill2 = 0;
    protected int cooldownSkill3 = 0;
    protected int cooldownSkill4 = 0;


    private Random rand = new Random();

    public Characters(String name ,int health, int shield){
        this.name = name;
        this.health = health;
        this.shield = 0;
    }
    
    public String getName(){return name;}
    public int getHealth(){return health;}   
    public int getShield(){return shield;}

    public void setHealth(int health){
        this.health = health;
    }
    protected void setShield(int amount, int turns) {
        this.shield = amount;
        this.shieldTurns = turns;
    }

    public boolean isAlive(){
        return health > 0;
    }
    
    public void receiveDamage(int damage) {
        if (shield > 0) {
            int blocked = Math.min(shield, damage);
            shield -= blocked;
            damage -= blocked;
        }

        if (damage > 0) {
            health -= damage;
            if (health <= 0) {
                health = 0;
                System.out.println(name + " has died");
            }
        }
    }


    /*public void healAmount(int amount) {
    this.health += amount;
    System.out.println(name + " heals " + amount + " health points!");
}*/


    public void tickShield() {
        if(shieldTurns > 0) {
            shieldTurns--;
            if(shieldTurns == 0) {
                shield = 0;
                System.out.println(name + "'s shield has worn off!");
            }
        }
    }


    public void startTurn() {
        // reduce ultimate cooldown
        if (cooldownSkill2 > 0) {
            cooldownSkill2--;
        }
        if (cooldownSkill3 > 0) {
            cooldownSkill3--;
        }
        if (cooldownSkill4 > 0) {
            cooldownSkill4--;
        }
    }

    public abstract String[] getSkillNames();

//Skill Systems Below:


    public void useSkill(int skillNum, Characters opponent) {
        switch (skillNum) {
            case 1:
                int dmg1 = rand.nextInt(11); // 0-10
                System.out.println(name + " uses Skill 1! Deals " + dmg1);
                opponent.receiveDamage(dmg1);
                break;

            case 2:
                int dmg2 = rand.nextInt(16); // 0-15
                System.out.println(name + " uses Skill 2! Deals " + dmg2);
                opponent.receiveDamage(dmg2);
                cooldownSkill2 = 2; // 2 turn cooldown
                break;

            case 3:
                setShield(10, 3); // Shield lasts 3 turns
                System.out.println(name + " uses Shield! Blocks 10 dmg for 3 turns.");
                break;

            case 4:
                if (cooldownSkill4 == 0) {
                    int dmg4 = 15 + rand.nextInt(26); // 15-25
                    System.out.println(name + " uses Ultimate! Deals " + dmg4);
                    opponent.receiveDamage(dmg4);;
                    cooldownSkill4 = 5; // 5 turn cooldown
                } else {
                    System.out.println(name + "'s Ultimate is on cooldown! (" + cooldownSkill4 + " turns left)");
                }
                break;

            default:
                System.out.println("Invalid skill.");
                break;
        }

    }
}