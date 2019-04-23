package com.example.mymath;

import android.widget.Toast;
import java.lang.reflect.Array;

public class Matrix {
    private Fraction[][] A;
    private int numColumns;
    private int numRows;

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        if (numColumns <1){
            Exception ex = new Exception("Incorrect number of Columns");
        }else {
            this.numColumns = numColumns;
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        if (numRows <1){
            Exception ex = new Exception("Incorrect number of Rows");
        }else {
            this.numRows = numRows;
        }
    }

    public Matrix() {
        setNumRows(3);
        setNumColumns(3);
        A = new Fraction[numRows][numColumns];
        for (int i = 0;i< numRows;i++){
            for (int j = 0;j < numColumns;j++){
                A[i][j] = new Fraction();
            }
        }
    }

    public  Matrix(int nRows, int nCols){
        setNumRows(nRows);
        setNumColumns(nCols);
        A = new Fraction[nRows][nCols];
        for (int i = 0;i< nRows;i++){
            for (int j = 0;j < nCols;j++){
                A[i][j] = new Fraction();
            }
        }
    }

    public void setMatrixElement(int row, int col, Fraction fr){
        if (row>=getNumRows()||col>=getNumColumns()){
            Exception ex = new Exception();

        }else{
            A[row][col] = fr;
        }
    }

    public Fraction getMatrixElement(int row, int col){
        return A[row][col];
    }

    public void addColumns(int nColumns){

        Fraction[][] B = new Fraction[numRows][numColumns + nColumns];
        for (int i = 0;i<numRows;i++){
            for (int j = 0; j<numColumns; j++){
                B[i][j] = A[i][j];
            }
            for (int j = numColumns; j<numColumns + nColumns; j++){
                B[i][j] = new Fraction();
            }
        }
        A = B;
        B = null;
        setNumColumns(numColumns + nColumns);
    }

