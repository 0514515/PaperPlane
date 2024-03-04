package com.example.demo3.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
//메모리에 임시저장
public class UserRepository {

    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    public User save(User user){
        user.setId(++sequence);
        log.info("save : user = {}", user);
        store.put(user.getId(),user);
        return user;
    }

    public User findById(Long id){
        return store.get(id);
    }

    public Optional<User> findByLoginId(String loginId){
        return findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }
    public Optional<User> findByName(String name){
        return findAll().stream().filter(m -> m.getName().equals(name)).findFirst();
    }

    public List<User> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
