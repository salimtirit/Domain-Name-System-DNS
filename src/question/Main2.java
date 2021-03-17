package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        int testSize = (int) 10e2;
        long startTime = System.currentTimeMillis();
        DnsTree tree = new DnsTree();
        PrintStream printer  = new PrintStream(new File("output.txt"));
        tree.insertRecord("google.com", "3.3.3.30");
        tree.insertRecord("mail.google.com", "4.4.4.4");
        tree.insertRecord("google.com", "3.3.3.40");
        //tree.insertRecord("google.com", "3.3.3.50");
        for (int i = 0; i <testSize;i++){
            tree.insertRecord("site"+i+".com.tr",""+i+".234.24.1");
            tree.insertRecord("site"+i+".com.tr",""+i+".334.24.1");
            tree.insertRecord("site"+i+".com.tr",""+i+".244.24.1");
            tree.insertRecord("site"+i+".com.tr",""+i+".236.24.1");

        }
        tree.insertRecord("mail.google.com", "4.4.4.5");
        tree.insertRecord("twitter.com", "5.5.5.5");
        tree.insertRecord("developer.twitter.com", "6.6.6.6");
        tree.insertRecord("site1.com.tr","1.234.24.1");
        tree.insertRecord("site2.com.tr","2.234.24.1");
        tree.insertRecord("site3.com.tr","3.234.24.1");
        tree.insertRecord("site4.com.tr","4.234.24.1");
        tree.insertRecord("site5.com.tr","5.234.24.1");
        tree.insertRecord("site6.com.tr","6.234.24.1");
        tree.insertRecord("site7.com.tr","7.234.24.1");
        tree.insertRecord("site8.com.tr","8.234.24.1");
        tree.insertRecord("site9.com.tr","9.234.24.1");
        
        tree.insertRecord("boun.edu.tr", "1.1.1.1");
        tree.insertRecord("cmpe.boun.edu.tr", "2.2.2.2");
        tree.insertRecord("metu.edu.tr", "1.1.1.1");
        
        tree.insertRecord("bbc.co.uk", "7.7.7.7");
        tree.insertRecord("cambridge.ac.uk", "8.8.8.8");
        /*
        System.out.println(tree.removeRecord("mail.google.com"));
        //tree.removeRecord("google.com","3.3.3.3");
        System.out.println(tree.removeRecord("google.com","3.3.3.3"));
        System.out.println(tree.removeRecord("google.com","3.3.3.4"));
        System.out.println(tree.removeRecord("google.com","3.3.3.5"));
        
        */
        //tree.removeRecord("bbc.co.uk");
        /*
        tree.removeRecord("bbc.com.uk");
        tree.removeRecord("boun.edu.tr");
        */
        printer.println(tree.getAllRecords().toString());
        //printer.println(tree.queryDomain("google.com"));
        //printer.println(tree.queryDomain("google.com"));
        //printer.println(tree.queryDomain("google.com"));
        //printer.println(tree.queryDomain("google.com"));
        //printer.println(tree.queryDomain("google.com"));
        Client user1 = new Client("user1", tree);
        printer.println(user1.sendRequest("google.com"));
        Client user2 = new Client("user2", tree);
        printer.println(user2.sendRequest("google.com"));
        Client user3 = new Client("user3", tree);
        printer.println(user3.sendRequest("google.com"));
        Client user4 = new Client("user4", tree);
        //tree.insertRecord("google.com", "3.3.3.15");
        printer.println(user4.sendRequest("google.com"));
        Client user5 = new Client("user5", tree);
        printer.println(user5.sendRequest("google.com"));
        Client user6 = new Client("user6", tree);
        printer.println(user6.sendRequest("google.com"));
        Client user7 = new Client("user7", tree);
        printer.println(user7.sendRequest("google.com"));
        Client user8 = new Client("user8", tree);
        printer.println(user8.sendRequest("google.com"));
        
        for (int j = 0; j <20; j++){
            Client x = new Client("x", tree);
            for (int i = 0; i < testSize; i++) {
                x.sendRequest("site"+i+".com.tr");
                x.sendRequest("cmpe.boun.edu.tr");
                //x.sendRequest("google.com");
                x.sendRequest("metu.edu.tr");
                x.sendRequest("developer.twitter.com");
                x.sendRequest("site1.com.tr");
                x.sendRequest("site2.com.tr");
                x.sendRequest("site3.com.tr");
                x.sendRequest("site4.com.tr");
                x.sendRequest("site8.com.tr");    
            }
            
        }
        
        
        
        
        ///user4.sendRequest("site8.com.tr");
        for (int i = 0; i < 8000; i++) {
            user4.sendRequest("twitter.com");
            //user4.sendRequest("site1.com.tr");
        }
        user4.sendRequest("bbc.co.uk");
       // user4.sendRequest("site19.com.tr");
        
        printer.println(Arrays.toString(user4.cacheList));
        printer.println(tree.getAllRecords().toString());
        //printer.println(tree.queryDomain("google.com"));
        //printer.println(tree.queryDomain("google.com"));
        

        System.out.println((double)((System.currentTimeMillis() - startTime))/1000);


        


    }
}