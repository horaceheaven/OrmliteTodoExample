package com.example.horaceheaven.todo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.horaceheaven.todo.R;
import com.example.horaceheaven.todo.dbhelper.TodoOpenDatabaseHelper;
import com.example.horaceheaven.todo.dbmodel.Todo;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity {


    /**
     * The testOutOrmLiteDatabase method is called each time a new instance of the
     * application is created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            testOutOrmLiteDatabase();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing out the TodoOrmLiteExample app by creating some Todo entries in the database,
     * and querying for all the Todo object from the todo table.
     * @throws SQLException
     */
    private void testOutOrmLiteDatabase() throws SQLException {
        TodoOpenDatabaseHelper todoOpenDatabaseHelper = OpenHelperManager.getHelper(this,
                TodoOpenDatabaseHelper.class);

        Dao<Todo, Long> todoDao = todoOpenDatabaseHelper.getDao();

        Date currDateTime = new Date(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currDateTime);
        calendar.add(Calendar.DATE, 14);

        Date dueDate = calendar.getTime();

        todoDao.create(new Todo("Todo Example 1", "Todo Example 1 Description", currDateTime, dueDate));
        todoDao.create(new Todo("Todo Example 2", "Todo Example 2 Description", currDateTime, dueDate));
        todoDao.create(new Todo("Todo Example 3", "Todo Example 3 Description", currDateTime, dueDate));

        List<Todo> todos = todoDao.queryForAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
