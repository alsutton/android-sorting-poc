package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout);
    }

    @Override
    public void onStart() {
        super.onStart();

        ArrayList<IntHolder> list = new ArrayList<>();
        list.add(new IntHolder(0));
        list.add(new IntHolder(-5));
        list.add(new IntHolder(-10));
        list.add(new IntHolder(16));
        list.sort(new IntHolderComparator());

        StringBuilder listOutput = new StringBuilder("OK List : ");
        list.forEach(value -> listOutput.append(value.value + ", "));
        listOutput.delete(list.size() - 2, list.size());

        list = new ArrayList<>();
        list.add(new IntHolder(0));
        list.add(new IntHolder(-2147483647));
        list.add(new IntHolder(-2147483646));
        list.add(new IntHolder(2));
        list.sort(new IntHolderComparator());

        listOutput.append("\nBad List : ");
        list.forEach(value -> listOutput.append(value.value + ", "));
        listOutput.delete(list.size() - 2, list.size());

        ((TextView) findViewById(R.id.lists)).setText(listOutput.toString());
    }

    class IntHolder {
        public int value;

        IntHolder(int value) {
            this.value = value;
        }
    }

    class IntHolderComparator implements Comparator<IntHolder> {

        @Override
        public int compare(IntHolder o1, IntHolder o2) {
            return o1.value - o2.value;
        }
    }
}