import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		String[] enemies = { "Skeleton", "Zombie", "Warrior", "Assassin" };
		String[] enemiesBosses = { "Skeleton Lord", "Zombie warchief" };
		int maxEnemyHealth = 75;
		int maxEnemyBossHealth = 175;
		int enemyAttackDamage = 25;
		int enemyBossAttackDamage = 40;
		int countEnemiesToBoss = 4; //how many basic enemies you need to defeat to have a chance to fight boss
		
		int health = 100;
		int maxHealth = health;
		int attackDamage = 50;
		int numHealthPots = 3;
		int healthPotionHealAmount = 30;
		int healthPotionDropChance = 50; // %
		int countEnemyDefeated = 0; //for final score and counting for bossFight boolean
		int countEnemyBossDefeated = 0; // for final score
		int currentExp = 0;
		int expToNextLvl = 75;
		int lvl = 1;
		
		boolean bossFight = false; //for checking if you earned boss fight option
		boolean running = true;
		System.out.println("Welcome to your Adventure!");
		
		
		GAME:
		while(running) {
			
			//boss fight arc
			if(bossFight) {
				System.out.println("-----------------------------");
				
				int enemyBossHealth = rand.nextInt(maxEnemyBossHealth);
				String enemyBoss = enemiesBosses[rand.nextInt(enemiesBosses.length)];
				System.out.println("\t# " + enemyBoss + " appeared #\n");
				
				while(enemyBossHealth>0) {
					System.out.println("\tYour HP: " + health + "/ " + maxHealth);
					System.out.println("\t " + enemyBoss + "'s HP: " + enemyBossHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Drink Potion " + " (" +numHealthPots + ") " + " left");
					System.out.println("\t3. Run away ");
					
					String input = in.nextLine();
					if(input.equals("1")) {
						int damageDealt = rand.nextInt(attackDamage);
						int damageTaken = rand.nextInt(enemyBossAttackDamage);
						
						enemyBossHealth -= damageDealt;
						health -= damageTaken;
						
						System.out.println("\t> You strike the " + enemyBoss + " for " + damageDealt + " damage.");
						System.out.println("\t> You recieve " + damageTaken + " in return.");
						
						if(health<1) {
							System.out.println("\t> Your wounds are too great to handle, you are too weak to go on...");
							break;
						}
					}
					else if(input.equals("2")) {
						if(numHealthPots > 0 && health < maxHealth*0.7) {
							health += healthPotionHealAmount;
							numHealthPots--;
							System.out.println("\t> You drink health potion, healing for " + healthPotionHealAmount + " . "
							+ "\n\t> You now have " + health + "/" + maxHealth + " HP." 
							+ "\n\t> You have " + numHealthPots + " health potions left!\n");
						}
						else if(numHealthPots > 0 && health > maxHealth*0.7){
							health += healthPotionHealAmount/2;
							numHealthPots--;
							System.out.println("You Overheal for half of the potion power!");
							System.out.println("\t> You drink health potion, healing for " + healthPotionHealAmount/2 + " . "
							+ "\n\t> You now have " + health + "/" +maxHealth + " HP." 
							+ "\n\t> You have " + numHealthPots + " health potions left!\n");
						}
						else {
							System.out.println("\t> You have no health potions left! You still have a chance to loot potion from defeated enemy!");
						}
					}
					else if(input.equals("3")) {
						bossFight = false;
						System.out.println("\tYou run away from " + enemyBoss + "!");
						System.out.println("\tYou meet regular enemy ");
						
						continue GAME;
					}
					else {
						System.out.println("\tInvalid command!");
					}
				}

				if(health<1) {
					System.out.println("You must leave dungeon, recieving even one more wound would be lethal!");
					break;
				}
				
				System.out.println("-----------------------------");
				System.out.println(" # " + enemyBoss + " was defeated! # ");
				System.out.println(" # You have " + health + "/" + maxHealth + " HP left. # ");
				countEnemyBossDefeated++;
				currentExp += 200;
				System.out.println("-----------------------------");
				System.out.println("You gained " + 200 + " experience from this fight!");
				System.out.println("Your current exp is " + currentExp + "/" + expToNextLvl);

				if(currentExp >= expToNextLvl) {
					lvl++;
					maxHealth += 15;
					attackDamage += 15;
	
					System.out.println("-----------------------------");
					System.out.println(" # You gained a level! # ");
					System.out.println(" # Your lvl is now " + lvl + " # ");
					System.out.println(" # Your maximum attack value is now " + attackDamage + " # ");
					System.out.println(" # Your maximum health is now " + maxHealth + " # ");

					expToNextLvl += 25;
					currentExp = 0;
				}
				if(numHealthPots < 1) {
					numHealthPots++;
					System.out.println("-----------------------------");
					System.out.println(" # The " + enemyBoss + " dropped a health potion! # ");
					System.out.println(" # You have " + numHealthPots + " health potion(s). # ");
				}
				else if(rand.nextInt(100) < healthPotionDropChance) {
					numHealthPots++;
					System.out.println("-----------------------------");
					System.out.println(" # The " + enemyBoss + " dropped a health potion! # ");
					System.out.println(" # You have " + numHealthPots + " health potion(s). # ");
				}
				
				countEnemiesToBoss = 4;
				bossFight = false;
				System.out.println("Your current lvl is " + lvl);
				System.out.println("-----------------------------");

				System.out.println("What would you like to do now?");
				System.out.println("1. Continue fighting");
				System.out.println("2. Exit dungeon");
			}
			
			//standard enemy fight arc
			else {
				System.out.println("-----------------------------");
				
				int enemyHealth = rand.nextInt(maxEnemyHealth);
				String enemy = enemies[rand.nextInt(enemies.length)];
				System.out.println("\t# " + enemy + " appeared #\n");
				
				while(enemyHealth>0) {
					System.out.println("\tYour HP: " + health + "/" + maxHealth );
					System.out.println("\t " + enemy + "'s HP: " + enemyHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Drink Potion " + " (" +numHealthPots + ") " + " left");
					System.out.println("\t3. Run away");
					
					String input = in.nextLine();
					if(input.equals("1")) {
						int damageDealt = rand.nextInt(attackDamage);
						int damageTaken = rand.nextInt(enemyAttackDamage);
						
						enemyHealth -= damageDealt;
						health -= damageTaken;
						
						System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
						System.out.println("\t> You recieve " + damageTaken + " in return.");
						
						if(health<1) {
							System.out.println("\t> Your wounds are too great to handle, you are too weak to go on...");
							break;
						}
					}
					else if(input.equals("2")) {
						if(numHealthPots > 0 && health < maxHealth*0.7) {
							health += healthPotionHealAmount;
							numHealthPots--;
							System.out.println("\t> You drink health potion, healing for " + healthPotionHealAmount + " . "
							+ "\n\t> You now have " + health + "/" + maxHealth + " HP." 
							+ "\n\t> You have " + numHealthPots + " health potions left!\n");
						}
						else if(numHealthPots > 0 && health > maxHealth*0.7){
							health += healthPotionHealAmount/2;
							numHealthPots--;
							System.out.println("You Overheal for half of the potion power!");
							System.out.println("\t> You drink health potion, healing for " + healthPotionHealAmount/2 + " . "
							+ "\n\t> You now have " + health + "/" + maxHealth + " HP." 
							+ "\n\t> You have " + numHealthPots + " health potions left!\n");
						}
						else {
							System.out.println("\t> You have no health potions left! You still have a chance to loot potion from defeated enemy!");
						}
					}
					else if(input.equals("3")) {
						System.out.println("\tYou run away from " + enemy + "!");
						continue GAME;
					}
					else {
						System.out.println("\tInvalid command!");
					}
				}
				if(health<1) {
					System.out.println("You must leave dungeon, recieving even one more wound would be lethal!");
					break;
				}
				
				System.out.println("-----------------------------");
				System.out.println(" # " + enemy + " was defeated! # ");
				System.out.println(" # You have " + health + "/" + maxHealth + " HP left. # ");
				countEnemiesToBoss--;
				countEnemyDefeated++;
				currentExp += 50;
				System.out.println("-----------------------------");
				System.out.println("You gained " + 50 + " experience from this fight!");
				System.out.println("Your current exp is " + currentExp + "/" + expToNextLvl);
				if(currentExp >= expToNextLvl) {
					lvl++;
					maxHealth += 15;
					attackDamage += 15;
	
					System.out.println("-----------------------------");
					System.out.println(" # You gained a level! # ");
					System.out.println(" # Your lvl is now " + lvl + " # ");
					System.out.println(" # Your maximum attack value is now " + attackDamage + " # ");
					System.out.println(" # Your maximum health is now " + maxHealth + " # ");
					expToNextLvl += 25;
					currentExp = 0;
				}
				else {
					System.out.println("You still need " + (expToNextLvl-currentExp) +" experience to next level" );
				}
				if(numHealthPots < 1) {
					if(rand.nextInt(100) > (healthPotionDropChance/4)) {
					numHealthPots++;
					System.out.println("-----------------------------");
					System.out.println(" # The " + enemy + " dropped a health potion! # ");
					System.out.println(" # You have " + numHealthPots + " health potion(s). # ");
					}
				}
				else if(rand.nextInt(100) < healthPotionDropChance) {
					numHealthPots++;
					System.out.println("-----------------------------");
					System.out.println(" # The " + enemy + " dropped a health potion! # ");
					System.out.println(" # You have " + numHealthPots + " health potion(s). # ");
				}
					
				System.out.println("-----------------------------");
				if(countEnemiesToBoss == 0) {
				System.out.println("! You have a chance to fight powerful enemy ! ");
				}
				System.out.println("Your current lvl is " + lvl);
				System.out.println("-----------------------------");

				System.out.println("What would you like to do now?");
				System.out.println("1. Continue fighting");
				System.out.println("2. Exit dungeon");
				if(countEnemiesToBoss == 0) {
					System.out.println("3. Go to the Boss room");
					countEnemiesToBoss = 4;
				}
				
			}

			//after fight
			
			String input = in.nextLine();
			
			while(!input.equals("1") && !input.equals("2") && !input.equals("3")) {
				System.out.println("Invalid command");
				input = in.nextLine();
			}
			if(input.equals("1")) {
				System.out.println("You continue your battle");
			}
			else if(input.equals("2")) {
				System.out.println("You exit the dungeon");
				break;
			}
			else if(input.equals("3")) {
				System.out.println("You engage with the Boss!");
				bossFight = true;
			}	
	}

		System.out.println(" ---------------------- ");
		System.out.println(" # Thanks for playing # ");
		System.out.println(" # You defeated " + countEnemyDefeated + " enemies! #" );
		System.out.println(" # You defeated " + countEnemyBossDefeated + " Super Powerful enemies! #" );
		System.out.println(" ---------------------- ");
	}
}

