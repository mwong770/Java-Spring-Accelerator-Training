package com.company;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetIterator {

    public void printSet(int a, int b, int c, int d, int e) {

        Set<Integer> nums = new HashSet<>();

        nums.add(a);
        nums.add(b);
        nums.add(c);
        nums.add(d);
        nums.add(e);

        Iterator<Integer> iter = nums.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

}
