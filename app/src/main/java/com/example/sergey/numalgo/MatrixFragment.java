package com.example.sergey.numalgo;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MatrixFragment extends Fragment {
    public static final String ID_MATRIX_FRAGMENT = "id_matrix_fragment";
    private MatrixViewGroup mMatrixViewGroup;
    private int pageNumber;
    private MatrixLab mMatrixLab;

    public static MatrixFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ID_MATRIX_FRAGMENT,page);
        MatrixFragment matrixFragment = new MatrixFragment();
        matrixFragment.setArguments(args);
        return  matrixFragment;
    }

    public MatrixFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMatrixLab = MatrixLab.get(getActivity());
        pageNumber = getArguments()!=null ? getArguments().getInt(ID_MATRIX_FRAGMENT):1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MatrixViewGroup v =(MatrixViewGroup)inflater.inflate(R.layout.fragment_matrix,container,false);
        mMatrixViewGroup = v;
        mMatrixViewGroup.setId(pageNumber);
        switch (pageNumber){
            case 0:
                v.setCellColor(R.color.colorM1);
                break;
            case 1:
                v.setCellColor(R.color.colorM2);
                break;
            case 2:
                v.setCellColor(R.color.colorM3);
                break;
            default:
                v.setCellColor(R.color.colorPrimary);
        }
        updateFragment();
        return v;
    }

    public void updateFragment(){
        mMatrixViewGroup.SetMatrix(mMatrixLab.getMatrixItem(pageNumber));
    }

    public void writeMatrixLab(){
        mMatrixLab.setMatrixItem(pageNumber,mMatrixViewGroup.getMatr());
    }


}
