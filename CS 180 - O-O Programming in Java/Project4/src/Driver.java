//import org.jsoup.Jsoup;

import javax.swing.text.Document;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Spencer on 7/18/2017.
 */
public class Driver {

    private Scanner s = new Scanner(System.in);

    private ArrayList<SuperHero> heroList;
    private HashMap<String, SuperHero> map;

    public Driver() {
        heroList = new ArrayList<>();
        map = new HashMap<>();
    }

    void addHero() {
        //s = new Scanner(System.in);
        System.out.println("Enter World:");
        String world = s.nextLine();
        if (!world.equals("Marvel") && !world.equals("DC Universe") && !world.equals("DC")) {
            System.out.println("That World is not valid!");
            return;
        }

        System.out.println("Enter name:");
        String name = s.nextLine();

        if (map.containsKey(name)) {
            System.out.println("That Hero already exists!");
            return;
        }

        System.out.println("Enter weapon:");
        String weapon = s.nextLine();

        System.out.println("Enter vehicle:");
        String vehicle = s.nextLine();

        Equipment eq = new Equipment(weapon, vehicle);

        ArrayList<String> powers = new ArrayList<>();
        boolean cont = true;
        do {
            System.out.println("Enter powers");
            String power = s.nextLine();
            if (power.isEmpty()) {
                cont = false;
            }
            else {
                powers.add(power);
            }
        } while (cont);

        SuperPowers superPowers = new SuperPowers(powers.toArray(new String[powers.size()]));
        Arsenal arsenal = new Arsenal(eq, superPowers);

        if (world.equals("Marvel")) {
            SuperHero hero = new Marvel(arsenal, name);
            heroList.add(hero);
            map.put(name, hero);
        }
        else if (world.equals("DC Universe") || world.equals("DC")) {
            SuperHero hero = new DC(arsenal, name);
            heroList.add(hero);
            map.put(name, hero);
        }

        System.out.println(name + " added.");
        //s.close();
    }

    void modifyHero(String name) {
        if (name.isEmpty()) return;
        if (!map.containsKey(name)) {
            System.out.println("That Hero does not Exist!");
            return;
        }
        SuperHero hero = map.get(name);
        int index = heroList.indexOf(hero);

        System.out.println("1 - Change weapon");
        System.out.println("2 - Change vehicle");
        System.out.println("3 - Change powers");

        //s = new Scanner(System.in);
        String choice = s.nextLine();
        Arsenal arsenal = hero.getArsenal();
        switch (choice) {
            case "1":
                System.out.println("Enter the new Weapon you want:");
                Equipment equipment = arsenal.getEquipment();
                String newWep = s.nextLine();
                equipment.setWeapon(newWep);
                arsenal.setEquipment(equipment);
                System.out.println(name + "'s Weapon has been changed to " + newWep);
                break;
            case "2":
                System.out.println("Enter the new Vehicle you want:");
                Equipment equipment1 = arsenal.getEquipment();
                String newVehicle = s.nextLine();
                equipment1.setVehicle(newVehicle);
                arsenal.setEquipment(equipment1);
                System.out.println(name + "'s Vehicle has been changed to " + newVehicle);
                break;
            case "3":
                ArrayList<String> list = new ArrayList<>();
                boolean cont = true;
                do {
                    System.out.println("Enter in a new power:");
                    String power = s.nextLine();
                    if (power.isEmpty()) {
                        cont = false;
                    }
                    else {
                        list.add(power);
                    }
                }
                while (cont);
                SuperPowers superPowers = arsenal.getSuperPowers();
                superPowers.setPowers(list.toArray(new String[list.size()]));
                arsenal.setSuperPowers(superPowers);
        }
        hero.setArsenal(arsenal);
        map.replace(name, hero);
        heroList.set(index, hero);
    }

