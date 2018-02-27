//Vrinda Mittal 2016279

import java.io.*;

 
class applicant{             // class to represent applicant details
   // fields
  private String name;  
  private String roll;
  private String prog;
  private int dist;
  private int order; // a unique number to identify the order of application

  //constructor
  public applicant(String name,String roll,String prog, int dist,int order){

    this.name=name;
    this.roll=roll;
    this.prog=prog;
    this.dist=dist;
    this.order=order;
  }
  
  public String toString(){      // returns applicant details as a string
    return name+" "+roll+" "+prog+" "+dist;
    
  }

  public int getOrder(){
    return order;
  }

}


public class lab1{
  
  public static void main(String[] args) throws IOException{
  
    BufferedReader rd=new BufferedReader(new InputStreamReader(System.in));
    String[] data=rd.readLine().split(" ");
    int n=Integer.parseInt(data[0]);
    int r=Integer.parseInt(data[1]);
    
    String[] names=new String[n]; //names array
    String[] rolls=new String[n];  //roll No. array
    String[] prog=new String[n];  //program array
    int[] dist=new int[n];   // distances array
    int[] order=new int[n];  // order of application
    int[] verdict=new int[n];  // final verdict of hostel allocation

    for(int i=0;i<n;i++){ 
      String[] student=rd.readLine().split(" ");
      names[i]=student[0];
      rolls[i]=student[1];
      prog[i]=student[2];
      dist[i]=Integer.parseInt(student[3]);
      order[i]=i;
      verdict[i]=0;  
    }


//Sorting based on distances

    for(int i=0;i<n-1;i++){       
      for (int j=i+1;j<n;j++){
        if(dist[i]<dist[j]){
          String tname=names[i];
          String troll=rolls[i];
          String tprog=prog[i];
          int tdist=dist[i];
          int torder=order[i];
          names[i]=names[j];
          rolls[i]=rolls[j];
          prog[i]=prog[j];
          dist[i]=dist[j];
          order[i]=order[j];
          names[j]=tname;
          rolls[j]=troll;
          prog[j]=tprog;
          dist[j]=tdist;
          order[j]=torder;
        }
      }
    }

    int n_phd=0; // will contain max room allocated to phds
    int n_pg=0;   // will indicate max rooms allocated to Mtechs
    
    if (r%2==0){
      n_phd=r/2;
      n_pg=r/2;

    }
    else{
      n_phd=(r/2)+1;
      n_pg=r/2;
    }


//marking people who will get a room
    for(int i=0; i<n;i++){
      if(prog[i].equals("PhD") && n_phd>0){
        verdict[i]=1;
        n_phd-=1;
      }
      if(prog[i].equals("PG") && n_pg>0){
        verdict[i]=1;
        n_pg-=1;
      }      
    }

    int n_ug=n_pg+n_phd;  //no. of under graduates who get the room

    for(int t=0;t<n;t++){
      if(prog[t].equals("UG") && n_ug>0){
        verdict[t]=1;
        n_ug-=1;
      }

    }

    applicant[] flist=new applicant[r];  //finalised list of applicants stored on array of objects
    int f=0;
    for(int i=0; i<n;i++){

      if(verdict[i]==1){
      	
        flist[f]=new applicant(names[i],rolls[i],prog[i],dist[i],order[i]);
        f++;

      }
    }

//sorting the list of applicants on the basis of who applied first

    for(int i=0;i<(r-(n_pg+n_phd))-1;i++){
      for(int j=i+1;j<(r-(n_pg+n_phd));j++){
        if (flist[i].getOrder()>flist[j].getOrder()){
          applicant temp=flist[i];
          flist[i]=flist[j];
          flist[j]=temp;
        }
      }

    }

// printing the final output
    for(int i=0;i<(r-(n_pg+n_phd));i++){
      System.out.println(flist[i].toString());
    }
}
}