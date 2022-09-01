import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MazeGUI implements MouseListener
{
    int r=0, c=0;
    JFrame frame1=new JFrame();
    JTextField rows=new JTextField(), columns=new JTextField();
    JButton submit=new JButton();
    JPanel panel=new JPanel();
    JTextArea directions=new JTextArea();

    String dtext;

    public void MazeDimensions()
    {
        rows.setPreferredSize(new Dimension(200, 50));
        rows.setBackground(Color.BLACK);
        rows.setForeground(Color.GREEN);
        rows.setCaretColor(Color.GREEN);
        rows.setHorizontalAlignment(JTextField.CENTER);
        rows.setText("Enter nof Rows");
        rows.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

        columns.setPreferredSize(new Dimension(200, 50));
        columns.setBackground(Color.BLACK);
        columns.setForeground(Color.GREEN);
        columns.setCaretColor(Color.GREEN);
        columns.setHorizontalAlignment(JTextField.CENTER);
        columns.setText("Enter nof Columns");
        columns.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

        dtext="•Click options to set start point, end point, barricades.\n\n";
        dtext+="•To remove barricades, deactivate the barricades option and click on cells to remove barricades.\n\n";
        dtext+="•Resize window to your preference. ENJOY !!!";
        
        directions.setPreferredSize(new Dimension(405, 100));
        directions.setBackground(Color.BLACK);
        directions.setForeground(Color.WHITE);
        directions.setText(dtext);
        directions.setWrapStyleWord(true);
        directions.setLineWrap(true);

        submit.setPreferredSize(new Dimension(405, 50));
        submit.setOpaque(true);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.CYAN);
        submit.setText("SUBMIT");
        submit.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
        submit.addMouseListener(this);


        panel.setPreferredSize(new Dimension(450, 250));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // (alignment, height gap, width gap)
        panel.setBackground(Color.BLACK);

        panel.add(rows); panel.add(columns); panel.add(directions); panel.add(submit);

        frame1.setTitle("Maze Dimensions");
        frame1.add(panel);
        frame1.pack();
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
    }

    JPanel cells_panel=new JPanel(), buttons_panel=new JPanel();
    JPanel[][] cells;
    JLabel info_label=new JLabel(), result_label=new JLabel(), time_label=new JLabel();
    int cell_length=15;
    JButton barricade=new JButton(), start_point=new JButton(), end_point=new JButton();
    JButton clear_barricade=new JButton(), clear_maze=new JButton(), run=new JButton();
    JButton exit=new JButton();

    int height=0, width=0;

    Color bgc=new Color(0x2f2e2d);
    Color spc=new Color(0xec5127); Color epc=new Color(0x40b22a);
    Color bcdc=new Color(0x8a2cd8); Color cpc=new Color(0x979499);
    Color rc=new Color(0x28dcf1);
    Color pc=Color.BLACK;
    Color eb=Color.YELLOW;

    public void Maze()
    {
        info_label.setPreferredSize(new Dimension(200, 30));
        info_label.setHorizontalAlignment(JLabel.CENTER);
        info_label.setForeground(Color.WHITE);
        info_label.setText("Rows:- "+r+" | Columns:- "+c);

        result_label.setPreferredSize(new Dimension(225, 30));
        result_label.setHorizontalAlignment(JLabel.CENTER);
        result_label.setForeground(Color.WHITE);
        result_label.setText("PATH EXISTS | NO PATH EXISTS");

        time_label.setPreferredSize(new Dimension(200, 30));
        time_label.setHorizontalAlignment(JLabel.CENTER);
        time_label.setForeground(Color.WHITE);
        time_label.setText("TIME TAKEN: ");

        cells_panel.setPreferredSize(new Dimension((c+1)*cell_length, (r+1)*cell_length));
        cells_panel.setLayout(new GridLayout(r, c, 1, 1));
        cells_panel.setBackground(cpc);
        cells=new JPanel[r][c];
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                cells[i][j]=new JPanel();
                cells[i][j].setPreferredSize(new Dimension(cell_length, cell_length));
                cells[i][j].setOpaque(true);
                cells[i][j].setBackground(Color.WHITE);
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells[i][j].addMouseListener(this);
                cells_panel.add(cells[i][j]);
            }
        }

        start_point.setPreferredSize(new Dimension(200, 30));
        start_point.setOpaque(true);
        start_point.setBackground(pc);
        start_point.setForeground(spc);
        start_point.setBorder(BorderFactory.createLineBorder(spc));
        start_point.setHorizontalAlignment(JButton.CENTER);
        start_point.setText("SET START POINT");
        start_point.addMouseListener(this);

        end_point.setPreferredSize(new Dimension(200, 30));
        end_point.setOpaque(true);
        end_point.setBackground(pc);
        end_point.setForeground(epc);
        end_point.setBorder(BorderFactory.createLineBorder(epc));
        end_point.setHorizontalAlignment(JButton.CENTER);
        end_point.setText("SET END POINT");
        end_point.addMouseListener(this);

        barricade.setPreferredSize(new Dimension(200, 30));
        barricade.setOpaque(true);
        barricade.setBackground(pc);
        barricade.setForeground(bcdc);
        barricade.setBorder(BorderFactory.createLineBorder(bcdc));
        barricade.setHorizontalAlignment(JButton.CENTER);
        barricade.setText("ADD BARRICADE");
        barricade.addMouseListener(this);

        clear_barricade.setPreferredSize(new Dimension(200, 30));
        clear_barricade.setOpaque(true);
        clear_barricade.setBackground(pc);
        clear_barricade.setForeground(bcdc);
        clear_barricade.setBorder(BorderFactory.createLineBorder(bcdc));
        clear_barricade.setHorizontalAlignment(JButton.CENTER);
        clear_barricade.setText("CLEAR BARRICADE");
        clear_barricade.addMouseListener(this);

        clear_maze.setPreferredSize(new Dimension(200, 30));
        clear_maze.setOpaque(true);
        clear_maze.setBackground(pc);
        clear_maze.setForeground(bcdc);
        clear_maze.setBorder(BorderFactory.createLineBorder(bcdc));
        clear_maze.setHorizontalAlignment(JButton.CENTER);
        clear_maze.setText("CLEAR MAZE");
        clear_maze.addMouseListener(this);

        run.setPreferredSize(new Dimension(200, 30));
        run.setOpaque(true);
        run.setBackground(pc);
        run.setForeground(rc);
        run.setBorder(BorderFactory.createLineBorder(rc));
        run.setHorizontalAlignment(JButton.CENTER);
        run.setText("RUN");
        run.addMouseListener(this);

        height=(int)((r)*1.2*cell_length)+50;
        if(height<400)
        {
            height=450;
        }
        width=(int)(c*cell_length*1.2)+220;
        if(width<540)
        {
            width=575;
        }

        exit.setPreferredSize(new Dimension(200, 30));
        exit.setOpaque(true);
        exit.setBackground(pc);
        exit.setForeground(eb);
        exit.setBorder(BorderFactory.createLineBorder(eb));
        exit.setHorizontalAlignment(JButton.CENTER);
        exit.setText("EXIT");
        exit.addMouseListener(this);

        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(pc);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // (alignment, height gap, width gap)

        buttons_panel.setPreferredSize(new Dimension(270, 400));
        buttons_panel.setBackground(pc);
        buttons_panel.setLayout(new FlowLayout(1, 10, 10));

        buttons_panel.add(info_label);
        buttons_panel.add(start_point); buttons_panel.add(end_point);
        buttons_panel.add(barricade);
        buttons_panel.add(clear_barricade); buttons_panel.add(clear_maze);
        buttons_panel.add(run);
        buttons_panel.add(result_label);
        buttons_panel.add(time_label);
        buttons_panel.add(exit);

        panel.add(cells_panel);
        panel.add(buttons_panel);

        frame1.setTitle("Maze");
        frame1.add(panel);
        frame1.pack();
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(true);
    }

    public void runMaze()
    {
        int[][] grid=new int[r][c];
        if(sp_set && ep_set)
        {
            for(int i=0; i<r; i++)
            {
                for(int j=0; j<c; j++)
                {
                    if(cells[i][j].getBackground()==bcdc)
                    {
                        grid[i][j]=1; // 1 - barrier
                    }
                    else
                    {
                        grid[i][j]=0; // 0 - empty space
                    }
                }
            }
            long start=System.nanoTime();
            boolean check=findPath(grid, spx, spy, epx, epy, new ArrayList<>(), new HashMap<>());
            double time=(System.nanoTime()-start)/1000000000.0;
            time=((int)(time*1000000))/1000000.0;
            if(check)
            {
                result_label.setText("PATH EXISTS");
            }
            else
            {
                result_label.setText("NO PATH EXISTS");
            }
            time_label.setText("TIME TAKEN:- "+time+" secs");
        }
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
            // System.out.println(cx+"|"+cy);
            if(cells[cx][cy].getBackground()==Color.WHITE)
            {
                cells[cx][cy].setBackground(Color.ORANGE);
            }
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
                        if(!q.contains((cx-1)*c+(cy)+1)) q.add((cx-1)*c+(cy)+1);
                    }
                }
            }
        }
        return(false);
    }

    boolean bcd_active=false, sp_active=false, ep_active=false; // button state - active | inactive
    boolean sp_set=false, ep_set=false; // start point, end point state - Set or Not Set
    int spx=-1, spy=-1, epx=-1, epy=-1; // start point coordinates, end point coordinates

    @Override
    public void mouseClicked(MouseEvent me)
    {
        if(me.getSource()==submit)
        {
            if(checkForInteger(rows.getText()) && checkForInteger(columns.getText()))
            {
                r=Integer.valueOf(rows.getText());
                c=Integer.valueOf(columns.getText());
                panel.removeAll();
                frame1.getContentPane().removeAll();
                frame1.getContentPane().repaint();
                Maze();
            }
        }
        if(me.getSource()==barricade)
        {
            if(bcd_active==false)
            {
                bcd_active=true;
                barricade.setBackground(bcdc); barricade.setForeground(pc);
            }
            else
            {
                bcd_active=false;
                barricade.setBackground(pc); barricade.setForeground(bcdc);
            }
        }
        if(me.getSource()==start_point)
        {
            if(sp_active==false)
            {
                sp_active=true;
                start_point.setBackground(spc); start_point.setForeground(pc);
            }
            else
            {
                sp_active=false;
                start_point.setBackground(pc); start_point.setForeground(spc);
            }
        }
        if(me.getSource()==end_point)
        {
            if(ep_active==false)
            {
                ep_active=true;
                end_point.setBackground(epc); end_point.setForeground(pc);
            }
            else
            {
                ep_active=false;
                end_point.setBackground(epc); end_point.setForeground(pc);
            }
        }
        if(me.getSource()==clear_barricade)
        {
            for(int i=0; i<r; i++)
            {
                for(int j=0; j<c; j++)
                {
                    if(cells[i][j].getBackground()==bcdc)
                    {
                        cells[i][j].setBackground(Color.WHITE);
                    }
                }
            }
        }
        if(me.getSource()==clear_maze)
        {
            for(int i=0; i<r; i++)
            {
                for(int j=0; j<c; j++)
                {
                    cells[i][j].setBackground(Color.WHITE);
                    sp_set=ep_set=false;
                }
            }
        }
        if(bcd_active==false)
        {
            for(int i=0; i<r; i++)
            {
                for(int j=0; j<c; j++)
                {
                    if(me.getSource()==cells[i][j])
                    {
                        cells[i][j].setBackground(Color.WHITE);
                    }
                }
            }
        }
        if(sp_active)
        {
            if(sp_set==false)
            {
                for(int i=0; i<r; i++)
                {
                    for(int j=0; j<c; j++)
                    {
                        if(me.getSource()==cells[i][j])
                        {
                            if(cells[i][j].getBackground()==Color.WHITE)
                            {
                                cells[i][j].setBackground(spc);
                                spx=i; spy=j;
                                sp_set=true;
                                break;
                            }
                        }
                    }
                }
            }
            else
            {
                if(me.getSource()==cells[spx][spy])
                {
                    cells[spx][spy].setBackground(Color.WHITE);
                    spx=-1; spy=-1; sp_set=false;
                }
            }
        }
        if(ep_active)
        {
            if(ep_set==false)
            {
                for(int i=0; i<r; i++)
                {
                    for(int j=0; j<c; j++)
                    {
                        if(me.getSource()==cells[i][j])
                        {
                            if(cells[i][j].getBackground()==Color.WHITE)
                            {
                                cells[i][j].setBackground(epc);
                                epx=i; epy=j;
                                ep_set=true;
                                break;
                            }
                        }
                    }
                }
            }
            else
            {
                if(me.getSource()==cells[epx][epy])
                {
                    cells[epx][epy].setBackground(Color.WHITE);
                    epx=-1; epy=-1; ep_set=false;
                }
            }
        }
        if(me.getSource()==run)
        {
            if(sp_set && ep_set)
            {
                runMaze();
            }
            else
            {
                if(sp_set==false && ep_set==true)
                {
                    result_label.setText("START POINT NOT SET");
                }
                else if(ep_set==false && sp_set==true)
                {
                    result_label.setText("END POINT NOT SET");
                }
                else
                {
                    result_label.setText("START POINT, END POINT NOT SET");
                }
            }
        }
        if(me.getSource()==exit)
        {
            frame1.dispose();
        }
    }
    public void mouseEntered(MouseEvent me)
    {
        if(me.getSource()==submit)
        {
            String row_text=rows.getText(), column_text=columns.getText();
            if(checkForInteger(row_text) && checkForInteger(column_text))
            {
                submit.setForeground(Color.YELLOW);
                submit.setText("SUBMIT");
                submit.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            }
            else
            {
                submit.setText("SUBMIT");
            }
        }
        if(me.getSource()==start_point)
        {
            start_point.setBackground(spc); start_point.setForeground(pc);
        }
        if(me.getSource()==end_point)
        {
            end_point.setBackground(epc); end_point.setForeground(pc);
        }
        if(me.getSource()==barricade)
        {
            barricade.setBackground(bcdc); barricade.setForeground(pc);
        }
        if(me.getSource()==clear_barricade)
        {
            clear_barricade.setBackground(bcdc); clear_barricade.setForeground(pc);
        }
        if(me.getSource()==clear_maze)
        {
            clear_maze.setBackground(bcdc); clear_maze.setForeground(pc);
        }
        if(bcd_active)
        {
            for(int i=0; i<r; i++)
            {
                for(int j=0; j<c; j++)
                {
                    if(me.getSource()==cells[i][j] && cells[i][j].getBackground()==Color.WHITE)
                    {
                        cells[i][j].setBackground(bcdc);

                    }
                }
            }
        }
        if(sp_active)
        {
            if(sp_set==false)
            {
                for(int i=0; i<r; i++)
                {
                    for(int j=0; j<c; j++)
                    {
                        if(me.getSource()==cells[i][j])
                        {
                            if(cells[i][j].getBackground()==Color.WHITE)
                            {
                                cells[i][j].setBackground(spc);
                            }
                        }
                    }
                }
            }
        }
        if(ep_active)
        {
            if(ep_set==false)
            {
                for(int i=0; i<r; i++)
                {
                    for(int j=0; j<c; j++)
                    {
                        if(me.getSource()==cells[i][j])
                        {
                            if(cells[i][j].getBackground()==Color.WHITE)
                            {
                                cells[i][j].setBackground(epc);
                            }
                        }
                    }
                }
            }
        }
        if(me.getSource()==run)
        {
            run.setBackground(rc); run.setForeground(pc);
        }
        if(me.getSource()==exit)
        {
            exit.setBackground(eb); exit.setForeground(pc);
        }
    }
    public void mouseExited(MouseEvent me)
    {
        if(me.getSource()==submit)
        {
            submit.setForeground(Color.CYAN);
            submit.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
        }
        if(me.getSource()==start_point)
        {
            if(sp_active==false)
            {
                start_point.setBackground(pc); start_point.setForeground(spc);
            }
        }
        if(me.getSource()==end_point)
        {
            if(ep_active==false)
            {
                end_point.setBackground(pc); end_point.setForeground(epc);
            }
        }
        if(me.getSource()==barricade)
        {
            if(bcd_active==false)
            {
                barricade.setBackground(pc); barricade.setForeground(bcdc);
            }
        }
        if(me.getSource()==clear_barricade)
        {
            clear_barricade.setBackground(pc); clear_barricade.setForeground(bcdc);
        }
        if(me.getSource()==clear_maze)
        {
            clear_maze.setBackground(pc); clear_maze.setForeground(bcdc);
        }
        if(sp_active)
        {
            for(int i=0; i<r; i++)
            {
                for(int j=0; j<c; j++)
                {
                    if(me.getSource()==cells[i][j])
                    {
                        if(cells[i][j].getBackground()!=bcdc && sp_set==false)
                        {
                            cells[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
            }
        }
        if(ep_active)
        {
            for(int i=0; i<r; i++)
            {
                for(int j=0; j<c; j++)
                {
                    if(me.getSource()==cells[i][j])
                    {
                        if(cells[i][j].getBackground()!=bcdc && ep_set==false)
                        {
                            cells[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
            }
        }
        if(me.getSource()==exit)
        {
            exit.setBackground(pc); exit.setForeground(eb);
        }
        if(me.getSource()==run)
        {
            run.setBackground(pc); run.setForeground(rc);
        }
    }
    public void mousePressed(MouseEvent me){}
    public void mouseReleased(MouseEvent me){}

    boolean checkForInteger(String text)
    {
        for(int i=0; i<text.length(); i++)
        {
            if(text.charAt(i)<'0' || text.charAt(i)>'9')
            {
                return(false);
            }
        }
        return(true);
    }

    public static void main(String[] args)
    {
        MazeGUI maze=new MazeGUI();
        maze.MazeDimensions();
    }
}