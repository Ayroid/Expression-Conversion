import java.util.*;
public class Stacks{
    public class Node{
        String data;
        int priority;
        Node next;

        Node(){
            data=" ";
            priority=0;
            next=null;
        }
    }

    Node top = new Node();

    public int priorityCheck(String ch){
        switch(ch){
            case ")": return 5;
            case "(": return 4;
            case "^": return 3;
            case "*": return 2;
            case "/": return 2;
            case "+": return 1;
            case "-": return 1;
            default: return 0;
        }
    }

    public void push(String ch, int p){
        if(top==null){
            top.data=ch;
            top.priority=p;
            top.next=null;
        }
        else{
            Node newnode = new Node();
            newnode.data=ch;
            newnode.priority=p;
            newnode.next=top;
            top=newnode;
        }
    }

    public String pop(){
        if(top==null)
            return " ";
        else{
            String ret=top.data;
            top=top.next;
            return ret;
        }
    }

    public static void main(String[] args) {
        Stacks st = new Stacks();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Infix Expression: ");
        String infix = sc.nextLine()+")";
        String postfix="";
        sc.close();
        st.push("(",2);
        int ctr=1;
        for(int i=0;i<infix.length();i++){
            String c =  String.valueOf(infix.charAt(i));
            int priority = st.priorityCheck(c);
            if(priority>0){
                ctr++;
            }
            if(c==")"){
                while(st.top.priority!=4){
                    postfix+=st.pop();
                    ctr--;
                }
                postfix+=st.pop();
                ctr--;
            }
            if(priority<st.top.priority){
                st.push(c, priority);
            }
            else{
                while(priority>=st.top.priority){
                    ctr--;
                    if(ctr==0)break;
                    postfix+=st.pop();
                }
                st.push(c, priority);
            }
        }
        System.out.println(postfix);
    }
}