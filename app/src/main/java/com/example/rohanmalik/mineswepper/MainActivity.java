package com.example.rohanmalik.mineswepper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
    LinearLayout mainSwepper;
    LinearLayout rowLayouts[];
    Blocks block[][];
    public static final int rows = 12;
    public static final int columns = 7;
    Random rowm;
    Random columnm;
    int rowmine;
    int columnmine;
    public final int mineNumber = 25;
    Boolean GameOver=false;
    Boolean GameWin=false;
    int winningCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainSwepper = (LinearLayout) findViewById(R.id.Main);
        rowLayouts = new LinearLayout[rows];
        block = new Blocks[rows][columns];
        createSwepper();
        assignValues();
        setMines();
        findNumber();
    }

    public void createSwepper() {
        for (int i = 0; i < rows; i++) {
            rowLayouts[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f);
            rowLayouts[i].setLayoutParams(params);
            mainSwepper.addView(rowLayouts[i]);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                block[i][j] = new Blocks(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
                block[i][j].setLayoutParams(params);
                block[i][j].setOnClickListener(this);
                block[i][j].setOnLongClickListener(this);
                block[i][j].setTextSize(20);
                rowLayouts[i].addView(block[i][j]);
            }
        }
    }

    public void setMines() {
        rowm = new Random();
        columnm = new Random();
        for(int i=0;i<mineNumber;i++) {
            rowmine = rowm.nextInt(rows);
            columnmine = columnm.nextInt(columns);
            block[rowmine][columnmine].mine=true;
        }
    }

    @Override
    public void onClick(View view) {
        Blocks blockSelected=(Blocks)view;
        blockSelected.visited=true;
        if(blockSelected.getMine()){
            blockSelected.setBackgroundResource(R.drawable.minesweeper);
            GameOver=true;
            if(GameOver)
            {
                Toast.makeText(MainActivity.this,"LOSSSSSSt",Toast.LENGTH_LONG).show();
                for(int i=0;i<rows;i++){
                    for(int j=0;j<columns;j++){
                        if(block[i][j].getMine()&& !block[i][j].flagged){
                            block[i][j].setBackgroundResource(R.drawable.minesweeper);
                        }
                        block[i][j].setEnabled(false);
                    }
                }
            }
        }
        else if(blockSelected.getMine()==false){
            blockSelected.setText(blockSelected.getNumber()+"");
            if(blockSelected.getNumber()==0){
                removeZeroes(blockSelected.getRowNumber(),blockSelected.getColumnNumber());
            }
        }
    }
    public void findNumber(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(!block[i][j].getMine()){
                    int count=0;
                    for(int p=i-1;p<=i+1;p++){
                        for(int q=j-1;q<=j+1;q++){
                            if(0<=p && p<rows && 0<=q && q<columns){
                                if(block[p][q].getMine()){
                                    count++;
                                }
                            }
                        }
                    }
                    block[i][j].setNumber(count);
                }
            }
        }
    }
    public void assignValues(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                block[i][j].setRowNumber(i);
                block[i][j].setColumnNumber(j);
            }
        }
    }
    public void removeZeroes(int xpos,int ypos)
    {
                for(int p=xpos-1;p<=xpos+1;p++){
                 for(int q=ypos-1;q<=ypos+1;q++){
                        if(0<=p && p<rows && 0<=q && q<columns ) {
                            if(block[p][q].getNumber()==0 && !block[p][q].visited){
                                block[p][q].visited=true;
                                removeZeroes(p,q);
                            }
                            block[p][q].setText(Integer.toString(block[p][q].getNumber()));
                        }

                    }

                }

    }

    @Override
    public boolean onLongClick(View view) {
        Blocks buttonSelected=(Blocks) view;
        buttonSelected.flagged=true;
        buttonSelected.setBackgroundResource(R.drawable.flag);
        if(buttonSelected.getMine()){
            winningCount++;
            if(winningCount==mineNumber){
                GameWin=true;
            }
        }
        if(GameWin){
            Toast.makeText(this,"Game Win",Toast.LENGTH_LONG).show();
            mainSwepper.removeAllViews();
        }
        buttonSelected.setEnabled(false);
        return true;
    }
}
