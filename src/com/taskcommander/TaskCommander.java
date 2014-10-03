package com.taskcommander;


/**
 * In general the application �Task Commander� is a uncomplicated, command-line based �todo� list app for PC. 
 * In doing so, it represents a scaled-down version of a �Siri for keyboards� which manages command related 
 * �todo� tasks. Although these tasks are not truly formulated in natural language, the command format is still 
 * flexible yet comfortable to use.

Examples of commands:
...
..
.

 * @author Group F11-1J
 */

public class TaskCommander {
	
	/**
	 * Components
	 * (Please note: All components except for the framework class "TaskCommander" are  instantiated. 
	 * In other words, instances instead of classes are used.)
	 */
	// Controller
	public static Controller controller;
	// Parser
	public static Parser parser;
	// Data (temporary memory containing a list of task objects)
	public static Data tasks;
	// Storage (permanent memory consisting of a local .txt-file)
	public static Storage file;
	// User Interface
	public static UI ui;

	/**
	 * Launch the application
	 * @param  args
	 */
	public static void main(String[] args) {
		
		// Creation of the components
		file = new Storage();
		tasks = new Data();
		controller = new Controller();
		parser = new Parser();
		ui = new UI();
		
		// Start of the user interface
		UI.open();
	}
}
