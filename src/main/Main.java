package main;

import question.Client;
import question.DnsNode;
import question.DnsTree;

public class Main {
	public static void main(String[] args) {
		DnsTree tree = new DnsTree();
		Client client = new Client("192.168.1.1", tree);
		Client client2 = new Client("11",tree);
		tree.insertRecord("boun.edu.tr", "1.1.1.1");
		tree.insertRecord("rps.boun.edu.tr", "1.1.1.2");
		tree.insertRecord("twitter.com", "1.1.2.1");
		tree.insertRecord("google.com", "1.1.3.1");
		tree.insertRecord("mail.google.com", "1.1.3.2");
		tree.insertRecord("google.com", "1.1.3.3");
		tree.insertRecord("hello.google.com", "1.1.3.4");
		tree.insertRecord("a.google.com", "1.1.3.5");
		tree.insertRecord("b.google.com", "1.1.3.6");
		tree.insertRecord("c.google.com", "1.1.3.7");
		tree.insertRecord("d.google.com", "1.1.3.8");
		tree.insertRecord("e.google.com", "1.1.3.9");
		tree.insertRecord("f.google.com", "1.1.4.1");
		
		System.out.println("1"+client.sendRequest("a.google.com"));
		
		System.out.println("2"+client.sendRequest("b.google.com"));
		System.out.println("3"+client.sendRequest("b.google.com"));
		
		System.out.println("4"+client.sendRequest("c.google.com"));
		System.out.println("5"+client.sendRequest("c.google.com"));
		
		System.out.println("6"+client.sendRequest("d.google.com"));
		System.out.println("7"+client.sendRequest("d.google.com"));
		
		System.out.println("8"+client.sendRequest("e.google.com"));
		System.out.println("9"+client.sendRequest("e.google.com"));
		
		System.out.println("10"+client.sendRequest("f.google.com"));	
		System.out.println("11"+client.sendRequest("f.google.com"));
		
		System.out.println("12"+client.sendRequest("mail.google.com"));
		System.out.println("13"+client.sendRequest("mail.google.com"));
		
		System.out.println("14"+client.sendRequest("google.com"));
		System.out.println("15"+client.sendRequest("google.com"));
		
		System.out.println("16"+client.sendRequest("boun.edu.tr"));
		System.out.println("17"+client.sendRequest("boun.edu.tr"));
		
		System.out.println("18"+client.sendRequest("rps.boun.edu.tr"));
		System.out.println("19"+client.sendRequest("rps.boun.edu.tr"));
		
		System.out.println("20"+client.sendRequest("twitter.com"));
		
		
//		for (int i = 0; i < client.cacheList.length; i++) {
//			System.out.println("cached contend : "+i+" " +client.cacheList[i].domainName);
//		}
		
		System.out.println("1"+client.sendRequest("a.google.com"));
		
//		for (int i = 0; i < client.cacheList.length; i++) {
//			System.out.println("cached contend : "+i+" " +client.cacheList[i].domainName);
//		}
		
		System.out.println(client2.sendRequest("google.com"));
        
        System.out.println(tree.getAllRecords());
        
        System.out.println("query domain"+tree.queryDomain(".google.com"));
        System.out.println("query domain"+tree.queryDomain(".google.com"));
      
        tree.removeRecord("twitter.com");
        
        tree.removeRecord("google.com", "1.1.3.3");
        
        tree.removeRecord("google.com", "1.1.3.1");
        
        tree.removeRecord("rps.boun.edu.tr", "1.1.1.2");
        
        System.out.println(tree.getAllRecords());
	}
}
