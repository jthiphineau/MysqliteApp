package com.doranco.mysqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    public void enregistrerUser(User user);
    @Query("select *from user where nom=:nom")
    public User findUserByName (String nom);
    @Delete
    public void deleteUser(User user);
    @Query("select *from user")
    public List<User> getAll();
}
