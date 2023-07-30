import java.io.*;
import java.util.*;
class P{
   int a,b;
   P(int a,int b){
      this.a = a;
      this.b = b;
   }
}
class PR{
   int a,b,ind;
   PR(int a,int b,int ind){
      this.a = a;
      this.b = b;
      this.ind = ind;
   }
}
public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true){
		   System.out.println("Press 0 to exit");
   		System.out.println("Press 1 for First Come First Serve (FCFS)");
   		System.out.println("Press 2 for Shortest Job First (Non-preemptive)");
   		System.out.println("Press 3 for Shortest Remaining Time First (SRTF)");
   		System.out.println("Press 4 for Round Robin (RR)");
   		System.out.println("Press 5 for Priority Scheduling (Preemptive)");
   		System.out.println("Press 6 for Priority Scheduling (Non-preemptive)");
   		int algo = sc.nextInt();
   		if(algo==0){
   		   break;
   		}
   		else if(algo==1){
   		   fcfs();
   		}
   		else if(algo==2){
   		   sjf();
   		}
   		else if(algo==3){
   		   srtf();
   		}
   		else if(algo==4){
   		   rr();
   		}
   		else if(algo==5){
   		   ps();
   		}
   		else if(algo==6){
   		   psN();
   		}
		}
	}
	static void fcfs(){
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Number of Processes");
	   int n = sc.nextInt();
	   int arr[] = new int[n];
	   int burst[] = new int[n];
	   System.out.println("Enter Arrival times of Processes");
	   for(int i=0;i<n;i++){
	      arr[i] = sc.nextInt();
	   }
	   System.out.println("Enter Burst times of Processes");
	   for(int i=0;i<n;i++){
	      burst[i] = sc.nextInt();
	   }
	   ArrayList<P> l = new ArrayList<>();
	   for(int i=0;i<n;i++){
	      l.add(new P(arr[i],burst[i]));
	   }
	   Collections.sort(l,(a,b)->{
	      return a.a-b.a;
	   });
	   int x = 0;
	   ArrayList<Integer> comp = new ArrayList<>();
	   ArrayList<Integer> tat = new ArrayList<>();
	   ArrayList<Integer> wait = new ArrayList<>();
	   for(int i=0;i<n;i++){
	      if(x<l.get(i).a){
	         x += l.get(i).a-x;
	      }
	      x += l.get(i).b;
	     // System.out.println(l.get(i).a+" "+l.get(i).b+" "+x);
	      comp.add(x);
	      tat.add(comp.get(i)-arr[i]);
	      wait.add(tat.get(i)-burst[i]);
	   }
	   float tSum = 0,wtSum = 0;
	   System.out.println("First Come First Serve (FCFS) : ");
	   for(int i=0;i<n;i++){
	      tSum += tat.get(i);
	      wtSum += wait.get(i);
	      System.out.println("Process "+(i+1)+" :  Arr "+arr[i]+"  Burst "+burst[i]+"  comp "+comp.get(i)+"  TAT "+tat.get(i)+"  wait "+wait.get(i));
	   }
	   System.out.println("Average TAT = "+(float)(tSum/n)+"  Average WAIT = "+(float)(wtSum/n));
	   System.out.println();
	}
	static void sjf(){
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Number of Processes");
	   int n = sc.nextInt();
	   int arr[] = new int[n];
	   int burst[] = new int[n];
	   System.out.println("Enter Arrival times of Processes");
	   for(int i=0;i<n;i++){
	      arr[i] = sc.nextInt();
	   }
	   System.out.println("Enter Burst times of Processes");
	   for(int i=0;i<n;i++){
	      burst[i] = sc.nextInt();
	   }
	   ArrayList<P> l = new ArrayList<>();
	   for(int i=0;i<n;i++){
	      l.add(new P(arr[i],burst[i]));
	   }
	   int x = 0;
	   int comp[] = new int[n];
	   int tat[] = new int[n];
	   int wait[] = new int[n];
	   boolean f[] = new boolean[n];
	   while(true){
	      int ind = -1;
	      int crt = Integer.MAX_VALUE;//criteria = burst time
	      boolean flag = false;
	      for(int i=0;i<n;i++){
	         if(!f[i] && l.get(i).a<=x && l.get(i).b<crt){
	            crt = l.get(i).b;
	            ind = i;
	            flag = true;
	         }
	         if(!f[i]){
	            flag = true;
	         }
	      }
	      if(!flag){
	         break;
	      }
	      if(ind==-1){
	         x++;
	         continue;
	      }
	      f[ind] = true;
	      x += crt;
	      comp[ind] = (x);
	      tat[ind] = comp[ind]-arr[ind];
	      wait[ind] = tat[ind]-burst[ind];
	   }
	   float tSum = 0,wtSum = 0;
	   System.out.println("Shortest Job First (Non-preemptive) : ");
	   for(int i=0;i<n;i++){
	      tSum += tat[i];
	      wtSum += wait[i];
	      System.out.println("Process "+(i+1)+" :  Arr "+arr[i]+"  Burst "+burst[i]+"  comp "+comp[i]+"  TAT "+tat[i]+"  wait "+wait[i]);
	   }
	   System.out.println("Average TAT = "+(float)(tSum/n)+"  Average WAIT = "+(float)(wtSum/n));
	   System.out.println();
	}
	static void srtf(){
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Number of Processes");
	   int n = sc.nextInt();
	   int arr[] = new int[n];
	   int burst[] = new int[n];
	   System.out.println("Enter Arrival times of Processes");
	   for(int i=0;i<n;i++){
	      arr[i] = sc.nextInt();
	   }
	   System.out.println("Enter Burst times of Processes");
	   for(int i=0;i<n;i++){
	      burst[i] = sc.nextInt();
	   }
	   ArrayList<P> l = new ArrayList<>();
	   for(int i=0;i<n;i++){
	      l.add(new P(arr[i],burst[i]));
	   }
	   int x = 0;
	   int comp[] = new int[n];
	   int tat[] = new int[n];
	   int wait[] = new int[n];
	   boolean f[] = new boolean[n];
	   while(true){
	      int ind = -1;
	      boolean flag = false;
	      int crt = Integer.MAX_VALUE; // criteria = Burst Time;
	      for(int i=0;i<n;i++){
	         if(!f[i] && l.get(i).a<=x && l.get(i).b<crt && l.get(i).b>0){
	            ind = i;
	            crt = l.get(i).b;
	            flag = true;
	         }
	         if(!f[i]){
	            flag = true;
	         }
	      }
	      if(!flag){
	         break;
	      }
	      if(ind==-1){
	         x++;
	         continue;
	      }
	      x++;
	      crt--;
	      if(crt==0){
	         f[ind]=true;
	         comp[ind] = x;
	         tat[ind] = comp[ind]-arr[ind];
   	      wait[ind] = tat[ind]-burst[ind];
	      }
	      l.set(ind,new P(l.get(ind).a,crt));
	   }
	   float tSum = 0,wtSum = 0;
	   System.out.println("Shortest Remaining Time First (SRTF) : ");
	   for(int i=0;i<n;i++){
	      tSum += tat[i];
	      wtSum += wait[i];
	      System.out.println("Process "+(i+1)+" :  Arr "+arr[i]+"  Burst "+burst[i]+"  comp "+comp[i]+"  TAT "+tat[i]+"  wait "+wait[i]);
	   }
	   System.out.println("Average TAT = "+(float)(tSum/n)+"  Average WAIT = "+(float)(wtSum/n));
	   System.out.println();
	}
	static void rr(){
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Number of Processes");
	   int n = sc.nextInt();
	   int arr[] = new int[n];
	   int burst[] = new int[n];
	   System.out.println("Enter Arrival times of Processes");
	   for(int i=0;i<n;i++){
	      arr[i] = sc.nextInt();
	   }
	   System.out.println("Enter Burst times of Processes");
	   for(int i=0;i<n;i++){
	      burst[i] = sc.nextInt();
	   }
	   System.out.println("Enter Time quantum");
	   int tq = sc.nextInt();
	   ArrayList<PR> l = new ArrayList<>();
	   for(int i=0;i<n;i++){
	      l.add(new PR(arr[i],burst[i],i));
	   }
	   Collections.sort(l,(a,b)->{
	      return a.a-b.a;
	   });
	   int x = 0;
	   int comp[] = new int[n];
	   int tat[] = new int[n];
	   int wait[] = new int[n];
	   boolean f[] = new boolean[n];
	   ArrayDeque<PR> q = new ArrayDeque<>();
	   HashSet<Integer> h = new HashSet<>();
	   while(true){
	      boolean flag = false;
	      int ind = -1;
	      int crt = -1;
	      for(int i=0;i<n;i++){
	         if(h.size()==n){
	            break;
	         }
	         if(!f[i] && l.get(i).a<=x  && !h.contains(i)){
	            q.offer(l.get(i));
	            h.add(i);
	         }
	        
	      }
	      int cnt = 0;
	      for(int i=0;i<n;i++){
	         if(f[i]){
	            cnt++;
	         }
	      }
	      if(cnt==n){
	         break;
	      }
	      if(q.size()==0){
	         x++;
	         continue;
	      }
	      PR p = q.poll();
	       if(!f[p.ind]){
	            crt = p.b;
	            ind = p.ind;
	            flag = true;
	         }
	      if(ind==-1){
	         x++;
	         continue;
	      }
	      if(crt>=tq){
	         crt-=tq;
	         x+=tq;
	      }
	      else if(crt<tq){
	         x+=(tq-crt);
	         crt=0;
	      }
	      if(crt==0){
	         f[ind]=true;
	         comp[ind] = x;
	         tat[ind] = comp[ind]-arr[ind];
   	      wait[ind] = tat[ind]-burst[ind];
	      }
	      l.set(ind,new PR(l.get(ind).a,crt,ind));
	      for(int i=0;i<n;i++){
	         if(h.size()==n){
	            break;
	         }
	         if(!f[i] && l.get(i).a<=x  && !h.contains(i)){
	            q.offer(l.get(i));
	            h.add(i);
	         }
	        
	      }
	      if(crt>0){
	         q.offer(l.get(ind));
	      }
	       cnt = 0;
	      for(int i=0;i<n;i++){
	         if(f[i]){
	            cnt++;
	         }
	      }
	      if(cnt==n){
	         break;
	      }
	   }
	   float tSum = 0,wtSum = 0;
	   System.out.println("Round Robin (RR) : ");
	   for(int i=0;i<n;i++){
	      tSum += tat[i];
	      wtSum += wait[i];
	      System.out.println("Process "+(i+1)+" :  Arr "+arr[i]+"  Burst "+burst[i]+"  comp "+comp[i]+"  TAT "+tat[i]+"  wait "+wait[i]);
	   }
	   System.out.println("Average TAT = "+(float)(tSum/n)+"  Average WAIT = "+(float)(wtSum/n));
	   System.out.println();
	}
	static void ps(){
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Number of Processes");
	   int n = sc.nextInt();
	   int arr[] = new int[n];
	   int burst[] = new int[n];
	   System.out.println("Enter Arrival times of Processes");
	   for(int i=0;i<n;i++){
	      arr[i] = sc.nextInt();
	   }
	   System.out.println("Enter Burst times of Processes");
	   for(int i=0;i<n;i++){
	      burst[i] = sc.nextInt();
	   }
	   ArrayList<P> l = new ArrayList<>();
	   for(int i=0;i<n;i++){
	      l.add(new P(arr[i],burst[i]));
	   }
	   int x = 0;
	   int comp[] = new int[n];
	   int tat[] = new int[n];
	   int wait[] = new int[n];
	   boolean f[] = new boolean[n];
	   while(true){
	      int ind = -1;
	      boolean flag = false;
	      int crt = Integer.MAX_VALUE; // criteria = Burst Time;
	      for(int i=n-1;i>=0;i--){
	         if(!f[i] && l.get(i).a<=x && l.get(i).b<crt && l.get(i).b>0){
	            ind = i;
	            crt = l.get(i).b;
	            flag = true;
	            break;
	         }
	         if(!f[i]){
	            flag = true;
	         }
	      }
	      if(!flag){
	         break;
	      }
	      if(ind==-1){
	         x++;
	         continue;
	      }
	      x++;
	      crt--;
	      if(crt==0){
	         f[ind]=true;
	         comp[ind] = x;
	         tat[ind] = comp[ind]-arr[ind];
   	      wait[ind] = tat[ind]-burst[ind];
	      }
	      l.set(ind,new P(l.get(ind).a,crt));
	   }
	   float tSum = 0,wtSum = 0;
	   System.out.println("Priority Scheduling (Preemptive) : ");
	   for(int i=0;i<n;i++){
	      tSum += tat[i];
	      wtSum += wait[i];
	      System.out.println("Process "+(i+1)+" :  Arr "+arr[i]+"  Burst "+burst[i]+"  comp "+comp[i]+"  TAT "+tat[i]+"  wait "+wait[i]);
	   }
	   System.out.println("Average TAT = "+(float)(tSum/n)+"  Average WAIT = "+(float)(wtSum/n));
	   System.out.println();
	}
	static void psN(){
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter Number of Processes");
	   int n = sc.nextInt();
	   int arr[] = new int[n];
	   int burst[] = new int[n];
	   System.out.println("Enter Arrival times of Processes");
	   for(int i=0;i<n;i++){
	      arr[i] = sc.nextInt();
	   }
	   System.out.println("Enter Burst times of Processes");
	   for(int i=0;i<n;i++){
	      burst[i] = sc.nextInt();
	   }
	   ArrayList<P> l = new ArrayList<>();
	   for(int i=0;i<n;i++){
	      l.add(new P(arr[i],burst[i]));
	   }
	   int x = 0;
	   int comp[] = new int[n];
	   int tat[] = new int[n];
	   int wait[] = new int[n];
	   boolean f[] = new boolean[n];
	   while(true){
	      int ind = -1;
	      boolean flag = false;
	      int crt = Integer.MAX_VALUE; // criteria = Burst Time;
	      for(int i=n-1;i>=0;i--){
	         if(!f[i] && l.get(i).a<=x && l.get(i).b<crt && l.get(i).b>0){
	            ind = i;
	            crt = l.get(i).b;
	            flag = true;
	            break;
	         }
	         if(!f[i]){
	            flag = true;
	         }
	      }
	      if(!flag){
	         break;
	      }
	      if(ind==-1){
	         x++;
	         continue;
	      }
	      x+=crt;
	      crt=0;
	      if(crt==0){
	         f[ind]=true;
	         comp[ind] = x;
	         tat[ind] = comp[ind]-arr[ind];
   	      wait[ind] = tat[ind]-burst[ind];
	      }
	      l.set(ind,new P(l.get(ind).a,crt));
	   }
	   float tSum = 0,wtSum = 0;
	   System.out.println("Priority Scheduling (Non-preemptive) : ");
	   for(int i=0;i<n;i++){
	      tSum += tat[i];
	      wtSum += wait[i];
	      System.out.println("Process "+(i+1)+" :  Arr "+arr[i]+"  Burst "+burst[i]+"  comp "+comp[i]+"  TAT "+tat[i]+"  wait "+wait[i]);
	   }
	   System.out.println("Average TAT = "+(float)(tSum/n)+"  Average WAIT = "+(float)(wtSum/n));
	   System.out.println();
	}
}
