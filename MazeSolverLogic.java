import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MazeSolverLogic 
{
    int r=0, c=0;
    public static void main(String[] args) 
    {
        Scanner in=new Scanner(System.in);
        MazeSolverLogic obj=new MazeSolverLogic();
        obj.r=in.nextInt(); obj.c=in.nextInt();
        int[][] grid=new int[obj.r][obj.c];
        for(int i=0; i<obj.r; i++)
        {
            for(int j=0; j<obj.c; j++)
            {
                grid[i][j]=in.nextInt();
            }
        }
        System.out.println("Rows and Columns are 1 indexed");
        int spx=in.nextInt(), spy=in.nextInt(), epx=in.nextInt(), epy=in.nextInt();
        if(obj.findPath(grid, spx-1, spy-1, epx-1, epy-1, new ArrayList<>(), new HashMap<>()))
        {
            System.out.println("Path Exists.");
        }
        else
        {
            System.out.println("No Path Exists.");
        }
        in.close();
    }

    boolean findPath(int[][] grid, int x, int y, int epx, int epy, ArrayList<Integer> q, HashMap<Integer, Boolean> v)
    {
        q.add(x*c+y+1);
        while(q.size()>0)
        {
            int cxy=q.remove(0);
            v.put(cxy, true);
            int cx=(cxy%c==0)?cxy/c-1:cxy/c;
            int cy=(cxy%c==0)?c-1:cxy%c-1; 
            System.out.println(cx+" - "+cy);
            if(cx==epx && cy==epy)
            {
                return(true);
            }
            else
            {
                if(cx>=0 && cx<r && cy+1>=0 && cy+1<c && grid[cx][cy+1]==0) // right cell
                {
                    if(!v.getOrDefault((cx)*c+(cy+1)+1, false))
                    {
                        if(!q.contains((cx)*c+(cy+1)+1)) q.add((cx)*c+(cy+1)+1);
                    }
                }
                if(cx+1>=0 && cx+1<r && cy>=0 && cy<c && grid[cx+1][cy]==0) // bottom cell
                {
                    if(!v.getOrDefault((cx+1)*c+(cy)+1, false))
                    {
                        if(!q.contains((cx+1)*c+(cy)+1)) q.add((cx+1)*c+(cy)+1);
                    }
                }
                if(cx>=0 && cx<r && cy-1>=0 && cy-1<c && grid[cx][cy-1]==0) // left cell
                {
                    if(!v.getOrDefault((cx)*c+(cy-1)+1, false))
                    {
                        if(!q.contains((cx)*c+(cy-1)+1)) q.add((cx)*c+(cy-1)+1);
                    }
                }
                if(cx-1>=0 && cx-1<r && cy>=0 && cy<c && grid[cx-1][cy]==0) // top cell
                {
                    if(!v.getOrDefault((cx-1)*c+(cy)+1, false))
                    {
                        if(!q.contains((cx-1)*c+(cy)+1))q.add((cx-1)*c+(cy)+1);
                    }
                }
            }
        }
        return(false);
    }
}
/*
Sample mazes: 1 - block, 0 - empty

10 10
0 0 1 1 1 1 1 1 1 1
1 0 0 0 0 0 1 1 1 1
1 0 1 1 1 1 1 1 1 1
1 0 0 0 0 1 0 0 0 1
1 0 0 0 0 1 0 0 0 1
1 0 1 1 1 1 0 1 0 1
1 0 0 0 0 0 0 1 0 1
1 0 0 1 1 1 0 1 0 1
1 0 0 0 1 1 0 1 0 1
1 1 1 1 1 1 1 1 0 0
1 1
10 10

10 10
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 1 1 1 1 1 1 1 1 1
0 0 0 0 0 0 0 0 0 0
1 1
10 10

10 10
0 1 1 1 1 1 1 1 1 1
0 1 1 1 0 0 0 0 1 1
0 1 1 1 0 1 1 1 1 1
0 1 0 0 0 0 0 1 1 1
0 1 0 1 1 1 0 1 1 1
0 1 0 1 1 1 0 1 1 1
0 1 0 0 0 0 0 0 1 1
0 1 1 1 1 1 0 1 1 1
0 1 1 1 1 1 0 1 1 1
0 0 0 0 0 0 0 1 1 1
1 1
10 10 

*/