package sh4k4w4t.github.io.roomdatabaselearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    ExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertAuser(View view) {
        service= Executors.newSingleThreadExecutor();
        service.execute(() -> {
            User user= new User("Shakawat", "24",true);
            UserDatabase.getInstance(MainActivity.this).userDao().insert(user);
            Log.d(TAG, "run: Inserted user successfully!!");
        });
    }

    public void updateAuser(View view) {
        service= Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                User user= UserDatabase.getInstance(MainActivity.this).userDao().findbyid(1);
                user.name="Java Programmer Shakawat";
                user.age="3";
                UserDatabase.getInstance(MainActivity.this).userDao().update(user);
                Log.d(TAG, "run: Updated Successfully!!");
            }
        });
    }

    public void deleteAuser(View view) {
        service= Executors.newSingleThreadExecutor();
        service.execute(() -> {
            User user= UserDatabase.getInstance(MainActivity.this).userDao().findbyid(8);
            UserDatabase.getInstance(MainActivity.this).userDao().delete(user);
            Log.d(TAG, "run: Deleted Successfully!!");
        });
    }

    public void readAuser(View view) {
        service= Executors.newSingleThreadExecutor();
        service.execute(() -> {
            User user= UserDatabase.getInstance(MainActivity.this).userDao().findbyid(6);
            Log.d(TAG, "run: "+user.toString());
        });
    }

    public void readAlluser(View view) {
        service= Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                List<User> userList= UserDatabase.getInstance(MainActivity.this).userDao().readAllusers();
                Log.d(TAG, "run: "+userList.toString());
            }
        });
    }

    public void deleteAlluser(View view) {
        service= Executors.newSingleThreadExecutor();
        service.execute(() -> {
            UserDatabase.getInstance(MainActivity.this).userDao().deleteAll();
            Log.d(TAG, "run: Delete all users Successfully");
        });
    }

    public void readOnlyVerifieduser(View view) {
        service= Executors.newSingleThreadExecutor();
        service.execute(() -> {
            List<User> verifiedUsers = UserDatabase.getInstance(MainActivity.this).userDao().getVerifiedUsers();
            Log.d(TAG, "run: "+verifiedUsers.toString());
        });
    }

    public void addMultipleUsers(View view) {
        service= Executors.newSingleThreadExecutor();
        service.execute(() -> {
            List<User> list= new ArrayList<User>();
            list.add(new User("shakawat","15",true));
            list.add(new User("shaheen","30",false));
            list.add(new User("hossain","25",false));
            list.add(new User("Mdshs","43",false));
            list.add(new User("mdshaheen","100",true));
            UserDatabase.getInstance(MainActivity.this).userDao().addMultipleUser(list);

            Log.d(TAG, "addMultipleUsers: Multiple Users added");
        });
    }
}