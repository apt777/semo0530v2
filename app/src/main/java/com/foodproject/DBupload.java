package com.foodproject;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DBupload
{
    private DatabaseReference databaseReference;
    public DBupload()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://semofarm-8146d-default-rtdb.firebaseio.com/");
        //데이터베이스 URL 연결
        databaseReference = database.getReference(DBcontent.class.getSimpleName());
        Log.d("marrine",database.getReference().toString());
    }
    public Task<Void> add(DBcontent emp)
    {
        Log.d("marrine", emp.getName() + emp.getContent());
        return databaseReference.push().setValue(emp);
    }

    public Task<Void> update(String key, HashMap<String ,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key)
    {
        if(key == null)
        {
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

    public Query get()
    {
        return databaseReference;
    }
}