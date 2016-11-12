import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AlphaBeta {
	static Map<Node,Integer> pruneStateMap=new LinkedHashMap<Node,Integer>();
	public static int alphaBetaFunction(Node node, List<Node> nodes, String youPlay, int depth, boolean isMax,
			int alpha,int beta) {
		
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
				int value=alphaBetaFunction(current,nodes,youPlay,depth-1,!isMax,alpha,beta);
				//System.out.println("VALUE IN MAX:"+value);
				pruneStateMap.put(current,value);
				opt=Math.max(opt, value);
				//System.out.println("OPT IN MAX:"+opt);
				alpha=Math.max(alpha, opt);
				//System.out.println("a,b in min:"+alpha+"|"+beta);
				if(beta<=alpha){
					break;
				}
			}
			return opt;
		}
		else{
			int opt=Integer.MAX_VALUE;
			Iterator<Node> itr=node.adjacencies.iterator();
			while(itr.hasNext()){
				Node current=itr.next();
				//System.out.println("MIN:"+current.sumO+"|"+current.sumX+"|"+current.state);
				int value=alphaBetaFunction(current,nodes,youPlay,depth-1,!isMax,alpha,beta);
				pruneStateMap.put(current,value);
				//System.out.println("value in min:"+value);
				opt=Math.min(opt, value);
				//System.out.println("opt in min:"+opt);
				beta=Math.min(beta, opt);
				//System.out.println("a,b in min:"+alpha+"|"+beta);
				if(beta<=alpha){
					break;
				}
			}
			return opt;
			
		}
		
	}

}
