import java.util.NoSuchElementException;
import java.util.Stack;

public class Queue<E> extends Stack<E>{
    private  Stack<E> stk;
    private final int capacity = 100;
    public Queue(){
        stk = new Stack<>();
    }

    public boolean add(E e) throws IllegalStateException, ClassCastException,
            NullPointerException, IllegalArgumentException{
        // check capacity !
        if(this.full())
            throw new IllegalStateException("Queue is full !");

        if (super.size() == 0) {
            int size = stk.size();
            for (int i = 0; i < size; ++i) {
                super.push(stk.pop());
            }
        } else if (super.size() == capacity) {
            int size=super.size();
            for (int i = 0; i < size; ++i) {
                E t=super.remove(super.size()-1);
                stk.push(t);
            }
        }
        super.push(e);
        return true;
    }

    public boolean offer(E e) throws ClassCastException, NullPointerException,
            IllegalArgumentException{

        if(this.full())
            return false;

        if(super.size()==0){
            int size = stk.size();
            for (int i = 0; i < size; ++i) {
                super.push(stk.pop());
            }
        }
        else if (super.size()==capacity){
            int size = super.size();
            for (int i = 0; i < size; ++i) {
                stk.push(super.remove(super.size()-1));
            }
        }

        super.push(e);
        return true;
    }

    public E remove( ) throws NoSuchElementException {
        if(super.size() == 0 && stk.size()==0){
            throw new NoSuchElementException();
        }

        if (super.size()>0 && stk.size() > 0){
            return stk.pop();
        }

        int size=super.size();
        for (int i = 0; i < size; ++i) {
            stk.push(super.remove(super.size()-1));
        }
        return stk.pop();
    }

    public E poll( ) {
        if(super.size() == 0 && stk.size() == 0){
            return null;
        }

        if (super.size()>0&&stk.size()>0){
            return stk.pop();
        }
        int size=super.size();
        for (int i = 0; i < size; ++i) {
            stk.push(super.remove(super.size()-1));
        }
        return stk.pop();
    }


    public E peek (){
        if(super.size()==0&&stk.size()==0){
            return null;
        }
        return stk.size()>0?stk.elementAt(stk.size()-1):super.elementAt(0);

    }

    public E element() throws NoSuchElementException {
        if(super.size()==0&&stk.size()==0){
            throw new NoSuchElementException();
        }
        return stk.size()>0?stk.elementAt(stk.size()-1):super.elementAt(0);
    }

    public boolean full(){
        return super.size() == capacity && stk.size() > 0;
    }

    public boolean empty(){
        return size()==0&&stk.size()==0;
    }
}
