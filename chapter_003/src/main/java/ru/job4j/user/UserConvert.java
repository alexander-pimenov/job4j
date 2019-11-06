package ru.job4j.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
//        HashMap<Integer, User> hashMap = new HashMap<>();
//        for (User user : list) {
//            hashMap.put(user.getId(), user);
//        }
//        return hashMap;

        return (HashMap<Integer, User>) list.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
    }
}


