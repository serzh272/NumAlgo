package com.example.sergey.numalgo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mymath.Matrix;
import com.example.mymath.MatrixOperationException;

import java.util.List;

public class MatrixPagerActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private MatrixFragmentPagerAdapter mAdapter;
    private Button btnMult;
    private Button btnDiv;
    private Button btnAdd;
    private Button btnSub;

    private FragmentManager fm;
    private List<Matrix> mMatrices;

    @Override
    public void onClick(View v) {
        for (MatrixFragment m:mAdapter.getMatrixFragments()) {
            m.writeMatrixLab();
            m.updateFragment();
        }
        switch (v.getId()){
            case R.id.btn_mult:
                try {
                    MatrixLab.get(MatrixPagerActivity.this).setMatrixItem(2,Matrix.Mult(mMatrices.get(0), mMatrices.get(1)));
                    mViewPager.setCurrentItem(2);
                }catch (MatrixOperationException e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_add:
                try {
                    MatrixLab.get(MatrixPagerActivity.this).setMatrixItem(2,Matrix.Add(mMatrices.get(0), mMatrices.get(1)));
                    mViewPager.setCurrentItem(2);
                }catch (MatrixOperationException e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_sub:

                break;
            default:

                break;
        }
        if (v instanceof Button){
            mAdapter.getMatrixFragments().get(2).updateFragment();
        }
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_pager);
        mMatrices = MatrixLab.get(this).getMatrices();
        mViewPager = (ViewPager)findViewById(R.id.fragment_matrix_view_pager);
        mViewPager.setOffscreenPageLimit(2);
        fm = getSupportFragmentManager();
        mAdapter = new MatrixFragmentPagerAdapter(fm);
        mViewPager.setAdapter(mAdapter);
        btnMult = (Button)findViewById(R.id.btn_mult);
        btnMult.setOnClickListener(this);
        btnAdd = (Button)findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        btnDiv = (Button)findViewById(R.id.btn_div);
        btnDiv.setOnClickListener(this);
        btnSub = (Button)findViewById(R.id.btn_sub);
        btnSub.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_matrix_pager,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        for (MatrixFragment m:mAdapter.getMatrixFragments()) {
            m.writeMatrixLab();
            m.updateFragment();
        }
        switch (item.getItemId()){
            case R.id.menu_item_transpose:
                mMatrices.get(mViewPager.getCurrentItem()).Transpose();
                mAdapter.getMatrixFragments().get(mViewPager.getCurrentItem()).updateFragment();
                MatrixLab.get(MatrixPagerActivity.this).setMatrixItem(mViewPager.getCurrentItem(),mMatrices.get(mViewPager.getCurrentItem()));
                break;
            case R.id.menu_item_determinant:
                try {
                    Toast.makeText(getApplicationContext(), "determinant = " + MatrixLab.get(MatrixPagerActivity.this).getMatrixItem(mViewPager.getCurrentItem()).determinant().Write(), Toast.LENGTH_SHORT).show();
                }catch (MatrixOperationException e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_item_invert:
                try {
                    MatrixLab.get(MatrixPagerActivity.this).setMatrixItem(2,mMatrices.get(mViewPager.getCurrentItem()).TransformMatr(1));
                    mViewPager.setCurrentItem(2);
                }catch (MatrixOperationException e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_item_frobenius:
                try {
                    MatrixLab.get(MatrixPagerActivity.this).setMatrixItem(2,mMatrices.get(mViewPager.getCurrentItem()).getFrobeniusMatr());
                    mViewPager.setCurrentItem(2);
                }catch (MatrixOperationException e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_item_top_tri:
                try {
                    MatrixLab.get(MatrixPagerActivity.this).setMatrixItem(2,mMatrices.get(mViewPager.getCurrentItem()).TransformMatr(3));
                    mViewPager.setCurrentItem(2);
                }catch (MatrixOperationException e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_item_bot_tri:
                try {
                    MatrixLab.get(MatrixPagerActivity.this).setMatrixItem(2,mMatrices.get(mViewPager.getCurrentItem()).TransformMatr(2));
                    mViewPager.setCurrentItem(2);
                }catch (MatrixOperationException e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MatrixPagerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_item_gersh:

                break;
            case R.id.menu_item_clear:
                MatrixLab.get(MatrixPagerActivity.this).setMatrixItem(mViewPager.getCurrentItem(),new Matrix(mMatrices.get(mViewPager.getCurrentItem()).getNumRows(),mMatrices.get(mViewPager.getCurrentItem()).getNumColumns()));
                break;
            case R.id.menu_item_resetall:
                //code
                break;
            default:
                break;
        }
        mAdapter.getMatrixFragments().get(mViewPager.getCurrentItem()).updateFragment();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        for (MatrixFragment m:mAdapter.getMatrixFragments()) {
            m.writeMatrixLab();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        this.finish();
        MatrixLab.destroy();
        super.onBackPressed();
    }
}
