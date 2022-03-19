import java.util.ArrayList;
import java.util.List;

public class Mouse {
    int[][] strategy=new int[][]{
            {-15, 10, -5},
            {-10, -5, -5},
            {15, 15, -15},
            {15, -5, 5},
            {5, -5, 10},
            {0, -10, 15}};

    String[] name = new String[]{"胜利", "失败", "安妮", "围巾", "草帽", "皇冠"};
    int[] money = new int[]{0, 0, 0, 1000, 2000, 5000};
    int[] time = new int[]{0, 0, 5, 0, 0, 0};

    boolean[][][] state = new boolean[101][101][101];
    List<Integer> solution = new ArrayList<>();

    public void search(){
        iter(100,0,100, 0);
    }

    public boolean iter(int s1_, int s2_, int s3_, int count){
        if(count>10){
            return false;
        }
        for(int i=0;i<6;i++){
            int s1 = s1_ + strategy[i][0];
            int s2 = s2_ + strategy[i][1];
            int s3 = s3_ + strategy[i][2];
            s1 = Math.min(100, s1);
            s2 = Math.min(100, s2);
            s3 = Math.min(100, s3);
            s1 = Math.max(0, s1);
            s2 = Math.max(0, s2);
            s3 = Math.max(0, s3);
            if(s1 == 0){
                continue;
            }

            if(s1==50&&s2==50&&s3==50){
                solution.add(i);
                return true;
            }else {
                if(!state[s1][s2][s3]){
                    state[s1][s2][s3] = true;
                    boolean res = iter(s1, s2, s3, count+1);
                    solution.add(i);
                    if(res){
                        System.out.println(s1 + " " + s2 + " " + s3);
                        return true;
                    }else {
                        solution.remove(solution.size()-1);
                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args){
        Mouse mouse = new Mouse();
        mouse.search();
        int totalMoney=0;
        int totalTime = 0;
        for(int index = mouse.solution.size()-1;index>=0;index--){
            int i = mouse.solution.get(index);
            totalMoney+=mouse.money[i];
            totalTime+=mouse.time[i];
            System.out.print(mouse.name[i]+" ");
        }
        System.out.println();
        System.out.println("洛克贝："+totalMoney);
        System.out.println("时间："+totalTime+" min");
    }
}
