package com.wite.main;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class window {

	JFrame _applicationWindow;
	JTextArea _applicationTextField;
	private String _applicationName = "WITE";
	private float _applicationVersion = 0.1f;
	
	window()
	{
		// Create frame
		_applicationWindow = new JFrame(_applicationName + " " + _applicationVersion);
		// Text component
		_applicationTextField = new JTextArea();
	}
}
