package Application.DataStructures;
import Application.CommandLine.Format;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task extends Format implements Comparable<Task>{
	protected LocalDateTime startDate;
	protected String task;
	protected LocalDateTime endDate;

	//constructors
	public Task(String task, LocalDateTime endDate){
		this.task = task;
		this.endDate = endDate;
	}
	public Task(LocalDateTime startDate, String task, LocalDateTime endDate){
		this(task, endDate);	
		this.startDate = startDate;
	}
	public Task(String task, int year, int month, int dayOfMonth, int hour, int minute){
		this.task = task;
		this.endDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
	}
	
	public Task(int stYear, int stMonth, int stDayOfMonth, int stHour, int stMinute, String task, 
			int year, int month, int dayOfMonth, int hour, int minute){
		this.startDate = LocalDateTime.of(stYear, stMonth, stDayOfMonth, stHour, stMinute);	
		this.task = task;
		this.endDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
	}

	//getters and setters	
	public void setTask(String task){
		this.task = task;
	}
	public String getTask(){
		return task;
	}
	
	public LocalDateTime getStartDate(){
		return startDate;
	}
	public LocalDateTime getEndDate(){
		return endDate;
	}

	public int compareTo(Task t2) {
		int result = endDate.compareTo(t2.getEndDate());
		
		if(result > 0) {
			return 1;
		} else if (result < 0) {
			return -1;
		} else {
			return 0;
		}
	}	
	//methods for changing the start and end date	
	public void changeStartDate(int year, int month, int dayOfMonth, int hour, int minute){
		startDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
	}

	//these probably will be needed for the ui version of the app
/*	public void changeStartDate(int year){
		if(startDate == null) {
			LocalDateTime temp = LocalDateTime.now();
			startDate = LocalDateTime.of(year, temp.getMonth(), temp.getDayOfMonth(), temp.getHour(), temp.getMinute());
		} else {
			startDate = LocalDateTime.of(year, startDate.getMonth(), startDate.getDayOfMonth(), startDate.getHour(), startDate.getMinute());
		}
	}*/

	public void changeStartDate(int year, int month, int dayOfMonth){
		if(startDate == null) {
			startDate = LocalDateTime.of(year, month, dayOfMonth, 0 , 0);
		} else {
			startDate = LocalDateTime.of(year, month, dayOfMonth, startDate.getHour(), startDate.getMinute());
		}
	}
	public void changeStartDate(int hour, int minute) {
		if (startDate == null) {
			LocalDateTime temp = LocalDateTime.now();
			startDate = LocalDateTime.of(temp.getYear(), temp.getMonth(), temp.getDayOfMonth(), hour, minute);
		} else {
			startDate = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), hour, minute);
		}
	}
	
	//might be a good idea to remove these two methods(currently using them in the tests)
	public void changeStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public void changeEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public void changeEndDate(int year, int month, int dayOfMonth, int hour, int minute){
		endDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);	
	}
	public void changeEndDate(int year, int month, int dayOfMonth){
		endDate = LocalDateTime.of(year, month, dayOfMonth, endDate.getHour(), endDate.getMinute());
	
	}
	public void changeEndDate(int hour, int minute){
		endDate = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), hour, minute);
	}

	//"tostring" methods
	private String dateString(LocalDateTime a){
		//using %03d is the minimum left padding
		return String.format("%ta %tb %s%d, %d", a.getDayOfWeek(), a.getMonth(),
				a.getDayOfMonth()/10 == 0 ? "0" : "", a.getDayOfMonth(), a.getYear());
	}
	private String timeString(LocalDateTime a){
		int hour = a.getHour();

		if(hour > 12){
			hour -= 12;
		} else if (hour == 0){
			hour = 12;
		}

		return String.format("%s%d:%s%d %s", hour/10 == 0? "0" : "", hour, a.getMinute()/10 == 0? "0" : "",
				a.getMinute(), a.getHour() >= 12 ? "pm": "am");
	}

	public String startDateString(){
		return startDate == null? "" : dateString(startDate) + ", "  + timeString(startDate);
	}
	public String endDateString(){
		return dateString(endDate) + ", " + timeString(endDate);
	}
	//56 is the default width of the command prompt window
	public String toString(){
		StringBuilder outStr = new StringBuilder();
		outStr.append(String.format(getFormat() + '\n', startDateString(), task, endDateString()));
		int length = Math.min(task.length(), getSpacingMid());
		String tempTask = task.substring(length);

		while(tempTask.length() != 0) {
			length = Math.min(tempTask.length(), getSpacingMid());
			outStr.append(String.format(getFormat() + '\n', "", tempTask.substring(0, length), ""));
			tempTask = tempTask.substring(length);
		}

		return outStr.toString();
	}
	public ArrayList<String> toStringArrList(){
		ArrayList<String> outStr = new ArrayList<>();
		outStr.add(String.format(getFormat(), startDateString(), task, endDateString()));
		int length = Math.min(task.length(), getSpacingMid());
		String tempTask = task.substring(length);

		while(tempTask.length() != 0) {
			length = Math.min(tempTask.length(), getSpacingMid());
			outStr.add(String.format(getFormat(), "", tempTask.substring(0, length), ""));
			tempTask = tempTask.substring(length);
		}

		return outStr;
	}

}
