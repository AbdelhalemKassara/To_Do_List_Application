public class ToDoList {
	Node<String> list;


}

class Node <T>{
	private T item;
	private Node child;
	
	public Node(){
	}
	public Node(T item){
		this.item = item;
		this.child = null;	
	}
	public Node(Node child){
		this.child = child;
	}	
	public Node(T item, Node child) {
		this.item = item;
		this.child = child;
	}	
	public T getItem(){
		try{
			return item;			
		}catch(Exception e) {
			System.out.println(e);	
		}	
	}
	public Node getChild(){
		return child;
	}
	public void setChild(Node child){
		this.child = child;
	}
	public void setItem(T item) {
		this.item = item;
	}
}


