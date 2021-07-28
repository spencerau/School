import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Spencer on 4/6/2017.
 */

public final class WordNet {

    private Digraph d;
    private HashMap<Integer, ArrayList<String>> map;
    private HashMap<String, ArrayList<Integer>> mapWKey;

    /* constructor that takes that name of the two input files */

    public WordNet(String synsets, String hypernyms) throws FileNotFoundException {
        map = new HashMap<>();
        mapWKey = new HashMap<>();
        File hyper = new File(hypernyms);
        Scanner scanH = new Scanner(hyper);
        File syn = new File(synsets);
        Scanner scanS = new Scanner(syn);
        int size = 0;

        while (scanS.hasNext()) {
            String buffer = scanS.nextLine();
            ArrayList<String> listS = new ArrayList<>();
            ArrayList<Integer> listI = new ArrayList<>();
            int comma1 = buffer.indexOf(',');
            int comma2 = buffer.indexOf(',', comma1+1);
            int key = Integer.parseInt((buffer.substring(0, comma1)));
            String wordList = buffer.substring(comma1+1, comma2);
            listI.add(key);
            for (String regex: wordList.split(" ")) {
                listS.add(regex);
                map.put(key, listS);
                if (mapWKey.containsKey(regex)) {
                    listI = mapWKey.get(regex);
                    listI.add(key);
                    mapWKey.put(regex, listI);
                }
                mapWKey.put(regex, listI);
            }
            size++;
        }

        d = new Digraph(++size);
        while (scanH.hasNext()) {
            String buffer;
            buffer = scanH.nextLine();
            boolean first = true;
            int synset = -1;
            int hypemym;
            for (String regex: buffer.split(",")) {
                if (first) {
                    synset = Integer.parseInt(regex);
                    first = false;
                }
                else {
                    hypemym = Integer.parseInt(regex);
                    d.addEdge(synset, hypemym);
                    if (d.outdegree(hypemym) == 0) d.rootList.add(hypemym);
                }
            }
        }
        d.removeRoots();
    }

    public int getKey(String word) {
        for (Map.Entry<Integer, ArrayList<String>> entry : map.entrySet()) {
            for (String noun : entry.getValue()) {
                if (noun.equals(word)) return entry.getKey();
            }
        }
        return -1;
    }

    /* Is the word a WordNet noun? This can be used to search for existing nouns at the beginning of the printSap
       method */

    public boolean isNoun(String word) {
        //return getKey(word) != -1;
        return mapWKey.containsKey(word);
        //else return false;
    }

    /* Print the synset (second field of synsets.txt) that is the common ancestor of nounA and nounB in a shortest
       ancestral path as well as the length of the path, following this format:
       "sap<space>=<space><number>,<space>ancestor<space>=<space><synsettext>"
       If no such path exists the SAP should contain -1 and the ancestor should say "null"
       This method should use the previous defined SAP datatype. */

    public void printSap(String nounA, String nounB) {
        int SAP = Integer.MAX_VALUE;
        String ANC = "null";

        if (!isNoun(nounA) || !isNoun(nounB)) {
            SAP = -1;
            System.out.println("sap = " + SAP + ", ancestor = " + ANC);
        } else {
            SAP sap = new SAP(d);
            for (int A : mapWKey.get(nounA)) {
                for (int B : mapWKey.get(nounB)) {
                    int tempSap = sap.length(A, B);
                    if (tempSap < SAP) {
                        SAP = tempSap;
                        int anc = sap.ancestor(A, B);
                        if (anc != -1) ANC = map.get(anc).get(0);
                    }
                }
            }
            System.out.println("sap = " + SAP + ", ancestor = " + ANC);
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        /*
        WordNet wordNet = new WordNet("C:\\Users\\Spencer\\Desktop\\Project 4\\synsets.txt",
                "C:\\Users\\Spencer\\Desktop\\Project 4\\hypernyms.txt");
        File input = new File("C:\\Users\\Spencer\\Desktop\\Project 4\\wordnet.input");
        */
        WordNet wordNet = new WordNet(args[0], args[1]);
        File input = new File(args[2]);
        Scanner s = new Scanner(input);

        String buffer;
        while (s.hasNext()) {
            buffer = s.nextLine();
            int space = buffer.indexOf(' ');
            String word1 = buffer.substring(0, space);
            String word2 = buffer.substring(space+1);
            wordNet.printSap(word1, word2);
        }

    }

}
