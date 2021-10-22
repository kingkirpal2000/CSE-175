import java.util.*;

public class BFSearch {
	public int expansionCount;

	public Map map;
	public String startLoc;
	public String destLoc;
	public int depthLimit;
	public SearchDisplay display;

	public BFSearch(Map map, String startLoc, String destLoc, int depthLimit, SearchDisplay display) {

		this.map = map;
		this.startLoc = startLoc;
		this.destLoc = destLoc;
		this.depthLimit = depthLimit;
		this.display = display;

	}

	
	
	public Node search(boolean repeatedState) {
		Node startNode = new Node(map.findLocation(startLoc));   //declaring start node
		Frontier queue = new Frontier();  //initializing queue
		queue.addToBottom(startNode);
		
		if(repeatedState == false) {
			while(expansionCount < depthLimit) {
	
				if(queue.isEmpty()) {
					return null;
				} else {
					startNode = queue.removeTop();
					display.updateDisplay(startNode, queue, null, expansionCount);
					if(startNode.isDestination(destLoc)) {
						return startNode;
					} else {
						expansionCount++;
						startNode.expand();
						queue.addToBottom(startNode.children);
						
					}
				}
			}
		} else {
			expansionCount = 0;
			LocationSet visited = new LocationSet();
			while(expansionCount < depthLimit) {
				if(queue.isEmpty()) {
					return null;
				} else {
					startNode = queue.removeTop();
					display.updateDisplay(startNode, queue, visited, expansionCount);
					if(startNode.isDestination(destLoc)) {
						return startNode;
					} else {
						
						visited.add(startNode.loc.name);
						
						startNode.expand();
						expansionCount++;
						
						for(Node n : startNode.children) {  //repeated state
							if(!(queue.contains(n.loc)) && !(visited.contains(n.loc.name))) {
								queue.addToBottom(n);
							}
						}
					}
					
				}
			}
		}
		





		return null;
	}

}
