package com.example.rxjavademo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Observable<Integer> observable,observable2;
    Observer<Integer> observer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        intiThings();


    }

    public void intiThings() {

        observable = Observable.just(1,2,3)
                .map(i -> i*i)
                .map(i -> i*i)
                .filter(i -> i>10);
        observable2=Observable.range(2,100);






        observer=new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                textView.setText(textView.getText()+"\n"+s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                textView.setText(textView.getText()+"\n"+"onComplete");
            }
        };


       observable.subscribe(observer);
       observable2.subscribe(observer);



    }


}
