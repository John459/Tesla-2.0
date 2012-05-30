package irc.events;


public abstract class Event<E> {
	
	E source;
	
	public Event(E source) {
		this.source = source;
	}
	
	public E getSource() {
		return source;
	}
	
	public void setSource(E source) {
		this.source = source;
	}
	
}