    public  void deleteRow(int nRow){
        if (numRows > 1) {
            Fraction[][] B = new Fraction[numRows - 1][numColumns];
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    if (i < nRow) {
                        B[i][j] = A[i][j];
                    }
                    if (i > nRow) {
                        B[i - 1][j] = A[i][j];
                    }
                }
            }
            A = B;
            B = null;
            setNumRows(numRows - 1);
        }
    }

    public  void deleteColumn(int nColumn){
        if (numColumns > 1) {
            Fraction[][] B = new Fraction[numRows][numColumns-1];
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    if (j < nColumn) {
                        B[i][j] = A[i][j];
                    }
                    if (j > nColumn) {
                        B[i][j-1] = A[i][j];
                    }
                }
            }
            A = B;
            B = null;
            setNumColumns(numColumns - 1);
        }
    }

    public void addRows(int nRows){
        Fraction[][] B = new Fraction[numRows+ nRows][numColumns];
        for (int i = 0;i<numRows;i++){
            for (int j = 0; j<numColumns; j++){
                B[i][j] = A[i][j];
            }
        }
        for (int i = numRows;i<numRows+ nRows;i++){
            for (int j = 0; j<numColumns; j++){
                B[i][j] = new Fraction();
            }
        }
        A = B;
        B = null;
        setNumRows(numRows + nRows);
    }

    public static Matrix Mult(Matrix m1, Matrix m2) throws MatrixOperationException {
        if (m1.getNumColumns() == m2.getNumRows() && m1.getNumRows() == m2.getNumColumns())
        {
            Matrix rez = new Matrix(m1.getNumRows(), m2.getNumColumns());
            for (int i = 0; i < m1.getNumRows(); i++)
            {
                for (int j = 0; j < m2.getNumColumns(); j++)
                {
                    for (int g = 0; g < m2.getNumRows(); g++)
                    {
                        rez.A[i][j] = Fraction.Add( rez.A[i][j], Fraction.Mult(m1.A[i][g],m2.A[g][j]));
                    }
                }
            }
            return rez;
        }
        else if (m1.getNumRows() ==1 && m1.getNumColumns() == 1)
        {
            return Mult(m1.A[0][0],m2);
        }
        else if (m2.getNumRows() == 1 && m2.getNumColumns() == 1)
        {
            return Mult( m1, m2.A[0][0]);
        }
        else
        {
            throw new MatrixOperationException("Количество столбцов матрицы1 должно быть равно количеству строк матрицы2!");
        }
    }

    public static Matrix Mult(Matrix m1, Fraction fr)    {
        Matrix rez = new Matrix(m1.getNumRows(), m1.getNumColumns());
        for (int i = 0; i < m1.getNumRows(); i++)
        {
            for (int j = 0; j < m1.getNumColumns(); j++)
            {
                rez.A[i][j] = Fraction.Mult( m1.A[i][j], fr);
            }
        }
        return rez;
    }

    public static Matrix Mult(Fraction fr, Matrix m1)    {
        Matrix rez = new Matrix(m1.getNumRows(), m1.getNumColumns());
        for (int i = 0; i < m1.getNumRows(); i++)
        {
            for (int j = 0; j < m1.getNumColumns(); j++)
            {
                rez.A[i][j] = Fraction.Mult( m1.A[i][j], fr);
            }
        }
        return rez;
    }

    public static Matrix Add(Matrix m1, Matrix m2)throws MatrixOperationException{
        if (m1.getNumColumns() != m2.getNumColumns() || m1.getNumRows() != m2.getNumRows())
        {
            throw new MatrixOperationException("Размерности матриц не совпадают");
        }
        Matrix rez = new Matrix(m2.getNumRows(), m2.getNumColumns());
        for (int i = 0; i < m2.getNumRows(); i++)
        {
            for (int j = 0; j < m2.getNumColumns(); j++)
            {
                rez.A[i][j] = Fraction.Add(m1.A[i][j],m2.A[i][j]);
            }
        }
        return rez;
    }
    public static Matrix Sub(Matrix m1, Matrix m2)throws MatrixOperationException{
        if (m1.getNumColumns () != m2.getNumColumns() || m1.getNumRows() != m2.getNumRows())
        {
            throw new MatrixOperationException("Размерности матриц не совпадают");
        }
        Matrix rez = new Matrix(m2.getNumRows(), m2.getNumColumns());
        for (int i = 0; i < m2.getNumRows(); i++)
        {
            for (int j = 0; j < m2.getNumColumns(); j++)
            {
                rez.A[i][j] = Fraction.Sub(m1.A[i][j],m2.A[i][j]);
            }
        }
        return rez;
    }
    public static Matrix Div(Matrix m1, Fraction fr)
    {
        Matrix rez = new Matrix(m1.getNumRows(), m1.getNumColumns());
        for (int i = 0; i < m1.getNumRows(); i++)
        {
            for (int j = 0; j < m1.getNumColumns(); j++)
            {
                rez.A[i][j] = Fraction.Div(m1.A[i][j], fr);
            }
        }

        return rez;
    }
    public void swapCols(int c1, int c2)
    {
        Fraction f;
        for (int i = 0; i < this.numRows; i++)
        {
            f = this.A[i][c1];
            this.A[i][c1] = this.A[i][c2];
            this.A[i][c2] = f;
        }
    }

    public void swapRows(int r1, int r2)
    {
        Fraction f;
        for (int j = 0; j < this.numColumns; j++)
        {
            f = this.A[r1][j];
            this.A[r1][j] = this.A[r2][j];
            this.A[r2][j] = f;
        }
    }

    public void multRows(int r1, int r2, Fraction fr)
    {
        for (int j = 0; j < this.numColumns; j++)
        {
            this.A[r2][j] = Fraction.Sub(this.A[r2][j], Fraction.Mult(this.A[r1][j], fr));
        }
    }

    public void multCols(int c1, int c2, Fraction fr)
    {
        for (int i = 0; i < this.numColumns; i++)
        {

            this.A[i][c2] = Fraction.Sub(this.A[i][c2], Fraction.Mult(this.A[i][c1], fr));
        }
    }

    public double Norm3()
    {
        Fraction fr = new Fraction();
        for (int i = 0; i < this.numRows; i++)
        {
            for (int j = 0; j < this.numColumns; j++)
            {
                fr = Fraction.Add(fr, this.A[i][j].Power(2));
            }
        }
        return Math.sqrt(fr.ToDouble());
    }

    public void Transpose(){
        Matrix m = new Matrix(this.numColumns, this.numRows);
        for (int i = 0; i < this.numRows; i++)
        {
            for (int j = 0; j < this.numColumns; j++)
            {
                m.A[j][i] = this.A[i][j];
            }
        }
        this.A = m.A;
        this.numColumns = m.getNumColumns();
        this.numRows = m.getNumRows();
        m = null;
    }

    public Matrix Copy(){
        Matrix m = new Matrix(this.numRows, this.numRows);
        for (int i = 0;i<this.numRows;i++){
            for (int j = 0;j<this.numRows;j++){
                m.A[i][j] = this.A[i][j].Copy();
            }
        }
        return m;
    }

    public Fraction determinant() throws MatrixOperationException
    {
        if (this.numRows == this.numColumns) {
            Matrix m = this.Copy();
            int n = this.numColumns;
            Fraction fr1, fr2;
            Fraction d = new Fraction(1, 1);
            double sumRow = 0, sumCol = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (Fraction.Equal(m.A[i][j],0))
                    {
                        sumRow = 0;
                        sumCol = 0;
                        for (int p = 0; p < n; p++) {
                            sumCol += Math.abs(m.A[i][p].ToDouble());
                            sumRow += Math.abs(m.A[p][j].ToDouble());
                        }
                        if (sumCol == 0 || sumRow == 0) {
                            return new Fraction();
                        }
                    }
                }
            }
            for (int shag = 0; shag < n - 1; shag++) {
                for (int i = shag; i < n; i++) {
                    if (!Fraction.Equal(m.A[i][shag],0))
                    {
                        if (Fraction.Equal(m.A[shag][shag], 0))
                        {
                            this.swapRows(shag, i);
                        }
                        else
                        {
                            for (int j = 0; j < n; j++) {
                                if (i != j && !Fraction.Equal(m.A[j][shag],0) && j >= shag)
                                {
                                    if (j < i) {
                                        fr1 = m.A[i][shag];
                                        fr2 = m.A[j][shag];
                                        m.multRows(j, i, Fraction.Div(fr1, fr2));
                                    } else {
                                        fr1 = m.A[j][shag];
                                        fr2 = m.A[i][shag];
                                        m.multRows(i, j, Fraction.Div(fr1, fr2));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                d = Fraction.Mult(d, m.A[i][i]);
            }
            return d;
        }else {
            throw new MatrixOperationException("Матрица должна быть квадратной!");
        }
    }

    public void setEMatr() throws MatrixOperationException
    {
        if (this.numColumns == this.numRows) {
            for (int i = 0; i < this.numColumns; i++) {
                for (int j = 0; j < this.numColumns; j++) {
                    if (i == j) {
                        this.A[i][j].Read("1");
                    } else {
                        this.A[i][j].Read("0");
                    }
                }
            }
        }else{
            throw new MatrixOperationException("Матрица не квадратная! Операция невозможна!");
        }
    }

    public Matrix TransformMatr(int type) throws MatrixOperationException //вычисление обратной[1], верхней треугольной[2], нижней треугольной матрицы[3]
    {
        Fraction dt = this.determinant();
        if (Fraction.Equal(dt,0))
        {
            throw new MatrixOperationException("Определитель матрицы равен нулю, обратная матрица не существует!");
        }
        else
        {
            Fraction m = new Fraction();
            int n = this.numColumns;
            Matrix e = new Matrix(n, n);
            Matrix x= new Matrix(n, n);
            Matrix y = new Matrix(n, n);
            Matrix b = new Matrix(n, n);
            Matrix c = new Matrix(n, n);
            e.setEMatr();
            for (int j = 0; j < n; j++)
            {
                for (int i = j; i < n; i++)
                {
                    for (int g = 0; g <= j - 1; g++)
                    {
                        m = Fraction.Add(m,Fraction.Mult(b.A[i][g], c.A[g][j]));
                    }
                    b.A[i][j] = Fraction.Sub(this.A[i][j], m);
                    m.Read("0");
                    for (int g = 0; g <= j - 1; g++)
                    {
                        m = Fraction.Add(m, Fraction.Mult(b.A[j][g], c.A[g][i]));
                    }
                    c.A[j][i] = Fraction.Div(Fraction.Sub(this.A[j][i], m), b.A[j][j]);
                    m.Read("0");
                }
            }
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    for (int g = 0; g <= j - 1; g++)
                    {
                        m = Fraction.Add(m,Fraction.Mult(b.A[j][g], y.A[g][i]));
                    }
                    y.A[j][i] = Fraction.Div(Fraction.Sub(e.A[j][i], m), b.A[j][j]);
                    m.Read("0");
                }
            }
            for (int i = 0; i < n; i++)
            {
                for (int j = n - 1; j >= 0; j--)
                {
                    for (int g = 0; g < n - j - 1; g++)
                    {
                        m = Fraction.Add(m,Fraction.Mult(c.A[j][n - g - 1], x.A[n - g - 1][i]));
                    }
                    x.A[j][i] = Fraction.Sub(y.A[j][i], m);
                    m.Read("0");
                }
            }
            switch (type)
            {
                case 1:
                    return x;
                case 2:
                    return b;
                default:
                    return c;
            }
        }
    }

    public Matrix getFrobeniusMatr () throws MatrixOperationException
    {
        if (this.numRows == this.numColumns) {
            int n = this.numColumns;
            Matrix mFr = new Matrix(n, n);
            Matrix e = new Matrix(n, n);
            Matrix m1 = new Matrix(n, n);
            mFr = this.Copy();
            for (int g = 0; g < n - 1; g++) {
                Fraction d = new Fraction();
                for (int i = 0; i <= n - g - 2; i++) {
                    d = Fraction.Add(d,mFr.A[n - g - 1][i]);
                }
                if (Fraction.Equal(d, 0)) {
                    Matrix b = new Matrix(n - g - 1, n - g - 1);
                    for (int i = 0; i < n - g - 1; i++) {
                        for (int j = 0; j < n - g - 1; j++) {
                            b.A[i][j] =mFr.A[i][j];
                        }
                    }
                    b = b.getFrobeniusMatr();
                    for (int i = 0; i < n - g - 1; i++) {
                        for (int j = 0; j < n - g - 1; j++) {
                            mFr.A[i][j] = b.A[i][j];
                            //mFr.A[i][j].Color = Color.FromRgb(127, 251, 189);
                        }
                    }
                    for (int i = n - g - 1; i < n; i++) {
                        for (int j = n - g - 1; j < n; j++) {
                            //mFr.A[i, j].Color = Color.FromRgb(127, 251, 189);
                        }
                    }
                    return mFr;
                } else if (Fraction.Equal(mFr.A[n - g - 1][n - g - 2],0))
                {
                    mFr.swapCols(n - g - 1, n - g - 2);
                    mFr.swapRows(n - g - 1, n - g - 2);
                }
                e.setEMatr();
                for (int i = 0; i < n; i++) {
                    e.A[n - g - 2][i].Read(mFr.A[n - g - 1][i].Write());
                }
                if (!Fraction.Equal(e.determinant(), 0)) {
                    mFr = Matrix.Mult(Matrix.Mult(e, mFr), e.TransformMatr(1));
                } else {
                    mFr = Matrix.Mult(Matrix.Mult(e, mFr), e.TransformMatr(1));
                }
            }
            return mFr;
        }else{
            throw new MatrixOperationException("Матрица не квадратная! Операция невозможна!");
        }
    }
}