    void removeHero(String name) {
        if (name.isEmpty()) return;
        if (!map.containsKey(name)) {
            System.out.println("That Hero does not Exist!");
            return;
        }
        SuperHero hero = map.get(name);

        heroList.remove(hero);
        map.remove(name);

        System.out.println(name + " has been removed!");
    }

    void printDetail(String name) {
        if (name.isEmpty());
        if (heroList.isEmpty()) return;
        else if (name.isEmpty()) {
            System.out.println("Input is null");
            return;
        }
        else if (!map.containsKey(name)) {
            System.out.println("That hero does not exist!");
            return;
        }
        SuperHero hero = map.get(name);
        if (hero instanceof Marvel) {
            System.out.println("World: Marvel");
        }
        else if (hero instanceof DC){
            System.out.println("World: DC Universe");
        }
        System.out.println("Name: " + name);
        System.out.println("===== Equipment =====");
        Arsenal arsenal = hero.getArsenal();
        if (arsenal != null) {
            Equipment equipment = arsenal.getEquipment();
            SuperPowers superPowers = arsenal.getSuperPowers();
            System.out.println("Weapon: " + equipment.getWeapon());
            System.out.println("Vehicle: " + equipment.getVehicle());
            System.out.println("===== Powers =====");
            int i = 1;
            for (String power : superPowers.getPowers()) {
                System.out.println(i + ": " + power);
                i++;
            }
        }
        else {
            System.out.println("The Arsenal is null");
        }
    }

    void printHeroes() {
        System.out.println("====== World: Marvel =======");
        int a = 1;
        for (int i = 0; i < heroList.size(); i++) {
            SuperHero hero = heroList.get(i);
            if (hero instanceof Marvel) {
                System.out.println(a + ": " + hero.getName());
                a++;
            }
        }
        a = 1;
        System.out.println("==== World: DC Universe ====");
        for (int i = 0; i < heroList.size(); i++) {
            SuperHero hero = heroList.get(i);
            if (hero instanceof DC) {
                System.out.println(a + ": " + hero.getName());
                a++;
            }
        }
    }

    ArrayList<SuperHero> getHeroList() {
        return this.heroList;
    }

    void run() {
        boolean cont = true;
        //Scanner s = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (cont) {
            System.out.println("Main menu:");
            System.out.println("1. Add hero");
            System.out.println("2. Modify hero");
            System.out.println("3. Remove hero");
            System.out.println("4. Print hero details");
            System.out.println("5. Print all heroes");
            System.out.println("6. Read from file");
            System.out.println("7. Write to file");
            System.out.println("8. Exit");

            String choiceString = s.nextLine();

            int choice;
            HashMap<String, String> errorCheckInt = new HashMap<>();
            for (int i = 0; i < 9; i++) {
                errorCheckInt.put(String.valueOf(i), "yes");
            }
            if (errorCheckInt.containsKey(choiceString)) {
                choice = Integer.parseInt(choiceString);
            }
            else {
                System.out.println("Please choose a valid option!");
                choice = -1;
            }

            String name;
            String filename;
            //s = new Scanner(System.in);

            switch (choice) {
                case -1:
                    break;
                case 0:
                    System.out.println("Enter your world of interest:");
                    String world = s.nextLine();
                    System.out.println("Enter the Character to be filtered:");
                    String input = s.nextLine();
                    try {
                        miner(world, input);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    addHero();
                    break;
                case 2:
                    System.out.println("Enter hero name");
                    name = s.nextLine();
                    modifyHero(name);
                    break;
                case 3:
                    System.out.println("Enter hero name");
                    name = s.nextLine();
                    removeHero(name);
                    break;
                case 4:
                    System.out.println("Enter hero name");
                    name = s.nextLine();
                    printDetail(name);
                    break;
                case 5:
                    printHeroes();
                    break;
                case 6:
                    System.out.println("Enter the name of the file");
                    filename = s.nextLine();
                    addFromFile(filename);
                    break;
                case 7:
                    System.out.println("Enter the name of the file");
                    filename = s.nextLine();
                    writeToFile(filename);
                    break;
                case 8:
                    //cont = false;
                    return;
            }
            //s = new Scanner(System.in);
            System.out.println("Return to menu?(y/n)");
            String answer = s.nextLine();
            if (answer.equals("n")) cont = false;
            //}
        }
    }

