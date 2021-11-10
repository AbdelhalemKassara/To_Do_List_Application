import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
	private LocalDateTime startDate;
	private String task;
	private LocalDateTime endDate;
	private Task nextItem;

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
	
	public void setNextItem(Task nextItem){
		this.nextItem = nextItem;
	}
	public Task getNextItem(){
		return nextItem;
	}
	
	//wrapper methods for LocalDateTime class (written this way incase I need to reformat inputs first)	
	private void changeDate(LocalDateTime a, int year, int month, int dayOfMonth, int hour, int minute){
		a = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
	}
	private void changeDate(LocalDateTime a, int year, int month, int dayOfMonth){
		if(a == null) {
			a = LocalDateTime.of(year, month, dayOfMonth, 0, 0);
		} else {	
			a = LocalDateTime.of(year, month, dayOfMonth, a.getHour(), a.getMinute());
		}
	}	
	private void changeDate(LocalDateTime a, int hour, int minute) {
		if(a == null){
			LocalDateTime temp = LocalDateTime.now();
			a = LocalDateTime.of(temp.getYear(), temp.getMonth(), temp.getDayOfMonth(), hour, minute);
		} else {
			a = LocalDateTime.of(a.getYear(), a.getMonth(), a.getDayOfMonth(), hour, minute);
		}
	}
	public int compareTo(LocalDateTime otherEndDate) {
		return	endDate.compareTo(otherEndDate);
	}	
	//methods for changing the start and end date	
	public void changeStartDate(int year, int month, int dayOfMonth, int hour, int minute){
		changeDate(startDate, year, month, dayOfMonth, hour, minute);
	}
	public void changeStartDate(int year, int month, int dayOfMonth){
		changeDate(startDate, year, month, dayOfMonth);
	}
	public void changeStartDate(int hour, int minute){
		changeDate(startDate, hour, minute);
	}
	public void changeEndDate(int year, int month, int dayOfMonth, int hour, int minute){
		changeDate(endDate, year, month, dayOfMonth, hour, minute);
	}
	public void changeEndDate(int year, int month, int dayOfMonth){
		changeDate(endDate, year, month, dayOfMonth);
	}
	public void changeEndDate(int hour, int minute){
		changeDate(endDate, hour, minute);
	}
	
	//"tostring" methods
	private String dateString(LocalDateTime a){
		//using %03d is the minimum left padding
		return String.format("%ta %tb %s%d, %d", a.getDayOfWeek(), a.getMonth(), 
				a.getDayOfMonth()/10 == 0 ? "0" : "", a.getDayOfMonth(), a.getYear());
	}
	private String timeString(LocalDateTime a){
		int hour = a.getHour();
		String ampm = "am";

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
		String outputString = String.format("%s | %-56.56s | %s\n", startDateString(), task, endDateString());
		int length = task.length() < 56? task.length() : 56;
		String tempTask = task.substring(length, task.length());	

		while(tempTask.length() != 0) {	
			length = tempTask.length() < 56? tempTask.length() : 56;
			outputString += String.format("%-26.26s | %-56.56s | %1$-26.26s\n", "", tempTask.substring(0, length));
			tempTask = tempTask.substring(length, tempTask.length());	
		}

		return outputString;	
	}
}
