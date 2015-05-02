package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import core.TextAnalyser;
import core.TextComparer;

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
				
				// Enter  website number 1
				
				Document doc = null;
				try {
					doc = Jsoup.connect(url1).get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String text = doc.body().text().toLowerCase();

				// Enter website number 2
				Document doc2 = null;
				try {
					doc2 = Jsoup.connect(url2).get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String text2 = doc2.body().text().toLowerCase();
				
				
				// Use Hashmap 
				
				File file = new File("./stopwords/english.txt");
				//File file2 = new File("C:\\Users\\Alex\\Desktop\\english.txt");
				Set<String> stopwords = null;
				
				
				/*try {
					//stopwords = FileParser.parseStopwords(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				TextAnalyser ta = new TextAnalyser(text, null);
				//System.out.println(TextAnalyser.getWordFrequencies(text, stopwords));
		        System.out.println(ta.getMostFrequentWords(30, true));
		        
		        System.out.println(ta.getMostFrequentChars(30, false));
		        
		        System.out.println(ta.getMostFrequentCharPairs(30, false));
		        
		        TextComparer tc = new TextComparer();
		        Set<String> set = tc.getTextIntercept(text, text2);
		        System.out.println(set);
		        System.out.println(set.size());
				
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
	
	
}
