import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Spencer on 4/6/2017.
 */
public class SAP {

    private Digraph d;

    //constructor; use digraph implementation in book

    public SAP(Digraph G) {
        this.d = G;
    }

    // return length of shortest ancestral path of v and w; -1 if no such path

    public int length(int v, int w) {

        if (v == w) {
            return 0;
        }
        else if (d.outdegree(v) == 0 && d.outdegree(w) == 0) {
            return -1;
        }
        else {
            BreadthFirstDirectedPaths pathV = new BreadthFirstDirectedPaths(d, v);
            BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(d, w);
            int anc = ancestor(v, w);
            if (anc != -1) {
                return pathV.distTo(anc) + pathW.distTo(anc);
            }
            else {
                return -1;
            }
        }
    }

    // return a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path

    public int ancestor(int v, int w) {
        if (v == w) {
            return v;
        } else if (d.outdegree(v) == 0 && d.outdegree(w) == 0) {
            return -1;
        } else if (d.outdegree(v) == 0 && d.indegree(v) != 0) { // v is ancestor
            BreadthFirstDirectedPaths path = new BreadthFirstDirectedPaths(d, w);
            if (path.hasPathTo(v)) {
                return v;
            } else {
                return -1;
            }
        } else if (d.outdegree(w) == 0) { // w is ancestor
            BreadthFirstDirectedPaths path = new BreadthFirstDirectedPaths(d, v);
            if (path.hasPathTo(w)) {
                return w;
            } else {
                return -1;
            }
        } else {  // look for intersection
            BreadthFirstDirectedPaths searchV = new BreadthFirstDirectedPaths(d, v);
            BreadthFirstDirectedPaths searchW = new BreadthFirstDirectedPaths(d, w);
            int SAP = Integer.MAX_VALUE;
            int anc = -1;
            for (int root : d.rootList) {
                Iterable<Integer> path = searchV.pathTo(root);
                if (path == null) return -1;
                for (int tempAnc : path) {
                    if (searchW.hasPathTo(tempAnc)) {
                        int tempSAP = searchV.distTo(tempAnc) + searchW.distTo(tempAnc);
                        if (tempSAP < SAP) {
                            SAP = tempSAP;
                            anc = tempAnc;
                        }
                    }
                }
            }
            return anc;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        File file = new File(args[0]);
        File input = new File(args[1]);

        //File file = new File("C:\\Users\\Spencer\\Desktop\\Project 4\\digraph1.txt");
        //File input = new File("C:\\Users\\Spencer\\Desktop\\Project 4\\digraph1.input");

        Scanner s = new Scanner(file);
        Digraph digraph = new Digraph(s.nextInt());
        s.nextInt();
        while (s.hasNextInt()) {
            int v = s.nextInt();
            int w = s.nextInt();
            digraph.addEdge(v, w);
            if (digraph.outdegree(w) == 0) digraph.rootList.add(w);
        }
        digraph.removeRoots();

        SAP sapObj = new SAP(digraph);
        Scanner scan = new Scanner(input);
        while (scan.hasNextInt()) {
            int v = scan.nextInt();
            int w = scan.nextInt();
            int sap = sapObj.length(v, w);
            int anc = sapObj.ancestor(v, w);
            System.out.println("sap = " + sap + ", ancestor = " + anc);
        }
    }

}
