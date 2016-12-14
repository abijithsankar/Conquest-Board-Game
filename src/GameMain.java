import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

public class GameMain {
	static List<Node> nodes = new ArrayList<Node>();
	static List<Character> stateList = new ArrayList<Character>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int i, j;
		BufferedReader br = new BufferedReader(new FileReader("Z:/MS Books/AI/input.txt"));
		BufferedWriter brw = new BufferedWriter(new FileWriter("Z:/MS Books/AI/output.txt"));
		int boardSize = Integer.parseInt(br.readLine());
		String mode = br.readLine();
		String player = br.readLine();
		int[][] cellValues = new int[boardSize][boardSize];
		char[][] boardState = new char[boardSize][boardSize];
		int sumX = 0, sumO = 0;
		String youPlay = player;
		int depth = Integer.parseInt(br.readLine());
		int depthFlag = depth;
		for (i = 0; i < boardSize; i++) {
			String[] valueArray = br.readLine().split("\\s+");
			for (j = 0; j < boardSize; j++) {
				cellValues[i][j] = Integer.parseInt(valueArray[j]);
			}
		}
		for (i = 0; i < boardSize; i++) {
			String[] valueArray = br.readLine().split("");
			for (j = 0; j < boardSize; j++) {
				boardState[i][j] = valueArray[j].charAt(0);
			}
		}
		for (i = 0; i < boardSize; i++) {
			for (j = 0; j < boardSize; j++) {
				if (boardState[i][j] == 'X') {
					sumX += cellValues[i][j];
				} else if (boardState[i][j] == 'O') {
					sumO += cellValues[i][j];
				}
			}
		}
		Node root;
		String state = "";
		for (int m = 0; m < boardSize; m++) {
			for (int n = 0; n < boardSize; n++) {
				state = state + boardState[m][n];
			}
		}
		if (player.equals("X")) {
			root = new Node(sumX, sumO, false, state, "O","Root",-1,-1);
		} else {
			root = new Node(sumX, sumO, false, state, "X","Root",-1,-1);
		}
		br.close();
		nodes.add(root);
		Graph.createGraph(root, boardState, cellValues, sumX, sumO, depth, boardSize, player, true);
		switch (mode) {
		case "MINIMAX":{
			int score = MiniMax.miniMaxFunction(nodes.get(0), nodes, youPlay, depth, true);
			Iterator<Node> itr=root.adjacencies.iterator();
			int rowpos = 0,finalScore = 0;
			int mmFlag=0;
			String mmPrevious = null;
			String move = null;
			char[] mmPosArray = null;
			char colpos = 0;
			while(itr.hasNext()){
				Node child=itr.next();
				if(MiniMax.stateMap.get(child)==score){
					if(mmFlag!=1){
						colpos=(char)(child.colPos+65);
						int counter=0;
						rowpos=child.rowPos+1;
						System.out.print(colpos);
						System.out.println(rowpos+" "+child.move);
						move=child.move;
						if(youPlay.equals("X")){
							finalScore=child.sumX-child.sumO;
						}
						else{
							finalScore=child.sumO-child.sumX;
						}
						mmPosArray=child.state.toCharArray();
						for(i=0;i<boardSize;i++){
							for(j=0;j<boardSize;j++){
								System.out.print(mmPosArray[counter]);
								counter++;
							}
							System.out.println();
						}
						mmFlag=1;
						mmPrevious=child.move;
					}
					else if(mmFlag==1 && mmPrevious.equals("Raid") && child.move.equals("Stake")){
						int finalTempScore;
						if(youPlay.equals("X")){
							finalTempScore=child.sumX-child.sumO;
						}
						else{
							finalTempScore=child.sumO-child.sumX;
						}
						if(finalTempScore>=finalScore){
							colpos=(char)(child.colPos+65);
							int counter=0;
							rowpos=child.rowPos+1;
							System.out.print(colpos);
							System.out.println(rowpos+" "+child.move);
							move=child.move;
							mmPosArray=child.state.toCharArray();
							for(i=0;i<boardSize;i++){
								for(j=0;j<boardSize;j++){
									System.out.print(mmPosArray[counter]);
									counter++;
								}
								System.out.println();
							}
							break;
						}
						
					}
					
				}
			}
			writeToFile(colpos,rowpos,mmPosArray,boardSize,move,brw);
			
		}
			
			break;
		case "ALPHABETA":{
			int pruningScore=AlphaBeta.alphaBetaFunction(nodes.get(0), nodes, youPlay, depth, true,Integer.MIN_VALUE,Integer.MAX_VALUE);
			System.out.println("alphabetascore:" + pruningScore);
			Iterator<Node> pruneItr=root.adjacencies.iterator();
			int pruneRowPos = 0,pruneFinalScore = 0,pruneFlag=0;
			char pruneColpos = 0;
			char[] prunePosArray = null;
			String move=null;
			String previous = null;
			while(pruneItr.hasNext()){
				Node child=pruneItr.next();
				if(AlphaBeta.pruneStateMap.get(child)==pruningScore){
					if(pruneFlag!=1){
						int pruneCounter=0;
						pruneColpos=(char)(child.colPos+65);
						pruneRowPos=child.rowPos+1;
						System.out.print(pruneColpos);
						System.out.println(pruneRowPos+" "+child.move);
						move=child.move;
						if(youPlay.equals("X")){
							pruneFinalScore=child.sumX-child.sumO;
						}
						else{
							pruneFinalScore=child.sumO-child.sumX;
						}
						prunePosArray=child.state.toCharArray();
						for(i=0;i<boardSize;i++){
							for(j=0;j<boardSize;j++){
								System.out.print(prunePosArray[pruneCounter]);
								pruneCounter++;
							}
							System.out.println();
						}
						pruneFlag=1;
						previous=child.move;
					}
					else if(pruneFlag==1 && previous.equals("Raid") && child.move.equals("Stake")){
						int pruneBoardTempScore;
						if(youPlay.equals("X")){
							pruneBoardTempScore=child.sumX-child.sumO;
						}
						else{
							pruneBoardTempScore=child.sumO-child.sumX;
						}
						if(pruneBoardTempScore>=pruneFinalScore){
							int pruneCounter=0;
							pruneColpos=(char)(child.colPos+65);
							pruneRowPos=child.rowPos+1;
							System.out.print(pruneColpos);
							System.out.println(pruneRowPos+" "+child.move);
							move=child.move;
							prunePosArray=child.state.toCharArray();
							for(i=0;i<boardSize;i++){
								for(j=0;j<boardSize;j++){
									System.out.print(prunePosArray[pruneCounter]);
									pruneCounter++;
								}
								System.out.println();
							}
							break;
						}
						
					}
					
				}
				
			}
			writeToFile(pruneColpos,pruneRowPos,prunePosArray,boardSize,move,brw);
		}
		break;	
		}
		brw.close();
	}

	private static void writeToFile(char colpos, int rowPos, char[] posArray, 
			int boardSize, String move, BufferedWriter brw) throws IOException {
		// TODO Auto-generated method stub
		int i,j,counter=0;
		brw.write(colpos+""+rowPos+" "+move);
		brw.newLine();
		for(i=0;i<boardSize;i++){
			for(j=0;j<boardSize;j++){
				brw.write(posArray[counter]);
				counter++;
			}
			brw.newLine();
		}
		
	}

}
