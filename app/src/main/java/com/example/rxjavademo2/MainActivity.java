package com.example.rxjavademo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Observable<Integer> observable;
    Observer<Integer> observer;
    List<Integer> numbers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        intiThings();


    }

    public void intiThings() {
        numbers=new ArrayList<>();
        numbers.add(11);
        numbers.add(12);
        numbers.add(13);
        numbers.add(14);
        numbers.add(15);
        numbers.add(16);
        observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (Integer number : numbers) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(number);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });






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


    }


}
