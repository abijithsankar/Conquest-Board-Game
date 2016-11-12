import java.util.Iterator;
import java.util.*;

public class MiniMax {
	static Map<Node,Integer> stateMap=new LinkedHashMap<Node,Integer>();
	public static int miniMaxFunction(Node node,List<Node> nodes, String youPlay, int depth, boolean isMax) {
		// TODO Auto-generated method stub
		if(depth==0){
			if(youPlay.equals("X")){
				return node.sumX-node.sumO;
			}
			else{
				return node.sumO-node.sumX;
			}
		}
		if(isMax){
			int opt=Integer.MIN_VALUE;
			Iterator<Node> itr=node.adjacencies.iterator();
			while(itr.hasNext()){
				Node current=itr.next();
				//System.out.println("MAX:"+current.sumO+"|"+current.sumX+"|"+current.state);
				int value=miniMaxFunction(current,nodes,youPlay,depth-1,!isMax);
				//System.out.println("VALUE IN MAX:"+value);
				stateMap.put(current,value);
				opt=Math.max(opt, value);
				//System.out.println("OPT IN MAX:"+opt);
			}
			return opt;
			
		}
		else{
			int opt=Integer.MAX_VALUE;
			Iterator<Node> itr=node.adjacencies.iterator();
			while(itr.hasNext()){
				Node current=itr.next();
				//System.out.println("MIN:"+current.sumO+"|"+current.sumX+"|"+current.state);
				int value=miniMaxFunction(current,nodes,youPlay,depth-1,!isMax);
				//System.out.println("value in min:"+value);
				stateMap.put(current,value);
				//System.out.println("value in min:"+value);
				opt=Math.min(opt, value);
				//System.out.println("opt in min:"+opt);
			}
			return opt;
		}
		
	}
	
		
		
}

