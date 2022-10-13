package com.wite.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class main_editor extends JFrame implements ActionListener
{

	// Text
	JTextArea t;
	
	// Window
	JFrame f;
	
	// Constructor
	main_editor()
	{
		// Create frame
		f = new JFrame("WITE");
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		catch (Exception e) {
			
		}
		
		// Text component
		t = new JTextArea();
		
		// Primary menu
		JMenuBar mb = new JMenuBar(); 
		JMenu m1 = new JMenu("File");
		
		// Menu items
		JMenuItem mi1 = new JMenuItem("New");
		JMenuItem mi2 = new JMenuItem("Open");
		JMenuItem mi3 = new JMenuItem("Save");
		JMenuItem mi9 = new JMenuItem("Print");
		
		// Add action listener
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi9.addActionListener(this);
		
		// Populate menu
		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi9);
		
		// Secondary menu
		JMenu m2 = new JMenu("Edit");
		JMenuItem mi4 = new JMenuItem("Cut");
		JMenuItem mi5 = new JMenuItem("Copy");
		JMenuItem mi6 = new JMenuItem("Paste");
		
		// Font menu
		JMenu m3 = new JMenu("Fonts");
		
		// Add action listener
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);
		
		
		// Populate menu
		m2.add(mi4);
		m2.add(mi5);
		m2.add(mi6);
		
		
		JMenuItem mc = new JMenuItem("Close");
		
		mc.addActionListener(this);
		
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(mc);
		
		f.setJMenuBar(mb);
		f.add(t);
		f.setSize(500, 500);
		f.show();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		
		if (s.equals("Cut"))
		{
			t.cut();
		} else if (s.equals("Copy"))
		{
			t.copy();
		} else if (s.equals("Paste"))
		{
			t.paste();
		} else if (s.equals("Save"))
		{
			JFileChooser j = new JFileChooser("f:");
			
			int r = j.showSaveDialog(null);
			
			if(r == JFileChooser.APPROVE_OPTION)
			{
				File fi = new File(j.getSelectedFile().getAbsolutePath());
				
				try {
					FileWriter wr = new FileWriter(fi, false);
					
					BufferedWriter w = new BufferedWriter(wr);
					
					w.write(t.getText());
					
					w.flush();
					w.close();
				} 
				catch (Exception evt)
				{
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			else
			{
				JOptionPane.showMessageDialog(f, "User cancelled operation");
			}
		} else if (s.equals("Print"))
		{
			try {
				t.print();
			} catch (Exception evt) {
				JOptionPane.showMessageDialog(f, evt.getMessage());

			}
		} else if (s.equals("Open"))
		{
			JFileChooser j = new JFileChooser("f:");
			
			int r = j.showOpenDialog(null);
			
			if(r == JFileChooser.APPROVE_OPTION) {
				File fi = new File(j.getSelectedFile().getAbsolutePath());
				
				try {
					String s1 = "", sl = "";
					
					FileReader fr = new FileReader(fi);
					
					BufferedReader br = new BufferedReader(fr);
					
					s1 = br.readLine();
					
					while ((s1 = br.readLine()) != null)
					{
						sl = sl + "\n" + s1;
					}
					
					t.setText(sl);
				} catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			else {
				JOptionPane.showMessageDialog(f, "User cancelled operation");
			}
		} else if (s.equals("New")) {
			t.setText("");
		} else if (s.equals("Close")) {
			f.setVisible(false);
		}
	}
	
	// Main
	public static void main(String args[])
	{
		main_editor e = new main_editor();
	}

}
