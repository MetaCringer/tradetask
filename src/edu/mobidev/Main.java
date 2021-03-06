package edu.mobidev;

import edu.mobidev.Towns.TownFromFileFactory;
import edu.mobidev.Towns.Trader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static int startcapital = 50;
    public static void main(String[] args){
        ITradePointFactory townfactory = null;

        Scanner s = new Scanner(System.in);
        File dir =new File("configs/");
        if(!dir.exists()){
            dir.mkdir();
        }


        int temp=-1;
        do {
            System.out.print("Write start capital of money: ");
            try{
                temp = Integer.parseInt(s.nextLine());
            }catch (Exception e){

            }
        }while (temp < 0);


        System.out.println("Availible configurations of towns files: ");
        File[] configs = dir.listFiles();
        for (int i = 0; i < configs.length; i++) {
            if(configs[i].isFile()){
                System.out.println(i+" - "+configs[i].getName());
            }
        }
        temp=-1;
        do {
            System.out.print("Choose number file [0.." + (configs.length - 1) + "]");
            try{
                temp = Integer.parseInt(s.nextLine());
            }catch (Exception e){

            }
        }while (temp < 0 || temp >= configs.length);
        File f = configs[temp];

        try {
            townfactory = new TownFromFileFactory(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Config contains: ");
        List<ITradePoint> towns = townfactory.getTradePoints();
        for (ITradePoint town:
             towns) {
            System.out.println("Town: "+town.getName());
            System.out.println("Price: ");
            for (Map.Entry<IGoodie,Integer> price:
                 town.getPrices().entrySet()) {
                System.out.println(price.getKey().getName()+" : "+price.getValue());
            }
        }

        ITrader trader = new Trader(startcapital);
        System.out.println("Trader begin its journey...");
        trader.followOptimalPath(towns);
    }
}
