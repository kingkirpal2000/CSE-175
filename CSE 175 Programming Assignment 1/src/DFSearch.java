
public class DFSearch {
	public int expansionCount;
	
	public Map map;
	public String startLoc;
	public String destLoc;
	public int depthLimit;
	public SearchDisplay display;
	
	public DFSearch(Map map, String startLoc, String destLoc, int depthLimit, SearchDisplay display) {
		
		this.map = map;
		this.startLoc = startLoc;
		this.destLoc = destLoc;
		this.depthLimit = depthLimit;
		this.display = display;
		
		
	}
	
	
	public Node search(boolean repeatedState) {
		Node startNode = new Node(map.findLocation(startLoc));
		Frontier stack = new Frontier();
		stack.addToTop(startNode);
		
		if(repeatedState == false) {
			while(expansionCount < depthLimit) {
				if(stack.isEmpty()) return null;
				
				startNode = stack.removeTop();
				display.updateDisplay(startNode, stack, null, expansionCount);
				if(startNode.isDestination(destLoc)) return startNode;
				
				expansionCount++;
				startNode.expand();
				stack.addToTop(startNode.children);	
			}
		} else {
			expansionCount = 0;
			LocationSet visited = new LocationSet();
			
			while(expansionCount < depthLimit) {
				if(stack.isEmpty()) return null;
				startNode = stack.removeTop();
				display.updateDisplay(startNode, stack, visited, expansionCount);
				if(startNode.isDestination(destLoc)) return startNode;
		
				visited.add(startNode.loc);
				startNode.expand();
				expansionCount++;
				for(Node n : startNode.children) {
					if(!(visited.contains(n.loc)) && !(stack.contains(n.loc))) {
						stack.addToTop(n);
					}
				}
				
			}
		}
		
	
		
		
		return null;
	}
}
