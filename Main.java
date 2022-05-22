import java.util.*;


class Edge {
    Node src, dest;
    int weight;
    Edge(Node src, Node dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Node {
    Character name;
    Node(Character name)  {
        this.name = name;
    }
}

class Graph {
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    int vertices;
    Map<Character,List<Edge>> adj_list;
    int[][] array = new int[6][6];
    public Graph(int vertices) {
        this.vertices = vertices;
        adj_list = new HashMap<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adj_list.put(alphabet[i], new ArrayList<>());
        }
    }
    public void addEdge (Node src,Node dest,int weight) {
        Edge edge = new Edge(src,dest,weight);
        adj_list.get(src.name).add(edge);
    }
    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            List<Edge> list = adj_list.get(alphabet[i]);
            for (int j = 0;j < list.size();j++) {
                System.out.println(alphabet[i] + " -> " + list.get(j).dest.name + " (" + list.get(j).weight + ")");
            }
        }
    }
    public void floyd() {
        int[][] matrix = new int[vertices][vertices];
        for(int i = 0;i < vertices;i++) {
            for(int j = 0;j < vertices;j++) {
                matrix[i][j] = array[i][j];
            }
        }
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j])
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                }
            }
        }
        for (int i = 0; i < vertices; ++i) {
            for (int j = 0; j < vertices; ++j) {
                if (matrix[i][j] == Integer.MAX_VALUE)
                    System.out.print("INF ");
                else
                    System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
    public void graphAsArray() {
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0;i < 26;i++) {
            map.put(alphabet[i],i);
        }
        for(int i = 0;i < 6;i++) {
            for(int j = 0;j < 6;j++) {
                array[i][j] = 9999;
            }
        }
        for(int i = 0;i < vertices;i++) {
            List<Edge> list = adj_list.get(alphabet[i]);
            for(int j = 0;j < vertices;j++) {
                if (list.size() > j) {
                    if (list.contains(list.get(j))) {
                        array[i][map.get(list.get(j).dest.name)] = list.get(j).weight;
                    }
                }
            }
        }
        for(int i = 0;i < vertices;i++) {
            for(int j = 0;j < vertices;j++) {
                if (i == j) {
                    array[i][j] = 0;
                }
            }
        }
    }
}


class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(new Node('A'), new Node('A'),0);
        graph.addEdge(new Node('A'), new Node('B'),2);
        graph.addEdge(new Node('A'), new Node('C'), 4);
        graph.addEdge(new Node('B'), new Node('B'),0);
        graph.addEdge(new Node('B'), new Node('C'), 4);
        graph.addEdge(new Node('B'), new Node('A'),2);
        graph.addEdge(new Node('C'), new Node('C'),0);
        graph.addEdge(new Node('C'), new Node('A'),4);
        graph.addEdge(new Node('C'), new Node('E'),5);
        graph.addEdge(new Node('C'), new Node('B'),4);
        graph.addEdge(new Node('C'), new Node('D'),3);
        graph.addEdge(new Node('D'), new Node('D'),0);
        graph.addEdge(new Node('D'), new Node('C'),3);
        graph.addEdge(new Node('D'), new Node('E'),2);
        graph.addEdge(new Node('E'), new Node('E'),0);
        graph.addEdge(new Node('E'), new Node('F'),1);
        graph.addEdge(new Node('E'), new Node('C'),5);
        graph.addEdge(new Node('E'), new Node('D'),2);
        graph.addEdge(new Node('F'), new Node('F'),0);
        graph.addEdge(new Node('F'), new Node('E'),1);
        graph.printGraph();
        graph.graphAsArray();
        graph.floyd();
    }
}
