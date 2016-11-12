import java.util.ArrayList;
import java.util.List;

public class Node {

	int sumX,sumO;
	boolean nodeType;//false=min, true=max
	String state;
	List<Node> adjacencies=new ArrayList<Node>();
	String player;
	String move;
	int rowPos,colPos;
	public Node(int sumX,int sumO, boolean nodeType,String state,String player,
			String move,int rowPos,int colPos) {
		this.sumX=sumX;
		this.sumO=sumO;
		this.nodeType=nodeType;
		this.state=state;
		this.player=player;
		this.move=move;
		this.rowPos=rowPos;
		this.colPos=colPos;
	}

	

}
