import java.lang.reflect.Array;
import java.util.*;

public class Graph {
    private final GraphNode[] vertices;  // Adjacency list for graph.
    private final String name;  //The file from which the graph was created.
    private Integer[][] residual; // This stores the residual capacities: (i,j) is from vertex i to vertex j.

    public Graph(String name, int vertexCount) {
        this.name = name;

        vertices = new GraphNode[vertexCount];
        residual = new Integer[vertexCount][vertexCount];
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            vertices[vertex] = new GraphNode(vertex);
        }
        for (int i=0; i < vertexCount; i++){
            for (int j=0; j < vertexCount; j++){
                residual[i][j] = 0; //initialize to zero.
            }
        }
    }

    private void initialResidual(){
        for (int i=0; i < vertices.length; i++){
            for (GraphNode.EdgeInfo suc: vertices[i].successor){
                residual[i][suc.to] = suc.capacity;
            }
        }
        //return residual;
    }

    public boolean addEdge(int source, int destination, int capacity) {
        // A little bit of validation
        if (source < 0 || source >= vertices.length) return false;
        if (destination < 0 || destination >= vertices.length) return false;

        // This adds the actual requested edge, along with its capacity
        vertices[source].addEdge(source, destination, capacity);
        //adding the backwards edges.
        vertices[destination].addEdge(destination,source, 0);

        return true;
    }

    /**
     * Algorithm to find max-flow in a network
     */
    public int findMaxFlow(int s, int t, boolean report) {
        // TODO:
        initialResidual(); //make the residual graph.
        System.out.println();
        System.out.println("-- Max Flow: " + name + " --");
        int totalFlow = 0;
        while(hasAugmentingPath(s,t)){
            GraphNode sNode = getNode(s);
            ArrayList<Integer> augPath = getAugmentingPath(s,t); //This is ''backwards.''
            GraphNode tNode = getNode(t);
            GraphNode parTNode = getNode(tNode.parent);
            int availableFlow = 0;
            for (GraphNode.EdgeInfo sucCands: parTNode.successor){ // this block code was the temp hack for lack of residual graph.
                if (sucCands.to == t){
                    availableFlow += sucCands.capacity;
                }
            }
            for (int vInt: augPath.subList(0, augPath.size()-1)){
                GraphNode vNode = getNode(vInt);
                GraphNode par = getNode(vNode.parent);
                for (GraphNode.EdgeInfo sucCands: par.successor){
                    if (sucCands.to == vInt){
                        availableFlow = Math.min(availableFlow, residual[sucCands.from][sucCands.to]);
                    }
                }
            }

            for (int i = 0; i< augPath.size()-1 ; i++){
                int vInt = augPath.get(i);
                GraphNode vNode = getNode(vInt);
                GraphNode par = getNode(vNode.parent);
                // Now we're going to update the residual graph.
                for (GraphNode.EdgeInfo sucCands: par.successor){
                    if (sucCands.to == vInt){
                        residual[sucCands.from][sucCands.to] -= availableFlow;
                        residual[sucCands.to][sucCands.from] += availableFlow;
                    }
                }
            }

            totalFlow += availableFlow;
            String AugPath = "";
            while (!augPath.isEmpty()){
                AugPath += augPath.remove(augPath.size()-1).toString();
                AugPath += " ";
            }
            System.out.println("Flow " + availableFlow + ": " + AugPath);
        }
        //hasAugmentingPath(s,t);
        System.out.println();
        String msg = "";
        for (int i=0; i < vertices.length; i++){
            for (GraphNode.EdgeInfo suc: vertices[i].successor){
                int vertFlow = suc.capacity - residual[i][suc.to];
                if (vertFlow > 0) {
                    msg += "Edge(" + i + "," + suc.to + ") transports " + vertFlow + " items\n";
                }
                //System.out.println(msg);
            }
        }
        System.out.print(msg);

        return totalFlow;
    }

    /**
     * Algorithm to find an augmenting path in a network
     */
    private boolean hasAugmentingPath(int s, int t) {
        // TODO:
        boolean hasPath = false;
        for (GraphNode vertex: this.vertices){
            vertex.parent = -1;
        }
        GraphNode tNode = getNode(t); //Get the node associated with integer t.
        //ArrayList<Integer> fifoQueue = new ArrayList<>(); //Use a linked list instead.
        Queue<Integer> fifoQueue = new LinkedList<>();
        fifoQueue.add(s);
        while(!fifoQueue.isEmpty() && tNode.parent ==-1 ){
            //int vInt = fifoQueue.remove(0);
            int vInt = fifoQueue.remove();
            GraphNode vNode = getNode(vInt);
            for (GraphNode.EdgeInfo sucEdge: vNode.successor){ //need to check the backward edges.
                int wInt = sucEdge.to;
                GraphNode wNode = getNode(wInt);
                if(residual[vInt][wInt] > 0 && wNode.parent == -1 && wInt != s){
                    wNode.parent = vInt;
                    //fifoQueue.add(fifoQueue.size(), wInt);
                    fifoQueue.add(wInt);
                }
            }
        }
        if (tNode.parent != -1){
            hasPath = true;
        }
        return hasPath;
    }

    private GraphNode getNode(int t){
        ArrayList<Integer> nodeIds = new ArrayList<>();
        for (GraphNode vertex: this.vertices){
            nodeIds.add(vertex.id);
        }
        int tInd = nodeIds.indexOf(t);
        return vertices[tInd];
    }

    private ArrayList<Integer> getAugmentingPath(int s, int t){
        for (GraphNode vertex: this.vertices){
            vertex.parent = -1;
        }
        GraphNode tNode = getNode(t); //Get the node associated with integer t.
        //ArrayList<Integer> fifoQueue = new ArrayList<>();
        Queue<Integer> fifoQueue = new LinkedList<>();
        fifoQueue.add(s);
        while(!fifoQueue.isEmpty() && tNode.parent ==-1 ){
            //int vInt = fifoQueue.remove(0);
            int vInt = fifoQueue.remove();
            GraphNode vNode = getNode(vInt);
            for (GraphNode.EdgeInfo sucEdge: vNode.successor){
                int wInt = sucEdge.to;
                GraphNode wNode = getNode(wInt);
                if(residual[vInt][wInt] > 0 && wNode.parent == -1 && wInt != s){
                    wNode.parent = vInt;
                    //fifoQueue.add(fifoQueue.size(), wInt);
                    fifoQueue.add(wInt);
                }
            }
        }
        int par = tNode.parent;
        ArrayList<Integer> thing = new ArrayList<>();
        thing.add(tNode.id);
        while(par != -1){
            //thing.add(0,par);
            thing.add(par);
            par = getNode(par).parent;
        }
        return thing;
    }

    /**
     * Algorithm to find the min-cut edges in a network
     */
    public void findMinCut(int s) {
        // TODO:
        System.out.println();
        System.out.println("-- Min Cut: " + name + " --");
        //GraphNode sNode = getNode(s);
        ArrayList<Integer> R = new ArrayList<>();
        R.add(s);
        Queue<Integer> myQueue = new LinkedList<>();
        myQueue.add(s);
        while (!myQueue.isEmpty()){
            int vInt = myQueue.remove();
            GraphNode vNode = getNode(vInt);
            for (GraphNode.EdgeInfo suc: vNode.successor){
                GraphNode sucTo = getNode(suc.to);
                if (!sucTo.visited && residual[vInt][suc.to] > 0){
                    myQueue.add(sucTo.id);
                    if(!R.contains(sucTo.id)) {
                        R.add(sucTo.id);
                    }
                    sucTo.visited = true; //it goes here instead of below (duh!)
                }
                //sucTo.visited = true;
            }
        }
        //With R defined (hopefully), we can get the min cut edges.
        String msg = "";
        for (int vInt: R){
            GraphNode vNode = getNode(vInt);
            for (GraphNode.EdgeInfo suc: vNode.successor){
                if (!R.contains(suc.to)){
                    int vertFlow = suc.capacity - residual[suc.from][suc.to];
                    if(vertFlow > 0) {
                        msg += "Min Cut Edge: (" + vInt + "," + suc.to + ") : " + vertFlow + "\n";
                    }
                }
            }
        }
        System.out.println(msg);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("The Graph " + name + " \n");
        for (var vertex : vertices) {
            sb.append((vertex.toString()));
        }
        return sb.toString();
    }
}
