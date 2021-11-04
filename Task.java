import java.time.LocalDateTime;

public class Task {
	private LocalDateTime startDate;
	private String task;
	private LocalDateTime endDate;
	
	//constructors	
	public Task(String task, LocalDateTime endDate){
		this.task = task;
		this.endDate = endDate;
	}
	public Task(LocalDateTime startDate, String task, LocalDateTime endDate){
		this(task, endDate);	
		this.startDate = startDate;
	}
	
	//getters and setters	
	public void setTask(String task){
		this.task = task;
	}
	
	public LocalDateTime getStartDate(){
		return startDate;
	}
	public LocalDateTime getEndDate(){
		return endDate;
	}
	public String getTask(){
		return task;
	}


	//wrapper methods for LocalDateTime class (written this way incase I need to reformat inputs first)	
	private void changeDate(LocalDateTime a, int year, int month, int dayOfMonth, int hour, int minuite){
		a = LocalDateTime.of(year, month, dayOfMonth, hour, minuite);
	}
	private void changeDate(LocalDateTime a, int year, int month, int dayOfMonth){
		a = LocalDateTime.of(year, month, dayOfMonth, a.getHour(), a.getMinute());
	}	
	private void changeDate(LocalDateTime a, int hour, int minuite) {
		a = LocalDateTime.of(a.getYear(), a.getMonth(), a.getDayOfMonth(), hour, minuite);
	}

	//methods for changing the start and end date	
	public void changeStartDate(int year, int month, int dayOfMonth, int hour, int minuite){
		changeDate(startDate, year, month, dayOfMonth, hour, minuite);
	}
	public void changeStartDate(int year, int month, int dayOfMonth){
		changeDate(startDate, year, month, dayOfMonth);
	}
	public void changeStartDate(int hour, int minuite){
		changeDate(startDate, hour, minuite);
	}
	public void changeEndDate(int year, int month, int dayOfMonth, int hour, int minuite){
		changeDate(endDate, year, month, dayOfMonth, hour, minuite);
	}
	public void changeEndDate(int year, int month, int dayOfMonth){
		changeDate(endDate, year, month, dayOfMonth);
	}
	public void changeEndDate(int hour, int minuite){
		changeDate(endDate, hour, minuite);
	}
	
	//"tostring" methods
	private String dateString(LocalDateTime a){
		//using %03d is the minimum left padding
		return String.format("%d-%s%d-%s%d", a.getYear(),a.getMonthValue()/10 == 0 ? "0" : "", a.getMonthValue(),
			       				a.getDayOfMonth()/10 == 0 ? "0" : "", a.getDayOfMonth());
	}
	private String timeString(LocalDateTime a){
		return String.format("%d:%d", a.getHour(), a.getMinute());
	}

	public String startDateString(){
		return startDate == null? "" : dateString(startDate) + " "  + timeString(startDate);
	}
	public String endDateString(){
		return dateString(endDate) + " " + timeString(endDate);
	}

	public String toString(){
		return startDateString() + " " + task + " " + endDateString();		
	}
}
