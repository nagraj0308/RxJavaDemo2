package com.example.rxjavademo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Observable<String> observable;
    Observer<String> observer;
    Subscriber<String> subscriber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        intiThings();


    }

    public void intiThings() {


        observable= Observable.create(new ObservableOnSubscribe<String>() {
                                          @Override
                                          public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                              emitter.onNext("Hello");
                                              emitter.onComplete();

                                             int i=0;
                                              while(i<5)
                                              {
                                                  subscriber.onNext("Hello World "+ i);
                                                  observer.onNext("hi"+i);
                                                  observer.onComplete();
                                                  i++;
                                              }

                                          }
                                      }
        );


        subscriber= new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {
                textView.setText(textView.getText()+"\n"+s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                textView.setText(textView.getText()+"\n"+"onComplete");

            }
        };
        observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
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
