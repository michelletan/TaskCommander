package com.taskcommander;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class UI {

	private static final int SHELL_MIN_HEIGHT = 500;
	private static final int SHELL_MIN_WIDTH = 200;

	private static final int GRID_COLUMNS_SPAN = 2;
	private static final boolean GRID_COLUMNS_EQUAL_SIZE = false;

	private static final boolean INPUT_FIT_HORIZONTAL = true;
	private static final boolean INPUT_FIT_VERTICAL = false;
	private static final int INPUT_COLUMNS_SPAN = 1;
	private static final int INPUT_ROWS_SPAN = 1;
	private static final int INPUT_PREFERRED_WIDTH = 500;

	private static final boolean OUTPUT_FIT_HORIZONTAL = true;
	private static final boolean OUTPUT_FIT_VERTICAL = false;
	private static final int OUTPUT_COLUMNS_SPAN = 2;
	private static final int OUTPUT_ROWS_SPAN = 1;
	private static final int OUTPUT_PREFERRED_WIDTH = 500;
	private static final int OUTPUT_PREFERRED_HEIGHT = 50;

	private static final int TABLE_STYLE = SWT.NONE;
	private static final boolean TABLE_FIT_HORIZONTAL = true;
	private static final boolean TABLE_FIT_VERTICAL = true;
	private static final int TABLE_COLUMNS_SPAN = 2;
	private static final int TABLE_ROWS_SPAN = 1;
	private static final int TABLE_PREFERRED_WIDTH = 500;
	private static final int TABLE_PREFERRED_HEIGHT = 100;
	private static final int TABLE_COLUMNS_NUM = 4;
	private static final String[] TABLE_COLUMNS_NAMES = {"No.", "Date", "Task", "Status"};

	private final Display display = Display.getDefault();
	private final Shell shell = new Shell(display);
	private final Table table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);

	private final Color red = display.getSystemColor(SWT.COLOR_RED);
	private final Color gray = display.getSystemColor(SWT.COLOR_GRAY);
	private final Color blue = display.getSystemColor(SWT.COLOR_BLUE);
	private final Color black = display.getSystemColor(SWT.COLOR_BLACK);
	private final Color darkRed = display.getSystemColor(SWT.COLOR_DARK_RED);
	private final Color darkGray = display.getSystemColor(SWT.COLOR_DARK_GRAY);
	private final Color darkBlue = display.getSystemColor(SWT.COLOR_DARK_BLUE);
	private final Color darkCyan = display.getSystemColor(SWT.COLOR_DARK_CYAN);
	private final Color darkMagenta = display.getSystemColor(SWT.COLOR_DARK_MAGENTA);
	
	private final Color COLOR_COL_FIRST = darkGray;
	private final Color COLOR_COL_SECOND = blue;
	private final Color COLOR_COL_THIRD = black;
	private final Color COLOR_DATE_ROW = darkCyan;
	private final Color COLOR_DONE = darkGray;
	private final Color COLOR_NOT_DONE = red;

	private Text input;
	private Text output;

	public UI() {

	}

	//@author A0105753J
	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		setupShell();

		createTextFields();
		setupUIElements();

		displayTasksUponOpening();
		addListenerForInput();

		runUntilWindowClosed();
	}

	private void setupShell() {
		shell.setLayout(new GridLayout(GRID_COLUMNS_SPAN, GRID_COLUMNS_EQUAL_SIZE));
		shell.setText(Global.APPLICATION_NAME);
		shell.setMinimumSize(SHELL_MIN_WIDTH, SHELL_MIN_HEIGHT);
	}

	private void createTextFields() {
		output = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		new Label(shell, SWT.NONE).setText("Enter command: ");
		input = new Text(shell, SWT.BORDER);
	}

	private void setupUIElements() {
		setupInput();
		setupOutput();
		setupTable();
	}

	private void setupInput() {
		GridData inputGridData = new GridData(SWT.FILL, SWT.CENTER, INPUT_FIT_HORIZONTAL, INPUT_FIT_VERTICAL, 
				INPUT_COLUMNS_SPAN, INPUT_ROWS_SPAN);
		inputGridData.widthHint = INPUT_PREFERRED_WIDTH;
		input.setLayoutData(inputGridData);
	}

	private void setupOutput() {
		GridData outputGridData = new GridData(SWT.FILL, SWT.CENTER, OUTPUT_FIT_HORIZONTAL, OUTPUT_FIT_VERTICAL, 
				OUTPUT_COLUMNS_SPAN, OUTPUT_ROWS_SPAN);
		outputGridData.widthHint = OUTPUT_PREFERRED_WIDTH;
		outputGridData.heightHint = OUTPUT_PREFERRED_HEIGHT;
		output.setLayoutData(outputGridData);
		output.setText(Global.MESSAGE_WELCOME);
		output.setEditable(false);
	}

	private void setupTable() {
		GridData tableGridData = new GridData(SWT.FILL, SWT.FILL, TABLE_FIT_HORIZONTAL, TABLE_FIT_VERTICAL, 
				TABLE_COLUMNS_SPAN, TABLE_ROWS_SPAN);
		tableGridData.widthHint = TABLE_PREFERRED_WIDTH;
		tableGridData.heightHint = TABLE_PREFERRED_HEIGHT;
		table.setLayoutData(tableGridData);
		table.setHeaderVisible(true);
		for (int i = 0; i < TABLE_COLUMNS_NUM; i++) {
			TableColumn column = new TableColumn(table, TABLE_STYLE);
			column.setText(TABLE_COLUMNS_NAMES[i]);
		}
	}

	private void addListenerForInput() {
		input.addListener(SWT.Traverse, new Listener(){
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				if(event.detail == SWT.TRAVERSE_RETURN)
					try {
						clearTableItems();
						String command = input.getText();
						displayFeedback(TaskCommander.controller.executeCommand(command));
						clearInput();
					}catch (Exception e1) {
						displayErrorMessage(e1.getMessage());
					}
			}
		});
	}

	/**
	 * Opens the shell and runs until the shell is closed.
	 * Disposes of the display.
	 */
	private void runUntilWindowClosed() {
		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	//@author A0112828H 
	private void displayTasksUponOpening() {
		displayTasks(TaskCommander.controller.getDisplayedTasks());
	}

	private void displayFeedback(String fb) {
		displayMessage(fb);
		displayTasks(TaskCommander.controller.getDisplayedTasks());
	}

	public void displayTasks(ArrayList<Task> tasks) {
		int index = 1;
		String lastDate = null;
		for (Task task : tasks) { 
			switch(task.getType()) {
			case FLOATING:
				FloatingTask ft = (FloatingTask) task;
				createRowFromTask(index, ft);
				break;
			case DEADLINE:
				DeadlineTask dt = (DeadlineTask) task;
				if (isNewDay(dt, lastDate)) {
					lastDate = getDisplayDate(dt.getDate());
					createDateRow(lastDate);
				}
				createRowFromTask(index, dt);
				break;
			case TIMED:
				TimedTask tt = (TimedTask) task;
				if (isNewDay(tt, lastDate)) {
					lastDate = getDisplayDate(tt.getDate());
					createDateRow(lastDate);
				}
				createRowFromTask(index, tt);
				break;
			}
			index++;
		}
		packUI();
	}

	private void createRowFromTask(int index, FloatingTask task) {
		TableItem item = new TableItem(table, TABLE_STYLE);
		item.setText(new String[] {Integer.toString(index),
				" ", 
				task.getName(),
				getDoneMessage(task)});
		setColorsForTableItem(item, getDoneColor(task));
	}

	private void createRowFromTask(int index, DeadlineTask task) {
		TableItem item = new TableItem(table, TABLE_STYLE);
		item.setText(new String[] {Integer.toString(index),
				getDisplayDate(task), 
				task.getName(),
				getDoneMessage(task)});
		setColorsForTableItem(item, getDoneColor(task));
	}

	private void createRowFromTask(int index, TimedTask task) {
		TableItem item = new TableItem(table, TABLE_STYLE);
		item.setText(new String[] { Integer.toString(index),
				getDisplayDate(task), 
				task.getName(),
				getDoneMessage(task)});
		setColorsForTableItem(item, getDoneColor(task));
	}
	
	private void createDateRow(String date) {
		TableItem item = new TableItem(table, TABLE_STYLE);
		item.setText(new String[] { " ", date, " ", " "});
		item.setForeground(1, COLOR_DATE_ROW);
	}

	private String getDisplayDate(DeadlineTask task) {
		return Global.timeFormat.format(task.getEndDate());
	}

	private String getDisplayDate(TimedTask task) {
		return Global.timeFormat.format(task.getStartDate())+ "-"+ 
			   Global.timeFormat.format(task.getEndDate());
	}

	private String getDisplayDate(Date date) {
		return "["+Global.dayFormat.format(date)+"]";
	}

	private String getDoneMessage(Task task) {
		if (task.isDone()) {
			return "done";
		} else {
			return "not done";
		}
	}

	private Color getDoneColor(Task task) {
		if (task.isDone()) {
			return COLOR_DONE;
		} else {
			return COLOR_NOT_DONE;
		}
	}
	
	/** Checks if the given task has a different day from the
	 *  last date string, by converting the task's date into
	 *  a display date string.
	 * @param task
	 * @param lastDate
	 * @return          If the given task has a later day.
	 */
	private boolean isNewDay(DatedTask task, String lastDate) {
		if (lastDate != null) {
			return !(getDisplayDate(task.getDate()).equals(lastDate));
		} else {
			return true;
		}
	}

	private void setColorsForTableItem(TableItem item, Color doneColor) {
		item.setForeground(0, COLOR_COL_FIRST);
		item.setForeground(1, COLOR_COL_SECOND);
		item.setForeground(2, COLOR_COL_THIRD);
		item.setForeground(3, doneColor);
	}

	// Calls pack() for UI elements.
	private void packUI() {
		for (TableColumn t : table.getColumns()) {
			t.pack();
		}
		shell.pack();
	}

	private void clearTableItems() {
		table.removeAll();
	}

	private void clearInput() {
		input.setText("");
	}

	private void displayMessage(String s) {
		output.setText(s);
		output.setForeground(blue);
	}

	private void displayErrorMessage(String s) {
		output.setText(s);
		output.setForeground(red);
	}

	//@author A0105753J-unused
	public ArrayList<Task> getTasks(Feedback fb) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		switch(fb.getCommandType()){
		case DISPLAY: 
			tasks = fb.getCommandRelatedTasks();
			break;
		case ADD:case DELETE: case UPDATE:case DONE: case OPEN:
			tasks.add(fb.getCommandRelatedTask());
		case HELP:
			// Desired Output has to be discussed, but low priority anyway
			break;

		case SYNC:
			// Desired Output has to be discussed
			break;

		case EXIT:
			break;

		case INVALID:
			break;

		default:
			break;
		}
		return tasks;
	}

}
