package sh4k4w4t.github.io.roomdatabaselearn;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Insert
    void addMultipleUser(List<User> users);

    @Query("SELECT * FROM user_db")
    List<User> readAllusers();

    @Query("SELECT * FROM User_db WHERE uid LIKE :id")
    User findbyid(int id);

    @Update
    void update(User user);

    @Query("SELECT * FROM user_db WHERE verified LIKE 1")
    List<User> getVerifiedUsers();

    @Query("DELETE FROM user_db")
    void deleteAll();

    @Delete
    void delete(User user);
}
