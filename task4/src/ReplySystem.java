import java.util.ArrayList;

public class ReplySystem {
    private ArrayList<Reply> replies = new ArrayList<Reply>();

    public ReplySystem() {

    }

    public ReplySystem(ArrayList<Reply> replies) {
	this.replies = replies;
    }

    public ArrayList<Reply> getReplies() {
	return replies;
    }

    public void addReply(Reply reply) {
	this.replies.add(reply);
    }

    public void executeReply(Store store, Reply reply) {
	store.addBookType(reply.getBookType());
	reply.setExecuted(true);
	
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (Reply reply : replies) {
	    sb.append(reply.toString() + System.lineSeparator());
	}
	return sb.toString();
    }

}
