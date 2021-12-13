package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout);
    }

    @Override
    public void onStart() {
        super.onStart();

        StringBuilder listOutput = new StringBuilder();

        listOutput.append("OK List : ");
        List<IntHolder> list = createAndSortList(0, -5, -10, 16);
        listOutput.append(convertListToString(list));

        listOutput.append("\nBad List : ");
        list = createAndSortList(0, -2147483647, -2147483646, 2);
        listOutput.append(convertListToString(list));

        ((TextView) findViewById(R.id.lists)).setText(listOutput.toString());
    }

    /**
     * Create a List from the numbers provided, and then sort it. This
     * shows the same code is executed in both instances, and it's where
     * the sorting issue happens.
     */

    private List<IntHolder> createAndSortList(Integer... values) {
        Function<Integer, IntHolder> mapFunction = IntHolder::new;
        List<IntHolder> list = Arrays.stream(values).map(mapFunction).collect(Collectors.toList());
        list.sort(new IntHolderComparator());   // <-- This is where the issue occurs in the bad list
        return list;
    }

    /**
     * Convert a list to a string representation. This isn't part of the problem,
     * but allows us to easily see when is in the list and the order it is in.
     */

    private String convertListToString(List<IntHolder> list) {
        StringBuilder listString = new StringBuilder();
        list.forEach(value -> listString.append(value.value + ", "));
        listString.delete(listString.length() - 2, listString.length());
        return listString.toString();
    }

    class IntHolder {
        public int value;

        IntHolder(Integer value) {
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