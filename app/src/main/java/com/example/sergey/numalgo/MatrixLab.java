package com.example.sergey.numalgo;

import android.content.Context;
import android.util.Log;

import com.example.mymath.Matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixLab {
    private static final String TAG_MATRIXLAB = "matrix_lab_tag";
    private static MatrixLab sMatrixLab;
    private Context mContext;
    private List<Matrix> mMatrices;
    public static MatrixLab get(Context context){
        if (sMatrixLab == null){
            sMatrixLab = new MatrixLab(context);
        }
        return sMatrixLab;
    }

    private MatrixLab(Context context) {
        mContext = context.getApplicationContext();
        mMatrices = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            mMatrices.add(new Matrix(3,3));
        }
    }

    public Matrix getMatrixItem(int id){
        return mMatrices.get(id);
    }


    public void setMatrixItem(int id, Matrix matrix){
        Log.d(TAG_MATRIXLAB, "setMatrixItem id=" + id + " Calling");
        mMatrices.set(id, matrix);
    }

    public List<Matrix> getMatrices(){
        return mMatrices;
    }

    public static void destroy(){
        if (sMatrixLab != null){
            sMatrixLab = null;
        }
    }
}
