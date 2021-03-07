package edu.mobidev;

import edu.mobidev.Towns.Town;
import edu.mobidev.Towns.TownFromFileFactory;
import edu.mobidev.Towns.Trader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //public static File defaultfile = new File("sources/source.txt");
    public static int startcapital = 50;
    public static void main(String[] args){
        ITradePointFactory townfactory = null;

        Scanner s = new Scanner(System.in);
        File dir =new File("sources/");
        if(!dir.exists()){
            dir.mkdir();
        }
        System.out.println("Availible configs file: ");
        File[] configs = dir.listFiles();
        for (int i = 0; i < configs.length; i++) {
            if(configs[i].isFile()){
                System.out.println(i+" - "+configs[i].getName());
            }
        }
        int temp=-1;
        do {
            System.out.print("Choose number file [0.." + (configs.length - 1) + "]");
            temp = s.nextInt();
        }while (temp < 0 || temp >= configs.length);
        File f = configs[temp];

        try {
            townfactory = new TownFromFileFactory(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        List<ITradePoint> towns = townfactory.getTradePoints();
        ITrader trader = new Trader(startcapital);
        trader.followOptimalPath(towns);
    }
}
