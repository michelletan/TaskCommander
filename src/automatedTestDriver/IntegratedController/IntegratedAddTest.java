package automatedTestDriver.IntegratedController;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.taskcommander.Global;
import com.taskcommander.TaskCommander;

/**
 * This class contains all test cases for the Integrated Testing of the add method.
 */
//@author A0105753J
@RunWith(Parameterized.class)
public class IntegratedAddTest {

	private String userCommand;
	private String expectedResult;

	public IntegratedAddTest(String userCommand, String expectedResult) {
		this.userCommand = userCommand;
		this.expectedResult = expectedResult;
	}

	/*
	 * Test cases for add
	 * Formats:
	 * add "content"
	 * add "content" deadline
	 * add "content" period
	 * 
	 * There are multiple ways to indicate time. Like "in 20 minutes", "weekend", 
	 * 	"winter vacation", "5 hours later" and so on
	 */
	@Parameterized.Parameters
	public static Collection<Object[]>  cases() {
		String addCommand = "add";
		String addCommandCapital[] = {"Add", "ADD", "AdD"};
		String q = "\"";
		String content = "task content";
		String deadline = "Nov 11 5pm";
		String period = "3 Dec 5pm - 6 Dec 6pm";
		String periodLastDaysWithoutDate = "6pm - 3 am";

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		calendar.setTimeInMillis(today.getTime());
		calendar.add(Calendar.MINUTE, 20);

		return Arrays.asList(new Object[][] {
				{ addCommand+" "+q+content+q+" " + periodLastDaysWithoutDate, "Added: ["+Global.dayFormat.format(today)+" "+"18:00-"+ Global.dayFormat.format(tomorrow)+" 03:00] "+q+content+q },

				{ addCommand, "Invalid command format: \""+addCommand+"\". Refer to help tab to see the list of commands."},
				{ addCommand+" "+q+content+q, "Added: "+q+content+q},
				{ addCommandCapital[0], "Invalid command format: \""+addCommandCapital[0]+"\". Refer to help tab to see the list of commands."},
				{ addCommandCapital[0]+" "+q+content+q, "Added: "+q+content+q},
				{ addCommandCapital[1], "Invalid command format: \""+addCommandCapital[1]+"\". Refer to help tab to see the list of commands."},
				{ addCommandCapital[1]+" "+q+content+q, "Added: "+q+content+q},
				{ addCommandCapital[2], "Invalid command format: \""+addCommandCapital[2]+"\". Refer to help tab to see the list of commands."},
				{ addCommandCapital[2]+" "+q+content+q, "Added: "+q+content+q},
				{ addCommand+" "+q+content+q+" " + deadline, "Added: [by Tue Nov 11 '14 17:00] "+q+content+q },
				{ addCommand+" "+q+content+q+" " + period, "Added: [Wed Dec 3 '14 17:00-Sat Dec 6 '14 18:00] "+q+content+q },
				{ addCommand +" "+q+"Read ma2214 textbook"+q, "Added: \"Read ma2214 textbook\""}
		});
	}

	@Test
	public void testcontainsParameter() {
		assertEquals(expectedResult, TaskCommander.controller.executeCommand(userCommand)); 
	}
}
