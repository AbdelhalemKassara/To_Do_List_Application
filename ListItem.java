import java.time.LocalDate;

//this class contains the start date and the list item.
//the map which will be using this class will contain the end date and will be sorted accordingly.
public class ListItem {
	private LocalDate startDate;
	private String item;
	
	public ListItem(String item){
		this.item = item;
	}
	public ListItem(LocalDate startDate, String item){
		this.startDate = startDate;
		this.item = item;
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
	public String getItem(){
		return item;
	}
}
