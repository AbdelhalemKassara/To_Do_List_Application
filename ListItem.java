import java.time.LocalDate;

public class ListItem {
	private LocalDate startDate;
	private String item;
	private LocalDate endDate;
	
	public ListItem(){
	}
	public ListItem(String item, LocalDate endDate){
		this.item = item;
		this.endDate = endDate;
	}
	public ListItem(LocalDate startDate, String item, LocalDate endDate){
		this.startDate = startDate;
		this.item = item;
		this.endDate = endDate;
	}

	public void setEndDate(LocalDate endDate){
		this.endDate = endDate;
	}	
	public void setStartDate(LocalDate startDate){
		this.startDate = startDate;
	}
	public void setItem(String item){
		this.item = item;
	}
	
	public LocalDate getStartDate(){
		return startDate;
	}
	public LocalDate getEndDate(){
		return endDate;
	}
	public String getItem(){
		return item;
	}
	public String toString(){
		return String.format("%1$tB %1$te, %1$tY\n",endDate);
	}
}
//%1$tl:%1$tM %1$tp"