    void addFromFile(String filename) {
        if (filename.isEmpty()) return;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            String heroLine = reader.readLine();
            while (heroLine != null) {
                if (heroLine.isEmpty()) {
                    heroLine = reader.readLine();
                    continue;
                }
                //String heroLine = s.nextLine();
                String[] heroInfo = heroLine.split("; ");
                int start = 0;
                int end = heroLine.indexOf(":");
                String world = heroLine.substring(start, end);

                start = end + 2;
                end = heroLine.indexOf(";");
                String name = heroLine.substring(start, end);

                String weapon = heroInfo[1];
                String vehicle = heroInfo[2];

                Equipment equipment = new Equipment(weapon, vehicle);
                SuperPowers superPowers;
                if (heroInfo[3].contains("none;")) {
                    String[] powers = new String[1];
                    powers[0] = "none";
                    superPowers = new SuperPowers(powers);
                }
                else {
                    String[] powers = heroInfo[3].split(", ");
                    int numPowers = powers.length;
                    String lastPower = powers[numPowers - 1];
                    if (lastPower.contains(";")) {
                        lastPower = lastPower.substring(0, lastPower.length() - 1);
                        powers[numPowers - 1] = lastPower;
                    }
                    superPowers = new SuperPowers(powers);
                }
                Arsenal arsenal = new Arsenal(equipment, superPowers);

                if (world.equals("Marvel")) {
                    SuperHero superHero = new Marvel(arsenal, name);
                    heroList.add(superHero);
                    map.put(name, superHero);
                    System.out.println("Marvel Hero (" + name + ") Added");
                }
                else if (world.equals("DC Universe") || world.equals("DC")){
                    SuperHero superHero = new DC(arsenal, name);
                    heroList.add(superHero);
                    map.put(name, superHero);
                    System.out.println("DC Hero (" + name + ") Added");
                }
                heroLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String createEntry(SuperHero hero) {
        String heroLine = "";
        if (hero instanceof Marvel) heroLine += "Marvel: ";
        else if (hero instanceof DC) heroLine += "DC Universe: ";

        String name = hero.getName();
        heroLine += name + "; ";

        Arsenal arsenal = hero.getArsenal();
        Equipment equipment = arsenal.getEquipment();

        String weapon = equipment.getWeapon();
        heroLine += weapon + "; ";

        String vehicle = equipment.getVehicle();
        heroLine += vehicle + "; ";

        SuperPowers superPowers = arsenal.getSuperPowers();
        String[] powers = superPowers.getPowers();
        for (int i = 0; i < powers.length; i++) {
            if (i != powers.length-1) {
                heroLine += powers[i] + ", ";
            }
            else {
                heroLine += powers[i] + ";";
            }
        }
        return heroLine;
    }

    void writeToFile(String filename) {
        if (filename.isEmpty()) return;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
            for (SuperHero hero : heroList) {
                String heroLine = createEntry(hero);
                writer.write(heroLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void miner(String world, String input) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(world)));
        boolean cont = true;
        String firstHero = "";
        do {
            String line = reader.readLine();
            if (line.startsWith(input)) {
                firstHero = line;
                cont = false;
            }

        } while (cont);
        String name = firstHero;
        for (int i = 0; i < 200; i++) {
            SuperHero hero = new SuperHero(null, name);
            heroList.add(hero);
            map.put(name, hero);
            name = reader.readLine();
        }

        //Document doc = Jsoup.connect("");
    }

    public static void main(String[] args) {
        Driver d = new Driver();
        d.run();
    }

}
