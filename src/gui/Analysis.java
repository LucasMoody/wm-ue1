package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Text;

public class Analysis extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;



	/**
	 * Create the frame.
	 */
	public Analysis() {
		setTitle("Analyzing a text");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseTwoText = new JLabel("Choose two texts to analyze.");
		lblChooseTwoText.setForeground(new Color(0, 0, 0));
		lblChooseTwoText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblChooseTwoText.setBounds(10, 22, 347, 14);
		contentPane.add(lblChooseTwoText);
		
		textField = new JTextField();
		textField.setBounds(77, 47, 347, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(77, 78, 347, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Use english stop words");
		chckbxNewCheckBox.setFont(new Font("Segoe UI", chckbxNewCheckBox.getFont().getStyle(), 13));
		chckbxNewCheckBox.setBounds(10, 110, 180, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnStart = new JButton("start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url1 = textField.getText();
				String url2 = textField_1.getText();
				
				System.out.print("Website 1:");
				System.out.println(url1);
				
				
				// Zum Testen
				//url1 = "http://www.tim.org/";
				
				System.out.println("The following text has been found:");
				
				// Enter the website
				
				Document doc = null;
				try {
					doc = Jsoup.connect(url1).get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				String text = doc.body().text();
				//String text="Welcome to the TinyTIM WWW Page! Commonly known as our HTML Resource, or, simply, Owen's Brother, Phil This site is provided as a service to help people who have had their interaction with the Internet reduced to a series of Icon Clicking and moving a little bar around. It's time to expand your horizons with TinyTIM! TinyTIM is the world's oldest running MUSH (Multi-User Shared Hallucination), over 18 years old. It's a lot of fun and beats getting run over by a car. To get more pages and stuff, simply choose one of the following three areas and follow the link that best describes what's going through your mind at the moment. Nothing could be easier! Never used TinyTIM I've never used TinyTIM before. I was sure this was going to be a WWW page about the singer! Where's Tiptoe through the Tulips in .au form? Oh, well. What the hell is this TinyTIM thing? What are MUSHes? Where am I? Used TinyTIM, want to expand experience Of course I use TinyTIM! Everybody uses TinyTIM! Some people are even using TinyTIM right now! TinyTIM is one of the basic four food groups. What I really want is to expand my addiction to TinyTIM by getting pictures, sounds, and blurbs about my all-time favorite Internet Experience and its well-read wizards... and then I'll get the t-shirt. Clicks on random icons in WWW pages Uh, I'm looking for weather maps of Africa. Play TinyTIM! (telnet yay.tim.org 5440) And remember, Accept NO Imitations! his mess is maintained by Empedocles the Ash Ock who learned HTML in several passionate and info-filled minutes and lives in a condo. At the same time. Most of the main text, including this biography of Empedocles that Emp would never think to write, is by Sketch the Art Cow, who has a lava lamp and spends too much time with your sister.";

				
				/*
				// Text Kontrolle für Tom Sawyer
				if(text.contains("Some minutes later the")){
					System.out.println(true);
				}
				if(text.contains("After breakfast they went whooping and prancing out on the bar, and chased each other round and round")){
					System.out.println(true);
					
				}
				if(text.contains("and sail thro' blood-y seas")){
					System.out.println(true);
				}*/
				
				// Ausgabe des Textes
				System.out.println(text);
				
				
				// Use Hashmap 
				
		        
				TreeMap<String, Integer> map = new TreeMap<String,Integer>(String.CASE_INSENSITIVE_ORDER);
				
				// Kick punctuations
				text = text.replaceAll("[!?,.')(]", "");
				text = text.replaceAll("\"", "");
				
				// Extract words
				String[] words = text.split("\\s+");
				
				File file = new File("C:\\Users\\Alex\\workspace\\WebMining\\stopwords\\english.txt");
				try {
					System.out.println(parseStopwords(file));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				int count;
				for (int i = 0; i < words.length; i++) {
					if(!map.containsKey(words[i])){
						map.put(words[i], 1);
					}else{
						count = map.get(words[i]);
						count++;
						map.put(words[i], count);
					}
				}
				
				System.out.println(map);
				
				
			
				
				
				
			}
		});
		
	  
		
		
		btnStart.setBounds(232, 228, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnNewButton = new JButton("close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnNewButton.setBounds(335, 228, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblUrl = new JLabel("URL 1:");
		lblUrl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblUrl.setBounds(10, 47, 46, 14);
		contentPane.add(lblUrl);
		
		JLabel lblUrl_1 = new JLabel("URL 2:");
		lblUrl_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblUrl_1.setBounds(10, 81, 46, 14);
		contentPane.add(lblUrl_1);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Analyze letters");
		chckbxNewCheckBox_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		chckbxNewCheckBox_1.setBounds(10, 141, 142, 23);
		contentPane.add(chckbxNewCheckBox_1);
	}
	
	LinkedList<String> stopwords;
	 LinkedList<String> parseStopwords(File file) throws FileNotFoundException, IOException{
		   try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       stopwords.add(line);
			    }
			}
		   return stopwords;
		   
	   }
	
}
