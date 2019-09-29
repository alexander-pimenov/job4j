package ru.job4j.departments;

import java.util.*;

public class DepartmensSorter2 {

    public TreeSet<String> addDeptRightSort(Set<String> orgs) {
        String[] parsDepts;
        TreeSet<String> newOrgs = new TreeSet<>();
        for (String nameOrg : orgs) {
            String name = "";
            parsDepts = nameOrg.split("/");
            for (String valueName : parsDepts) {
                if (name.length() > 1) {
                    name = name + "/" + valueName;
                } else {
                    name = name + valueName;
                }
                newOrgs.add(name);
            }
        }
        return newOrgs;
    }

    public Set<String> sortBack(Set<String> orgs) {
        Set<String> departments = addDeptRightSort(orgs);
        List<String> deptList = new ArrayList<>(departments);
        deptList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result;
                if (o1.length() == o2.length()) {
                    result = o2.compareTo(o1);
                } else {
                    int min = Math.min(o1.length(), o2.length());
                    String first = o1.substring(0, min);
                    String second = o2.substring(0, min);
                    if (first.compareTo(second) == 0
                            && o1.length() > o2.length()) {
                        result = 1;
                    } else if (first.compareTo(second) == 0
                            && o1.length() < o2.length()) {
                        result = -1;
                    } else {
                        result = o2.compareTo(o1);
                    }
                }
                return result;
            }
        });

        return new LinkedHashSet<>(deptList);
    }


}
