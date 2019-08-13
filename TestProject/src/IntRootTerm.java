public class IntRootTerm {
    IntRootFactor head;
    IntRootTermTail tail;

    @Override
    public String toString(){
        return head.toString() + tail.toString();
    }
}
