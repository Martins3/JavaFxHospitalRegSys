import java.util.NoSuchElementException;
import java.util.Stack;

public class Queue<E> extends Stack<E>{
    private  Stack<E> stk;
    private final int capacity=5; //设置每个栈上限
    public Queue(){
        stk = new Stack<>();
    }

    public boolean add(E e) throws IllegalStateException, ClassCastException,
            NullPointerException, IllegalArgumentException{
        // check capacity !
        if(this.full())
            throw new IllegalStateException("Queue is full !");

        if(super.size()==0){//当栈1为空时把栈2倒回栈1
            int size = stk.size();
            for (int i = 0; i < size; ++i) {
                super.push(stk.pop());
            }
        }else if (super.size()==capacity){//栈1满时，倒入栈2
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

    public static void main(String[] args) {

    }
}
