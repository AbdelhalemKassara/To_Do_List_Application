import java.time.LocalDateTime;

public class Task {
	private LocalDateTime startDate;
	private String task;
	private LocalDateTime endDate;
	
	public ListItem(){
	}
	public ListItem(String task, LocalDateTime endDate){
		this.task = task;
		this.endDate = endDate;
	}
	public ListItem(LocalDateTime startDate, String task, LocalDateTime endDate){
		this.startDate = startDate;
		this.task = task;
		this.endDate = endDate;
	}

	public void setEndDate(LocalDateTime endDate){
		this.endDate = endDate;
	}	
	public void setStartDate(LocalDateTime startDate){
		this.startDate = startDate;
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
	public String toString(){
		return	endDate.toString(); 
			//String.format("%1$tB %1$te, %1$tY\n",endDate);
	}
}
