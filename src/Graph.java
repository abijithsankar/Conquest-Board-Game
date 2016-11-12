import java.util.ArrayList;
import java.util.List;

public class Graph {
	public static void createGraph(Node root, char[][] boardState, int[][] cellValues, int sumX, int sumO, int depth,
			int boardSize, String player, boolean isMax) {
		int i, j;
		for (i = 0; i < boardSize; i++) {
			for (j = 0; j < boardSize; j++) {
				/*********************************** STAKE *************************************************/
				if (boardState[i][j] == '.') {
					boardState[i][j] = player.charAt(0);
					if (player.equals("X")) {
						sumX += cellValues[i][j];
					} else {
						sumO += cellValues[i][j];
					}
					String state = createState(boardState, boardSize);
					Node node = new Node(sumX, sumO, isMax, state, player,"Stake",i,j);
					homework.nodes.add(node);
					root.adjacencies.add(node);
					depth--;
					if (depth > 0) {
						String opponent;
						if (player.equals("X")) {
							opponent = "O";
						} else {
							opponent = "X";
						}
						createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, opponent, !isMax);
					}
					depth++;
					boardState[i][j] = '.';
					if (player.equals("X")) {
						sumX -= cellValues[i][j];
					} else {
						sumO -= cellValues[i][j];
					}
				}
				/************************************** RAID *************************************************************/
				if (boardState[i][j] == player.charAt(0)) {
					String opponent;
					if (player.equals("X")) {
						opponent = "O";
					} else {
						opponent = "X";
					}
					/************* LEFT ***********************************/
					if (j - 1 >= 0 && boardState[i][j - 1] == '.') {
						int ll = 0, lt = 0, lb = 0;
						boardState[i][j - 1] = player.charAt(0);
						if (player.equals("X")) {
							sumX += cellValues[i][j - 1];
						} else {
							sumO += cellValues[i][j - 1];
						}
						/* ************LEFT.LEFT ****************************/
						if (j - 2 >= 0 && boardState[i][j - 2] == opponent.charAt(0)) {
							ll = 1;
							boardState[i][j - 2] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i][j - 2];
								sumO -= cellValues[i][j - 2];
							} else {
								sumO += cellValues[i][j - 2];
								sumX -= cellValues[i][j - 2];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i,j-1);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********LEFT.LEFT ENDS ********************/
						/* ************LEFT.TOP ****************************/
						if (i - 1 >= 0 && boardState[i - 1][j - 1] == opponent.charAt(0)) {
							lt = 1;
							boardState[i - 1][j - 1] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i - 1][j - 1];
								sumO -= cellValues[i - 1][j - 1];
							} else {
								sumO += cellValues[i - 1][j - 1];
								sumX -= cellValues[i - 1][j - 1];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i,j-1);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********LEFT.TOP ENDS ********************/
						/* ************LEFT.BOTTOM ****************************/
						if (i + 1 != boardSize && boardState[i + 1][j - 1] == opponent.charAt(0)) {
							lb = 1;
							boardState[i + 1][j - 1] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i + 1][j - 1];
								sumO -= cellValues[i + 1][j - 1];
							} else {
								sumO += cellValues[i + 1][j - 1];
								sumX -= cellValues[i + 1][j - 1];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i,j-1);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********LEFT.BOTTOM ENDS ********************/
						String state = createState(boardState, boardSize);
						Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i,j-1);
						homework.nodes.add(node);
						root.adjacencies.add(node);
						depth--;
						if (depth > 0) {
							String localOpponent;
							if (player.equals("X")) {
								localOpponent = "O";
							} else {
								localOpponent = "X";
							}
							createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
									!isMax);
						}
						depth++;
						if (ll == 1) {
							boardState[i][j - 2] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i][j - 2];
								sumO += cellValues[i][j - 2];
							} else {
								sumO -= cellValues[i][j - 2];
								sumX += cellValues[i][j - 2];
							}
						}
						if (lt == 1) {
							boardState[i - 1][j - 1] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i - 1][j - 1];
								sumO += cellValues[i - 1][j - 1];
							} else {
								sumO -= cellValues[i - 1][j - 1];
								sumX += cellValues[i - 1][j - 1];
							}
						}
						if (lb == 1) {
							boardState[i + 1][j - 1] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i + 1][j - 1];
								sumO += cellValues[i + 1][j - 1];
							} else {
								sumO -= cellValues[i + 1][j - 1];
								sumX += cellValues[i + 1][j - 1];
							}
						}
						boardState[i][j - 1] = '.';
						if (player.equals("X")) {
							sumX -= cellValues[i][j - 1];
						} else {
							sumO -= cellValues[i][j - 1];
						}

					}
					/****************** LEFT ENDS **************************/
					/******************** RIGHT ***********************************/
					if (j + 1 != boardSize && boardState[i][j + 1] == '.') {
						int rr = 0, rt = 0, rb = 0;
						boardState[i][j + 1] = player.charAt(0);
						if (player.equals("X")) {
							sumX += cellValues[i][j + 1];
						} else {
							sumO += cellValues[i][j + 1];
						}
						/* ************RIGHT.RIGHT ****************************/
						if (j + 2 != boardSize && boardState[i][j + 2] == opponent.charAt(0)) {
							rr = 1;
							boardState[i][j + 2] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i][j + 2];
								sumO -= cellValues[i][j + 2];
							} else {
								sumO += cellValues[i][j + 2];
								sumX -= cellValues[i][j + 2];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i,j+1);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********RIGHT.RIGHT ENDS ********************/
						/* ************RIGHT.TOP ****************************/
						if (i - 1 >= 0 && boardState[i - 1][j + 1] == opponent.charAt(0)) {
							rt = 1;
							boardState[i - 1][j + 1] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i - 1][j + 1];
								sumO -= cellValues[i - 1][j + 1];
							} else {
								sumO += cellValues[i - 1][j + 1];
								sumX -= cellValues[i - 1][j + 1];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i,j+1);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********RIGHT.TOP ENDS ********************/
						/*
						 * ************RIGHT.BOTTOM
						 ****************************/
						if (i + 1 != boardSize && boardState[i + 1][j + 1] == opponent.charAt(0)) {
							rb = 1;
							boardState[i + 1][j + 1] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i + 1][j + 1];
								sumO -= cellValues[i + 1][j + 1];
							} else {
								sumO += cellValues[i + 1][j + 1];
								sumX -= cellValues[i + 1][j + 1];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i,j+1);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********RIGHT.BOTTOM ENDS ********************/
						String state = createState(boardState, boardSize);
						Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i,j+1);
						homework.nodes.add(node);
						root.adjacencies.add(node);
						depth--;
						if (depth > 0) {
							String localOpponent;
							if (player.equals("X")) {
								localOpponent = "O";
							} else {
								localOpponent = "X";
							}
							createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
									!isMax);
						}
						depth++;
						if (rr == 1) {
							boardState[i][j + 2] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i][j + 2];
								sumO += cellValues[i][j + 2];
							} else {
								sumO -= cellValues[i][j + 2];
								sumX += cellValues[i][j + 2];
							}
						}
						if (rt == 1) {
							boardState[i - 1][j + 1] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i - 1][j + 1];
								sumO += cellValues[i - 1][j + 1];
							} else {
								sumO -= cellValues[i - 1][j + 1];
								sumX += cellValues[i - 1][j + 1];
							}

						}
						if (rb == 1) {
							boardState[i + 1][j + 1] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i + 1][j + 1];
								sumO += cellValues[i + 1][j + 1];
							} else {
								sumO -= cellValues[i + 1][j + 1];
								sumX += cellValues[i + 1][j + 1];
							}

						}
						boardState[i][j + 1] = '.';
						if (player.equals("X")) {
							sumX -= cellValues[i][j + 1];
						} else {
							sumO -= cellValues[i][j + 1];
						}

					}
					/****************** RIGHT ENDS **************************/
					/******************** TOP ***********************************/
					if (i - 1 >= 0 && boardState[i - 1][j] == '.') {
						int tr = 0, tt = 0, tl = 0;
						boardState[i - 1][j] = player.charAt(0);
						if (player.equals("X")) {
							sumX += cellValues[i - 1][j];
						} else {
							sumO += cellValues[i - 1][j];
						}
						/* ************TOP.RIGHT ****************************/
						if (j + 1 != boardSize && boardState[i - 1][j + 1] == opponent.charAt(0)) {
							tr = 1;
							boardState[i - 1][j + 1] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i - 1][j + 1];
								sumO -= cellValues[i - 1][j + 1];
							} else {
								sumO += cellValues[i - 1][j + 1];
								sumX -= cellValues[i - 1][j + 1];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i-1,j);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********TOP.RIGHT ENDS ********************/
						/* ************TOP.TOP ****************************/
						if (i - 2 >= 0 && boardState[i - 2][j] == opponent.charAt(0)) {
							tt = 1;
							boardState[i - 2][j] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i - 2][j];
								sumO -= cellValues[i - 2][j];
							} else {
								sumO += cellValues[i - 2][j];
								sumX -= cellValues[i - 2][j];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i-1,j);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********TOP.TOP ENDS ********************/
						/* ************TOP.LEFT ****************************/
						if (j - 1 >= 0 && boardState[i - 1][j - 1] == opponent.charAt(0)) {
							tl = 1;
							boardState[i - 1][j - 1] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i - 1][j - 1];
								sumO -= cellValues[i - 1][j - 1];
							} else {
								sumO += cellValues[i - 1][j - 1];
								sumX -= cellValues[i - 1][j - 1];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i-1,j);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********TOP.LEFT ENDS ********************/
						String state = createState(boardState, boardSize);
						Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i-1,j);
						homework.nodes.add(node);
						root.adjacencies.add(node);
						depth--;
						if (depth > 0) {
							String localOpponent;
							if (player.equals("X")) {
								localOpponent = "O";
							} else {
								localOpponent = "X";
							}
							createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
									!isMax);
						}
						depth++;
						if (tr == 1) {
							boardState[i - 1][j + 1] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i - 1][j + 1];
								sumO += cellValues[i - 1][j + 1];
							} else {
								sumO -= cellValues[i - 1][j + 1];
								sumX += cellValues[i - 1][j + 1];
							}
						}
						if (tt == 1) {
							boardState[i - 2][j] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i - 2][j];
								sumO += cellValues[i - 2][j];
							} else {
								sumO -= cellValues[i - 2][j];
								sumX += cellValues[i - 2][j];
							}

						}
						if (tl == 1) {
							boardState[i - 1][j - 1] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i - 1][j - 1];
								sumO += cellValues[i - 1][j - 1];
							} else {
								sumO -= cellValues[i - 1][j - 1];
								sumX += cellValues[i - 1][j - 1];
							}

						}
						boardState[i - 1][j] = '.';
						if (player.equals("X")) {
							sumX -= cellValues[i - 1][j];
						} else {
							sumO -= cellValues[i - 1][j];
						}

					}
					/****************** TOP ENDS **************************/
					/******************** BOTTOM ***********************************/
					if (i + 1 != boardSize && boardState[i + 1][j] == '.') {
						int br = 0, bb = 0, bl = 0;
						boardState[i + 1][j] = player.charAt(0);
						if (player.equals("X")) {
							sumX += cellValues[i + 1][j];
						} else {
							sumO += cellValues[i + 1][j];
						}
						/*
						 * ************BOTTOM.RIGHT
						 ****************************/
						if (j + 1 != boardSize && boardState[i + 1][j + 1] == opponent.charAt(0)) {
							br = 1;
							boardState[i + 1][j + 1] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i + 1][j + 1];
								sumO -= cellValues[i + 1][j + 1];
							} else {
								sumO += cellValues[i + 1][j + 1];
								sumX -= cellValues[i + 1][j + 1];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i+1,j);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********BOTTOM.RIGHT ENDS ********************/
						/*
						 * ************BOTTOM.BOTTOM
						 ****************************/
						if (i + 2 != boardSize && boardState[i + 2][j] == opponent.charAt(0)) {
							bb = 1;
							boardState[i + 2][j] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i + 2][j];
								sumO -= cellValues[i + 2][j];
							} else {
								sumO += cellValues[i + 2][j];
								sumX -= cellValues[i + 2][j];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i+1,j);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********BOTTOM.BOTTOM ENDS ********************/
						/* ************BOTTOM.LEFT ****************************/
						if (j - 1 >= 0 && boardState[i + 1][j - 1] == opponent.charAt(0)) {
							bl = 1;
							boardState[i + 1][j - 1] = player.charAt(0);
							if (player.equals("X")) {
								sumX += cellValues[i + 1][j - 1];
								sumO -= cellValues[i + 1][j - 1];
							} else {
								sumO += cellValues[i + 1][j - 1];
								sumX -= cellValues[i + 1][j - 1];
							}
							/*String state = createState(boardState, boardSize);
							Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i+1,j);
							homework.nodes.add(node);
							root.adjacencies.add(node);
							depth--;
							if (depth > 0) {
								String localOpponent;
								if (player.equals("X")) {
									localOpponent = "O";
								} else {
									localOpponent = "X";
								}
								createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
										!isMax);
							}
							depth++;*/
						}
						/* *********BOTTOM.LEFT ENDS ********************/
						String state = createState(boardState, boardSize);
						Node node = new Node(sumX, sumO, !isMax, state, player,"Raid",i+1,j);
						homework.nodes.add(node);
						root.adjacencies.add(node);
						depth--;
						if (depth > 0) {
							String localOpponent;
							if (player.equals("X")) {
								localOpponent = "O";
							} else {
								localOpponent = "X";
							}
							createGraph(node, boardState, cellValues, sumX, sumO, depth, boardSize, localOpponent,
									!isMax);
						}
						depth++;
						if (br == 1) {
							boardState[i + 1][j + 1] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i + 1][j + 1];
								sumO += cellValues[i + 1][j + 1];
							} else {
								sumO -= cellValues[i + 1][j + 1];
								sumX += cellValues[i + 1][j + 1];
							}
						}
						if (bb == 1) {
							boardState[i + 2][j] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i + 2][j];
								sumO += cellValues[i + 2][j];
							} else {
								sumO -= cellValues[i + 2][j];
								sumX += cellValues[i + 2][j];
							}

						}
						if (bl == 1) {
							boardState[i + 1][j - 1] = opponent.charAt(0);
							if (player.equals("X")) {
								sumX -= cellValues[i + 1][j - 1];
								sumO += cellValues[i + 1][j - 1];
							} else {
								sumO -= cellValues[i + 1][j - 1];
								sumX += cellValues[i + 1][j - 1];
							}

						}
						boardState[i + 1][j] = '.';
						if (player.equals("X")) {
							sumX -= cellValues[i + 1][j];
						} else {
							sumO -= cellValues[i + 1][j];
						}

					}
					/****************** BOTTOM ENDS **************************/
				}
				/************************************
				 * RAID ENDS
				 ****************************************************/
			}
		}

	}

	public static String createState(char[][] boardState, int boardSize) {
		// TODO Auto-generated method stub
		int i, j;
		String state = "";
		for (i = 0; i < boardSize; i++) {
			for (j = 0; j < boardSize; j++) {
				state = state + boardState[i][j];
			}
		}
		return state;
	}

}
