import java.time.LocalDateTime;

public class ListItem {
	private LocalDateTime startDate;
	private String item;
	private LocalDateTime endDate;
	
	public ListItem(){
	}
	public ListItem(String item, LocalDateTime endDate){
		this.item = item;
		this.endDate = endDate;
	}
	public ListItem(LocalDateTime startDate, String item, LocalDateTime endDate){
		this.startDate = startDate;
		this.item = item;
		this.endDate = endDate;
	}

	public void setEndDate(LocalDateTime endDate){
		this.endDate = endDate;
	}	
	public void setStartDate(LocalDateTime startDate){
		this.startDate = startDate;
	}
	public void setItem(String item){
		this.item = item;
	}
	
	public LocalDateTime getStartDate(){
		return startDate;
	}
	public LocalDateTime getEndDate(){
		return endDate;
	}
	public String getItem(){
		return item;
	}
	public String toString(){
		return	endDate.toString(); 
			//String.format("%1$tB %1$te, %1$tY\n",endDate);
	}
}
//%1$tl:%1$tM %1$tp"
