package com.taskcommander;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Stores global variables for use in the program.
 */
//@author A0112828H
public class Global {

	public static final String APPLICATION_NAME = "Task Commander";

	public static final String MESSAGE_FILE_NOT_GIVEN = "File not given. Please enter a valid file name.";
	public static final String MESSAGE_FILE_NOT_FOUND = "File not found. Please enter a valid file name.";
	public static final String MESSAGE_WELCOME = "Welcome to TaskCommander.";
	public static final String MESSAGE_ADDED = "Added: %1$s";
	public static final String MESSAGE_UPDATED = "Updated: %1$s";
	public static final String MESSAGE_DONE = "Done: %1$s";
	public static final String MESSAGE_ALREADY_DONE = "Already done.";
	public static final String MESSAGE_OPEN = "Opened: %1$s";
	public static final String MESSAGE_ALREADY_OPEN = "Already opened.";
	public static final String MESSAGE_DELETED = "Deleted: %1$s";
	public static final String MESSAGE_CLEARED = "All content deleted.";
	public static final String MESSAGE_DISPLAYED = "Display settings successfully adjusted.";
	public static final String MESSAGE_SEARCHED= "Search task with: %1$s";
	public static final String MESSAGE_UNDONE = "Undone latest command: %1$s.";
	public static final String MESSAGE_INVALID_FORMAT = "Invalid command format: \"%1$s\". Refer to help tab to see the list of commands.";
	public static final String MESSAGE_NO_COMMAND = "No command given.";
	public static final String MESSAGE_NO_INDEX = "Index does not exist. Please type a valid index.";
	public static final String MESSAGE_NO_CHANGE = "No changes were made.";
	public static final String MESSAGE_NULL_ID = "Task has no ID.";
	public static final String MESSAGE_EMPTY = "No tasks available";
	public static final String MESSAGE_UNDO_EMPTY = "No commands to undo";
	public static final String MESSAGE_HELP = "Commands: add ��<task title>�� <date> <end time>,\n display [timed] [deadline] [floating] [done|open] [date] [start time] [end time],\n open <index>, done <index>, delete <index of string>, clear, sort, undo, exit.";
	public static final String MESSAGE_LINE_FOUND = "Found \"%1$s\".";
	public static final String MESSAGE_LINE_NOT_FOUND = "The line \"%1$s\" does not exist.";
	public static final String MESSAGE_SORTED = "Tasks have been sorted.";

	public static final String MESSAGE_FILE_COULD_NOT_BE_WRITTEN = "Error: The File could not be written.";
	public static final String MESSAGE_FILE_COULD_NOT_BE_LOADED = "Error: The File could not be loaded.";
	public static final String MESSAGE_EXCEPTION_IO = "Unable to read the data retrieved.";
	public static final String MESSAGE_ILLEGAL_ARGUMENTS = "Illegal arguments given.";
	public static final String MESSAGE_ARGUMENTS_INVALID = "Invalid arguments given.";

	//@author A0109194A
	public static final String MESSAGE_SYNC_IN_PROGRESS = "Sync in progress...";
	public static final String MESSAGE_SYNC_SUCCESS = "Successfully synced to Google!";
	public static final String MESSAGE_SYNC_INVALID_TOKEN = "Invalid sync token, clearing event store and re-syncing.";
	public static final String MESSAGE_SYNC_NO_NEW = "No new events to sync.";
	public static final String MESSAGE_SYNC_FULL = "Peforming full sync.";
	public static final String MESSAGE_SYNC_INCREMENTAL = "Performing incremental sync.";
	public static final String MESSAGE_SYNC_COMPLETE = "Sync complete.";
	public static final String MESSAGE_SYNC_FAILED = "Sync failed.";

	public static final int INDEX_OFFSET = 1; // Difference between the array index and actual line number

	//@author A0128620M
	// Global Date Format
	public static final SimpleDateFormat dayFormat = new SimpleDateFormat("EEE MMM d ''yy", Locale.ENGLISH);
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

	// Parameters used to restrict the displayed type of tasks or, in case of "none", to remove the date when updating.
	public static final String PARAMETER_FLOATING = "none";
	public static final String PARAMETER_DEADLINE = "deadline";
	public static final String PARAMETER_TIMED = "timed";
	public static final String PARAMETER_DONE = "done";
	public static final String PARAMETER_OPEN = "open";

	// Display Setting Description
	public static final String DESCRIPTION_ALL = "All";
	public static final String DESCRIPTION_DEADLINE = "Date: by ";
	public static final String DESCRIPTION_TIMED = "Date: ";
	public static final String DESCRIPTION_TYPE = "Type: ";
	public static final String DESCRIPTION_STATUS = "Status: ";
	public static final String DESCRIPTION_SEARCH= "Words/Phrases: ";

	//@author A0112828H
	// Possible command types
	public static enum CommandType {
		ADD, 
		UPDATE,
		DONE,
		OPEN,
		DELETE,
		DISPLAY, 
		SEARCH,
		CLEAR,
		UNCLEAR,
		INVALID,
		SYNC,
		UNDO,
		EXIT
	}

	public static enum SyncState {
		PUSH,
		PULL,
		DONE,
		FAILED
	}

	// Name of Storage File
	public static String FILENAME = "tasks.json";

	public static boolean syncing = false;
}
