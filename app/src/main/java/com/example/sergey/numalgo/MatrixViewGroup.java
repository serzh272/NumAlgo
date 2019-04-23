package com.example.sergey.numalgo;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.mymath.Fraction;
import com.example.mymath.Matrix;

public class MatrixViewGroup extends ViewGroup {
    private static final String TAG_MATRIX_VIEW_GROUP = "matrix_view_group";
    private int rows = 3;
    private int columns = 3;
    private int spacing = 0;
    private Matrix mMatrix;
    private int cellColor;
    float cellSize;
    int idMatrixViewGroup = 0;
    EditText[][] mEditTexts;
    ImageButton btn_up;
    ImageButton btn_down;
    ImageButton btn_left;
    ImageButton btn_right;
    private int button_thickness = 80;

    public MatrixViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
    }

    public MatrixViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
    }

    public MatrixViewGroup(Context context) {
        super(context);
    }

    public void setId(int id){
        idMatrixViewGroup = id;
    }

    public void setNumRows(int id, int nRows){
        setNumRows(nRows);
        MatrixLab.get(getContext()).setMatrixItem(id, mMatrix);
    }

    public void setNumRows(int nRows){
        if (nRows>10){
            nRows = 10;
        }
        updateMatr();
        if (nRows < rows){
            for (int i = 0;i<rows - nRows;i++) {
                mMatrix.deleteRow(mMatrix.getNumRows() - 1);
            }
        }
        if (nRows > rows){
                mMatrix.addRows(nRows - rows);
        }
        SetMatrix(mMatrix);
    }

    public void setNumColumns(int id, int nCols){
        setNumColumns(nCols);
        MatrixLab.get(getContext()).setMatrixItem(id, mMatrix);
    }

    public void setNumColumns(int nCols){
        if (nCols>10){
            nCols = 10;
        }
        updateMatr();
        if (nCols < columns){
            for (int i = 0;i<columns - nCols;i++) {
                mMatrix.deleteColumn(mMatrix.getNumColumns() - 1);
            }
        }
        if (nCols > columns){
            mMatrix.addColumns(nCols - columns);
        }
        SetMatrix(mMatrix);
    }
    public  void updateMatr(){
        SetMatrix(getMatr());
    }
    public void SetMatrix(Matrix matrix){
        removeAllViewsInLayout();
        mMatrix = matrix;
        columns = matrix.getNumColumns();
        rows = matrix.getNumRows();
        initTextViews(getContext());
        initButtons(getContext());
        invalidate();
        requestLayout();
    }

    private void initTextViews(Context context) {
        mEditTexts = new EditText[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mEditTexts[i][j] = new EditText(context);
                if (!mMatrix.getMatrixElement(i, j).Write().equals("0")) {
                    mEditTexts[i][j].setText(mMatrix.getMatrixElement(i, j).Write());
                } else {
                    mEditTexts[i][j].setText("");
                }
                mEditTexts[i][j].setTag("r" + i + "_c" + j);
                addView(mEditTexts[i][j]);
            }
        }
    }

    public Matrix getMatr(){
        Matrix m = new Matrix(rows, columns);
        for (int i =0; i< rows;i++){
            for (int j = 0; j < columns;j++){
                Fraction fr = new Fraction();
                fr.Read(mEditTexts[i][j].getText().toString());
                m.setMatrixElement(i,j,fr);
            }
        }
        return m;
    }

    private void initAttrs(final Context context, AttributeSet attrs) {
        Log.d(TAG_MATRIX_VIEW_GROUP,"initAttrs Run (str:137)");
        cellColor = R.color.colorM1;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MatrixLayout, 0, 0);
        try {
            rows = a.getInt(R.styleable.MatrixLayout_rows, 2);
            columns = a.getInt(R.styleable.MatrixLayout_columns, 3);
            spacing = a.getDimensionPixelSize(R.styleable.MatrixLayout_spacing, 0);
        } finally {
            a.recycle();
        }
        if (mMatrix == null){
            mMatrix = new Matrix(rows,columns);
        }

        initTextViews(context);
        initButtons(context);
    }

    private void initButtons(Context context) {
        btn_up = new ImageButton(context);
        int pad = btn_up.getPaddingTop();
        btn_up.setImageResource(R.drawable.ic_expand_less_black_24dp);
        btn_up.setScaleType(ImageView.ScaleType.FIT_CENTER);
        btn_up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumRows(idMatrixViewGroup, rows+1);
            }
        });
        btn_down = new ImageButton(context);
        btn_down.setImageResource(R.drawable.ic_expand_more_black_24dp);
        btn_down.setScaleType(ImageView.ScaleType.FIT_CENTER);
        btn_down.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumRows(idMatrixViewGroup,rows-1);
            }
        });
        btn_left = new ImageButton(context);
        btn_left.setImageResource(R.drawable.ic_chevron_left_black_24dp);
        btn_left.setScaleType(ImageView.ScaleType.FIT_CENTER);
        btn_left.setPadding(pad,0,pad,0);
        btn_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumColumns(idMatrixViewGroup,columns-1);
            }
        });
        btn_right = new ImageButton(context);
        btn_right.setImageResource(R.drawable.ic_chevron_right_black_24dp);
        btn_right.setScaleType(ImageView.ScaleType.FIT_CENTER);
        btn_right.setPadding(pad,0,pad,0);
        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumColumns(idMatrixViewGroup,columns+1);
            }
        });
        addView(btn_up);
        addView(btn_down);
        addView(btn_left);
        addView(btn_right);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MatrixViewGroup.LayoutParams(getContext(),attrs);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MatrixViewGroup.LayoutParams;
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MatrixViewGroup.LayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MatrixLayoutParams();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightM = heightMeasureSpec;
        int widthM = widthMeasureSpec;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            widthMeasureSpec = getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec);
            heightMeasureSpec = Math.min(widthMeasureSpec,getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
        }else {
            heightMeasureSpec = getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec);
            widthMeasureSpec = heightMeasureSpec;
        }

        button_thickness = heightMeasureSpec/10;
        int cellHeight = (heightMeasureSpec-spacing*(rows-1)-getPaddingTop()-getPaddingBottom()-button_thickness*2)/rows;
        int cellWidth = (widthMeasureSpec-spacing*(columns-1)-getPaddingLeft()-getPaddingRight()-button_thickness*2)/columns;
        cellSize = Math.min(cellHeight,cellWidth);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            widthMeasureSpec = getDefaultSize(getSuggestedMinimumWidth(),widthM);
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int leftPadding = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()-(columns)*(int)cellSize - (columns-1)*spacing-button_thickness*2)/2;
        int topPadding = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom()-(rows)*(int)cellSize - (rows-1)*spacing-button_thickness*2)/2;
        //int leftPadding = 0;
        for (int g =0; g< rows;g++){
            for (int j = 0; j < columns;j++){
                int left = getPaddingLeft()+j*((int)cellSize+spacing) + leftPadding+button_thickness;
                int right = getPaddingLeft()+(j+1)*(int)cellSize + j*spacing + leftPadding+button_thickness;
                int top = getPaddingTop()+g*((int)cellSize + spacing) + topPadding+button_thickness;
                int bottom = getPaddingTop()+(g+1)*(int)cellSize + g*spacing + topPadding+button_thickness;
                LayoutParams lp = mEditTexts[g][j].getLayoutParams();
                mEditTexts[g][j].setMinWidth(0);
                mEditTexts[g][j].setPadding(0,0,0,0);
                mEditTexts[g][j].setMinHeight(0);
                mEditTexts[g][j].setHeight((int)cellSize);
                mEditTexts[g][j].setPadding(0,0,0,0);
                float ts = cellSize/10;
                mEditTexts[g][j].setTextSize(ts);
                mEditTexts[g][j].setBackgroundResource(cellColor);
                mEditTexts[g][j].setGravity(Gravity.CENTER);
                mEditTexts[g][j].layout(left, top, right, bottom);
            }

            btn_left.layout(getPaddingLeft()+ leftPadding,getPaddingTop() + topPadding+button_thickness,getPaddingLeft()+ leftPadding + button_thickness,getPaddingTop()+(rows)*(int)cellSize + (rows-1)*spacing + topPadding+button_thickness);
            btn_right.layout(getMeasuredWidth()-leftPadding-getPaddingRight()-button_thickness,getPaddingTop() + topPadding+button_thickness,getMeasuredWidth()-leftPadding-getPaddingRight(),getPaddingTop()+(rows)*(int)cellSize + (rows-1)*spacing + topPadding+button_thickness);
            btn_up.layout(getPaddingLeft() + leftPadding+button_thickness,getPaddingTop()+ topPadding,getPaddingLeft()+(columns)*(int)cellSize + (columns-1)*spacing + leftPadding+button_thickness,getPaddingTop()+ topPadding + button_thickness);
            btn_down.layout(getPaddingLeft() + leftPadding+button_thickness,getMeasuredHeight()-topPadding-getPaddingBottom()-button_thickness,getPaddingRight()+(columns)*(int)cellSize + (columns-1)*spacing + leftPadding+button_thickness,getMeasuredHeight()-topPadding-getPaddingBottom());
        }
    }

    public void setCellColor(int resId){
        cellColor = resId;
        for (int g =0; g< rows;g++){
            for (int j = 0; j < columns;j++){
                mEditTexts[g][j].setBackgroundResource(resId);
            }
        }
        invalidate();
    }

    public static class MatrixLayoutParams extends ViewGroup.LayoutParams {
        int columns = 1;
        int rows = 1;
        public MatrixLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs,R.styleable.MatrixLayout);
            columns = a.getInt(R.styleable.MatrixLayout_columns,1);
            rows = a.getInt(R.styleable.MatrixLayout_rows,1);
            a.recycle();
        }
        public MatrixLayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            if (source instanceof MatrixLayoutParams){
                MatrixLayoutParams matrixLayoutParams = (MatrixLayoutParams)source;
                width = matrixLayoutParams.width;
                height = matrixLayoutParams.height;
            }
        }
        public MatrixLayoutParams(){
            this(MATCH_PARENT,MATCH_PARENT);
        }

        public MatrixLayoutParams(int width, int height) {
            super(width, height);
        }
    }
}
