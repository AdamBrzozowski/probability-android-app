package com.fobile;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class CellularNetworksActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, View.OnTouchListener {
    private EditText etPossibleCluster;
    private EditText etFrequencies;
    private EditText etCellGeometry;
    private EditText etPropagationLaw;
    private EditText etActualCluster;
    private EditText etActualCellArea;
    private EditText etActualCellRadius;
    private EditText etActualCCI;
    private TextView tvSelectSomething;
    private TextView tvAntennaType;
    private TextView tvPossibleClusters;
    private TextView tvNumberFrequencies;
    private TextView tvCellGeometry;
    private TextView tvDistanceReuse;
    private TextView tvActualCluster;
    private TextView tvActualCellArea;
    private TextView tvActualCellRadius;
    private TextView tvActualCCI;
    private Button btnTestCluster;
    private Button btnTestNumberOfFrequencies;
    private Button btnCellArea;
    private Button btnCellRadius;
    private Button btnReuseDistance;
    private Button btnCCI;
    private RadioGroup rgAntennas;
    private RadioButton rbTris;
    private RadioButton rbOmni;
    private LinearLayout linear0,linear1,linear2,linear3,linear4,linear5,linear6,linear7,linear8,linear9,linear10;
    private int nFrequencies, possibleCluster, clusterValue;
    private float geometryValue, ethaValue, antennaValue;
    private double areaValue, radiusValue,reuseDistanceValue, cciValue;
    private boolean omni, triset, clusterOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellular_networks);

        Locale.setDefault(Locale.ENGLISH);

        etPossibleCluster = findViewById(R.id.etPossibleCluster);
        etFrequencies = findViewById(R.id.etFrequencies);
        etCellGeometry = findViewById(R.id.etCellGeometry);
        etPropagationLaw = findViewById(R.id.etPropagationLaw);
        etActualCluster = findViewById(R.id.etActualCluster);
        etActualCellArea = findViewById(R.id.etActualCellArea);
        etActualCellRadius = findViewById(R.id.etActualCellRadius);
        etActualCCI = findViewById(R.id.etActualCCI);
        tvSelectSomething = findViewById(R.id.tvSelectSomething);
        tvPossibleClusters = findViewById(R.id.tvPossibleClusters);
        tvNumberFrequencies = findViewById(R.id.tvNumberFrequencies);
        tvCellGeometry = findViewById(R.id.tvCellGeometry);
        tvDistanceReuse = findViewById(R.id.tvDistanceReuse);
        tvActualCluster = findViewById(R.id.tvActualCluster);
        tvActualCellArea = findViewById(R.id.tvActualCellArea);
        tvActualCellRadius = findViewById(R.id.tvActualCellRadius);
        tvActualCCI = findViewById(R.id.tvActualCCI);
        btnTestCluster = findViewById(R.id.btnTestCluster);
        btnTestNumberOfFrequencies = findViewById(R.id.btnTestNumberOfFrequencies);
        btnCellArea = findViewById(R.id.btnCellArea);
        btnCellRadius = findViewById(R.id.btnCellRadius);
        btnReuseDistance = findViewById(R.id.btnReuseDistance);
        btnCCI = findViewById(R.id.btnCCI);
        rbOmni = findViewById(R.id.rbOmni);
        rbTris = findViewById(R.id.rbTris);
        linear0 = findViewById(R.id.linear0);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear5 = findViewById(R.id.linear5);
        linear6 = findViewById(R.id.linear6);
        linear7 = findViewById(R.id.linear7);
        linear8 = findViewById(R.id.linear8);
        linear9 = findViewById(R.id.linear9);
        linear10 = findViewById(R.id.linear10);

        btnTestCluster.setOnClickListener(this);
        btnTestNumberOfFrequencies.setOnClickListener(this);
        btnCellArea.setOnClickListener(this);
        btnCellRadius.setOnClickListener(this);
        btnReuseDistance.setOnClickListener(this);
        btnCCI.setOnClickListener(this);
        linear0.setOnTouchListener(this);
        linear1.setOnTouchListener(this);
        linear2.setOnTouchListener(this);
        linear3.setOnTouchListener(this);
        linear4.setOnTouchListener(this);
        linear5.setOnTouchListener(this);
        linear6.setOnTouchListener(this);
        linear7.setOnTouchListener(this);
        linear8.setOnTouchListener(this);
        linear9.setOnTouchListener(this);
        linear10.setOnTouchListener(this);



        etPossibleCluster.setOnFocusChangeListener(this);
        etFrequencies.setOnFocusChangeListener(this);
        etCellGeometry.setOnFocusChangeListener(this);
        etPropagationLaw.setOnFocusChangeListener(this);
        etActualCluster.setOnFocusChangeListener(this);
        etActualCellArea.setOnFocusChangeListener(this);
        etActualCellRadius.setOnFocusChangeListener(this);
        etActualCCI.setOnFocusChangeListener(this);

        rbTris.setOnClickListener(this);
        rbOmni.setOnClickListener(this);

        omni = false;
        triset = false;
        clusterOK= false;
        antennaValue = 0;
        possibleCluster=1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbOmni:
                omni = true;
                triset = false;
                break;
            case R.id.rbTris:
                triset = true;
                omni = false;
                break;
            case R.id.btnTestCluster:
                if (etPossibleCluster.getText().toString().equals("")) {
                    tvSelectSomething.setText(R.string.msg_missing_data);
                } else {
                    try {
                        possibleCluster = Integer.parseInt(etPossibleCluster.getText().toString());
                        if (possibleCluster <= 0 || possibleCluster > 50) {
                            tvSelectSomething.setText(R.string.msg_out_of_bounds);
                        } else {
                            clusterOK = testCluster(possibleCluster);
                            if (clusterOK == true) {
                                etActualCluster.setText(String.valueOf(possibleCluster));
                                tvSelectSomething.setText(R.string.msg_cluster_positive_feed_a);
                                clusterValue = possibleCluster;
                            } else {
                                tvSelectSomething.setText(R.string.msg_cluster_negative_feed);
                            }
                        }
                    }catch (NumberFormatException e) {
                        tvSelectSomething.setText(R.string.msg_error_a);
                    }
                }
                break;
            case R.id.btnTestNumberOfFrequencies:
                if (etFrequencies.getText().toString().equals("")) {
                    tvSelectSomething.setText(R.string.msg_missing_data);
                } else {
                    try {
                        nFrequencies = Integer.parseInt(etFrequencies.getText().toString());
                        if (nFrequencies <= 0 || nFrequencies > 1000) {
                            tvSelectSomething.setText(R.string.msg_out_of_bounds);
                        }
                        if (etActualCluster.getText().toString().equals("")) {
                           tvSelectSomething.setText(R.string.msg_error_cluster_a);
                       }else if (omni == false && triset == false){
                            tvSelectSomething.setText(R.string.msg_error_antenna_a);
                        }else {
                            clusterValue = Integer.parseInt(etActualCluster.getText().toString());
                            antennaValue = calculateAntenna(nFrequencies, clusterValue);
                            if (antennaValue == 0) {
                                tvSelectSomething.setText(R.string.msg_negative_feed_antenna);
                            } else {
                                if (triset == true) {
                                    String ant = String.format(Locale.getDefault(), "%.0f", antennaValue);
                                    tvSelectSomething.setText(getString(R.string.msg_value_antenna_threesect_case)+ant);
                                } else {
                                    String ant = String.format(Locale.getDefault(), "%.0f", antennaValue);
                                    tvSelectSomething.setText(getString(R.string.msg_value_antenna_omni_case)+ ant);
                                }
                            }
                        }
                    }catch (NumberFormatException e) {
                        tvSelectSomething.setText(R.string.msg_error_a);
                    }
                }
                break;
            case R.id.btnCellArea:
                if (etCellGeometry.getText().toString().equals("")) {
                    tvSelectSomething.setText(R.string.msg_missing_data);
                } else {
                    try {
                        geometryValue = Float.parseFloat(etCellGeometry.getText().toString());
                        if (geometryValue <= 0 || geometryValue > 500) {
                            tvSelectSomething.setText(R.string.msg_out_of_bounds);
                        } else {
                            areaValue = calculateArea(geometryValue);
                            String av = String.format(Locale.getDefault(), "%.3f", areaValue);
                            etActualCellArea.setText(av);
                            tvSelectSomething.setText(getString(R.string.msg_area_value)+ av+ getString(R.string.squareKm));
                        }
                    }catch (NumberFormatException e) {
                        tvSelectSomething.setText(R.string.msg_error_a);
                    }
                }
                break;
            case R.id.btnCellRadius:
                if (etCellGeometry.getText().toString().equals("")) {
                    tvSelectSomething.setText(R.string.msg_missing_data);
                } else {
                    try {
                        geometryValue = Float.parseFloat(etCellGeometry.getText().toString());
                        if (geometryValue <= 0 || geometryValue > 1000) {
                            tvSelectSomething.setText(R.string.msg_out_of_bounds);
                        } else {
                            radiusValue = calculateRadius(geometryValue);
                            String rv = String.format(Locale.getDefault(), "%.3f", radiusValue);
                            etActualCellRadius.setText(rv);
                            tvSelectSomething.setText(getString(R.string.msg_radius_value)+rv + getString(R.string.km));
                        }
                    }catch (NumberFormatException e) {
                        tvSelectSomething.setText(R.string.msg_error_a);
                    }
                }
                break;
            case R.id.btnReuseDistance:
                if (etActualCellRadius.getText().toString().equals("")) {
                    tvSelectSomething.setText(R.string.msg_error_radius_a);// controlla se è presente il parametro Radius
                }else if (etActualCluster.getText().toString().equals("")) {
                    tvSelectSomething.setText(R.string.msg_error_cluster_a);// controlla se è presente il parametro K
                } else {
                    try {
                        clusterValue = Integer.parseInt(etActualCluster.getText().toString());
                        radiusValue = Float.parseFloat(etActualCellRadius.getText().toString());
                        if (clusterValue <= 0 || clusterValue > 50) {
                            tvSelectSomething.setText(R.string.msg_cluster_out_of_bound_b);}
                        else if (radiusValue <= 0 || radiusValue > 1000){
                            tvSelectSomething.setText(R.string.mag_radius_out_of_bound_a);}
                        else{
                        reuseDistanceValue = calculateReuseDistance(radiusValue,clusterValue);
                        String rdv = String.format(Locale.getDefault(), "%.3f", reuseDistanceValue);
                        tvSelectSomething.setText(getString(R.string.msg_reuse_distance_value)+rdv);}
                    }catch (NumberFormatException e) {
                        tvSelectSomething.setText(R.string.msg_error_a);
                    }
                }
                break;
            case R.id.btnCCI:
                if (etPropagationLaw.getText().toString().equals("")) {
                    tvSelectSomething.setText(R.string.msg_missing_data);
                } else if (etActualCluster.getText().toString().equals("")) {
                    tvSelectSomething.setText(R.string.msg_error_cluster_a);// controlla se è presente il parametro K
                } else if (omni == false && triset == false) {
                    tvSelectSomething.setText(R.string.msg_error_antenna_a);// controlla se è presente il parametro K
                } else {
                    try {
                        ethaValue = Float.parseFloat(etPropagationLaw.getText().toString());
                        clusterValue = Integer.parseInt(etActualCluster.getText().toString());
                        if (ethaValue <= 0 || ethaValue > 10) {
                            tvSelectSomething.setText(R.string.msg_out_of_bounds);}
                        else if (clusterValue <= 0 || clusterValue > 50) {
                                tvSelectSomething.setText(R.string.msg_cluster_out_of_bound_b);}
                        else{
                            if (omni == true) {
                            int xValue = 1;
                            cciValue = calculateCCI(xValue, clusterValue, ethaValue);
                            String cciv = String.format(Locale.getDefault(), "%.3f", cciValue);
                            etActualCCI.setText(cciv);
                            System.out.println(cciv);
                            tvSelectSomething.setText(getString(R.string.msg_correct_value_cct_omni )+ cciv + getString(R.string.msg_db));}
                            else {
                                int xValue = 3;
                                cciValue = calculateCCI(xValue, clusterValue, ethaValue);
                                String cciv = String.format(Locale.getDefault(), "%.3f", cciValue);
                                etActualCCI.setText(cciv);
                                tvSelectSomething.setText(getString(R.string.msg_cci_value_threesect) + cciv + getString(R.string.msg_db));}
                        }
                    }catch (NumberFormatException e) {
                        tvSelectSomething.setText(R.string.msg_error_a);
                    }
                }break;
        }
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.etPossibleCluster:
                if (v.hasFocus()) {
                    tvSelectSomething.setText(R.string.msg_cluster_a);
                    break;
                }
            case R.id.etFrequencies:
                if (v.hasFocus()) {
                    tvSelectSomething.setText(R.string.msg_frequencies_a);
                    break;
                }
            case R.id.etCellGeometry:
                if (v.hasFocus()) {
                    tvSelectSomething.setText(R.string.msg_cell_geometry_a);
                    break;
                }
            case R.id.etPropagationLaw:
                if (v.hasFocus()) {
                    tvSelectSomething.setText(R.string.msg_propagation_law);
                    break;
                }
            case R.id.etActualCluster:
                if (v.hasFocus()) {
                    tvSelectSomething.setText(R.string.msg_cluster_a);
                    break;
                }
            case R.id.etActualCellArea:
                if (v.hasFocus()) {
                    tvSelectSomething.setText(R.string.msg_cell_area_a);
                    break;
                }
            case R.id.etActualCellRadius:
                if (v.hasFocus()) {
                    tvSelectSomething.setText(R.string.msg_radius_a);
                    break;
                }
            case R.id.etActualCCI:
                if (v.hasFocus()) {
                    tvSelectSomething.setText(R.string.msg_cci_a);
                    break;
                }
        }
    }
    private boolean testCluster(double A) {
        float i, j;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                double formula = (Math.pow(i,2.0)+ Math.pow(j,2.0) + i*j);
                if (A == formula) {
                    return true;
                }
            }
        }
        return false;
    }
    private float calculateAntenna(int nf, int pc) {
        //nf = numero frequenze pc = possibile cluster
        if (nf % 3 != 0 || nf % pc != 0) {
            return 0;
        }else{
            if(omni == true){
                return (nf/pc);
            }else{
                return(nf/pc/3);
            }
        }
    }
    private double calculateRadius(float C) {
        // C == Area
        float val = (float) ((C * 2*Math.sqrt(3)*1/9));
        return (Math.sqrt(val));
    }
    private double calculateArea(double D) {
        // D == Radius
        float val = (float) (1.5 * Math.sqrt(3.0));
        double radiusSquare = Math.pow(D, 2.0);
        return (val * radiusSquare);
    }
    private double calculateReuseDistance(double E, double F) {
        // E == radiusValue - F == clusterValue
        double val = (3*F);
        return (E * Math.sqrt(val));
    }

    private double calculateCCI(float G, int H, float I) {
        //G == xValue, H==clusterValue, I==ethaValue
        float val1 = G/6;
        float val2 = 3*H;
        float val3 = I/2;
        double val4 = Math.pow(val2,val3);
        double val5 = (val1*val4);
        return 10*Math.log10(val5);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean onTouch(View v, MotionEvent event) {
        hideKeyboard(v);
        return false;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
