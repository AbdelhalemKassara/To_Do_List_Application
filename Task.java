import java.time.LocalDateTime;

public class Task {
	private LocalDateTime startDate;
	private String task;
	private LocalDateTime endDate;
	
	public Task(){
	}
	public Task(String task, LocalDateTime endDate){
		this.task = task;
		this.endDate = endDate;
	}
	public Task(LocalDateTime startDate, String task, LocalDateTime endDate){
		this.startDate = startDate;
		this.task = task;
		this.endDate = endDate;
	}
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
	
	
	private void changeDate(LocalDateTime a, int year, int month, int dayOfMonth, int hour, int minuite){
		a = LocalDateTime.of(year, month, dayOfMonth, hour, minuite);
	}
	private void changeDate(LocalDateTime a, int year, int month, int dayOfMonth){
		a = LocalDateTime.of(year, month, dayOfMonth, a.getHour(), a.getMinute());
	}	
	private void changeDate(LocalDateTime a, int hour, int minuite) {
		a = LocalDateTime.of(a.getYear(), a.getMonth(), a.getDayOfMonth(), hour, minuite);
	}

	public String toString(){
		return	endDate.toString(); 
			//String.format("%1$tB %1$te, %1$tY\n",endDate);
	}
}
