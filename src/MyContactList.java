import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.regex.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyContactList {

    Scanner sc = new Scanner(System.in);

    private int size;
    private Node head;
    private Node tail;

    MyContactList()
    {
        head=null;
        size=0;
    }

    public boolean isEmpty()
    {
        if(size==0)
            return true;

        return false;
    }

    public void addContact()
    {
        char choice;

        System.out.println("You have chosen to add a new contact : ");

        System.out.print("First Name :");
        String fn = sc.nextLine();

        System.out.print("Last Name :");
        String ln = sc.nextLine();

        boolean a=true;
        ArrayList<String> cn = new ArrayList<>();

        do {
            String regex = "[0]?(\\+91)?[6-9][0-9]{9}";
            Pattern p = Pattern.compile(regex);

            while(a)
            {
                System.out.print("Contact Number :");
                String no=sc.nextLine();
                if(no.matches(regex))
                {
                    cn.add(no);
                    a=false;
                }
                else
                    System.out.println("Enter valid phone number.");
            }
            System.out.print("Would you like to add another contact number? (y/n) :");
            choice = sc.next().charAt(0);
            sc.nextLine();
            a=true;
        }
        while (choice == 'y');


        String eid = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        Pattern p = Pattern.compile(eid);
        a=true;
        String email=null;
        while(a)
        {
            System.out.print("Email ID :");
            String id = sc.nextLine();
            if(id.matches(eid))
            {
                email = id;
                a=false;
            }
            else
                System.out.println("Enter valid email.");
        }


        Node node = new Node(new Person(fn,ln,cn,email));

        if(isEmpty())
        {
            node.setPrevious(null);
            node.setNext(null);
            head=node;
            tail=node;
        }
        else
        {

            Node head_t = head;
            for(int i=0;i<size;i++)
            {
                if((node.getData().getF_name()+node.getData().getL_name()).compareToIgnoreCase((head_t.getData().getF_name()+head_t.getData().getL_name()))<=0)
                {
                    if(head_t==head)
                    {
                        node.setPrevious(null);
                        node.setNext(head);
                        head.setPrevious(node);
                        head=node;
                    }
                    else
                    {
                        node.setPrevious(head_t.getPrevious());
                        node.setNext(head_t);
                        head_t.getPrevious().setNext(node);
                        head_t.setPrevious(node);
                    }
                }
                else if(head_t==tail)
                {
                    node.setNext(null);
                    node.setPrevious(tail);
                    tail.setNext(node);
                    tail=node;
                }
                head_t = head_t.getNext();
            }

        }

        size++;

        System.out.println("Added Successfully !\n\n");

    }


    public void display()
    {
        System.out.println("---Here are all your contacts---");

        Node temp = head;

        while(temp!=null)
        {
            System.out.println("-------- * -------- * -------- * --------");
            System.out.println("First Name: "+temp.getData().getF_name());
            System.out.println("Last Name: "+temp.getData().getL_name());

            if(temp.getData().getP_number().size()<=1)
            {
                System.out.print("Contact Number : "+temp.getData().getP_number().get(0)+"\n");
            }
            else
            {
                System.out.print("Contact Number(s) :");
                for(String l:temp.getData().getP_number())
                {
                    System.out.print(l);
                    System.out.print(", ");
                }
                System.out.print("\b\b\n");
            }

            System.out.println("Email ID: "+temp.getData().getEmail());
            System.out.println("-------- * -------- * -------- * --------\n");

            temp = temp.getNext();
        }
    }



    public void search(String s)
    {
        Node temp = head;
        int count=0;
        while(temp!=null)
        {
            if(temp.getData().getF_name().equals(s))
            {
                System.out.println("-------- * -------- * -------- * --------");
                System.out.println("First Name: "+temp.getData().getF_name());
                System.out.println("Last Name: "+temp.getData().getL_name());

                if(temp.getData().getP_number().size()<=1)
                {
                    System.out.print("Contact Number : "+temp.getData().getP_number().get(0)+"\n");
                }
                else
                {
                    System.out.print("Contact Number(s) :");
                    for(String l:temp.getData().getP_number())
                    {
                        System.out.print(l);
                        System.out.print(", ");
                    }
                    System.out.print("\b\b\n");
                }

                System.out.println("Email ID: "+temp.getData().getEmail());
                System.out.println("-------- * -------- * -------- * --------\n");
                count++;
            }
            temp = temp.getNext();
        }
        if(count==0)
            System.out.println("NO RESULTS FOUND!\n");
    }



    public void deleteContact() {
        if (!isEmpty()) {
            Node temp_h = head;
            System.out.println("Here are all your contacts:");
            for (int i = 0; i < size; i++) {
                System.out.println((i + 1) + ". " + temp_h.getData().getF_name()+" "+ temp_h.getData().getL_name());
                if(i<size-1)
                    temp_h = temp_h.getNext();
            }

            System.out.print("Press the number against the contact to delete it:");
            temp_h = head;

            int choice = sc.nextInt();

            if (size == 1 && choice == 1) {
                System.out.println(head.getData().getF_name() + " " + head.getData().getL_name() + "\'s contact deleted from list!\n\n");
                head = tail = null;
                size--;
                return;
            }
            if (size != 1 && choice == 1) {
                System.out.println(head.getData().getF_name() + " " + head.getData().getL_name() + "\'s contact deleted from list!\n\n");
                head = temp_h.getNext();
                head.setPrevious(null);
                size--;
                return;
            }
            if(!(choice==size)) {
                temp_h = head;

                for (int i = 1; i < choice-1; i++) {
                    temp_h = temp_h.getNext();
                }
                System.out.println(temp_h.getNext().getData().getF_name() + " " + temp_h.getNext().getData().getL_name() + "\'s contact deleted from list!\n\n");
                temp_h.setNext(temp_h.getNext().getNext());
                temp_h.getNext().setPrevious(temp_h);
                size--;
                return;
            }
            if(choice==size)
            {
                System.out.println(tail.getData().getF_name()+" "+tail.getData().getL_name() + "\'s contact deleted from list!\n\n");
                Node temp_t=tail;
                tail=temp_t.getPrevious();
                tail.setNext(null);
                return;
            }
        }
        else
            System.out.println("YOUR CONTACT LIST IS EMPTY !!!\n\n");
    }
}