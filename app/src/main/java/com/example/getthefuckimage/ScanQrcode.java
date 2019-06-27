package com.example.getthefuckimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQrcode extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView ScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);

    }

    @Override
    public void handleResult(Result result) {
        MainActivity.QrcodeResult = result.getText();
        //startActivity(new Intent(getApplicationContext(),showInfo.class));
        //finish();
    }

    @Override
    protected void onPause() {

        super.onPause();
        ScannerView.stopCamera();
    }
    @Override
    protected void onResume(){
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}
