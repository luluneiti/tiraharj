
package tiraharj.tools;

import tiraharj.Node;

public interface Heap {
    
    public boolean isEmpty();
    public Node poll();
    public void add(Node node);
    public void print();   
    public void clean();
}
